package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class Poloczenie {
    public Connection databaseLink;


    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.databaseLink = DriverManager.getConnection("jdbc:mysql://localhost:3306/biuropodrozy", "root", "");
        } catch (Exception var2) {
            var2.printStackTrace();
            var2.getCause();
        }

        return this.databaseLink;
    }
}
