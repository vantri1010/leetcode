class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        output = [-1, -1]
        if not nums:
            return output
        
        output[0] = self.find_first(nums, target)
        output[1] = self.find_last(nums, target)
        return output
    
    def find_first(self, nums, target):
        start, end = 0, len(nums) - 1
        
        while start < end:
            mid = start + (end - start) // 2
            if nums[mid] < target:
                start = mid + 1
            else:  # nums[mid] >= target
                end = mid  # Narrow to left, keeping mid in range
        
        return start if nums[start] == target else -1
    
    def find_last(self, nums, target):
        start, end = 0, len(nums) - 1
        
        while start < end:
            mid = start + (end - start + 1) // 2  # Bias toward right
            if nums[mid] > target:
                end = mid - 1
            else:  # nums[mid] <= target
                start = mid  # Narrow to right, keeping mid in range
        
        return start if nums[start] == target else -1