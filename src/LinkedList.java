import java.util.NoSuchElementException;

public class LinkedList {
    private static class Node{
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public void addFirst(int item) {
        var node = new Node(item);
        if (isEmpty()) {
            first = last = node;
        }
        else {
            node.next = first;
            first = node;
        }
        size++;
    }

    public void addLast(int item) {
        var node = new Node(item);
        if (isEmpty()) {
            first = last = node;
        }
        else{
            last.next = node;
            last = node;
        }
        size++;
    }

    public void deleteFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        if(first == last) {
            first = last = null;
            size--;
            return;
        }
        var second = first.next;
        first.next = null;
        first = second;
        size--;
    }

    public void deleteLast() {
        if(isEmpty()) throw new NoSuchElementException();
        if(first == last) {
            first = last = null;
            size--;
            return;
        }

        var current = first;
        while(current != null){
            if (current.next == last){
                last = current;
                last.next = null;
            }
            current = current.next;
            size--;
        }

    }

    public boolean contains(int item) {
        var current = first;
        while (current !=null) {
            if(current.value == item) return true;
            current = current.next;
        }
        return false;
    }

    public int indexOf(int item) {
        int index = 0;
        var current = first;
        while(current != null) {
            if (current.value == item) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public void print() {
        String string = "[";
        var current = first;
        while(current != null) {
            string += current.value + ", ";
            current = current.next;
        }
        String finalString = first == null? "[]" : string.substring(0, string.length() - 2) + "]";
        System.out.println(finalString);
    }

    private boolean isEmpty(){
        return first == null;
    }

    public int size() {
        return size;
    }

    public void reverse() {
        if (isEmpty()) return;
        var movingItem = first;
        Node placeHolder;
        while (movingItem.next != null){
            placeHolder = movingItem.next;
            movingItem.next = placeHolder.next;
            placeHolder.next = first;
            first = placeHolder;
        }
    }

    public Node getPrevious(Node node) {
        var current = first;
        while (current != null) {
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }

    public int findKthFromEnd(int Kth) {
        if(Kth > size) throw new NoSuchElementException();
        if(Kth < 1) throw new NoSuchElementException();

        Node init = first;
        Node end = first;
        int count = 0;
        while (count < Kth - 1) {
            end = end.next;
            count++;
        }
        while (end.next != null) {
            end = end.next;
            init = init.next;
        }
        return init.value;
    }

}
