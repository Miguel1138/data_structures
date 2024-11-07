package Structures;

public class mQueue {
    private mNode first;
    private mNode last;
    private int length;

    public mQueue(int value) {
        mNode newNode = new mNode(value);
        first = newNode;
        last = newNode;
        length = 1;
    }

    // O(1) complexity
    public void enqueue(int value) {
        mNode newNode = new mNode(value);
        if (length == 0) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    // O(1) complexity
    public mNode dequeue() {
        if(length == 0) return null;
        mNode temp = first;
        if(length == 1) {
            first = null;
            last = null;
        } else first = first.next;

        temp.next = null;
        length--;
        return temp;
    }

}
