public class Array {

    int size = 0;
    int[] array = new int[1];

    public void insert(int item) {
        size++;
        int[] newArr = new int[size];
        if (size == 1) {
            newArr[0] = item;
        }
        else {
            for (int i = 0; i < array.length; i++) {
                newArr[i] = array[i];
                newArr[size - 1] = item;
            }
        }
        array = newArr;
    }

    public void removeAt(int index) {
        int[] newArr;
        if (index < 0 || index >= size) {
            newArr = array;
        }
        else if (size > 1) {
            size--;
            newArr = new int[size];
            for (int i = 0; i < newArr.length; i++) {
                if (i < index) {
                    newArr[i] = array[i];
                }
                else {
                    newArr[i] = array[i + 1];
                }
            }

        }
        else {
            newArr = new int[0];
            size = 0;
        }
        array = newArr;
    }

    public void indexOf(int item) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (item == array[i]) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }

    public void print() {
        String printedArr = "[";

        for (int j : array) {
            printedArr += j + ", ";
        }
        String finalArr = size == 0? "[]" : printedArr.substring(0, printedArr.length() - 2) + "]";
        System.out.println(finalArr);
    }

    public int max() {
        int max = 0;
        for (int i = 0; i < size - 1; i++) {
            if (array[i] > max)
                max = array[i];
        }
        return max;
    }

}
