class Solution:
    def countBits(self, n: int) -> list[int]:
        """
        Count the number of 1-bits in binary representation for numbers 0 to n.
        
        Uses dynamic programming: dp[i] = dp[i//2] + (i % 2)
        Time: O(n), Space: O(1) excluding output array
        """
        dp = [0] * (n + 1)
        
        for i in range(1, n + 1):
            # Right shift divides by 2, & 1 checks if odd
            dp[i] = dp[i >> 1] + (i & 1)
            
        return dp


# Alternative Pythonic one-liner approach
class SolutionOneLiner:
    def countBits(self, n: int) -> list[int]:
        """One-liner using list comprehension and bit_count() method."""
        return [i.bit_count() for i in range(n + 1)]


# For educational purposes - showing the pattern more explicitly
class SolutionExplicit:
    def countBits(self, n: int) -> list[int]:
        """More explicit version showing the relationship clearly."""
        if n == 0:
            return [0]
            
        dp = [0] * (n + 1)
        
        for i in range(1, n + 1):
            # Number of bits in i = number of bits in (i // 2) + last bit
            parent_bits = dp[i // 2]  # or dp[i >> 1]
            last_bit = i % 2          # or i & 1
            dp[i] = parent_bits + last_bit
            
        return dp