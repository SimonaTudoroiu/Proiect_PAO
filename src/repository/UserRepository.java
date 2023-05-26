package repository;

import database.DbFunctions;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private DbFunctions dbFunctions;

    public UserRepository(DbFunctions dbFunctions) {
        this.dbFunctions = dbFunctions;
    }

    public void insertUser(User user){
        String preparedSql = "INSERT INTO \"user\" (name, email, password, phone, address, currency) VALUES (?, ?, ?, ?, ?, ?)";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        try{
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getCurrency());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByNameAndPassword(String nume, String password)
    {
        String selectSql = "SELECT * FROM \"user\" WHERE name = ? AND password = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, password);
            return mapToUser(preparedStatement.executeQuery());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateUser(String name, String email, String password, String phone, String address, String currency)
    {
        String updateSql = "UPDATE \"user\" SET email = ?, password = ?, phone = ?, address = ?, currency = ? WHERE name = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, currency);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(String nume)
    {
        String deleteSql = "DELETE FROM \"user\" WHERE name = ?";
        Connection databaseConnection = dbFunctions.connect_to_db("proiect_PAO", "postgres", "Reauugel02");
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setString(1, nume);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private User mapToUser(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            return new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
        }
        return null;
    }
}
