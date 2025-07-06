# Python - Prefix Sum Approach
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        min_prefix = 0
        current_prefix = 0
        max_subarray = float('-inf')
        
        for num in nums:
            current_prefix += num
            max_subarray = max(max_subarray, current_prefix - min_prefix)
            min_prefix = min(min_prefix, current_prefix)
        
        return max_subarray

# Python - Divide and Conquer
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        def max_subarray_helper(left: int, right: int) -> int:
            if left == right:
                return nums[left]
            
            mid = (left + right) // 2
            
            # Maximum subarray in left half
            left_max = max_subarray_helper(left, mid)
            
            # Maximum subarray in right half  
            right_max = max_subarray_helper(mid + 1, right)
            
            # Maximum subarray crossing the middle
            cross_max = max_crossing_sum(left, mid, right)
            
            return max(left_max, right_max, cross_max)
        
        def max_crossing_sum(left: int, mid: int, right: int) -> int:
            # Find max sum for left side ending at mid
            left_sum = float('-inf')
            current_sum = 0
            for i in range(mid, left - 1, -1):
                current_sum += nums[i]
                left_sum = max(left_sum, current_sum)
            
            # Find max sum for right side starting at mid+1
            right_sum = float('-inf')
            current_sum = 0
            for i in range(mid + 1, right + 1):
                current_sum += nums[i]
                right_sum = max(right_sum, current_sum)
            
            return left_sum + right_sum
        
        return max_subarray_helper(0, len(nums) - 1)