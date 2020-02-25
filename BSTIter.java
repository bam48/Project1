import java.util.*;
public class BSTIter {
    static Node root;

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
        System.out.println("Iterative BST Functions");
        insertIter(5);
        insertIter(3);
        insertIter(2);
        insertIter(4);
        insertIter(7);;
        insertIter(8);
        insertIter(6);

        //inOrderRec() not used for functionality. Only for testing code. Borrowed from BSTRec
        inorderRec();
        deleteIter(7);
        inorderRec();

        deleteIter(3);
        inorderRec();
        System.out.println("Prev for 5: " + findPrevIter(5).value);
        System.out.println("Next for 5: " + findNextIter(5).value);
        System.out.println("Min: " + findMinIter().value);
        System.out.println("Max: " + findMaxIter().value);

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
        }
        else{
            insertAfter.right = node;
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

    //For testing purposes. Not called in Iter functions
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
