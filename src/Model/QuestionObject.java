package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuestionObject {
    private StringProperty text;
    private IntegerProperty questionId;

    public QuestionObject(String text, int id) {
        this.questionId = new SimpleIntegerProperty(id);
        this.text = new SimpleStringProperty(text);
    }

    public String getText() {
        return text.get();
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public StringProperty textProperty() {
        if (text == null) text = new SimpleStringProperty("-");
        return text;
    }

    public int getQuestionId() {
        return questionId.get();
    }

    public void setQuestionId(int questionId) {
        this.questionId.set(questionId);
    }

    public IntegerProperty questionIdProperty() {
        return questionId;
    }
}
