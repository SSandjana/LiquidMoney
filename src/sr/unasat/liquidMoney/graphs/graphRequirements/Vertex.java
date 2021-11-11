package sr.unasat.liquidMoney.graphs.graphRequirements;

public class Vertex {

    public String label;
    public boolean visited;

    public Vertex(String label) {
        this.label = label;
        this.visited = false;
    }
}
