package model;

import java.util.Date;

abstract class Transaction {
    private Date date;
    private String description;
    private double amount;
    protected BankAccount account;
    protected Category category;

    public Transaction(Date date, String description, double amount, BankAccount account, Category category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.account = account;
        this.category = category;
    }

    @Override
    public String toString()
    {
        String s = null;
        s = "Account: " + account.getIBAN() + "Date: " + date + "\n Description: " + description + "\n Amount: " + amount + "\n Category: " + category + "\n";
        return s;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDestination() {
        return description;
    }

    public void setDestination(String destination) {
        this.description = destination;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public void addTransaction(Transaction transaction) {
        this.account.addTransaction(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        this.account.removeTransaction(transaction);
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryName() {
        if (category.equals("food"))
            return "food";
        else if (category.equals("transport"))
            return "transport";
        else if (category.equals("bills"))
            return "bills";
        else if (category.equals("entertainment"))
            return "entertainment";
        else if (category.equals("salary"))
            return "salary";
        else
            return "other";
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public void updateTransaction(Transaction transaction) {
//        this.account.updateTransaction(transaction);
//    }
//
//    public Transaction getTransactionById(int id) {
//        return this.account.getTransactionById(id);
//    }

    public String getDescription() {
        return description;
    }

    public String getIBAN() {
        return account.getIBAN();
    }
    public abstract void updateBalance(double amount);
}
