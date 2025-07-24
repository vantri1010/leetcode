class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        
        int prev2 = 1; // dp[i-2]: ways to decode up to i-2
        int prev1 = 1; // dp[i-1]: ways to decode up to i-1
        
        for (int i = 1; i < s.length(); i++) {
            int curr = 0;
            
            // Check if current single digit is valid (1-9)
            if (s.charAt(i) != '0') {
                curr += prev1;
            }
            
            // Check if last two digits form valid number (10-26)
            int twoDigit = (s.charAt(i-1) - '0') * 10 + (s.charAt(i) - '0');
            if (twoDigit >= 10 && twoDigit <= 26) {
                curr += prev2;
            }
            
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}