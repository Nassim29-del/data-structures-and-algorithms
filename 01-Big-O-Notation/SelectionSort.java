import java.lang.reflect.Array;
import java.util.Arrays;

public class SelectionSort {

    private SelectionSort(){
    }

    public static long  selectSort(int[] data){
        long iterations = 0;

        for (int i = 0; i < data.length; i++){
            int smallest = i;

            for ( int index  = i + 1; index < data.length; index++){
                iterations++;
                if (data[index] < data[smallest]){
                    smallest = index;
                }
            }

            swap(data, i , smallest);
        }
        return iterations;
    }

    private static void swap(int[] data, int a , int b){
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void main(String[] args){
        int data[] = {29, 9, 48, 24, 35, 20};

        System.out.println("Before: " + Arrays.toString(data));

        long iterations = selectSort(data);
        System.out.println("After: " + Arrays.toString(data));
    }
}
