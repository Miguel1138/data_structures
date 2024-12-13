package datastructures.binarysearchtree;

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
        if(currentNode == null) return null;

        if (value < currentNode.value) {
            currentNode.left = deleteNode(currentNode.left, value);
        } else if(value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else {
            // Leaf node
            if(currentNode.left == null && currentNode.right == null) {
                return null;
            }
            // Has left node
            else if(currentNode.right == null) {
                currentNode = currentNode.left;
            }
            // has right node
            else if(currentNode.left == null) {
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
        while(current.left != null) {
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

}
