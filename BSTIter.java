import java.util.*;
public class BSTIter {
    static Node root;
    static int counter;

    static class Node{
        int value;
        Node left;
        Node right;

        Node(int val){
            value = val;
            left = null;
            right = null;
        }

    }

    public static void main(String[] args){
        BSTIter tree1 = new BSTIter();
        tree1.root = new Node(2);
        tree1.root = tree1.insertIter(tree1.root, 1);
        tree1.root = tree1.insertIter(tree1.root, 4);
        tree1.inorderRec();

        BSTIter tree2 = new BSTIter();
        tree2.root = new Node(1);
        tree2.root = tree2.insertIter(tree2.root, 0);
        tree2.root = tree2.insertIter(tree2.root, 3);
        tree2.inorderRec();

        System.out.println(tree1.root.value);
        System.out.println(tree2.root.value);

        tree1.inorderRec();
        tree2.inorderRec();


    }

    public static void insertIter(int value){
        root = insertIter(root, value);
    }

    private static Node insertIter(Node root, int value){
        Node node = new Node(value);
        if(root == null) {
            root = node;
            return root;
        }
        Node currentRoot = root;
        Node insertAfter = null;
        while(currentRoot != null){
            counter++;
            insertAfter = currentRoot;
            if(value < currentRoot.value){
                currentRoot = currentRoot.left;
            }
            else{
                currentRoot = currentRoot.right;
            }
        }
        if(value < insertAfter.value){
            insertAfter.left = node;
            counter++;
        }
        else{
            insertAfter.right = node;
            counter++;
        }
        return root;
    }

    public static void deleteIter(int value){
        root = deleteIter(root, value);
    }

    private static Node deleteIter(Node root, int value) {
        Node nodeToDelete = search(value);
        Node parent = getParent(value);

        if(parent == null){
            return delete(nodeToDelete);
        }
        else if(parent.left == nodeToDelete){
            parent.left = delete(nodeToDelete);
        }
        else if(parent.right == nodeToDelete){
            parent.right = delete(nodeToDelete);
        }
        return root;

    }

    private static Node delete(Node root){
        if(root == null || (root.left == null && root.right == null)){
            return null;
        }
        else if(root.left == null){
            return root.right;
        }
        else if(root.right == null){
            return root.left;
        }

        Node nodeToDelete = findNextIter(root.value);
        Node parent = getParent(nodeToDelete.value);
        root.value = nodeToDelete.value;
        if(parent.left == nodeToDelete){
            parent.left = nodeToDelete.right;
        }
        else{
            parent.right = nodeToDelete.right;
        }
        return root;
    }

    public static Node getParent(int value){
        return getParent(root, value);
    }

    private static Node getParent(Node node, int value) {
        Node parent = null;
        while(node != null){
            if(value < node.value){
                parent = node;
                node = node.left;
            }
            else if(value > node.value){
                parent = node;
                node = node.right;
            }
            else{
                break;
            }
        }
        return parent;
    }


    public static Node findNextIter(int value){
        Node node = search(value);
        if(node.right != null){
            return findMinIter(node.right);
        }
        return findNextIter(root, value);
    }

    private static Node findNextIter(Node root, int value){
        Node next = null;
        while(root != null){
            if(value < root.value){
                next = root;
                root = root.left;
            }
            else if(value > root.value){
                root = root.right;
            }
            else {
                break;
            }
        }
        return next;
    }

    public static Node findPrevIter(int value){
        Node node = search(value);
        if(node.left != null){
            return findMaxIter(node.left);
        }
        return findprevIter(root, value);
    }

    private static Node findprevIter(Node root, int value) {
        Node prev = null;
        while(root != null){
            if(value < root.value){
                root = root.left;
            }
            else if(value > root.value){
                prev = root;
                root = root.right;
            }
            break;
        }
        return prev;
    }

    public static Node findMaxIter(){
        return findMaxIter(root);
    }

    private static Node findMaxIter(Node root) {
        if(root == null){
            return null;
        }

        Node current = root;
        while(current.right != null){
            current = current.right;
        }
        return current;
    }

    public static Node findMinIter(){
        return findMinIter(root);
    }

    private static Node findMinIter(Node root) {
        if(root == null){
            return null;
        }

        Node current = root;
        while(current.left != null){
            current = current.left;
        }
        return current;
    }

    public static Node search(int value){
        return search(root, value);
    }

    private static Node search(Node root, int value){
        if(root == null || root.value == value){
            return root;
        }
        Node current = root;
        while(current != null) {
            if (value < current.value) {
                current = current.left;
            }
            else if(value > current.value){
                current = current.right;
            }
            else{
                break;
            }
        }
        return current;
    }

    public static void inorderRec(){
        inorderRec(root);
        System.out.println();
    }

    private static void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.value + " ");
            inorderRec(root.right);
        }
    }
}
