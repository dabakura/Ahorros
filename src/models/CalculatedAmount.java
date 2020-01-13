package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CalculatedAmount {
    private IntegerProperty capital;
    private DoubleProperty interest;
    private IntegerProperty months;
    private DoubleProperty total;
    private DoubleProperty capitalizable;
    private DoubleProperty iva;

    public CalculatedAmount() {
        this.capital = new SimpleIntegerProperty(0);
        this.interest = new SimpleDoubleProperty(0.0);
        this.months = new SimpleIntegerProperty(0);
        this.total = new SimpleDoubleProperty(0.0);
        this.capitalizable = new SimpleDoubleProperty(0.0);
        this.iva = new SimpleDoubleProperty(7.0);
    }

    public double getCapitalizable() {
        return capitalizable.get();
    }

    public DoubleProperty capitalizableProperty() {
        return capitalizable;
    }

    public void setCapitalizable(double capitalizable) {
        this.capitalizable.set(capitalizable);
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public int getMonths() {
        return months.get();
    }

    public IntegerProperty monthsProperty() {
        return months;
    }

    public void setMonths(int months) {
        this.months.set(months);
    }

    public double getInterest() {
        return interest.get();
    }

    public DoubleProperty interestProperty() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest.set(interest);
    }

    public int getCapital() {
        return capital.get();
    }

    public IntegerProperty capitalProperty() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital.set(capital);
    }

    public double getIva() {
        return iva.get();
    }

    public DoubleProperty ivaProperty() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva.set(iva);
    }
}
