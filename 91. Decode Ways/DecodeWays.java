class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        
        int prev2 = 1; // Ways to decode up to i-2
        int prev1 = 1; // Ways to decode up to i-1
        
        for (int i = 1; i < s.length(); i++) {
            int curr = 0;
            int fst = Integer.valueOf(s.substring(i, i + 1)); // Last digit
            int scd = Integer.valueOf(s.substring(i - 1, i + 1)); // Last two digits
            
            if (fst >= 1 && fst <= 9) {
                curr += prev1; // Can decode last digit alone
            }
            if (i > 0 && scd >= 10 && scd <= 26) {
                curr += prev2; // Can decode last two digits
            }
            
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}