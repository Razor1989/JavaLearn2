package Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login  {
    Statement stat = new Connect().connecting();

    public boolean loginCheck(String name, String password) throws SQLException {
        ResultSet rs = stat.executeQuery("SELECT * FROM user");

        while (rs.next()) {
            if (rs.getString("name").equals(name) && rs.getString("password").equals(password))
                return true;

        }
        return false;
    }
}
