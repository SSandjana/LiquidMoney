package sr.unasat.liquidMoney.graphs.entities;

public class Sales {

    private String country;
    private int orderAmount;

    public Sales(String country, int orderAmount) {
        this.country = country;
        this.orderAmount = orderAmount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "Country: " + country + "\n" +
                "   OrderAmount: " + orderAmount;
    }
}
