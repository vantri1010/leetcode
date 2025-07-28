from heapq import heappush, heappop

class Solution:
    def kSmallestPairs(self, nums1: List[int], nums2: List[int], k: int) -> List[List[int]]:
        m, n = len(nums1), len(nums2)
        k = min(k, m * n)
        result = []
        if not m or not n:
            return result
        
        pq = []
        for i in range(min(m, k)):
            heappush(pq, (nums1[i] + nums2[0], i, 0))
        
        while k > 0 and pq:
            _, i, j = heappop(pq)
            result.append([nums1[i], nums2[j]])
            k -= 1
            if j + 1 < n:
                heappush(pq, (nums1[i] + nums2[j + 1], i, j + 1))
        
        return result