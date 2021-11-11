package sr.unasat.liquidMoney.graphs.dataStructures.linkedList;

public class SimpleLinkedList {

    private Link first;

    public SimpleLinkedList(){
        first = null;
    }

    public boolean isEmpty(){
        return (first==null);
    }

    public void insertFirst(int value, int link){
        Link newLink = new Link(value, link);
        newLink.next = first;
        first = newLink;
    }

    public Link deleteFirst() {
        Link temporary = first;
        first = first.next;
        return temporary;
    }

    public Link find(int key){
        Link current = first;
        while(current.value != key) {
            if(current.next == null) {
                return null;
            }else {
                current = current.next;
            }
        }
        return current;
    }

    public Link delete(int key) {
        Link current = first;
        Link previous = first;
        while(current.value != key) {
            if(current.next == null) {
                return null;
            }else {
                previous = current;
                current = current.next;
            }
        }
        if(current == first)
            first = first.next;
        else
            previous.next = current.next;
        return current;
    }

    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first;
        while(current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println(" ");
    }


}
