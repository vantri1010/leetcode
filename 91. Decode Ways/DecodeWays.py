class Solution:
    def numDecodings(self, s):
        if not s or s[0] == '0':
            return 0
            
        prev2, prev1 = 1, 1
        
        for i in range(1, len(s)):
            curr = 0
            fst = int(s[i:i+1]) if s[i] != '0' else 0  # Last digit
            scd = int(s[i-1:i+1]) if i > 0 else 0      # Last two digits
            
            if 1 <= fst <= 9:
                curr += prev1
            if i > 0 and 10 <= scd <= 26:
                curr += prev2
                
            prev2, prev1 = prev1, curr
        
        return prev1