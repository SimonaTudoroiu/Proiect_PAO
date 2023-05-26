package service;

import database.DbFunctions;
import model.*;
import repository.BankAccountRepository;
import repository.BudgetRepository;
import repository.TransactionRepository;
import repository.UserRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

public class ServiceDB {
    UserRepository userRepository;
    TransactionRepository transactionRepository;
    BudgetRepository budgetRepository;
    BankAccountRepository bankAccountRepository;

    public ServiceDB() {
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        this.userRepository = new UserRepository(db);
        this.transactionRepository = new TransactionRepository(db);
        this.budgetRepository = new BudgetRepository(db);
        this.bankAccountRepository = new BankAccountRepository(db);
    }

    public User login(String username, String password) {
        return userRepository.getUserByNameAndPassword(username, password);
    }

    public void register(User user) {
        userRepository.insertUser(user);
    }

    public void update(String name, String email, String password, String phone, String address, String currency) {
        userRepository.updateUser(name, email, password, phone, address, currency);
    }

    public void delete(String name) {
        userRepository.deleteUser(name);
    }

    public void addTransaction(Date date, String description, double amount, String IBAN, String category, String username, String type) {
        transactionRepository.insertTransaction((java.sql.Date) date, description, amount, IBAN, category, username, type);
    }



    public void printTransactionByDate(Date date) {
        ArrayList<String> transaction = transactionRepository.getTransactionByDate((java.sql.Date) date);

        System.out.println("Date: " + transaction.get(0) + "\nDescription: " + transaction.get(1) + "\nAmount: " + transaction.get(2) + "\nIBAN: " + transaction.get(3) + "\nCategory: " + transaction.get(4));
    }

    public void updateTransaction(Date date, String description, double amount, String IBAN, String category, String username, String type) {
        transactionRepository.updateTransaction((java.sql.Date) date, description, amount, IBAN, category,username,  type);
    }

    public void deleteTransaction(Date date) {
        transactionRepository.deleteTransaction((java.sql.Date) date);
    }

    public void addBudget(Budget budget) {
        budgetRepository.insertBudget(budget);
    }

    public Budget getBudgetByDUsername(String username) {
        return budgetRepository.getBudgetByUsername(username);
    }

    public void updateBudget(Double balance, Double income, Double expense, Double savings, String username) {
        budgetRepository.updateBudget(balance, income, expense, savings, username);
    }

    public void deleteBudget(String username) {
        budgetRepository.deleteBudget(username);
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccountRepository.insertBankAccount(bankAccount);
    }

    public void getBankAccountByUsername(String username) {
        ArrayList<String> bankAccount = bankAccountRepository.getBankAccountByUsername(username);

        System.out.println("Username: " + bankAccount.get(0) + "\nIBAN: " + bankAccount.get(1) + "\nName: " + bankAccount.get(2) + "\nCurrency: " + bankAccount.get(3) + "\nBalance: " + bankAccount.get(4));
    }

    public void updateBankAccount(String username, String IBAN, String name, String currency, Double balance) {
        bankAccountRepository.updateBankAccount(username, IBAN, name, currency, balance);
    }

    public void deleteBankAccount(String username) {
        bankAccountRepository.deleteBankAccount(username);
    }
}
