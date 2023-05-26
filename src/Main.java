import database.DbFunctions;
import model.Menu;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Map<String, String> comenzi = new HashMap<String, String>(){
        {
            put("1", "Afisare tranzactii");
            put("2", "Adaugare tranzactie");
            put("3", "Adaugare categorie");
            put("4", "Afisare categorii");
            put("5", "Adaugare cont");
            put("6", "Afisare conturi");
            put("7", "Adaugare buget");
            put("8", "Afisare bugete");
            put("9", "Adaugare cheltuiala");
            put("10", "Afisare cheltuieli");
            put("11", "Adaugare venit");
            put("12", "Afisare venituri");
            put("13", "Adaugare economii");
            put("14", "Afisare economii");


        }
    };
    public static void main(String[] args) throws Exception{
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("proiect_PAO", "postgres", "Reauugel02");

        Menu menu = Menu.getInstance();
        menu.mainMenu();


    }
}