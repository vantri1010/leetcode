# Approach 1: Recursive with Memoization (Python)
class Solution:
    def numDecodings(self, s: str) -> int:
        memo = {}
        
        def decode(i):
            if i == len(s):
                return 1
            if s[i] == '0':
                return 0
            if i in memo:
                return memo[i]
            
            # Take single digit
            result = decode(i + 1)
            
            # Take two digits if valid
            if i + 1 < len(s) and int(s[i:i+2]) <= 26:
                result += decode(i + 2)
            
            memo[i] = result
            return result
        
        return decode(0) if s else 0

# Approach 2: Full DP Array (Java-style)
class Solution:
    def numDecodings(self, s: str) -> int:
        if not s or s[0] == '0':
            return 0
        
        n = len(s)
        dp = [0] * (n + 1)
        dp[0] = dp[1] = 1
        
        for i in range(2, n + 1):
            # Single digit
            if s[i-1] != '0':
                dp[i] += dp[i-1]
            
            # Two digits
            if 10 <= int(s[i-2:i]) <= 26:
                dp[i] += dp[i-2]
        
        return dp[n]