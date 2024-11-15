package datastructures;

public class mStack {
    private mNode top;
    private int height;

    mStack(int value) {
        mNode newNode = new mNode(value);
        top = newNode;
        height = 1;
    }

    public void push (int value) {
        mNode newNode = new mNode(value);
        if (height == 0) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        height++;
    }

    public mNode pop() {
        if(height == 0) return null;

        mNode temp = top;
        top = top.next;
        temp.next = null;
        height--;

        return temp;
    }

    public mNode getTop() {
        return top;
    }

    public int getHeight() {
        return height;
    }
}
