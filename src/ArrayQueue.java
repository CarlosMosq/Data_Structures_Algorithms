import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue {
    private int size = 5;
    private int[] queueArr = new int[size];
    private int visible = 0;
    private int total = 0;
    private int front = queueArr[0];;
    private int rear;

    public void enqueue(int item) {
        if(isFull()) {
            resize();
        }
        queueArr[total] = item;
        visible++;
        total++;
        front = queueArr[total - visible];
        rear = queueArr[total - 1];
    }

    public int dequeue() {
        int temp;
        if(isEmpty()) throw new NoSuchElementException();
        visible--;
        temp = front = queueArr[total - visible];
        return temp;
    }

    public int peek() {
        return front;
    }

    public boolean isEmpty() {
        return visible == 0;
    }

    public boolean isFull() {
        return visible == size;
    }

    private void resize() {
        size = size * 2;
        int[] newArr = new int[size];
        for (int i = 0; i < queueArr.length; i++) {
            newArr[i] = queueArr[i];
        }
        queueArr = newArr;
    }

    public void print() {
        int[] newArr = Arrays.copyOfRange(queueArr, indexOf(front), indexOf(front) + visible);
        String string = "[";
        for (int item : newArr) {
            string += item + ", ";
        }
        String finalString = isEmpty()? "[]" : string.substring(0, string.length() - 2) + "]";
        System.out.println(finalString);
    }

    private int indexOf(int item) {
        for (int i = 0; i < queueArr.length; i++) {
            if (item == queueArr[i]) return i;
        }
        return -1;
    }



}
