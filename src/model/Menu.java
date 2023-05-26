package model;

import service.Service;
import service.ServiceDB;
import utile.Audit;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Menu {
    private static Menu instance = null;
    private Service service;

    private ServiceDB serviceDB;
    private Validator validator;
    private Audit audit = new Audit();

    private static Map<Integer, String> comenzi = new HashMap<Integer, String>(){
        {
            put(1," See details of your account");
            put(2," See details of your bank account");
            put(3," See details of your budget\n");
            put(4," See details of your transactions from today\n");
            put(5," Update budget\n");
            put(6," Update bank account\n");
            put(7," Add a new bank account\n");
            put(8," Remove bank account\n");
            put(9," Update your user profile\n");
            put(10," Delete your account\n");
            put(11," Delete all the transactions for today\n");
            put(12," Delete your budget\n");
            put(13," Add a transaction\n");
            put(14," Logout\n");

        }
    };
    private Menu() {
        service = new Service();
        validator = new Validator();
        serviceDB = new ServiceDB();
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public int firstMenu(){
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Please select an option:\n");
        System.out.println("1. Login\n");
        System.out.println("2. Register\n");
        System.out.println("3. Exit\n");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        return option;
    }

    public User loginMenu() throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Please enter your username:\n");
        String username = scanner.next();
        validator.isName(username);
        System.out.println("Please enter your password:\n");
        String password = scanner.next();
        validator.isPassword(password);

        User user = service.checkIfUserExists(username, password);

        if(user != null)
        {
            System.out.println("Login successful!\n");
            return user;
        }
        else
        {
            System.out.println("Login failed!\n");
            return null;
        }
    }

    public User loginMenuDB() throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Please enter your username:\n");
        String username = scanner.next();
        validator.isName(username);
        System.out.println("Please enter your password:\n");
        String password = scanner.next();
        validator.isPassword(password);

        User user = serviceDB.login(username, password);

        if(user != null)
        {
            System.out.println("Login successful!\n");
            return user;
        }
        else
        {
            System.out.println("Login failed!\n");
            return null;
        }
    }
    public void registerMenu() throws Exception {
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Please enter your username:\n");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        validator.isName(username);
        System.out.println("Please enter yout email:\n");
        String email = scanner.next();
        validator.isEmail(email);
        System.out.println("Please enter your password:\n");
        String password = scanner.next();
        validator.isPassword(password);
        System.out.println("Please enter your phone number:\n");
        String phone = scanner.next();
        validator.isPhone(phone);
        System.out.println("Please enter your address:\n");
        String address = scanner.next();
        validator.isAddress(address);
        System.out.println("Please enter your currency:\n");
        String currency = scanner.next();
        validator.isCurrency(currency);
        User user = new User(username, email, password, phone, address, currency);

        System.out.println("Now let's add your default bank account!\n");
        System.out.println("Please enter your IBAN:\n");
        String IBAN = scanner.next();
        validator.isIBAN(IBAN);
        System.out.println("Please enter a name for the account:\n");
        String name = scanner.next();
        validator.isName(name);
        System.out.println("Please enter the current balance of the account:\n");
        double balance = scanner.nextDouble();
        BankAccount bankAccount = new BankAccount(user, IBAN, name, currency, balance);

        List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount);
        service.addUser(user, bankAccounts);

        System.out.println("You have registered successfully! Now you have to login!\n");
    }

    public void registerMenuDB() throws Exception {
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Please enter your username:\n");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        validator.isName(username);
        System.out.println("Please enter yout email:\n");
        String email = scanner.next();
        validator.isEmail(email);
        System.out.println("Please enter your password:\n");
        String password = scanner.next();
        validator.isPassword(password);
        System.out.println("Please enter your phone number:\n");
        String phone = scanner.next();
        validator.isPhone(phone);
        System.out.println("Please enter your address:\n");
        String address = scanner.next();
        validator.isAddress(address);
        System.out.println("Please enter your currency:\n");
        String currency = scanner.next();
        validator.isCurrency(currency);
        User user = new User(username, email, password, phone, address, currency);

        serviceDB.register(user);

        System.out.println("Now let's add your default bank account!\n");
        System.out.println("Please enter your IBAN:\n");
        String IBAN = scanner.next();
        validator.isIBAN(IBAN);
        System.out.println("Please enter a name for the account:\n");
        String name = scanner.next();
        validator.isName(name);
        System.out.println("Please enter the current balance of the account:\n");
        double balance = scanner.nextDouble();
        BankAccount bankAccount = new BankAccount(user, IBAN, name, currency, balance);
        Budget budget = new Budget(0, 0, 0, user.getName());

        serviceDB.addBankAccount(bankAccount);
        serviceDB.addBudget(budget);

        System.out.println("You have registered successfully! Now you have to login!\n");
    }

    public int secondMenu()
    {
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Please select an option:\n");
        System.out.println("1. See details of your account\n");
        System.out.println("2. See details of your bank account\n");
        System.out.println("3. See details of your budget\n");
        System.out.println("4. Add a transaction\n");
//        System.out.println("5. Generate a report\n");
//        System.out.println("6. Generate a payment history\n");
        System.out.println("5. Set or update savings\n");
        System.out.println("6. Add a new bank account\n");
        System.out.println("7. Remove a bank account\n");
        System.out.println("8. Update your user profile\n");
        System.out.println("9. Delete your account\n");
        System.out.println("10. Logout\n");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        return option;
    }

    public int secondMenuDB() throws IOException {
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Please select an option:\n");
        System.out.println("1. See details of your account\n");
        System.out.println("2. See details of your bank account\n");
        System.out.println("3. See details of your budget\n");
        System.out.println("4. See details of your transactions from today\n");
//        System.out.println("5. Generate a report\n");
//        System.out.println("6. Generate a payment history\n");
        System.out.println("5. Update budget\n");
        System.out.println("6. Update bank account\n");
        System.out.println("7. Add a new bank account\n");
        System.out.println("8. Remove bank account\n");
        System.out.println("9. Update your user profile\n");
        System.out.println("10. Delete your account\n");
        System.out.println("11. Delete all the transactions for today\n");
        System.out.println("12. Delete your budget\n");
        System.out.println("13. Add a transaction\n");
        System.out.println("14. Logout\n");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        String comanda = comenzi.get(option);
        audit.logAction(comanda);
        return option;
    }

    public void mainMenu() throws Exception
    {
        System.out.println("Welcome to the Budget App!\n");
        System.out.println("Do you want to use the local app or the database app?\n");
        System.out.println("1. Local app\n");
        System.out.println("2. Database app\n");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if(option == 1)
            mainMenu1();
        else
            mainMenu2();

    }

    public void mainMenu1() throws Exception
    {
        meniuPrincipal:

        while(true)
        {
            int option1 = firstMenu();
            switch (option1)
            {
                case 1:
                    User user = loginMenu();
                    if(user != null)
                        alDoileaMeniu:

                            while(true) {
                                int option2 = secondMenu();

                                switch (option2)
                                {
                                    case 1:
                                        service.userToString(user);

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        Scanner scanner = new Scanner(System.in);
                                        String option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
                                    case 2:
                                        System.out.println("Please enter the IBAN of the bank account you want to see the details of:\n");
                                        Scanner scanner2 = new Scanner(System.in);
                                        String IBAN = scanner2.next();
                                        validator.isIBAN(IBAN);

                                        service.bankAccountToString(service.getBankAccountForUser(user, IBAN));

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
                                    case 3:
                                        service.budgetToString(user);

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
                                    case 4:
                                        System.out.println("Please enter the type of transaction: (income/expense)\n");
                                        scanner = new Scanner(System.in);
                                        String type = scanner.next();
                                        validator.isType(type);
                                        service.addTransaction(user, type);

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
//                                    case 5:
//                                        System.out.println("Please enter the IBAN of the bank account you want to see the details of:\n");
//                                        scanner = new Scanner(System.in);
//                                        IBAN = scanner.next();
//                                        validator.isIBAN(IBAN);
//
//                                        service.generateReport(user, service.getBankAccountForUser(user, IBAN));
//
//                                        System.out.println("Do you want to go back to the main menu? (y/n)");
//                                        scanner = new Scanner(System.in);
//                                        option = scanner.next();
//                                        if(option.equals("y"))
//                                            continue meniuPrincipal;
//                                        else
//                                            continue alDoileaMeniu;
//                                    case 6:
//                                        System.out.println("Please enter the IBAN of the bank account you want to see the details of:\n");
//                                        scanner = new Scanner(System.in);
//                                        IBAN = scanner.next();
//                                        validator.isIBAN(IBAN);
//
//                                        System.out.println("Please enter the number of the month you want to see the payment history of:\n");
//                                        int month = scanner.nextInt();
//
//                                        service.generatePaymentHistory(user, service.getBankAccountForUser(user, IBAN), month);
//
//                                        System.out.println("Do you want to go back to the main menu? (y/n)");
//                                        scanner = new Scanner(System.in);
//                                        option = scanner.next();
//                                        if(option.equals("y"))
//                                            continue meniuPrincipal;
//                                        else
//                                            continue alDoileaMeniu;
                                    case 5:
                                        System.out.println("Please enter the amount of money you want to save:\n");
                                        scanner = new Scanner(System.in);
                                        double amount = scanner.nextDouble();

                                        service.setSavingsInBudget(user, amount);

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
                                    case 6:
                                        System.out.println("Please enter the IBAN of the bank account you want to add:\n");
                                        scanner = new Scanner(System.in);
                                        IBAN = scanner.next();
                                        validator.isIBAN(IBAN);

                                        System.out.println("Please enter the name of the bank account you want to add:\n");
                                        scanner = new Scanner(System.in);
                                        String name = scanner.next();

                                        System.out.println("Please enter the currency of the bank account you want to add:\n");
                                        scanner = new Scanner(System.in);
                                        String currency = scanner.next();

                                        System.out.println("Please enter the balance of the bank account you want to add:\n");
                                        scanner = new Scanner(System.in);
                                        double balance = scanner.nextDouble();

                                        BankAccount bankAccount = new BankAccount(user, IBAN, name, currency, balance);
                                        service.addBankAccount(user, bankAccount);

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
                                    case 7:
                                        System.out.println("Please enter the IBAN of the bank account you want to delete:\n");
                                        scanner = new Scanner(System.in);
                                        IBAN = scanner.next();
                                        validator.isIBAN(IBAN);

                                        service.removeBankAccount(user, service.getBankAccountForUser(user, IBAN));

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
                                    case 8:
                                        System.out.println("Please enter what field you want to update:\n");
                                        scanner = new Scanner(System.in);
                                        String field = scanner.next();

                                        System.out.println("Please enter the new value of the field:\n");
                                        scanner = new Scanner(System.in);
                                        String newValue = scanner.next();

                                        service.updateUser(user, field, newValue);

                                        System.out.println("Do you want to go back to the main menu? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                            continue meniuPrincipal;
                                        else
                                            continue alDoileaMeniu;
                                    case 9:
                                        System.out.println("Are you sure you want to delete your account? (y/n)");
                                        scanner = new Scanner(System.in);
                                        option = scanner.next();
                                        if(option.equals("y"))
                                        {
                                            service.removeUser(user);
                                            System.out.println("Your account has been deleted!");
                                            continue meniuPrincipal;
                                        }
                                        else
                                            continue alDoileaMeniu;
                                    case 10:
                                        continue meniuPrincipal;
                                }
                            }
                    else
                        continue meniuPrincipal;
                case 2:
                    try {
                        registerMenu();
                        continue meniuPrincipal;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Your registration failed!Try again!");
                        continue meniuPrincipal;
                    }
                case 3:
                    return;

            }
        }
    }


    public void mainMenu2() throws Exception
    {
        meniuPrincipal:

        while(true)
        {
            int option1 = firstMenu();
            switch (option1)
            {
                case 1:
                    User user = loginMenuDB();
                    if(user != null)
                        alDoileaMeniu:

                                while(true) {
                                    int option2 = secondMenuDB();

                                    switch (option2)
                                    {
                                        case 1:
                                            service.userToString(user);

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            Scanner scanner = new Scanner(System.in);
                                            String option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 2:
                                            serviceDB.getBankAccountByUsername(user.getName());

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 3:
                                            System.out.println(serviceDB.getBudgetByDUsername(user.getName()).toString());

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 4:
                                            Date date = java.sql.Date.valueOf(LocalDate.now());
                                            serviceDB.printTransactionByDate(date);

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;

                                        case 5:
                                            System.out.println("Please enter the balance:\n");
                                            scanner = new Scanner(System.in);
                                            double balance = scanner.nextDouble();

                                            System.out.println("Please enter the income:\n");
                                            scanner = new Scanner(System.in);
                                            double income = scanner.nextDouble();

                                            System.out.println("Please enter the expense:\n");
                                            scanner = new Scanner(System.in);
                                            double expense = scanner.nextDouble();

                                            System.out.println("Please enter the savings:\n");
                                            scanner = new Scanner(System.in);
                                            double savings = scanner.nextDouble();

                                            serviceDB.updateBudget( balance, income, expense, savings, user.getName());

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 6:
                                            System.out.println("Please enter the IBAN:\n");
                                            scanner = new Scanner(System.in);
                                            String IBAN = scanner.next();
                                            validator.isIBAN(IBAN);

                                            System.out.println("Please enter the name of the bank account:\n");
                                            scanner = new Scanner(System.in);
                                            String name = scanner.next();

                                            System.out.println("Please enter the currency of the bank account:\n");
                                            scanner = new Scanner(System.in);
                                            String currency = scanner.next();

                                            System.out.println("Please enter the balance:\n");
                                            scanner = new Scanner(System.in);
                                            balance = scanner.nextDouble();

                                            serviceDB.updateBankAccount( user.getName(), IBAN, name, currency, balance);


                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 7:
                                            scanner = new Scanner(System.in);
                                            System.out.println("Please enter your IBAN:\n");
                                             IBAN = scanner.next();
                                            validator.isIBAN(IBAN);
                                            System.out.println("Please enter a name for the account:\n");
                                             name = scanner.next();
                                            validator.isName(name);
                                            System.out.println("Please enter the current balance of the account:\n");
                                             balance = scanner.nextDouble();
                                            System.out.println("Please enter your currency:\n");
                                             currency = scanner.next();
                                            BankAccount bankAccount = new BankAccount(user, IBAN, name, currency, balance);

                                            serviceDB.addBankAccount(bankAccount);

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 8:
                                            serviceDB.deleteBankAccount(user.getName());
                                            System.out.println("Your bank account has been deleted!");

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 9:
                                            System.out.println("Please enter your username:\n");
                                             scanner = new Scanner(System.in);
                                            String username = scanner.next();
                                            validator.isName(username);
                                            System.out.println("Please enter yout email:\n");
                                            String email = scanner.next();
                                            validator.isEmail(email);
                                            System.out.println("Please enter your password:\n");
                                            String password = scanner.next();
                                            validator.isPassword(password);
                                            System.out.println("Please enter your phone number:\n");
                                            String phone = scanner.next();
                                            validator.isPhone(phone);
                                            System.out.println("Please enter your address:\n");
                                            String address = scanner.next();
                                            validator.isAddress(address);
                                            System.out.println("Please enter your currency:\n");
                                             currency = scanner.next();
                                            validator.isCurrency(currency);

                                            serviceDB.update(username, email, password, phone, address, currency);

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;

                                        case 10:
                                            System.out.println("Are you sure you want to delete your account? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                            {
                                                serviceDB.delete(user.getName());
                                                System.out.println("Your account has been deleted!");
                                                continue meniuPrincipal;
                                            }
                                            else
                                                continue alDoileaMeniu;

                                        case 11:
                                             date = java.sql.Date.valueOf(LocalDate.now());
                                            serviceDB.deleteTransaction(date);

                                            System.out.println("Your transaction has been deleted!");

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;

                                        case 12:
                                            serviceDB.deleteBudget(user.getName());
                                            System.out.println("Your budget has been deleted!");

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;

                                        case 13:
                                            System.out.println("Please enter the type of the transaction (income, expense):\n");
                                            scanner = new Scanner(System.in);
                                            String type = scanner.next();
                                            System.out.println("Please enter the description of the transaction:\n");
                                            String description = scanner.next();
                                            System.out.println("Please enter the amount of the transaction:\n");
                                            double amount = scanner.nextDouble();
                                            System.out.println("Please enter the IBAN of the transaction:\n");
                                            IBAN = scanner.next();
                                            validator.isIBAN(IBAN);
                                            System.out.println("Please enter the category of the transaction:\n");
                                            String category = scanner.next();

                                            date = java.sql.Date.valueOf(LocalDate.now());
                                            serviceDB.addTransaction(date, description, amount, IBAN, category,user.getName(), type);

                                            System.out.println("Do you want to go back to the main menu? (y/n)");
                                            scanner = new Scanner(System.in);
                                            option = scanner.next();
                                            if(option.equals("y"))
                                                continue meniuPrincipal;
                                            else
                                                continue alDoileaMeniu;
                                        case 14:
                                            continue meniuPrincipal;
                                    }
                                }
                    else
                        continue meniuPrincipal;
                case 2:
                    try {
                        registerMenuDB();
                        continue meniuPrincipal;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Your registration failed!Try again!");
                        continue meniuPrincipal;
                    }
                case 3:
                    return;

            }
        }
    }
}
