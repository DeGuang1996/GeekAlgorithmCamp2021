package week08.classProblem;

public class IsPalindrome {

    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char temp : s.toCharArray()) {
            if (Character.isDigit(temp) || Character.isLetter(temp)) {
                stringBuilder.append(temp);
            }
        }
        s = stringBuilder.toString().toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
