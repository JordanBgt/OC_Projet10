package com.openclassrooms.library.service;

import com.openclassrooms.library.config.AppProperties;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.LoanRepository;
import com.openclassrooms.library.dao.UserRepository;
import com.openclassrooms.library.dto.LoanDto;
import com.openclassrooms.library.entity.ELoanState;
import com.openclassrooms.library.entity.Exemplar;
import com.openclassrooms.library.entity.Loan;
import com.openclassrooms.library.entity.User;
import com.openclassrooms.library.mapper.LoanMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage loans
 *
 * @see Loan
 * @see LoanDto
 * @see LoanRepository
 * @see LoanMapper
 * @see UserRepository
 * @see ExemplarRepository
 */
@Service
public class LoanService {

    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    private final ExemplarRepository exemplarRepository;

    private final UserRepository userRepository;

    private final long loanPeriod;

    public LoanService(AppProperties appProperties, LoanRepository loanRepository, LoanMapper loanMapper,
                       ExemplarRepository exemplarRepository, UserRepository userRepository) {
        this.loanPeriod = appProperties.getLoanPeriod();
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.exemplarRepository = exemplarRepository;
        this.userRepository = userRepository;
    }

    /**
     * Method to find all loans
     *
     * @return a list of loans
     * @see LoanRepository#findAll()
     */
    public List<LoanDto> findAll() {
        return loanMapper.toListLoanDto(loanRepository.findAll());
    }

    /**
     * Method to find all current loans for a user (only loans with PENDING state). Loans order by end date ASC
     *
     * @param userId id of the user for whom we are looking for loans
     *
     * @return a list of loans
     * @see LoanRepository#findAllByUserIdAndStateOrderByEndDateAsc(Long, ELoanState)
     */
    public List<LoanDto> findAllPendingByUserId(Long userId) {
        return loanRepository.findAllByUserIdAndStateOrderByEndDateAsc(userId, ELoanState.PENDING).stream().map(loanMapper::toLoanDto).collect(Collectors.toList());
    }

    /**
     * Method to get all ended loans (only loans with FINISHED state)
     *
     * @return a list of loans
     * @see LoanRepository#findAllEndedLoans()
     */
    public List<LoanDto> findAllEndedLoans() {
        return loanRepository.findAllEndedLoans().stream().map(loanMapper::toLoanDto).collect(Collectors.toList());
    }

    /**
     * Method to create or update a loan
     *
     * @param loanDto the loan to save
     *
     * @return the saved loan
     * @see LoanRepository#save(Object)
     */
    public LoanDto createOrUpdate(LoanDto loanDto) {
        Loan loan;
        if (loanDto.getId() != null) {
            loan = loanRepository.findById(loanDto.getId()).orElseThrow(EntityNotFoundException::new);
            loan.setStartDate(loanDto.getStartDate());
            loan.setEndDate(loanDto.getEndDate());
        } else {
            loan = new Loan();
            loan.setStartDate(LocalDate.now());
            loan.setEndDate(loan.getStartDate().plusWeeks(loanPeriod));
        }
        Exemplar exemplar = exemplarRepository.findById(loanDto.getExemplar().getId())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(loanDto.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        loan.setExemplar(exemplar);
        loan.setUser(user);
        loan.setRenewed(loanDto.isRenewed());
        loan.setState(ELoanState.PENDING);

        return loanMapper.toLoanDto(loanRepository.save(loan));
    }

    /**
     * Method to extend a loan
     * @param id id of the loan to extend
     *
     * @throws Exception if the loan has already been extended
     * @see LoanRepository#save(Object)
     */
    public void renew(Long id) throws Exception {
        LocalDate currentDate = LocalDate.now();
        Loan loan = loanRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (loan.isRenewed()) {
            throw new Exception("Le prêt a déjà été renouvelé une fois");
        } else if (currentDate.isAfter(loan.getEndDate())){
            throw new Exception("Le prêt ne peut être renouvelé après sa date de fin");
        }
        else {
            loan.setRenewed(true);
            loan.setEndDate(loan.getEndDate().plusWeeks(loanPeriod));
            loanRepository.save(loan);
        }
    }

    /**
     * Method end a loan
     *
     * @param id id of the loan to end
     * @see LoanRepository#save(Object)
     */
    public void returnExemplar(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        loan.setState(ELoanState.FINISHED);
        loanRepository.save(loan);
    }

    public LocalDate findDateNextLoanReturnByDocumentId(Long documentId) {
        Loan loan = loanRepository.findNextLoanReturnByDocumentId(documentId);
        if (loan != null) {
            return loan.getEndDate();
        }
        return null;
    }

    /**
     * Method to delete a loan by its id
     *
     * @param loanId id of the loan to delete
     */
    public void delete(Long loanId) {
        loanRepository.deleteById(loanId);
    }

}
