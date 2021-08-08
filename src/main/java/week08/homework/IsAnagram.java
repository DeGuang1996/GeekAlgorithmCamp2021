package week08.homework;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {

    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (count.get(s.charAt(i)) != null) {
                count.put(s.charAt(i), count.get(s.charAt(i)) + 1);
            } else {
                count.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (count.get(t.charAt(i)) != null) {
                count.put(t.charAt(i), count.get(t.charAt(i)) - 1);
                if (count.get(t.charAt(i)) == 0) {
                    count.remove(t.charAt(i));
                }
            } else {
                return false;
            }
        }
        return count.size() <= 0;
    }
}
