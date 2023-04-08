package model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Reporter {
    private Date date;
    private int numberOfDays;
    private BankAccount account;
    private Category category;

    public Reporter(Date date, int numberOfDays, BankAccount account, Category category) {
        this.date = date;
        this.numberOfDays = numberOfDays;
        this.account = account;
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }


    public void generateReport() {
        List<Transaction> transactions = account.getTransactions();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(calendar.DATE, -numberOfDays);
//        Date newDate = calendar.getTime();

        double incomeBalance = 0;
        double expenseBalance = 0;


        for (Transaction transaction : transactions) {
//            if (transaction.getDate().after(newDate) && transaction.getDate().before((Date) date)) {
                if (transaction instanceof IncomeTransaction) {
                    IncomeTransaction incomeTransaction = (IncomeTransaction) transaction;
                    if (incomeTransaction.getCategory().equals(category) ) {
                        incomeBalance += incomeTransaction.getAmount();
                    }
                } else if (transaction instanceof ExpenseTransaction) {
                    ExpenseTransaction expenseTransaction = (ExpenseTransaction) transaction;
                    if (expenseTransaction.getCategory().equals(category)) {
                        expenseBalance += expenseTransaction.getAmount();
                    }
                }
//            }
        }
        System.out.println("For the last " + numberOfDays + " days, until " + date + ", for the upcoming type of transactions: " + category + ", from de bank account with the name: " + account.getName() + ", you have the following report: \n");
        System.out.println("Income Balance: " + incomeBalance + "\n");
        System.out.println("Expense Balance: " + expenseBalance + "\n");

    }
}
