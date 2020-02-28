public class ConstructingTrees {
    public static void main(String[] args){
        ArrayOfIntegers aoi = new ArrayOfIntegers();
        BSTRec bstRec = new BSTRec();
        AVLIter avlIter = new AVLIter();
        BSTIter bstIter = new BSTIter();

        int[] arr = aoi.getRandomArray(10000);

        for(int i = 0; i < arr.length; i++){
            int val = arr[i];
            bstRec.insertRec(val);
            avlIter.insertIter(val);
            bstIter.insertIter(val);
        }
        System.out.println("BSTRec Root: " + bstRec.root.value);
        System.out.println("BSTRec Root.left: " + bstRec.root.left.value);
        System.out.println("BSTRec Root.right: " + bstRec.root.right.value);

        System.out.println("AVLIter Root: " + avlIter.root.value);
        System.out.println("AVLIter Root.left: " + avlIter.root.left.value);
        System.out.println("AVLIter Root.right: " + avlIter.root.right.value);

        System.out.println("BSTIter Root: " + bstIter.root.value);
        System.out.println("BSTIter Root.left: " + bstIter.root.left.value);
        System.out.println("BSTIter Root.right: " + bstIter.root.right.value);

    }
}
