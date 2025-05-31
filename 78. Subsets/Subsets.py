class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        if not nums:
            return []
        output = []
        self.helper(nums, output, 0, [])
        return output
    
    def helper(self, nums: List[int], output: List[List[int]], index: int, cur: List[int]) -> None:
        # Add a copy of the current subset to output
        output.append(cur[:])
        
        # Explore each element starting from index
        for i in range(index, len(nums)):
            cur.append(nums[i])           # Include current element
            self.helper(nums, output, i + 1, cur)  # Recurse with next index
            cur.pop()                    # Backtrack by removing last element