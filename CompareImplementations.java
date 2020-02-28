public class CompareImplementations {
    public static void main(String[] args) {
        //compareRandom();
        compareSorted();
    }

    public static void compareRandom(){
        ArrayOfIntegers aoi = new ArrayOfIntegers();
        AVLIter avlIterRand = new AVLIter();
        BSTIter bstIterRand = new BSTIter();

        int[] randomArray = aoi.getRandomArray(10000);

        for(int i = 0; i < 10000; i++) {
            int rand = randomArray[i];
            avlIterRand.insertIter(rand);
            bstIterRand.insertIter(rand);
        }

        System.out.println("BST Counter for Random Array: " + bstIterRand.counter);
        System.out.println("AVL Counter for Random Array: " + avlIterRand.counter);
    }

    public static void compareSorted(){
        ArrayOfIntegers aoi = new ArrayOfIntegers();
        AVLIter avlIterSort = new AVLIter();
        BSTIter bstIterSort = new BSTIter();

        int[] sortedArray = aoi.getSortedArray(10000);

        for(int i = 0; i < 10000; i++){
            int sort = sortedArray[i];
            avlIterSort.insertIter(sort);
            bstIterSort.insertIter(sort);
        }

        System.out.println("BST Counter for Sorted Array: " + bstIterSort.counter);
        System.out.println("AVL Counter for Sorted Array: " + avlIterSort.counter);
    }

}
