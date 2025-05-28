class Solution {
    public String addBinary(String a, String b) {
        StringBuilder output = new StringBuilder();

        int carry = 0;
        int aLen = a.length() - 1;
        int bLen = b.length() - 1;

        while (aLen >= 0 || bLen >= 0 || carry == 1) {
            if (aLen >= 0) {
                carry += a.charAt(aLen--) - '0';
            }
            if (bLen >= 0) {
                carry += b.charAt(bLen--) - '0';
            }
            output.append(carry % 2);
            carry /= 2;
        }

        return output.reverse().toString();
    }
}