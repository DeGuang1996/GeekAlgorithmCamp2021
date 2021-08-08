package week08.homework;

import java.util.ArrayList;

public class ReverseWords {

    public String reverseWords(String s) {
        ArrayList<StringBuilder> stringBuilderArrayList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean space = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (space) {
                    continue;
                }
                stringBuilderArrayList.add(new StringBuilder(stringBuilder));
                stringBuilder = new StringBuilder();
                space = true;
            } else {
                space = false;
                stringBuilder.append(s.charAt(i));
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilderArrayList.add(new StringBuilder(stringBuilder));
        }
        StringBuilder ans = new StringBuilder();
        for (int i = stringBuilderArrayList.size() - 1; i >= 0; i--) {
            ans.append(stringBuilderArrayList.get(i).toString());
            if (i != 0) {
                ans.append(" ");
            }
        }
        return ans.toString();
    }
}
