package models;

public class CouponSimple {
    private String bank;
    private Integer monthsCollect;
    private String startDate;
    private Integer numberCoupons;
    private Double amount;
    private Integer month;
    private Integer day;
    private Integer capital;

    public CouponSimple(String bank, Integer monthsCollect, String startDate, Integer numberCoupons, Double amount, Integer capital, Integer month, Integer day) {
        this.bank = bank;
        this.monthsCollect = monthsCollect;
        this.startDate = startDate;
        this.numberCoupons = numberCoupons;
        this.amount = amount;
        this.capital = capital;
        this.month = month;
        this.day = day;
    }

    public CouponSimple(Coupon c) {
        this.bank = c.getBank();
        this.monthsCollect = c.getMonthsCollect();
        this.startDate = c.getStartDate();
        this.numberCoupons = c.getNumberCoupons();
        this.amount = c.getAmount();
        this.capital = c.getCapital();
        this.month = c.getMonth();
        this.day = c.getDay();
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getMonthsCollect() {
        return monthsCollect;
    }

    public void setMonthsCollect(Integer monthsCollect) {
        this.monthsCollect = monthsCollect;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getNumberCoupons() {
        return numberCoupons;
    }

    public void setNumberCoupons(Integer numberCoupons) {
        this.numberCoupons = numberCoupons;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

}
