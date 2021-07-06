package week03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MinMutation {

    private final char[] change = {'A', 'C', 'G', 'T'};

    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> avail = Arrays.stream(bank).collect(Collectors.toCollection(HashSet::new));
        if (!avail.contains(end)) {
            return -1;
        }
        if (start.equals(end)) {
            return 0;
        }
        HashSet<String> first = new HashSet<>();
        first.add(start);
        HashSet<String> last = new HashSet<>();
        last.add(end);
        HashSet<String> visited = new HashSet<>();

        int ans = 0;
        while (!first.isEmpty() && !last.isEmpty()) {
            if (first.size() > last.size()) {
                HashSet<String> temp = first;
                first = last;
                last = temp;
            }
            HashSet<String> next = new HashSet<>();
            for (String cur : first) {
                if (bfs(cur, avail, next, last, visited)) {
                    return ans + 1;
                }
            }
            first = next;
            ans++;
        }
        return -1;
    }

    private boolean bfs(String cur, HashSet<String> avail, HashSet<String> next, HashSet<String> last, HashSet<String> visited) {
        visited.add(cur);
        StringBuilder stringBuilder = new StringBuilder(cur);
        for (int i = 0; i < stringBuilder.length(); i++) {
            char temp = stringBuilder.charAt(i);
            for (char c : change) {
                stringBuilder.setCharAt(i, c);
                String newString = stringBuilder.toString();
                if (last.contains(newString)) {
                    return true;
                }
                if (avail.contains(newString) && !visited.contains(newString)) {
                    next.add(newString);
                }
            }
            stringBuilder.setCharAt(i, temp);
        }
        return false;
    }
}
