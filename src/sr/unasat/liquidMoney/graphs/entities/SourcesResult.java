package sr.unasat.liquidMoney.graphs.entities;

public class SourcesResult {

    private int source;
    private int amount;

    public SourcesResult(int source, int amount) {
        this.source = source;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Bron: " + source + "\n" +
                "   Amount:" + amount;
    }
}
