package week08.homework;

import java.util.HashSet;
import java.util.Set;

public class NumJewelsInStones {

    public int numJewelsInStones(String jewels, String stones) {
        int ans = 0;
        Set<Character> jewelsSet = new HashSet<>();
        int jewelsLength = jewels.length(), stonesLength = stones.length();
        for (int i = 0; i < jewelsLength; i++) {
            jewelsSet.add(jewels.charAt(i));
        }
        for (int i = 0; i < stonesLength; i++) {
            if (jewelsSet.contains(stones.charAt(i))) {
                ans++;
            }
        }
        return ans;
    }
}
