import java.util.Scanner;

class Node {
    int data;
    Node leftChild, rightChild;

    Node(int data) {
        this.data = data;
        leftChild = rightChild = null;
    }
}

public class BinaryTree {
    Node root;

    // Add Node in Recursive
    public void addNode(int data) {
        root = addRecursive(root, data);
    }

    private Node addRecursive(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }

        if (data < current.data) {
            current.leftChild = addRecursive(current.leftChild, data);
        } else if (data > current.data) {
            current.rightChild = addRecursive(current.rightChild, data);
        }

        return current;
    }

    /*
    the minimum depth is the number of nodes on the shortest path from the root
    down to a leaf. the part to be careful with is a node that has only one child,
    the missing side is not a leaf so it can't be counted as a path of length 0.
    in that case the only way down is through the child that exists.
    so: an empty tree is 0, a leaf is 1, a node with one child is 1 + the depth of
    that child, and a node with two children is 1 + the smaller of the two depths.
    eg. 1 2 3 4 5 builds a chain going right, every node has one child, so the
    answer is 5 and not 1.
    every node is visited once so it is O(n).
    */
    public int minDepth(Node root) {
        if(root == null) return 0; // empty tree
        if(root.leftChild == null && root.rightChild == null) return 1; // base case, a leaf

        if(root.leftChild == null) return minDepth(root.rightChild) + 1; // only a right child, have to go that way
        if(root.rightChild == null) return minDepth(root.leftChild) + 1; // only a left child

        int left = minDepth(root.leftChild);
        int right = minDepth(root.rightChild);

        if(left < right) return left + 1;
        else return right + 1;
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int data = sc.nextInt();
            tree.addNode(data);
        }
        sc.close();

        int depth = tree.minDepth(tree.root);
        System.out.println("Minimum Depth of Binary Tree is " + depth);

    }
}
