package com.openclassrooms.library.fixture;

import com.openclassrooms.library.entity.ELoanState;
import com.openclassrooms.library.entity.Loan;

import java.time.LocalDate;

public class LoanFixture {

    public static Loan buildLoan() {
        return new Loan(1L, LocalDate.now(), LocalDate.now().plusDays(14), false, UserFixture.buildUser(),
                ExemplarFixture.buildExemplar(), ELoanState.PENDING);
    }
}
