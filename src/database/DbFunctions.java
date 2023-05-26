package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if(connection != null){
                System.out.println("Connected to the database!");
            }
            else{
                System.out.println("Failed to make connection!");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        return connection;
    }


}
