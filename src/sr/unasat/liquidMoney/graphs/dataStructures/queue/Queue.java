package sr.unasat.liquidMoney.graphs.dataStructures.queue;

public class Queue {

    private final int SIZE = 20;
    private final int[] queArray;
    private int front;
    private int rear;

    public Queue(){
        queArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int input){
        if(rear == SIZE-1)
            rear = -1;
        queArray[++rear] = input;
    }

    public int remove(){
        int temporary = queArray[front++];
        if(front == SIZE)
            front = 0;
        return temporary;
    }

    public int size(){
        return queArray.length;
    }

    public boolean isEmpty(){
        return ( rear+1==front || (front+SIZE-1==rear) );
    }


}
