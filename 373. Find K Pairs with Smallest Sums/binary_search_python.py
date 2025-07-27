from typing import List

class Solution:
    def kSmallestPairs(self, nums1: List[int], nums2: List[int], k: int) -> List[List[int]]:
        def count_pairs_with_sum_le(target_sum):
            """Count pairs with sum <= target_sum"""
            count = 0
            j = len(nums2) - 1
            
            for i in range(len(nums1)):
                while j >= 0 and nums1[i] + nums2[j] > target_sum:
                    j -= 1
                if j < 0:
                    break
                count += j + 1
            
            return count
        
        def get_pairs_with_sum_le(target_sum):
            """Get all pairs with sum <= target_sum"""
            pairs = []
            j = len(nums2) - 1
            
            for i in range(len(nums1)):
                while j >= 0 and nums1[i] + nums2[j] > target_sum:
                    j -= 1
                if j < 0:
                    break
                
                for jj in range(j + 1):
                    pairs.append([nums1[i], nums2[jj]])
            
            return pairs
        
        # Binary search on sum range
        left = nums1[0] + nums2[0]
        right = nums1[-1] + nums2[-1]
        
        while left < right:
            mid = (left + right) // 2
            if count_pairs_with_sum_le(mid) < k:
                left = mid + 1
            else:
                right = mid
        
        # Get all pairs with sum <= (left-1), then add pairs with sum = left
        pairs = get_pairs_with_sum_le(left - 1) if left > nums1[0] + nums2[0] else []
        
        # Add pairs with sum exactly = left until we have k pairs
        needed = k - len(pairs)
        if needed > 0:
            for i in range(len(nums1)):
                for j in range(len(nums2)):
                    if nums1[i] + nums2[j] == left:
                        pairs.append([nums1[i], nums2[j]])
                        needed -= 1
                        if needed == 0:
                            break
                if needed == 0:
                    break
        
        return sorted(pairs, key=lambda x: x[0] + x[1])[:k]