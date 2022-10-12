import java.util.Stack;
import java.util.*;

public class Strings {

    //nine
    public boolean isPalindrome(String word) {
        if(word == null) return false;

        int left = 0;
        int right = word.length() - 1;
        while(left < right) {
            if(word.charAt(left++) != word.charAt(right--)) {
                return false;
            }
        }
        return true;
// other possible solution
//        if(word == null) return false;
//
//        StringBuilder sb = new StringBuilder(word.toLowerCase());
//        return sb.reverse().toString().equals(word);

    }

    //eight
    public boolean isAnagram(String base, String toCompare) {
        if(base == null || toCompare == null || base.length() != toCompare.length()) return false;

        String[] baseArr = base.toLowerCase().split("");
        String[] toCompareArr = toCompare.toLowerCase().split("");

        Arrays.sort(baseArr);
        Arrays.sort(toCompareArr);

        return Arrays.equals(baseArr, toCompareArr);
    }

    //seven
    public String capitalize(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        for(String word : phrase.trim().split(" ")) {
            result
                    .append(word.trim().substring(0, 1).toUpperCase())
                    .append(word.trim().substring(1))
                    .append(" ");
        }
        return result.toString().trim();
    }

    //six
    public String mostFrequent(String word) {
        if(word == null) return "";
        Map<String, Integer> map = new HashMap<>();
        for (String letter : word.split("")) {
            if(map.containsKey(letter)) map.replace(letter, map.get(letter) + 1);
            else map.put(letter, 1);
        }
        int max = 0;
        String frequent = "";
        for(String key : map.keySet()) {
            if(map.get(key) > max) {
                max = map.get(key);
                frequent = key;
            }
        }
        return frequent;
    }

    //five
    public String filterDuplicated(String word) {
        if(word == null) return "";
        String[] split = word.split("");
        Set<String> filter = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(String letter : split) {
            if(!filter.contains(letter)) {
                sb.append(letter);
                filter.add(letter);
            }
        }
        return sb.toString();
    }

    //four
    public boolean isRotation(String word, String comparison) {
        if(word == null || comparison == null || word.length() != comparison.length()) return false;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < word.length(); i++) {
            if(sb
                    .append(word.substring(1))
                    .append(word.charAt(0))
                    .toString()
                    .equals(comparison))
                return true;
        }
        return false;

        /*
        optional shorter solution, but it requires allocation of double the space of the original string size
        if(word == null || comparison == null) return false;
        return word.length() == comparison.length() && (word + word).contains(comparison);
         */
    }

    //three
    public String reverseWords(String phrase) {
        if(phrase == null) return "";
        Stack<String> stack = new Stack<>();
        for(String word : phrase.trim().split(" ")) stack.push(word);
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append(stack.pop().trim());
            result.append(" ");
        }
        return result.toString().trim();
    }

    //two
    public String reverse(String word) {
        if(word == null) return "";
        Stack<String> stack = new Stack<>();
        for(String letter : word.split("")) stack.push(letter);
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) result.append(stack.pop());
        return result.toString();
    }

    //one
    public int countVowels(String word) {
        if(word == null) return 0;
        int count = 0;
        String vowels = "aeiou";
        for(char letter : word.toLowerCase().toCharArray()) {
            if(vowels.contains(Character.toString(letter))) {
                count++;
            }
        }
        return count;
    }


}
