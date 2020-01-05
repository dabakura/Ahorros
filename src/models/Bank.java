package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.List;

public class Bank {
    private final StringProperty Name;
    private final StringProperty Nick;
    private List<IBanksObserver> observers;

    public Bank() {
        this.Name = new SimpleStringProperty(null);
        this.Nick = new SimpleStringProperty(null);
    }

    public Bank(String name, String nick) {
        this.Name = new SimpleStringProperty(name);
        this.Nick = new SimpleStringProperty(nick);
    }

    public String getNick() {
        return Nick.get();
    }

    public void setNick(String nick) {
        if(this.observers!=null)
            this.observers.forEach(IBanksObserver::BankListener);
        this.Nick.set(nick);
    }

    public StringProperty nickProperty() {
        return Nick;
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String name) {
        if(this.observers!=null)
            this.observers.forEach(IBanksObserver::BankListener);
        this.Name.set(name);
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public void setObservers(List<IBanksObserver> observers) {
        this.observers = observers;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bank)) return false;
        return this.getName().equals(((Bank) obj).getName());
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
