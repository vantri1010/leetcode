import heapq
from collections import Counter

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # Step 1: Count frequencies (O(n))
        freq_map = Counter(nums)
        
        # Step 2: Use Min-Heap of size k (O(n log k))
        heap = []
        for num, freq in freq_map.items():
            heapq.heappush(heap, (freq, num))
            if len(heap) > k:
                heapq.heappop(heap)  # Remove smallest frequency
        
        # Step 3: Extract results (O(k log k))
        return [num for (freq, num) in heap]