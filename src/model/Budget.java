package model;

public class Budget {
    private double balance;
    private double income;
    private double expense;
    private double savings;
    private String username;

    public Budget(double balance, double income, double expense, String username) {
        this.balance = balance;
        this.income = income;
        this.expense = expense;
        this.savings = 0;
        this.username = username;
    }

    @Override
    public String toString()
    {
        String s = null;
        s = "Balance: " + balance + "\nIncome: " + income + "\nExpense: " + expense + "\nSavings: " + savings + "\n";
        return s;
    }

    public double getBalance() {
        return balance;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }

    public double getSavings() {
        return savings;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public void setSavings(double savings) {
        this.savings = savings;
        this.balance = this.balance - savings;
    }

    public void addIncome(double income) {
        this.income = this.income + income;
        this.balance = this.balance + income;
    }

    public void addExpense(double expense) {
        this.expense = this.expense + expense;
        this.balance = this.balance - expense;
    }

    public void addSavings(double savings) {
        this.savings = this.savings + savings;
        this.balance = this.balance - savings;
    }

    public void removeIncome(double income) {
        this.income = this.income - income;
        this.balance = this.balance - income;
    }

    public void removeExpense(double expense) {
        this.expense = this.expense - expense;
        this.balance = this.balance + expense;
    }

    public void removeSavings(double savings) {
        this.savings = this.savings - savings;
        this.balance = this.balance + savings;
    }

    public void updateBalance(double amount) {
        this.balance = this.balance + amount;
    }

    public String getUsername() {
        return username;
    }


}
