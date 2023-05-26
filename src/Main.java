import database.DbFunctions;
import model.Menu;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws Exception{
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        Menu menu = Menu.getInstance();
        menu.mainMenu();


    }
}