import heapq
from typing import List

class Solution:
    def kSmallestPairs(self, nums1: List[int], nums2: List[int], k: int) -> List[List[int]]:
        # Max-heap using negative sums (Python heapq is min-heap)
        max_heap = []
        
        for num1 in nums1:
            for num2 in nums2:
                current_sum = num1 + num2
                
                if len(max_heap) < k:
                    heapq.heappush(max_heap, (-current_sum, num1, num2))
                elif current_sum < -max_heap[0][0]:  # current_sum < max_sum_in_heap
                    heapq.heapreplace(max_heap, (-current_sum, num1, num2))
                else:
                    # Early termination: remaining pairs in this row will be larger
                    break
            
            # Early termination: if minimum possible sum >= heap's maximum
            if len(max_heap) == k and num1 + nums2[0] >= -max_heap[0][0]:
                break
        
        # Extract pairs and sort by sum (ascending)
        return sorted([[num1, num2] for _, num1, num2 in max_heap], 
                     key=lambda pair: pair[0] + pair[1])