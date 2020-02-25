import java.util.*;
public class BSTRec {
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
        System.out.println("Recursive BST Functions");
        insertRec(10);
        insertRec(8);
        insertRec(12);
        insertRec(9);
        insertRec(11);
        insertRec(13);
        inorderRec();
        deleteRec(10);
        inorderRec();

        System.out.println("Find Prev 9: " + findPrevRec(9).value);
        System.out.println("Find Next 9: " + findNextRec(9).value);
        deleteRec(9);
        inorderRec();
        System.out.println("Min: " + findMinRec().value);
        System.out.println("Max: " + findMaxRec().value);


    }

    public static void insertRec(int value){
        root = insertRec(root, value);
    }

    private static Node insertRec(Node root, int value){
        if(root == null) {
            root = new Node(value);
            return root;
        }

        if(value < root.value){
            root.left = insertRec(root.left, value);
        }
        else{
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    public static void deleteRec(int value){
        root = deleteRec(root, value);
    }

    private static Node deleteRec(Node root, int value) {
        if(root == null){
            return null;
        }

        if(value < root.value){
            root.left = deleteRec(root.left, value);
        }
        else if(value > root.value){
            root.right = deleteRec(root.right, value);
        }
        else{
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }

            root.value = findNextRec(root.value).value;
            root.right = deleteRec(root.right, root.value);
        }
        return root;
    }

    public static Node findNextRec(int value){
        //Node node = search(value);
        return findNextRec(root, value, null);
    }

    private static Node findNextRec(Node root, int value, Node next){
       if(root == null){
           return null;
       }
       if(root.value == value){
           if(root.right != null){
               return findMinRec(root.right);
           }
           else{
               return next;
           }
       }
       if(value < root.value){
           return findNextRec(root.left, value, root);
       }
       else{
           return findNextRec(root.right, value, next);
       }
    }

    public static Node findPrevRec(int value){
        //Node node = search(value);
        return findPrevRec(root, value, null);
    }

    private static Node findPrevRec(Node root, int value, Node prev){
        if(root == null){
            return null;
        }
        if(root.value == value){
            if(root.left != null){
                return findMaxRec(root.left);
            }
            else{
                return prev;
            }
        }
        if(value < root.value){
            return findPrevRec(root.left, value, prev);
        }
        else {
            return findPrevRec(root.right, value, root);
        }
    }

    public static Node findMaxRec(){
        return findMaxRec(root);
    }

    private static Node findMaxRec(Node root){
        if(root == null){
            return null;
        }

        if(root.right != null){
            return findMaxRec(root.right);
        }
        return root;
    }

    public static Node findMinRec(){
        return findMinRec(root);
    }

    private static Node findMinRec(Node root) {
        if(root == null){
            return null;
        }

        if(root.left != null){
            return findMinRec(root.left);
        }
        return root;
    }

    public static Node search(int value){
        return search(root, value);
    }

    private static Node search(Node root, int value){
        if(root == null || root.value == value){
            return root;
        }

        if(value < root.value){
            return search(root.left, value);
        }
        else{
            return search(root.right, value);
        }
    }

    //For testing purposes
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
