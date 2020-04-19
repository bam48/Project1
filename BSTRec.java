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

        ArrayList<Integer> out = new ArrayList<Integer>();

        BSTRec tree1 = new BSTRec();
        tree1.root = new BSTRec.Node(2);
        tree1.root = tree1.insertRec(tree1.root, 1);
        tree1.root = tree1.insertRec(tree1.root, 4);
        System.out.println("Tree 1");
        tree1.inorderRec();
        out.addAll(tree1.inorder(tree1.root));

        BSTRec tree2 = new BSTRec();
        tree2.root = new BSTRec.Node(1);
        tree2.root = tree2.insertRec(tree2.root, 0);
        tree2.root = tree2.insertRec(tree2.root, 3);
        System.out.println("Tree 2");
        tree2.inorderRec();
        out.addAll(tree2.inorder(tree2.root));

        System.out.println("Both");
        System.out.println(Arrays.toString(out.toArray()));



    }

    public static boolean isAlmostBst(Node node){
        ArrayList<Integer> ans = inorder(node);
        System.out.println(Arrays.toString(ans.toArray()));
        int error = 0;
        for (int i = 1; i < ans.size(); i++) {
            if(ans.get(i) < ans.get(i - 1)){
                error += 1;
            }
            if(error > 1)
                return false;
        }
        return true;
    }

    public static Node isBST(Node root){
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Node isBSTUtil(Node curr, int min, int max){
        if(curr == null)
            return root;

        if(curr.value < min || curr.value > max)
            return curr;

        isBSTUtil(curr.left, min, curr.value);
        isBSTUtil(curr.right, curr.value, max);

        return root;
    }

    public static void insertRec(int value){
        root = insertRec(root, value);
    }

    public static Node insertRec(Node root, int value){
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

    public static ArrayList<Integer> inorder(Node root){
        ArrayList<Integer> num = new ArrayList<Integer>();
        return inorder(root, num);
    }

    private static ArrayList<Integer> inorder(Node root, ArrayList<Integer> num) {
        if (root != null) {
            inorder(root.left, num);
            num.add(root.value);
            inorder(root.right, num);
        }
        return num;
    }

    //Prints out the nodes of the tree in In Order recursively.
    //Left node until it reaches a leaf, its root, and its right node.
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
