public class Search_Algs {


    public int exponentialSearch(int[] array, int target) {
        int bound = 1;
        while(bound < array.length && array[bound] < target) bound *= 2;
        return binarySearchRec(array, target, bound / 2, Math.min(bound, array.length - 1));
    }



    public int jumpSearch(int[] array, int target) {
        return jumpSearch(array, target, 0);
    }

    private int jumpSearch(int[] array, int target, int start) {
        if(array.length < 3) {
            return linearSearch(array, target);
        }

        if(start >= array.length) return -1;

        int blockSize = (int) Math.sqrt(array.length);
        int next = (array.length - 1 - start) >= blockSize? start + blockSize : start + (array.length - 1 - start);

        if(start == next && array[start] == target) return start;
        else if(array[next - 1] == target) return next - 1;
        else if(array[next - 1] > target) {
            for(int i = start; i < next; i++) {
                if(array[i] == target) return i;
            }
        }
        return jumpSearch(array, target, start + (next - start));
    }


    public int ternarySearch(int[] array, int target) {
        return ternarySearch(array, target, 0, array.length - 1);
    }

    private int ternarySearch(int[] array, int target, int left, int right) {
        if(right < left) return -1;

        int partitionSize = (right - left) / 3;
        int mid1 = left + partitionSize;
        int mid2 = right - partitionSize;

        if(array[mid1] == target) return mid1;
        else if(array[mid2] == target) return mid2;
        else if(target < array[mid1]) return ternarySearch(array, target, left, mid1 - 1);
        else if(target > array[mid1] && target < array[mid2]) return ternarySearch(array, target, mid1 + 1, mid2 - 1);
        else return ternarySearch(array, target, mid2 + 1, right);
    }


    public int binarySearchRec(int[] array, int target) {
        return binarySearchRec(array, target, 0, array.length - 1);
    }

    private int binarySearchRec(int[] array, int target, int left, int right) {
        if(right < left) return -1;
        int middle = (left + right) / 2;

        if(array[middle] == target) return middle;

        if(array[middle] > target) {
            return binarySearchRec(array, target, left, middle - 1);
        }
        return binarySearchRec(array, target, middle + 1, right);
    }


    public int binarySearch(int[] array, int target) {
        int right = array.length - 1;
        int left = 0;

        while(left <= right) {
            int middle = (right + left) / 2;
            if(array[middle] == target)
                return middle;
            else if (array[middle] > target)
                right = middle - 1;
            else
                left = middle + 1;
        }
        return -1;
    }

    public int linearSearch(int[] array, int target) {
        for(int i = 0; i < array.length; i++) {
            if(target == array[i]) return i;
        }
        return -1;
    }

}
