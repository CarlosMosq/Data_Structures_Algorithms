import java.util.Arrays;
import java.util.Queue;

public class Stack {
    private int size = 5;
    private int count = 0;
    private int[] stackArr = new int[size];

    public void push(int item) {
        if(isFull()) {
            resize();
        }
        stackArr[count] = item;
        count++;
    }

    public int pop() {
        if (!isEmpty()) {
            int limiter = count;
            int[] newArr = new int[size];
            for (int i = 0; i < limiter; i++) {
                newArr[i] = stackArr[i];
            }
            int end = stackArr[count - 1];
            count--;
            stackArr = newArr;
            return end;
        }
        else {
            throw new IllegalStateException();
        }
    }

    public String peek() {
        if(!isEmpty()) {
            return String.valueOf(stackArr[count - 1]);
        }
        else {
            throw new IllegalStateException();
        }
    }


    private boolean isFull() {
        return count >= stackArr.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private void resize() {
        size = size * 2;
        int[] newArr = new int[size];
        for (int i = 0; i < stackArr.length; i++) {
            newArr[i] = stackArr[i];
        }
        stackArr = newArr;
    }

    public int getCount() {
        return count;
    }

    public void print() {
        int[] newArr = Arrays.copyOfRange(stackArr, 0, count);
        String string = "[";
        for (int item : newArr) {
            string += item + ", ";
        }
        String finalString = isEmpty()? "[]" : string.substring(0, string.length() - 2) + "]";
        System.out.println(finalString);
    }

    public static void reverse(Queue<Integer> queue) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        while(!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        while(!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }
}
