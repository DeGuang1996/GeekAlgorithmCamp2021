package week08.homework;

import java.util.*;

public class FindAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        // 暴力解法
        // List<Integer> ans = new ArrayList<>();
        // char[] target = p.toCharArray();
        // Arrays.sort(target);
        // String targetStr = String.valueOf(target);
        // for (int i = 0; i <= s.length() - p.length(); i++) {
        //     char[] cur = s.substring(i, i + p.length()).toCharArray();
        //     Arrays.sort(cur);
        //     if (String.valueOf(cur).equals(targetStr)) {
        //         ans.add(i);
        //     }
        // }
        // return ans;

        int sn = s.length();
        int pn = p.length();
        List<Integer> res = new ArrayList<>();

        if (sn < pn) {
            return res;
        }

        int needCount = pn;
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < pn; i++) {
            char c = p.charAt(i);
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }

        //---- 先统计第一个窗口长度
        for (int i = 0; i < pn; i++) {
            char c = s.charAt(i);
            if (hashMap.containsKey(c)) {
                if (hashMap.get(c) > 0)
                    needCount--;
                hashMap.put(c, hashMap.get(c) - 1);
            }
        }
        if (needCount == 0) {
            res.add(0);
        }

        //---- s中剩下的区间
        for (int r = pn; r < sn; r++) {
            int l = r - pn;
            char lc = s.charAt(l);
            char rc = s.charAt(r);
            if (hashMap.containsKey(lc)) {
                if (hashMap.get(lc) >= 0)
                    needCount++;
                hashMap.put(lc, hashMap.get(lc) + 1);
            }
            if (hashMap.containsKey(rc)) {
                if (hashMap.get(rc) > 0)
                    needCount--;
                hashMap.put(rc, hashMap.get(rc) - 1);
            }

            if (needCount == 0) {
                res.add(l + 1);
            }
        }
        return res;
    }
}
