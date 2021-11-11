package sr.unasat.liquidMoney.graphs.entities;

public class Pressure {

    private int source;
    private int pressure;

    public Pressure(int source, int pressure) {
        this.source = source;
        this.pressure = pressure;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Bron: " + source + "\n" +
                "   Pressure: " + pressure;
    }
}
