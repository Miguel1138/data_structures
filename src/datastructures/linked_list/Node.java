package datastructures.linked_list;

public class Node {
        protected int value;
        protected Node next;

        Node(int value) {
            this.value = value;
        }

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

}
