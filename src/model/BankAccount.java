package model;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private User user;
    private String IBAN;
    private String name;
    private String currency;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(User user, String IBAN, String name, String currency, double balance, List<Transaction> transactions) {
        this.user = user;
        this.IBAN = IBAN;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.transactions = transactions;
    }

    public BankAccount(User user, String IBAN, String name, String currency, double balance){
        this.user = user;
        this.IBAN = IBAN;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.transactions = new ArrayList<Transaction>();
    }

    @Override
    public String toString()
    {
        String s = null;
        s = "User: " + user +"\nIBAN: " + IBAN + "\nName: " + name + "\nCurrency: " + currency + "\nBalance: " + balance + "\n";
        return s;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
    }

//    public void updateTransaction(Transaction transaction) {
//        for (int i = 0; i < this.transactions.size(); i++) {
//            if (this.transactions.get(i).getId() == transaction.getId()) {
//                this.transactions.set(i, transaction);
//            }
//        }
//    }
//
//    public Transaction getTransactionById(int id) {
//        for (Transaction transaction : this.transactions) {
//            if (transaction.getId() == id) {
//                return transaction;
//            }
//        }
//        return null;
//    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void removeBalance(double amount) {
        this.balance -= amount;
    }

    public double getIncome() {
        double income = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof IncomeTransaction) {
                income += transaction.getAmount();
            }
        }
        return income;
    }

    public double getExpense() {
        double expense = 0;
        for (Transaction transaction : transactions) {
            if (transaction instanceof ExpenseTransaction) {
                expense += transaction.getAmount();
            }
        }
        return expense;
    }

    public List<ExpenseTransaction> getExpenseTransactions() {
        List<ExpenseTransaction> expenseTransactions = null;
        for (Transaction transaction : transactions) {
            if (transaction instanceof ExpenseTransaction) {
                expenseTransactions.add((ExpenseTransaction) transaction);
            }
        }
        return expenseTransactions;
    }

    public List<IncomeTransaction> getIncomeTransactions() {
        List<IncomeTransaction> incomeTransactions = null;
        for (Transaction transaction : transactions) {
            if (transaction instanceof IncomeTransaction) {
                incomeTransactions.add((IncomeTransaction) transaction);
            }
        }
        return incomeTransactions;
    }

    public List<IncomeTransaction> getIncomeTransactionsByMonth(int month) {
        List<IncomeTransaction> incomeTransactionsByMonth = null;
        for (Transaction transaction : transactions) {
            if (transaction instanceof IncomeTransaction && transaction.getDate().getMonth() == month) {
                incomeTransactionsByMonth.add((IncomeTransaction) transaction);
            }
        }
        return incomeTransactionsByMonth;
    }

    public List<ExpenseTransaction> getExpenseTransactionsByMonth(int month) {
        List<ExpenseTransaction> expenseTransactionsByMonth = null;
        for (Transaction transaction : transactions) {
            if (transaction instanceof ExpenseTransaction && transaction.getDate().getMonth() == month) {
                expenseTransactionsByMonth.add((ExpenseTransaction) transaction);
            }
        }
        return expenseTransactionsByMonth;
    }
}
