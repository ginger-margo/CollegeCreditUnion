package com.creditunion.services;

import com.creditunion.dao.DepositDAO;
import com.creditunion.entities.Deposit;
import java.util.List;

public class DepositService {

    private final DepositDAO depositDAO = new DepositDAO();

    public void createDeposit(Deposit deposit) {
        depositDAO.persist(deposit);
    }

    public Deposit getDepositById(int id) {
        return depositDAO.getDepositById(id);
    }

    public List<Deposit> getAllDeposits() {
        return depositDAO.getAllDeposits();
    }

    public Deposit updateDeposit(Deposit deposit) {
        return depositDAO.merge(deposit);
    }

    public boolean deleteDeposit(int id) {
        Deposit deposit = depositDAO.getDepositById(id);
        if (deposit != null) {
            depositDAO.removeDeposit(deposit);
            return true;
        }
        return false;
    }
}
