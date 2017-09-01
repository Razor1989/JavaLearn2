package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect  {
    Connection conn;
    Statement stat;
    public Statement connecting() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javalearn?user=root&password=");
            stat = conn.createStatement();
            return stat;
        } catch(SQLException e) {
            System.out.println("Konnt ekeine Verbindung zur DAtenbank herstellen.");
        }
        return null;
    }
}
