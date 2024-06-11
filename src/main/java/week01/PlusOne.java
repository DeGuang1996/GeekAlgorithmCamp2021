package week01;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (carry == 0) {
                digits[i]++;
            } else {
                digits[i] += carry;
            }
            if (digits[i] > 9) {
                carry = digits[i] / 10;
                digits[i] = digits[i] % 10;
            } else {
                carry = 0;
                break;
            }
        }
        if (carry > 0) {
            int[] nums = new int[digits.length + 1];
            System.arraycopy(digits, 0, nums, 1, nums.length - 1);
            nums[0] = carry;
            digits = nums;
        }
        return digits;
    }

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        int[] nums = {8,9,9,9};
        plusOne.plusOne(nums);
    }
}
