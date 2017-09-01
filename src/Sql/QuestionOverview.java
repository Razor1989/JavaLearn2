package Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Sql.Connect.*;

public class QuestionOverview {
    Statement stat = new Connect().connecting();
    public ResultSet questionList() {
        ResultSet rs = null;
        try {
            rs = stat.executeQuery("SELECT * FROM questions");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
