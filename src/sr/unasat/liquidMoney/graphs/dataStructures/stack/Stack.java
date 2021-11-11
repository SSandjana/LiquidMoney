package sr.unasat.liquidMoney.graphs.dataStructures.stack;

public class Stack {

    private final int SIZE;
    private final int[] array;
    private int top;

    public Stack(int SIZE) {
        this.SIZE = SIZE;
        array = new int[SIZE];
        top = -1;
    }

    public void push(int input){
        array[++top] = input;
    }

    public int pop(){
        return array[top--];
    }

    public int peek(){
        return array[top];
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public int size(){
        return top+1;
    }

    public int get(int index){
        return array[index];
    }

    public void displayStack() {
        System.out.print("Stack: ");
        for (int index = 0; index < size(); index++) {
            System.out.print(get(index));
            System.out.print(" ");
        }
        System.out.println(" ");
    }

}
