package week02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GenerateParenthesis {

    // private List<String> res;
    //
    // private void doGenerateParenthesis(int left, int right, String cur, int n) {
    //     if (cur.length() == 2 * n) {
    //         res.add(cur);
    //     }
    //     if (left < n) {
    //         doGenerateParenthesis(left + 1, right, cur + "(", n);
    //     }
    //     if (right < left) {
    //         doGenerateParenthesis(left, right + 1, cur + ")", n);
    //     }
    // }
    //
    // public List<String> generateParenthesis(int n) {
    //     res = new ArrayList<>();
    //     doGenerateParenthesis(0, 0, "", n);
    //     return res;
    // }

    private HashMap<Integer, List<String>> hashMap = new HashMap<>();

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
            return res;
        }
        if (hashMap.containsKey(n)) {
            return hashMap.get(n);
        }
        for (int i = 1; i <= n; i++) {
            List<String> result_a = generateParenthesis(i - 1);
            List<String> result_b = generateParenthesis(n - i);

            for (String a : result_a) {
                for (String b : result_b) {
                    res.add("(" + a + ")" + b);
                }
            }
        }
        hashMap.put(n, res);
        return res;
    }
}
