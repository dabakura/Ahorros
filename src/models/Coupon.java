package models;

import javafx.beans.property.*;


public class Coupon {
    private StringProperty bank;
    private IntegerProperty monthsCollect;
    private StringProperty startDate;
    private IntegerProperty numberCoupons;
    private DoubleProperty amount;
    private IntegerProperty month;
    private IntegerProperty day;
    private IntegerProperty capital;

    public Coupon() {
        this.bank = new SimpleStringProperty();
        this.monthsCollect = new SimpleIntegerProperty(1);
        this.startDate = new SimpleStringProperty();
        this.numberCoupons = new SimpleIntegerProperty(12);
        this.amount = new SimpleDoubleProperty(0.0);
        this.capital = new SimpleIntegerProperty(0);
        this.month = new SimpleIntegerProperty();
        this.day = new SimpleIntegerProperty();
    }

    public Coupon(CouponSimple cs) {
        this.bank = new SimpleStringProperty(cs.getBank());
        this.monthsCollect = new SimpleIntegerProperty(cs.getMonthsCollect());
        this.startDate = new SimpleStringProperty(cs.getStartDate());
        this.numberCoupons = new SimpleIntegerProperty(cs.getNumberCoupons());
        this.amount = new SimpleDoubleProperty(cs.getAmount());
        this.capital = new SimpleIntegerProperty(cs.getCapital());
        this.month = new SimpleIntegerProperty(cs.getMonth());
        this.day = new SimpleIntegerProperty(cs.getDay());
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
        String[] date = startDate.split("-");
        this.day.setValue(Integer.valueOf(date[0]));
        this.month.setValue(Integer.valueOf(date[1]));
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

    public int getMonth() {
        return month.get();
    }

    public int getDay() {
        return day.get();
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
}
