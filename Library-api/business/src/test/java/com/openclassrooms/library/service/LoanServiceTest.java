package com.openclassrooms.library.service;

import com.openclassrooms.library.config.AppProperties;
import com.openclassrooms.library.dao.LoanRepository;
import com.openclassrooms.library.entity.Loan;
import com.openclassrooms.library.fixture.LoanFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private AppProperties appProperties;

    @Test
    public void renewLoan_alreadyRenewed_ThrowException() {
        // GIVEN
        Loan loan = LoanFixture.buildLoan();
        loan.setRenewed(true);
        when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));

        // THEN
        Exception e = catchThrowableOfType(() -> loanService.renew(loan.getId()), Exception.class);
        assertThat(e.getMessage()).isEqualTo("Le prêt a déjà été renouvelé une fois");
    }

    @Test
    public void renewLoan_endedDateExpired_ThrowException() {
        // GIVEN
        Loan loan = LoanFixture.buildLoan();
        loan.setEndDate(LocalDate.now().minusWeeks(1));
        when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));

        // THEN
        Exception e = catchThrowableOfType(() -> loanService.renew(loan.getId()), Exception.class);
        assertThat(e.getMessage()).isEqualTo("Le prêt ne peut être renouvelé après sa date de fin");
    }

}
