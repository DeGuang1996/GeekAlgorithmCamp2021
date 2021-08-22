package week09.classProblem;

import java.util.HashSet;
import java.util.List;

public class LadderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (wordSet.isEmpty() || !wordSet.contains(endWord)) {
            return 0;
        }
        HashSet<String> visited = new HashSet<>();
        HashSet<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        HashSet<String> endSet = new HashSet<>();
        endSet.add(endWord);

        int ans = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                HashSet<String> temp = endSet;
                endSet = beginSet;
                beginSet = temp;
            }
            HashSet<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                if (doLadderLength(word, wordSet, nextSet, visited, endSet)) {
                    return ans + 1;
                }
            }
            beginSet = nextSet;
            ans++;
        }
        return 0;
    }

    private boolean doLadderLength(String word, HashSet<String> wordSet, HashSet<String> nextSet, HashSet<String> visited, HashSet<String> endSet) {
        if (endSet.contains(word)) {
            return true;
        }
        visited.add(word);
        StringBuilder stringBuilder = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            char temp = stringBuilder.charAt(i);
            for (int j = 0; j < 26; j++) {
                stringBuilder.setCharAt(i, (char) (j + 'a'));
                String cur = stringBuilder.toString();
                if (wordSet.contains(cur) && !visited.contains(cur)) {
                    if (endSet.contains(cur)) {
                        return true;
                    }
                    nextSet.add(cur);
                }
            }
            stringBuilder.setCharAt(i, temp);
        }
        return false;
    }
}
