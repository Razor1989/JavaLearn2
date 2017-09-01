package Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.*;

import javax.management.modelmbean.ModelMBean;

class ObjQuestion {
    String question;
    int typ;
}

public class Question {
    Statement stat = new Connect().connecting();

    public int getMaxQuestion() {

        int anzahl = 0;
        try {
            ResultSet rs = stat.executeQuery("SELECT COUNT(*) AS anzahl FROM questions");
            System.out.println("Ausgabe:");
            while (rs.next())
                anzahl = rs.getInt("anzahl");


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return anzahl;
    }

    public static int question_id;

    public String[] getQuestion(int id) {
        String list[] = new String[2];
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM questions WHERE id = " + id);

            while(rs.next()) {
                list[0] = rs.getString("text");
                list[1] = rs.getString("typ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Model.Question> getAwnsers(int id) {
        ArrayList<Model.Question> list = new ArrayList<>();
        try {
            System.out.println("question ID: " + question_id);
            ResultSet rs = stat.executeQuery("SELECT * FROM awnsers WHERE question_id = " + id);
            while(rs.next()) {
                list.add(new Model.Question(rs.getString("text"), rs.getInt("true_or_false")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
