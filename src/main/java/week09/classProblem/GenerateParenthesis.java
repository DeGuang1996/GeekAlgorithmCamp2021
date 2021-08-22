package week09.classProblem;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    private List<String> res;

    private void doGenerateParenthesis(int left, int right, String cur, int n) {
        if (cur.length() == 2 * n) {
            res.add(cur);
        }
        if (left < n) {
            doGenerateParenthesis(left + 1, right, cur + "(", n);
        }
        if (right < left) {
            doGenerateParenthesis(left, right + 1, cur + ")", n);
        }
    }

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        doGenerateParenthesis(0, 0, "", n);
        return res;
    }
}
