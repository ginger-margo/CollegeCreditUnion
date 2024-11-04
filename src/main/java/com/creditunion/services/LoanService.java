package com.creditunion.services;

import com.creditunion.dao.LoanDAO;
import com.creditunion.entities.Loan;
import java.util.List;

public class LoanService {

    private final LoanDAO loanDAO = new LoanDAO();

    public void createLoan(Loan loan) {
        loanDAO.persist(loan);
    }

    public Loan getLoanById(int id) {
        return loanDAO.getLoanById(id);
    }

    public List<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    public Loan updateLoan(Loan loan) {
        return loanDAO.merge(loan);
    }

    public boolean deleteLoan(int id) {
        Loan loan = loanDAO.getLoanById(id);
        if (loan != null) {
            loanDAO.removeLoan(loan);
            return true;
        }
        return false;
    }
}
