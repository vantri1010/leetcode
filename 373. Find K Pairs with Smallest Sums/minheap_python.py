import heapq
from typing import List

class Solution:
    def kSmallestPairs(self, nums1: List[int], nums2: List[int], k: int) -> List[List[int]]:
        # Min-heap: stores (sum, i, j)
        min_heap = []
        visited = set()
        
        # Start with the smallest possible pair
        heapq.heappush(min_heap, (nums1[0] + nums2[0], 0, 0))
        visited.add((0, 0))
        
        result = []
        
        while len(result) < k and min_heap:
            current_sum, i, j = heapq.heappop(min_heap)
            
            result.append([nums1[i], nums2[j]])
            
            # Add adjacent pairs: (i+1, j) and (i, j+1)
            if i + 1 < len(nums1) and (i + 1, j) not in visited:
                heapq.heappush(min_heap, (nums1[i + 1] + nums2[j], i + 1, j))
                visited.add((i + 1, j))
            
            if j + 1 < len(nums2) and (i, j + 1) not in visited:
                heapq.heappush(min_heap, (nums1[i] + nums2[j + 1], i, j + 1))
                visited.add((i, j + 1))
        
        return result