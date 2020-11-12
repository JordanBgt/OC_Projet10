package com.openclassrooms.libraryclient.controller;

import com.openclassrooms.libraryclient.model.*;
import com.openclassrooms.libraryclient.proxy.DocumentProxy;
import com.openclassrooms.libraryclient.proxy.ExemplarProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * Controller to display documents page
 *
 * @see DocumentProxy
 * @see ExemplarProxy
 */
@Controller
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentProxy documentProxy;

    @Autowired
    private ExemplarProxy exemplarProxy;

    private DocumentForm documentForm = new DocumentForm();

    /**
     * It displays document.jsp with a page of documents
     *
     * @param model model which supply attributes used for rendering views
     * @param page the requested page
     * @param size number of documents per page
     * @param sortBy sort criteria
     * @param direction direction criteria
     * @param unpaged boolean if we want all documents in a single page
     *
     * @return name of the requested jsp
     * @see DocumentProxy#getAllDocuments(String, String, String, String, String, String, Integer, Integer, String, Sort.Direction, boolean)
     */
    @GetMapping
    public String getAllDocuments(Model model,
                                  @RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer size,
                                  @RequestParam(required = false) String sortBy,
                                  @RequestParam(required = false) Sort.Direction direction,
                                  @RequestParam(required = false) boolean unpaged) {

        RestPageImpl<DocumentLight> pageDocuments;
        pageDocuments = documentProxy.getAllDocuments(documentForm.getTitle(), documentForm.getIsbn(),
                documentForm.getAuthorName(), documentForm.getPublisherName(), documentForm.getType(),
                documentForm.getCategory(), page, size, sortBy, direction, unpaged);
        model.addAttribute("pageDocuments", pageDocuments);
        model.addAttribute("documentForm", documentForm);
        return "document";
    }

    /**
     * It displays details of a document
     *
     * @param model model which supply attributes used for rendering views
     * @param id id of the requested document
     *
     * @return name of the requested jsp
     * @see DocumentProxy#getDocument(Long)
     * @see ExemplarProxy#getAllAvailableExemplarsByDocumentId(Long)
     */
    @GetMapping("/{id}")
    public String getDocument(Model model, @PathVariable Long id) {
        Document document = documentProxy.getDocument(id);
        List<ExemplarAvailable> exemplars = exemplarProxy.getAllAvailableExemplarsByDocumentId(id);
        Optional<Integer> sumNmberExemplarsAvailable = exemplars.stream().map(ExemplarAvailable::getNumber).reduce(Integer::sum);
        model.addAttribute("document", document);
        model.addAttribute("exemplars", exemplars);
        model.addAttribute("sumExemplarsAvailable", sumNmberExemplarsAvailable);
        return "document-detail";
    }

    /**
     * Method called when a user want to filter results
     *
     * @param documentForm form which represents search criteria
     *
     * @return redirect to getAllDocuments method
     * @see DocumentForm
     */
    @PostMapping("/search")
    public ModelAndView searchDocuments(@ModelAttribute DocumentForm documentForm) {
        this.documentForm = documentForm;
        return new ModelAndView("redirect:/documents");
    }

    private boolean checkIfUserCanBeAddedToWaitingList(User user, Document document) {
        if (user == null) {
            return false;
        }
        else if (document.getWaitingList().isFull()) {
            return false;
        }
        // s'il a un emprunt en cours => false
        // s'il a une réservation en cours pour cet ouvrage => false

        return true;
    }

}
