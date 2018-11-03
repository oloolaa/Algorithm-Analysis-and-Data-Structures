import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //Build an array.
    private static int[] buildArray(int num){
        int[] res = new int[num + 1];
        res[0] = num;
        for(int i = 1; i <= num; i++)
            res[i] = (int)(Math.random() * 100);
        return res;
    }
    //Traverse an arry.
    private static void traverseArray(int[] array){
        System.out.println(Arrays.toString(array));
    }
    //Heapify an array.
    private static void makeHeap(int[] array){   //Building a max-heap.
        int len = array[0];                     //array[0] saves the amount of the nodes in heap.
        for (int i = len / 2; i > 0; i--){
            int check = i;                      //"check" is the "check"-th node I start checking.
            while (check * 2 <= len){           //Percolate down.
                int swap_idx = 0;
                if ((check * 2 + 1 > len) || (check * 2 + 1 <= len && array[check * 2] >= array[check * 2 + 1]))
                    swap_idx = check * 2;
                else if (check * 2 + 1 <= len && array[check * 2] < array[check * 2 + 1])
                    swap_idx = check * 2 + 1;

                if (array[check] < array[swap_idx]){
                    int tmp = array[check];
                    array[check] = array[swap_idx];
                    array[swap_idx] = tmp;
                    check = swap_idx;
                }
                else
                    break;
            }
        }
    }
    //Insert a new number into an array.
    private static int[] insertNumber(int[] array, int ins){
        int[] res = new int[array.length + 1];
        res[0] = array[0] + 1;
        System.arraycopy(array, 1, res, 1, array.length - 1);
        res[res.length - 1] = ins;
        return res;
    }
    //Delete a number from an array.
    private static int[] deleteNumber(int[] array){
        int[] res = new int[array.length - 1];
        res[0] = array[0] - 1;
        res[1] = array[array.length - 1];
        for (int i = 2; i < res.length; i++)
            res[i] = array[i];
        return res;
    }

    public static void main(String[] args) {
	    //1. Print the array before it is converted to heap.
        System.out.println("1. Print the array before it is converted to heap.");
        int[] array = buildArray(12);
	    traverseArray(array);
        System.out.println();

        //2. Print the heap after it is converted to heap.
        System.out.println("2. Print the heap after it is converted to heap.");
	    makeHeap(array);
        traverseArray(array);
        System.out.println();

        //3. Then insert a new number into heap. and print the array.
        System.out.println("3. Then insert a new number into heap. and print the array.");
        System.out.println("Which number do you want to insert(not index)?");
        Scanner scan = new Scanner(System.in);
        int num = 0;
        if (scan.hasNextInt())
           num = scan.nextInt();
        scan.close();
        array = insertNumber(array, num);
        traverseArray(array);
        System.out.println("Re-heapify...");
        makeHeap(array);
        traverseArray(array);
        System.out.println();

        //4. Then delete a new number in the heap and print the array.
        System.out.println("4. Then delete the root in the heap and print the array.");
        array = deleteNumber(array);
        traverseArray(array);
        System.out.println("Re-heapify...");
        makeHeap(array);
        traverseArray(array);
    }
}
