import java.util.NoSuchElementException;
import java.util.Stack;

public class StackQueue {

    private final Stack<Integer> stack1 = new Stack<>();
    private final Stack<Integer> stack2 = new Stack<>();

    public void enqueue(int item) {
        stack1.push(item);
    }

    public int dequeue() {
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        else if(stack2.isEmpty() && stack1.isEmpty()) throw new NoSuchElementException();
        return stack2.pop();
    }

    public void print1() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int item : stack1) {
            sb.append(item);
            sb.append(", ");
        }
        String finalString = stack1.isEmpty()? "[]" : sb.substring(0, sb.length() - 2) + "]";
        System.out.println(finalString);
    }

    public void print2() {
        String string = "[";
        for (int item : stack2) {
            string += item + ", ";
        }
        String finalString = stack2.isEmpty()? "[]" : string.substring(0, string.length() - 2) + "]";
        System.out.println(finalString);
    }
}
