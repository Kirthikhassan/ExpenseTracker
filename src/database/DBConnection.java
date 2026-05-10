package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/expense_tracker",
                    "root",
                    "root"
            );

            System.out.println("Database Connected Successfully");

            return con;

        } catch (Exception e) {

            System.out.println(e);
        }

        return null;
    }
}