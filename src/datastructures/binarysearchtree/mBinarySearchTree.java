package datastructures.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class mBinarySearchTree {

    private Node root;

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // Added recursive methods
    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node currentNode, int value) {
        if (currentNode == null) return false;

        if (currentNode.value == value) return true;

        if (value < currentNode.value)
            return contains(currentNode.left, value);
        else
            return contains(currentNode.right, value);
    }

    public void insert(int value) {
        if (root == null) root = new Node(value);
        insert(root, value);
    }

    private Node insert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);

        if (value < currentNode.value) {
            currentNode.left = insert(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = insert(currentNode.right, value);
        }

        return currentNode;
    }

    private Node deleteNode(Node currentNode, int value) {
        // base case
        if (currentNode == null) return null;

        if (value < currentNode.value) {
            currentNode.left = deleteNode(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else {
            // Leaf node
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            }
            // Has left node
            else if (currentNode.right == null) {
                currentNode = currentNode.left;
            }
            // has right node
            else if (currentNode.left == null) {
                currentNode = currentNode.right;
            } else {
                // has right and left node.
                // It will find the minimum value in the child right node, and rearrange the BST.
                int subTreeMin = minValue(currentNode.right);
                currentNode.value = subTreeMin;
                currentNode.right = deleteNode(currentNode.right, subTreeMin);
            }
        }

        return currentNode;
    }

    private int minValue(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    public void deleteNode(int value) {
        deleteNode(root, value);
    }

    // LeetCode exercise

    private Node sortedArrayToBST(int[] nums, int left, int right) {
        // base Case
        // Meaning there is no Node to return.
        if (left > right) return null;

        int mid = left + (right - left) / 2;
        Node bst = new Node(nums[mid]);

        bst.left = sortedArrayToBST(nums, left, mid - 1);
        bst.right = sortedArrayToBST(nums, mid + 1, right);

        return bst;
    }

    private Node invertTree(Node node) {
        if (node == null) return null;

        Node temp = node.left;
        node.left = node.right;
        node.right = temp;

        invertTree(node.left);
        invertTree(node.right);

        return node;
    }

    // Adding Tree Traversal methods
    public ArrayList<Integer> BreadthFirstTraversal() {
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> results = new ArrayList<>();
        queue.add(currentNode);

        while (queue.size() > 0) {
            currentNode = queue.remove();
            results.add(currentNode.value);

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }

        return results;
    }

    // Method that runs trough the binary search tree, always looking first at the minimum left.
    public ArrayList<Integer> DepthFirstTraversalPreOrder() {
        ArrayList<Integer> results = new ArrayList<>();

        // Since Java doesn't have a "method inside a method" feature, we construct a inner class inside with the proper method
        // we wanted to use.
        class Traverse {
            Traverse(Node currentNode) {
                results.add(currentNode.value);
                if (currentNode.left != null) new Traverse(currentNode.left);
                if (currentNode.right != null) new Traverse(currentNode.right);
            }
        }

        new Traverse(root);
        return results;
    }

    public ArrayList<Integer> DFSPostOrder() {
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left != null) new Traverse(currentNode.left);
                if (currentNode.right != null) new Traverse(currentNode.right);
                results.add(currentNode.value);
            }
        }
        new Traverse(root);
        return results;
    }

    public ArrayList<Integer> DFSInOrder() {
        ArrayList<Integer> results = new ArrayList<>();
        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left != null)
                    new Traverse(currentNode.left);

                results.add(currentNode.value);

                if (currentNode.right != null)
                    new Traverse(currentNode.right);
            }
        }
        return results;
    }

    // LeetCode TraversalTree
    public boolean isValidBST() {
        ArrayList<Integer> bstInOrder = DFSInOrder();
        for (int i = 1; i <= bstInOrder.size(); i++) {
            if (bstInOrder.get(i - 1) > bstInOrder.get(i)) {
                return false;
            }
        }

        return true;
    }

    public Integer kthSmallest(int value) {
        Stack<Node> stack = new Stack<>();
        Node currentNode = root;

        while (!stack.isEmpty() || currentNode != null) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            value -= 1;
            if (value == 0) {
                return currentNode.value;
            }
            currentNode = currentNode.right;
        }

        return null;
    }

}
