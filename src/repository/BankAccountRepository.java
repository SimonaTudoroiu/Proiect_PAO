package repository;

import database.DbFunctions;
import model.BankAccount;

import java.sql.*;
import java.util.ArrayList;

public class BankAccountRepository {
    private DbFunctions dbFunctions;

    public BankAccountRepository(DbFunctions dbFunctions) {
        this.dbFunctions = dbFunctions;
    }

    public void insertBankAccount(BankAccount budget){
        String preparedSql = "INSERT INTO bankAccount (username, IBAN, name, currency, balance) VALUES (?, ?, ?, ?, ?)";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        try{
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setString(1, budget.getUsername());
            preparedStatement.setString(2, budget.getIBAN());
            preparedStatement.setString(3, budget.getName());
            preparedStatement.setString(4, budget.getCurrency());
            preparedStatement.setDouble(5, budget.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getBankAccountByUsername(String username)
    {
        String selectSql = "SELECT * FROM bankAccount WHERE username = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> bankAccount = new ArrayList<>();
            bankAccount.add(resultSet.getString("username"));
            bankAccount.add(resultSet.getString("IBAN"));
            bankAccount.add(resultSet.getString("name"));
            bankAccount.add(resultSet.getString("currency"));
            bankAccount.add(resultSet.getString("balance"));
            return bankAccount;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    public void updateBankAccount(String username, String IBAN, String name, String currency, Double balance)
    {
        String updateSql = "UPDATE bankAccount SET IBAN = ?, name = ?, currency = ?, balance = ? WHERE username = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSql);
            preparedStatement.setString(2, IBAN);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, currency);
            preparedStatement.setDouble(5, balance);
            preparedStatement.setString(1, username);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public void deleteBankAccount(String username)
    {
        String deleteSql = "DELETE FROM bankAccount WHERE username = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
