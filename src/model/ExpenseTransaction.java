package model;

import java.util.Date;

public class ExpenseTransaction extends Transaction{
    private String destination;

    public ExpenseTransaction(Date date, String description, double amount, BankAccount account, Category category, String destination) {
        super(date, description, amount, account, category);
        this.destination = destination;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void updateBalance(double amount) {
        this.account.removeBalance(amount);
    }
}
