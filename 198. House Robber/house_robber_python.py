class Solution:
    def rob(self, nums: list[int]) -> int:
        """
        Rob houses to maximize money without robbing adjacent houses.
        
        Args:
            nums: List of non-negative integers representing money in each house
            
        Returns:
            Maximum amount that can be robbed
        """
        if not nums:
            return 0
        if len(nums) == 1:
            return nums[0]
        
        # Track max money robbed up to previous two houses
        prev2, prev1 = nums[0], max(nums[0], nums[1])
        
        # For each remaining house, decide whether to rob it
        for money in nums[2:]:
            prev2, prev1 = prev1, max(prev1, money + prev2)
        
        return prev1


# Alternative Pythonic one-liner approach using functools.reduce
from functools import reduce

class SolutionOneLiner:
    def rob(self, nums: list[int]) -> int:
        """One-liner solution using functools.reduce."""
        if not nums:
            return 0
        return reduce(
            lambda state, money: (state[1], max(state[1], money + state[0])),
            nums[1:],
            (0, nums[0])
        )[1]


# Usage example and test
if __name__ == "__main__":
    solution = Solution()
    
    # Test cases
    test_cases = [
        [2, 7, 9, 3, 1],  # Expected: 12
        [1, 2, 3, 1],     # Expected: 4
        [2, 1, 1, 2],     # Expected: 4
        [5],              # Expected: 5
        []                # Expected: 0
    ]
    
    for nums in test_cases:
        result = solution.rob(nums)
        print(f"rob({nums}) = {result}")
