package repository;

import database.DbFunctions;
import model.ExpenseTransaction;
import model.IncomeTransaction;

import java.sql.*;
import java.util.ArrayList;

public class TransactionRepository {
    private DbFunctions dbFunctions;

    public TransactionRepository(DbFunctions dbFunctions) {
        dbFunctions = new DbFunctions();
    }
    public void insertIncomeTransaction( IncomeTransaction transaction){
        String preparedSql = "INSERT INTO transaction (date, description, amount, IBAN, category) VALUES (?, ?, ?, ?, ?)";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        try{
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setDate(1, (Date) transaction.getDate());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getIBAN());
            preparedStatement.setString(5, transaction.getCategoryName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertExpenseTransaction( ExpenseTransaction transaction){
        String preparedSql = "INSERT INTO transaction (date, description, amount, IBAN, category) VALUES (?, ?, ?, ?, ?)";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        try{
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setDate(1, (Date) transaction.getDate());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getIBAN());
            preparedStatement.setString(5, transaction.getCategoryName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTransactionByDate(Date date)
    {
        String selectSql = "SELECT * FROM transaction WHERE date = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setDate(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> transaction = new ArrayList<>();
            transaction.add(resultSet.getString("date"));
            transaction.add(resultSet.getString("description"));
            transaction.add(resultSet.getString("amount"));
            transaction.add(resultSet.getString("IBAN"));
            transaction.add(resultSet.getString("category"));
            return transaction;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void updateTransaction(Date date, String description, double amount, String IBAN, String category)
    {
        String updateSql = "UPDATE transaction SET description = ?, amount = ?, IBAN = ?, category = ? WHERE date = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSql);
            preparedStatement.setDate(1, date);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, IBAN);
            preparedStatement.setString(5, category);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTransaction(Date date)
    {
        String deleteSql = "DELETE FROM transaction WHERE name = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setDate(1, date);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
