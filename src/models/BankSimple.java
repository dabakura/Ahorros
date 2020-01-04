package models;

import java.io.Serializable;

public class BankSimple implements Serializable {
    private String Name;
    private String Nick;

    public BankSimple() {
        this.Name = null;
        this.Nick = null;
    }

    public BankSimple(String name, String nick) {
        this.Name = name;
        this.Nick = nick;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        this.Nick = nick;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
