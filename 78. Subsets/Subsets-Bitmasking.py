class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        output = []
        for mask in range(1 << n):
            subset = [nums[i] for i in range(n) if (mask & (1 << i))]
            output.append(subset)
        return output