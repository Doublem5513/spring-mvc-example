package org.doublem.examples.webapp.utils;

import org.doublem.examples.webapp.settings.GlobalSettings;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by mint on 16/8/14.
 */
public class DatabaseUtils {
    public static String DATABASE_FILE = GlobalSettings.DB_FILE;

    public static void initDatabase(){
        File dbFile = new File(DATABASE_FILE);
        if(dbFile.exists()){
            System.out.println("Warning! Database already exists! To delete, please run resetDatabase()");
            return;
        }
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_FILE);

            Statement statement = connection.createStatement();
            String query = "CREATE TABLE POSTS" +
                    "(id INTEGER PRIMARY KEY," +
                    " title TEXT NOT NULL," +
                    " text TEXT NOT NULL," +
                    " created LONG NOT NULL, " +
                    "author TEXT NOT NULL," +
                    " avatar TEXT)";

            statement.executeUpdate(query);
            statement.close();
            connection.close();
            System.out.println("Database initialized!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetDatabase(){
        File dbFile = new File(DATABASE_FILE);
        if(dbFile.exists()){
            System.out.println("Deleting database...");
            boolean result = dbFile.renameTo(new File(DATABASE_FILE + ".backup-" + System.currentTimeMillis()));
            if(result){
                System.out.println("Success!");
            }else{
                System.out.println("FAILURE to remove database!");
            }
            return;
        }

        System.out.println("No database found for deletion!");
    }

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Please provide action:\n\t- init\n\t- reset\n\t- delete\n");
            return;
        }

        if(args[0].equals("init")){
            initDatabase();
            return;
        }

        if(args[0].equals("reset")){
            resetDatabase();
            initDatabase();
            return;
        }

        if(args[0].equals("delete")){
            resetDatabase();
        }

    }
}
