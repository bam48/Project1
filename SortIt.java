import java.util.Scanner;

public class SortIt {

    public static void main(String[] args){
        //Create Recursive BST from BSTRec
        BSTRec tree = new BSTRec();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of numbers to sort: ");
        int num = scanner.nextInt();
        System.out.println("Enter the numbers to sort: ");
        for(int i = 0; i < num; i++){
            tree.insertRec(scanner.nextInt());
        }
        tree.inorderRec();
    }
}
