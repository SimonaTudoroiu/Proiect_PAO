package service;

import model.*;

import java.time.LocalDate;
import java.util.*;

public class Service {
    private List<User> listOfUsers;
    private Map<User, List<BankAccount>> users;
    private Map<User, Budget> budgets;

    public Service() {
        this.listOfUsers = new ArrayList<User>();
        this.users = new HashMap<>();
        this.budgets = new HashMap<>();
        ;
    }

    public User checkIfUserExists(String username, String password) {
        for (User u : listOfUsers) {
            if (u.getName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public void addUser(User user, List<BankAccount> bankAccounts) {
        users.put(user, bankAccounts);

        double balance = 0, income = 0, expense = 0;
        for (BankAccount bankAccount : bankAccounts) {
            balance += bankAccount.getBalance();
            income += bankAccount.getIncome();
            expense += bankAccount.getExpense();
        }

        budgets.put(user, new Budget(balance, income, expense));

        listOfUsers.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
        budgets.remove(user);
        listOfUsers.remove(user);
    }

    public void addBankAccount(User user, BankAccount bankAccount) {
        users.get(user).add(bankAccount);
    }

    public void removeBankAccount(User user, BankAccount bankAccount) {
        users.get(user).remove(bankAccount);
    }

    public void updateUser(User user, String option, String value) {
        List<BankAccount> bankAccounts = users.get(user);
        users.remove(user);
        switch (option) {
            case "name":
                user.setName(value);
                users.put(user, bankAccounts);
                break;
            case "email":
                user.setEmail(value);
                users.put(user, bankAccounts);
                break;
            case "password":
                user.setPassword(value);
                users.put(user, bankAccounts);
                break;
            case "phone":
                user.setPhone(value);
                users.put(user, bankAccounts);
                break;
            case "address":
                user.setAddress(value);
                users.put(user, bankAccounts);
                break;
            case "currency":
                user.setCurrency(value);
                users.put(user, bankAccounts);
                break;
        }


    }

    public void updateSaving(User user, double saving) {
        Budget budget = budgets.get(user);
        budgets.remove(user);
        budget.setSavings(saving);
        budgets.put(user, budget);
    }

    public void viewBudget(User user) {
        Budget budget = budgets.get(user);
        System.out.println("Balance: " + budget.getBalance());
        System.out.println("Income: " + budget.getIncome());
        System.out.println("Expense: " + budget.getExpense());
        System.out.println("Savings: " + budget.getSavings());
    }

    public void useSavings(User user, double amount) {
        budgets.get(user).setSavings(budgets.get(user).getSavings() - amount);
    }

    public void addTransaction(User user, String type) {
        if (type.equals("expense")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter the amount: \n");
            double amount = scanner.nextDouble();
            if (amount > budgets.get(user).getBalance() - budgets.get(user).getSavings() && amount <= budgets.get(user).getBalance() + budgets.get(user).getSavings()) {
                System.out.println("You will have to use your savings to make this transaction. Do you want to continue? (yes/no)");
                String choiceSavings = scanner.next();
                if (choiceSavings.equals("no")) {
                    return;
                } else {
                    useSavings(user, amount - budgets.get(user).getBalance());
                }
            } else {
                if (amount > budgets.get(user).getBalance() + budgets.get(user).getSavings()) {
                    System.out.println("You don't have enough money to make this transaction.");
                    return;
                }
            }
            Date date = java.sql.Date.valueOf(LocalDate.now());
            System.out.println("\nEnter the description: \n");
            String description = scanner.next();
            System.out.println("\nEnter the IBAN of the bank account: \n");
            String IBAN = scanner.next();
            System.out.println("\nEnter the category of the transaction(food, entertainment, transport, bills, other): \n");
            String choice = scanner.next();
            System.out.println("\nEnter the destination of the transaction: \n");
            String destination = scanner.next();

            BankAccount ba = null;
            for (BankAccount bankAccount1 : users.get(user)) {
                if (bankAccount1.getIBAN().equals(IBAN)) {
                    ba = bankAccount1;
                    break;
                }
            }
            Category category;
            ExpenseTransaction expenseTransaction;
            switch (choice) {
                case "food":
                    category = Category.food;
                    expenseTransaction = new ExpenseTransaction(date, description, amount, ba, category, destination);
                    for (BankAccount bankAccount1 : users.get(user)) {
                        if (bankAccount1 == ba) {
                            bankAccount1.addTransaction(expenseTransaction);
                            bankAccount1.removeBalance(amount);
                            budgets.get(user).updateBalance(-amount);
                            budgets.get(user).addExpense(amount);
                            break;
                        }
                    }
                    break;
                case "entertainment":
                    category = Category.entertainment;
                    expenseTransaction = new ExpenseTransaction(date, description, amount, ba, category, destination);
                    for (BankAccount bankAccount1 : users.get(user)) {
                        if (bankAccount1 == ba) {
                            bankAccount1.addTransaction(expenseTransaction);
                            bankAccount1.removeBalance(amount);
                            budgets.get(user).updateBalance(-amount);
                            budgets.get(user).addExpense(amount);
                            break;
                        }
                    }
                    break;
                case "transport":
                    category = Category.transport;
                    expenseTransaction = new ExpenseTransaction(date, description, amount, ba, category, destination);
                    for (BankAccount bankAccount1 : users.get(user)) {
                        if (bankAccount1 == ba) {
                            bankAccount1.addTransaction(expenseTransaction);
                            bankAccount1.removeBalance(amount);
                            budgets.get(user).updateBalance(-amount);
                            budgets.get(user).addExpense(amount);
                            break;
                        }
                    }
                    break;
                case "bills":
                    category = Category.bills;
                    expenseTransaction = new ExpenseTransaction(date, description, amount, ba, category, destination);
                    for (BankAccount bankAccount1 : users.get(user)) {
                        if (bankAccount1 == ba) {
                            bankAccount1.addTransaction(expenseTransaction);
                            bankAccount1.removeBalance(amount);
                            budgets.get(user).updateBalance(-amount);
                            budgets.get(user).addExpense(amount);
                            break;
                        }
                    }
                    break;
                case "other":
                    category = Category.other;
                    expenseTransaction = new ExpenseTransaction(date, description, amount, ba, category, destination);
                    for (BankAccount bankAccount1 : users.get(user)) {
                        if (bankAccount1 == ba) {
                            bankAccount1.addTransaction(expenseTransaction);
                            bankAccount1.removeBalance(amount);
                            budgets.get(user).updateBalance(-amount);
                            budgets.get(user).addExpense(amount);
                            break;
                        }
                    }
                    break;
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter the amount: \n");
            double amount = scanner.nextDouble();
            Date date = java.sql.Date.valueOf(LocalDate.now());
            System.out.println("\nEnter the description: \n");
            String description = scanner.next();
            System.out.println("\nEnter the IBAN of the bank account: \n");
            String IBAN = scanner.next();
            System.out.println("\nEnter the category of the transaction(food, entertainment, transport, bills, other): \n");
            String choice = scanner.next();
            System.out.println("\nEnter the source of the transaction: \n");
            String source = scanner.next();

            BankAccount ba = null;
            for (BankAccount bankAccount1 : users.get(user)) {
                if (bankAccount1.getIBAN().equals(IBAN)) {
                    ba = bankAccount1;
                    break;
                }
            }
            Category category;
            IncomeTransaction incomeTransaction;
            switch (choice) {
                case "salary":
                    category = Category.salary;
                    incomeTransaction = new IncomeTransaction(date, description, amount, ba, category, source);
                    for (BankAccount bankAccount1 : users.get(user)) {
                        if (bankAccount1 == ba) {
                            bankAccount1.addTransaction(incomeTransaction);
                            bankAccount1.addBalance(amount);
                            budgets.get(user).updateBalance(amount);
                            budgets.get(user).addIncome(amount);
                            break;
                        }
                    }
                    break;
                case "other":
                    category = Category.other;
                    incomeTransaction = new IncomeTransaction(date, description, amount, ba, category, source);
                    for (BankAccount bankAccount1 : users.get(user)) {
                        if (bankAccount1 == ba) {
                            bankAccount1.addTransaction(incomeTransaction);
                            bankAccount1.addBalance(amount);
                            budgets.get(user).updateBalance(amount);
                            budgets.get(user).addIncome(amount);
                            break;
                        }
                    }
                    break;

            }
        }

    }

    public void generateReport(User user, BankAccount bankAccount) {
        for (BankAccount bankAccount1 : users.get(user)) {
            if (bankAccount1 == bankAccount) {


                System.out.println("What number of days do you want to see the report for?");
                Scanner scanner = new Scanner(System.in);
                int days = scanner.nextInt();
                System.out.println("This is the report for the bank account " + bankAccount.getIBAN() + " of the user " + user.getName() + ":\n");
                System.out.println("For the food category:\n");
                Date date = java.sql.Date.valueOf(LocalDate.now());
                Reporter reporter = new Reporter(date, days, bankAccount1, Category.food);
                reporter.generateReport();
                System.out.println("For the entertainment category:\n");
                Reporter reporter1 = new Reporter(date, days, bankAccount1, Category.entertainment);
                reporter1.generateReport();
                System.out.println("For the transport category:\n");
                Reporter reporter2 = new Reporter(date, days, bankAccount1, Category.transport);
                reporter2.generateReport();
                System.out.println("For the bills category:\n");
                Reporter reporter3 = new Reporter(date, days, bankAccount1, Category.bills);
                reporter3.generateReport();
                System.out.println("For the salary category:\n");
                Reporter reporter4 = new Reporter(date, days, bankAccount1, Category.salary);
                reporter4.generateReport();
                System.out.println("For the other category:\n");
                Reporter reporter5 = new Reporter(date, days, bankAccount1, Category.other);
                reporter5.generateReport();

                break;
            }
        }

    }

    public void generatePaymentHistory(User user, BankAccount bankAccount, int month) {

        for (BankAccount bankAccount1 : users.get(user)) {
            if (bankAccount1 == bankAccount) {

                System.out.println("This is the payment history for the bank account " + bankAccount1.getIBAN() + " of the user " + user.getName() + " for the selected month:\n");


                for (ExpenseTransaction transaction : bankAccount1.getExpenseTransactionsByMonth(month)) {
                    System.out.println(transaction.toString());
                }

                for (IncomeTransaction transaction : bankAccount1.getIncomeTransactionsByMonth(month)) {
                    System.out.println(transaction.toString());
                }

                break;
            }
        }
    }

    public void setSavingsInBudget(User user, double amount) {
        budgets.get(user).setSavings(amount);
    }

    public void userToString(User user) {
        System.out.println(user.toString());
    }

    public void bankAccountToString(BankAccount bankAccount) {
        System.out.println(bankAccount.toString());
    }

    public void budgetToString(User user) {
        System.out.println(budgets.get(user).toString());
    }

    public BankAccount getBankAccountForUser(User user, String IBAN) {
        for (BankAccount bankAccount : users.get(user)) {
            if (bankAccount.getIBAN().equals(IBAN)) {
                return bankAccount;
            }
        }
        return null;
    }
}
