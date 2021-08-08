package week08.homework;

public class ToLowerCase {

    public String toLowerCase(String s) {
        // return s.toLowerCase();

        if (s == null || s.length() == 0) {
            return s;
        }
        char[] ch = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                // ch[i] |= 32;
                ch[i] += 32;
            }
        }
        return String.valueOf(ch);
    }
}
