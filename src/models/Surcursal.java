package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Surcursal {
    private final StringProperty Name;
    private final StringProperty Nick;

    Surcursal() {
        this.Name = new SimpleStringProperty(null);
        this.Nick = new SimpleStringProperty(null);
    }

    Surcursal(String name, String nick) {
        this.Name = new SimpleStringProperty(name);
        this.Nick = new SimpleStringProperty(nick);
    }

    public String getNick() {
        return Nick.get();
    }

    public void setNick(String nick) {
        this.Nick.set(nick);
    }

    public StringProperty nickProperty() {
        return Nick;
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    public StringProperty nameProperty() {
        return Name;
    }
}
