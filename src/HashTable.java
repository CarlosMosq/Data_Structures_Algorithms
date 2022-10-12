import java.util.*;
import java.util.LinkedList;
import java.util.Stack;

public class HashTable {

    private final LinkedList<Entry>[] array = new LinkedList[5];
    private int size;

    private static class Entry {
        private final int key;
        private String value;

        private Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
    }

    public void put(int k, String v) {
        int index = determineIndex(k);
        if(array[index] == null) {
            LinkedList<Entry> list = new LinkedList<>();
            list.addLast(new Entry(k, v));
            array[index] = list;
        }
        else {
            LinkedList<Entry> chain = array[index];
            for (Entry entry : chain) {
                if(entry.getKey() == k) {
                    entry.value = v;
                    return;
                }
            }
            array[index].addLast(new Entry(k, v));
        }
        size++;
    }

    public String get(int k) {
        int index = determineIndex(k);
        LinkedList<Entry> chain = array[index];
        if(chain != null)
            for(Entry entry : chain) {
            if (entry.getKey() == k) return entry.getValue();
            }
        return "Item not found";
    }

    public void remove(int k) {
        int index = determineIndex(k);
        LinkedList<Entry> chain = array[index];
        if(chain != null)
            for(int i = 0; i < chain.size(); i++) {
                if (chain.get(i).getKey() == k) chain.remove(i);
            }
    }

    public void print() {
        String string = "{";
        for (LinkedList<Entry> chain : array) {
            if(chain != null)
            for(Entry entry : chain) {
                string += "[" + entry.getKey() + ", " + entry.getValue() + "]" + ", ";
            }

        }
        String finalString = size == 0? "{}" : string.substring(0, string.length() - 2) + "}";
        System.out.println(finalString);
    }

    private int determineIndex(int k) {
        return k % array.length;
    }


    public static String findNonRepeated(String phrase) {
        Map<String, Integer> map = new HashMap<>();
        String[] split = phrase.split("");
        for (String item : split) {
            if(!map.containsKey(item))
                map.put(item, 1);
            else{
                map.put(item, map.get(item) + 1);
            }
        }
        String charac = "";
        for(String item : split) {
            if (map.get(item) == 1) {
                charac = item;
                break;
            }
        }
        System.out.println(charac);
        return charac;
    }

    public static int findMostRepeated(int[] numbers) {
        Map<Integer, Integer> map = new HashMap<>();
        int number = 0;
        int counter = 0;
        for (int item : numbers) {
            if(!map.containsKey(item)) {
                map.put(item, 1);
            }
            else{
                if(map.get(item) > counter) {
                    number = item;
                    counter = map.get(item) + 1;
                }
                map.put(item, map.get(item) + 1);
            }
        }
        System.out.println(map);
        return number;
    }

    public static String findFirstRepeated(String phrase) {
        Set<String> set = new HashSet<>();
        String charac = "";
        for (String ch : phrase.split("")) {
            if (set.contains(ch)) {
                charac = ch;
                break;
            }
            else {
                set.add(ch);
            }
        }
        System.out.println(charac);
        return charac;
    }


}
