package week02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FindSubstring {

    public List<Integer> findSubstring(String s, String[] words) {
        // HashMap<String, Integer> wordsHash = buildHash(words);
        // int next = words.length * words[0].length();
        // List<Integer> res = new ArrayList<>();
        // for (int i = 0; i <= s.length() - next; i++) {
        //     HashMap<String, Integer> sHash = buildHash(s.substring(i, i + next + 1), words[0].length());
        //     if (cmpHashMap(sHash, wordsHash)) {
        //         res.add(i);
        //     }
        // }
        // return res;

        int m = words.length;
        int w = words[0].length();
        List<Integer> res = new ArrayList<>();
        HashMap<String, Integer> wordsHash = buildHash(words);

        for (int i = 0; i < w; i++) {
            HashMap<String, Integer> temp = new HashMap<>();
            int left = i;
            for (int j = i; j <= s.length() - w; j += w) {
                String word = s.substring(j, j + w);

                if (j - left >= m * w) {
                    String subWord = s.substring(left, left + w);

                    if (temp.get(subWord) == 1) {
                        temp.remove(subWord);
                    } else {
                        temp.put(subWord, temp.get(subWord) - 1);
                    }
                    left += w;
                }

                temp.put(word, temp.getOrDefault(word, 0) + 1);
                if (cmpHashMap(temp, wordsHash)) {
                    res.add(left);
                }
            }
        }

        return res;
    }

    private boolean cmpHashMap(HashMap<String, Integer> hashMap1, HashMap<String, Integer> hashMap2) {
        if (hashMap1.size() == hashMap2.size()) {
            for (String key : hashMap1.keySet()) {
                if (!hashMap2.containsKey(key) || !hashMap1.get(key).equals(hashMap2.get(key))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private HashMap<String, Integer> buildHash(String[] words) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        return hashMap;
    }

    private HashMap<String, Integer> buildHash(String s, int len) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i <= s.length() - len; i += len) {
            String word = s.substring(i, i + len + 1);
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }
        return hashMap;
    }
}
