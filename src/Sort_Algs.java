import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort_Algs {


    public void bucketSort(int[] array, int nbrOfBuckets) {
        List<List<Integer>> buckets = new ArrayList<>();

        for(int i = 0; i < nbrOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        for(int item : array) {
            buckets.get(item / nbrOfBuckets).add(item);
        }

        int i = 0;
        for(var bucket : buckets) {
            Collections.sort(bucket);
            for(var item: bucket) {
                array[i++] = item;
            }
        }

    }

    public void countingSort(int[] array, int max) {
        int[] counter = new int[max + 1];
        for(int nbr : array) {
            counter[nbr]++;
        }
        int index = 0;
        for(int i = 0; i < counter.length; i++) {
            for(int j = 0; j < counter[i]; j++) {
                array[index++] = i;
            }
        }
    }




    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end) {
        if(start >= end) return;

        var b = partition(array, start, end);

        quickSort(array, start, b - 1);
        quickSort(array, b + 1, end);
    }

    private int partition(int[] array, int start, int end) {
        int b = start - 1, pivot = array[end];
        for(int i = start; i <= end; i++) {
            if(array[i] <= pivot)
                swap(array, i, ++b);
        }
        return b;
    }

    private void swap(int[] array, int index1, int index2) {
        var temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    public void mergeSort(int[] array) {
        if(array.length < 2) return;

        int middle = array.length / 2;
        int[] left = new int[middle];
        for(int i = 0; i < middle; i++) {
            left[i] = array[i];
        }

        int[] right = new int[array.length - middle];
        for(int i = middle; i < array.length; i++) {
            right[i - middle] = array[i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(left, right, array);
    }

    private int[] merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j])
                result[k++] = left[i++];
            else
                result[k++] = right[j++];
        }

        while(i < left.length) {
            result[k++] = left[i++];
        }
        while(j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    public int[] insertionSort(int[] array) {
        if(array == null || array.length == 1) return array;

        for(int i = 0; i < array.length - 1; i++) {
            for(int j = i + 1; j > 0; j--) {
                while(array[j] < array[j - 1]){
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }

    public int[] selectionSort(int[] array) {
        if(array == null || array.length == 1) return array;

        int[] newArr = Arrays.copyOfRange(array, 0, array.length);

        for(int i = 0; i < array.length; i++) {
            int minIndex = getMinIndex(newArr);
            int min = newArr[minIndex];
            if(newArr[0] > min) {
                int temp = newArr[0];
                newArr[0] = min;
                newArr[minIndex] = temp;
            }
            array[i] = min;
            newArr = Arrays.copyOfRange(newArr, 1, newArr.length);
        }
        return array;
    }

    private int getMinIndex(int[] array) {
        int min = array[0];
        int index = 0;
        for(int i = 1; i < array.length; i++) {
            if(array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }






    public int[] bubbleSort(int[] array) {
        if(array == null || array.length == 1) return array;

        int comparisons = 0;

        for(int i = 0; i < array.length - 1; i++) {
            int temp;
            if(array[i] > array[i + 1]) {
                temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                comparisons++;
            }
        }

        if(comparisons > 0) {
            bubbleSort(array);
        }

        return array;
    }

}
