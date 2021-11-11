package sr.unasat.liquidMoney.graphs.dataStructures.linkedList;

public class Link {

    public int value;
    public int link;
    public Link next;

    public Link(int value, int link){
        this.value = value;
        this.link = link;
    }

    public void displayLink(){
        System.out.print("{" + value + ", " + link + "}");
    }

}
