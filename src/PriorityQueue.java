import java.util.Arrays;

public class PriorityQueue {
    int size = 5;
    int[] items = new int[size];
    int count = 0;

    public void insert(int item) {
        if (count ==  items.length) {
            resize();
            shiftAndReplace(item);
        }
        else if(isEmpty()) {
            items[0] = item;
        }
        else {
            shiftAndReplace(item);
        }
        count++;
    }

    public int remove() {
        if (count == 0) throw new IllegalStateException();
        return items[--count];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void print() {
        System.out.println(Arrays.toString(items));
    }

    public void shiftAndReplace(int item) {
        int i;
        for(i = count - 1; i >= 0; i--) {
            if (items[i] > item) {
                items[i + 1] = items[i];
            }
            else {
                break;
            }
        }
        items[i + 1] = item;
    }

    private void resize() {
        size = size * 2;
        int[] newArr = new int[size];
        for (int i = 0; i < items.length; i++) {
            newArr[i] = items[i];
        }
        items = newArr;
    }

}
