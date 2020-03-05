import java.util.*;
public class AVLIter {
    static Node root;
    static int counter;

    static class Node{
        int value;
        Node left;
        Node right;
        int height;

        Node(int val){
            value = val;
            height = 1;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args){
        System.out.println("AVL Iterative");
        insertIter(10);
        insertIter(5);
        insertIter(15);
        insertIter(4);
        insertIter(3);
        insertIter(16);
        insertIter(2);
        insertIter(1);
        deleteIter(5);

        System.out.println("Root:" + root.value);
        System.out.println("Root.left:" + root.left.value);
        System.out.println("Root.right:" + root.right.value);
        System.out.println("Root.left.left:" + root.left.left.value);
        System.out.println("Root.left.right:" + root.left.right.value);
        System.out.println("Root.right.right:" + root.right.right.value);
        System.out.println("Root.left.right.left:" + root.left.right.left.value);


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
        Stack<Node> path = new Stack<Node>();

        Node currentRoot = root;
        Node insertAfter = null;
        while(currentRoot != null){
            counter++;
            insertAfter = currentRoot;
            path.push(insertAfter);

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
        counter++;
        balanceAfterInsert(path, value);

        return root;
    }

    public static void deleteIter(int value){
        root = deleteIter(root, value);
    }

    private static Node deleteIter(Node root, int value) {
        Node nodeToDelete = root;
        Node parent = null;

        Stack<Node> path = new Stack<Node>();

        while(nodeToDelete != null && nodeToDelete.value != value){
            parent = nodeToDelete;
            path.push(parent);

            if(value < nodeToDelete.value){
                nodeToDelete = nodeToDelete.left;
            }
            else{
                nodeToDelete = nodeToDelete.right;
            }
        }


        if(parent == null){
            return delete(nodeToDelete);
        }
        else if(parent.left == nodeToDelete){
            parent.left = delete(nodeToDelete);
        }
        else if(parent.right == nodeToDelete){
            parent.right = delete(nodeToDelete);
        }

        balanceAfterDelete(path, value);

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

    private static void balanceAfterInsert(Stack<Node> path, int value){
        while(!path.isEmpty()){
            counter++;
            Node curr = path.pop();
            Node parent = getParent(curr.value);

            curr.height = max(getHeight(curr.left), getHeight(curr.right)) + 1;
            int bf = getBalanceFactor(curr);

            if(bf < -1){
                //RL
                if(value < curr.right.value){
                    curr.right = rightRotate(curr.right);

                }
                //RR
                if(parent == null){
                    setRoot(leftRotate(curr));
                }
                else if(parent.left == curr){
                    parent.left = leftRotate(curr);
                }
                else {
                    parent.right = leftRotate(curr);
                }
            }
            else if(bf > 1){
                //LR
                if (value >= curr.left.value) {
                    curr.left = leftRotate(curr.left);
                }
                if(parent == null){
                    setRoot(rightRotate(curr));
                }
                //LL
                else if(parent.left == curr){
                    parent.left = rightRotate(curr);
                }
                else {
                    parent.right = rightRotate(curr);
                }
            }
        }
    }

    private static void balanceAfterDelete(Stack<Node> path, int value){
        while(!path.isEmpty()){
            Node curr = path.pop();
            Node parent = getParent(curr.value);

            curr.height = max(getHeight(curr.left), getHeight(curr.right)) + 1;
            int bf = getBalanceFactor(curr);

            if(bf < -1){
                //RL
                if(getBalanceFactor(curr.right) > 0) {
                    curr.right = rightRotate(curr.right);

                    if(parent.left == curr){
                        parent.left = leftRotate(curr);
                    }
                    else {
                        parent.right = leftRotate(curr);
                    }
                }
                //RR
                else if(getBalanceFactor(curr.right) <= 0) {
                    if (parent.left == curr) {
                        parent.left = leftRotate(curr);
                    }
                    else {
                        parent.right = leftRotate(curr);
                    }
                }
            }
            else if(bf > 1){
                //LL
                if (getBalanceFactor(curr.left) >= 0) {
                    if(parent.left == curr){
                        parent.left = rightRotate(curr);
                    }
                    else {
                        parent.right = rightRotate(curr);
                    }
                }
                //LR
                else if (getBalanceFactor(curr.left) < 0){
                    curr.left = leftRotate(curr.left);
                    if(parent.left == curr){
                        parent.left = rightRotate(curr);
                    }
                    else {
                        parent.right = rightRotate(curr);
                    }
                }
            }
        }
    }

    private static void setRoot(Node node){
        root = node;
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

    private static Node rightRotate(Node root){
        Node newRoot = root.left;
        Node endNode = newRoot.right;

        newRoot.right = root;
        root.left = endNode;

        root.height = max(getHeight(root.left), getHeight(root.right)) + 1;
        newRoot.height = max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    private static Node leftRotate(Node root){
        Node newRoot = root.right;
        Node endNode = newRoot.left;

        newRoot.left = root;
        root.right = endNode;

        root.height = max(getHeight(root.left), getHeight(root.right)) + 1;
        newRoot.height = max(getHeight(newRoot.left), getHeight(newRoot.right)) + 1;

        return newRoot;
    }

    private static int max(int x, int y){

        return (x > y) ? x : y;
    }

    private static int getHeight(Node node){
        if(node != null){
            return node.height;
        }
        return 0;
    }

    private static int getBalanceFactor(Node node){
        if(node != null){
            return getHeight(node.left) - getHeight(node.right);
        }
        return 0;
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


}