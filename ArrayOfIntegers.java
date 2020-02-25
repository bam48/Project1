import java.util.*;
public class ArrayOfIntegers {
    public static void main(String[] args){
        getRandomArray(5);
        getSortedArray(5);
    }

    public static void getRandomArray(int n){
        int[] array = new int[n];
        Random random = new Random();
        for(int i = 0; i < n; i++){
            boolean inArray = true;
            while(inArray){
                int next = random.nextInt(n+1);
                if(inArray(next, array)){
                    continue;
                }
                array[i] = next;
                inArray = false;
            }
        }

        for(int num: array){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void getSortedArray(int n){
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = n - i;
        }
        for(int num: array){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static boolean inArray(int value, int[] array){
        for(int num: array){
            if(num == value){
                return true;
            }
        }
        return false;
    }
}
