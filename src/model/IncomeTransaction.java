package model;

import java.util.Date;

public class IncomeTransaction extends Transaction{
    private String source;

    public IncomeTransaction(Date date, String description, double amount, BankAccount account, Category category, String source) {
        super(date, description, amount, account, category);
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void updateBalance(double amount) {
        this.account.addBalance(amount);
    }
}
