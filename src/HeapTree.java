public class HeapTree {
    int size = 5;
    int count = 0;
    private int[] tree = new int[size];

    public void insert(int item) {
        if(isFull()) resize();
        tree[count] = item;
        while(isBigger(item)) {
            bubbleUp(item);
        }
        count++;
    }

    public int remove() {
        if(count == 0) throw new IllegalStateException();
        int root = tree[0];
        if(count == 1) {
            tree[0] = 0;
            count = 0;
            return root;
        }
        int lastItem = tree[count - 1];
        tree[0] = lastItem;
        tree[count - 1] = 0;
        while(isSmaller(lastItem)) bubbleDown(lastItem);
        count--;
        return root;
    }

    private boolean isBigger(int item) {
        int index = indexOf(item);
        return index > 0 && item > tree[(index - 1) / 2];
    }

    private boolean isSmaller(int item) {
        int index = indexOf(item);
        return index <= (count / 2) && (item < tree[(index * 2) + 1] || item < tree[(index * 2) + 2]);
    }

    private void bubbleUp(int item) {
        int index = indexOf(item);
        int temp = tree[(index - 1)/2];
        tree[(index - 1)/2] = item;
        tree[index] = temp;
    }

    private void bubbleDown(int item) {
        int index = indexOf(item);
        int max = indexOf(Math.max(tree[(index * 2) + 1], tree[(index * 2) + 2]));
        int temp = tree[max];
        tree[max] = item;
        tree[index] = temp;
    }

    public int indexOf(int item) {
        for(int i = 0; i < count; i++) {
            if(item == tree[i]) return i;
        }
        return -1;
    }

    private boolean isFull() {
        return tree.length == count;
    }

    private void resize() {
        size *= 2;
        int[] newArr = new int[size];
        System.arraycopy(tree, 0, newArr, 0, tree.length);
        tree = newArr;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void print() {
        String printedArr = "[";

        for (int j : tree) {
            printedArr += j + ", ";
        }
        String finalArr = count == 0? "[]" : printedArr.substring(0, printedArr.length() - 2) + "]";
        System.out.println(finalArr);
    }

    public static int findKthLargest(int[] arr, int level) {
        return heapify(arr)[level - 1];
    }

    public static int[] heapify(int[] numbers) {
        for(int i = 0; i < (numbers.length / 2) - 1; i++) {
            int maxIndex = numbers[(i * 2) + 1] > numbers[(i * 2) + 2]? (i * 2) + 1 : (i * 2) + 2;
            int temp = numbers[maxIndex];
            numbers[maxIndex] = numbers[i];
            numbers[i] = temp;
        }
        return numbers;
    }

}
