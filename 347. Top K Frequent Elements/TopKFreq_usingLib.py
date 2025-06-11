import heapq
from collections import Counter

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # nums = [1, 1, 1, 2, 2, 3, 4, 4, 4, 4]
        # # Step 1: Create Counter
        freq_map = Counter(nums)
        # Output: Counter({4: 4, 1: 3, 2: 2, 3: 1})

        # Step 2: Get most common k elements
        most_commons = freq_map.most_common(k)
        # Output: [(4, 4), (1, 3), (2, 2)]

        # Step 3: Extract just the numbers (not frequencies)
        return [num for num, freq in most_commons]
        # Output: [4, 1, 2]
        