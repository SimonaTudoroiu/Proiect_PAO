package repository;

import database.DbFunctions;
import model.Budget;

import java.sql.*;

public class BudgetRepository {
    private DbFunctions dbFunctions;

    public BudgetRepository(DbFunctions dbFunctions) {
        this.dbFunctions = dbFunctions;
    }

    public void insertBudget(Budget budget){
        String preparedSql = "INSERT INTO budget (balance, income, expense, savings, username) VALUES (?, ?, ?, ?, ?)";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        try{
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setDouble(1, budget.getBalance());
            preparedStatement.setDouble(2, budget.getIncome());
            preparedStatement.setDouble(3, budget.getExpense());
            preparedStatement.setDouble(4, budget.getSavings());
            preparedStatement.setString(5, budget.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Budget getBudgetByUsername(String username)
    {
        String selectSql = "SELECT * FROM budget WHERE username = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, username);
            return mapToBudget(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    public void updateBudget(Double balance, Double income, Double expense, Double savings, String username)
    {
        String updateSql = "UPDATE budget SET balance = ?, income = ?, expense = ?, savings = ? WHERE username = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSql);
            preparedStatement.setDouble(1, balance);
            preparedStatement.setDouble(2, income);
            preparedStatement.setDouble(3, expense);
            preparedStatement.setDouble(4, savings);
            preparedStatement.setString(5, username);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void deleteBudget(String username)
    {
        String deleteSql = "DELETE FROM budget WHERE username = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Budget mapToBudget(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            return new Budget(resultSet.getDouble(1), resultSet.getDouble(2), resultSet.getDouble(3), resultSet.getString(5));
        }
        return null;
    }

}
