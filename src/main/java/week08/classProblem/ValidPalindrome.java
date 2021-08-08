package week08.classProblem;

public class ValidPalindrome {

    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, true);
    }

    public boolean validPalindrome(String s, int left, int right, boolean canDelete) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (canDelete) {
                    return validPalindrome(s, left + 1, right, false) || validPalindrome(s, left, right - 1, false);
                }
                return false;
            }
        }
        return true;
    }
}
