import java.util.Stack;

class StringReverser {

    public String reverse(String original) {
        if(original == null) throw new IllegalArgumentException();

        Stack<String> stack = new Stack<>();
        String[] split = original.split("");
        StringBuilder toReturn = new StringBuilder();
        for (String item: split) {
            stack.push(item);
        }
        while( !stack.empty() ) {
            toReturn.append(stack.pop());
        }
        return toReturn.toString();
    }


    public boolean evaluateExp(String expression) {
        Stack<String> stack = new Stack<>();
        String[] split = expression.split("");

        for(String letter : split) {
            if(letter.equals("(") || letter.equals("[") || letter.equals("<") || letter.equals("{")) {
                stack.push(letter);
            }
            else if(!stack.empty() && letter.equals(")") && stack.peek().equals("(") ){
                stack.pop();
            }
            else if(!stack.empty() && letter.equals("]") && stack.peek().equals("[") ){
                stack.pop();
            }
            else if(!stack.empty() && letter.equals(">") && stack.peek().equals("<") ){
                stack.pop();
            }
            else if(!stack.empty() && letter.equals("}") && stack.peek().equals("{") ){
                stack.pop();
            }
            else{
                System.out.println(letter);
            }
        }
        return stack.empty();
    }
}
