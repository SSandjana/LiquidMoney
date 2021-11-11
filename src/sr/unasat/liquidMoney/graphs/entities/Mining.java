package sr.unasat.liquidMoney.graphs.entities;

import java.time.LocalDate;

public class Mining {

    private int source;
    private int amount;
    private String refinery;
    private LocalDate date;

    public Mining(int source, int amount, String refinery, LocalDate date) {
        this.source = source;
        this.amount = amount;
        this.refinery = refinery;
        this.date = date;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRefinery() {
        return refinery;
    }

    public void setRefinery(String refinery) {
        this.refinery = refinery;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Bron " + source + "\n" +
                "Amount: " + amount + "\n";
    }
}
