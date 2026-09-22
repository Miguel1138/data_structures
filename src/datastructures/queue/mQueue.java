package datastructures.queue;

import datastructures.linked_list.Node;

public class mQueue {
    private Node first;
    private Node last;
    private int length;

    public mQueue(int value) {
        Node newNode = new Node(value);
        first = newNode;
        last = newNode;
        length = 1;
    }

    // O(1) complexity
    public void enqueue(int value) {
        Node newNode = new Node(value);
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
    public Node dequeue() {
        if(length == 0) return null;
        Node temp = first;
        if(length == 1) {
            first = null;
            last = null;
        } else first = first.next;

        temp.next = null;
        length--;
        return temp;
    }

}
