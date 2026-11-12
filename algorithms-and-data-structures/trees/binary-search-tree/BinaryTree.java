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

    /*
    adds a node by starting at the root and going left when the new value is
    lower and right when it is higher, until an empty spot is found.
    i keep a reference to the parent as i go down so the new node can be attached
    to it once current falls off the tree.
    this is O(h) where h is the height of the tree, so O(log n) when the tree is
    balanced and O(n) in the worst case (values inserted in sorted order).
    */
    public void addNode(int data) {
        Node newNode = new Node(data);

        if(root == null){ // empty tree, the new node becomes the root
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while(current != null){
            parent = current;
            if(data < current.data){
                current = current.leftChild;
            }else if(data > current.data){
                current = current.rightChild;
            }else{
                return; // value is already in the tree, duplicates are ignored
            }
        }

        // current is null here so parent is the node the new one hangs off
        if(data < parent.data){
            parent.leftChild = newNode;
        }else{
            parent.rightChild = newNode;
        }
    }

    /*
    the height of a tree is 1 for the node itself plus the height of whichever of
    its two subtrees is taller, and an empty tree has a height of 0.
    this counts nodes, so a tree with only a root has height 1.
    every node is visited once so it is O(n).
    */
    public int getHeight(Node node) {
        if(node == null) return 0; // base case, an empty subtree adds nothing

        int left = getHeight(node.leftChild);
        int right = getHeight(node.rightChild);

        if(left > right) return left + 1;
        else return right + 1;
    }

    public void traverseInorder(Node localRoot) {
        if(localRoot != null){
            traverseInorder(localRoot.leftChild);  // everything smaller first
            System.out.print(localRoot.data + " ");
            traverseInorder(localRoot.rightChild); // then everything bigger
        }
    }

    public void traversePreorder(Node localRoot) {
        if(localRoot != null){
            System.out.print(localRoot.data + " "); // the root is printed before its children
            traversePreorder(localRoot.leftChild);
            traversePreorder(localRoot.rightChild);
        }
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int data = sc.nextInt();
            tree.addNode(data);
        }
        sc.close();

        int height = tree.getHeight(tree.root);
        System.out.println("The height of the binary tree is: " + height);

        System.out.println("Inorder traversal of the binary tree:");
        tree.traverseInorder(tree.root);
        System.out.println();

        System.out.println("Preorder traversal of the binary tree:");
        tree.traversePreorder(tree.root);
        System.out.println();
    }
}
