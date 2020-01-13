package models;

import javafx.beans.property.*;


public class Coupon {
    private StringProperty bank;
    private IntegerProperty monthsCollect;
    private StringProperty startDate;
    private IntegerProperty numberCoupons;
    private DoubleProperty amount;

    public Coupon() {
        this.bank = new SimpleStringProperty();
        this.monthsCollect = new SimpleIntegerProperty(0);
        this.startDate = new SimpleStringProperty();
        this.numberCoupons = new SimpleIntegerProperty(0);
        this.amount = new SimpleDoubleProperty(0.0);
    }

    public String getBank() {
        return bank.get();
    }

    public StringProperty bankProperty() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank.set(bank);
    }

    public int getMonthsCollect() {
        return monthsCollect.get();
    }

    public IntegerProperty monthsCollectProperty() {
        return monthsCollect;
    }

    public void setMonthsCollect(int monthsCollect) {
        this.monthsCollect.set(monthsCollect);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public int getNumberCoupons() {
        return numberCoupons.get();
    }

    public IntegerProperty numberCouponsProperty() {
        return numberCoupons;
    }

    public void setNumberCoupons(int numberCoupons) {
        this.numberCoupons.set(numberCoupons);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }
}
