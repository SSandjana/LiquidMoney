package sr.unasat.liquidMoney.graphs.entities;

import java.time.LocalDate;
import java.util.Date;

public class Order {

    private String country;
    private int amount;
    private String unit;
    private LocalDate date;

    public Order(String country, int amount, String unit, LocalDate date) {
        this.country = country;
        this.amount = amount;
        this.unit = unit;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Country: " + country + "\n" +
                "Amount: " + amount + unit + "\n" +
                "Date: " + date;
    }
}
