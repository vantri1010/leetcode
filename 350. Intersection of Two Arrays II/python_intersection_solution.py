from typing import List
from collections import Counter

class Solution:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        """
        Two-pointer approach with sorting
        Most similar to the original Java solution
        """
        # Optimization: ensure nums1 is the smaller array
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
        
        nums1.sort()
        nums2.sort()
        
        result = []
        i = j = 0
        
        while i < len(nums1) and j < len(nums2):
            if nums1[i] == nums2[j]:
                result.append(nums1[i])
                i += 1
                j += 1
            elif nums1[i] < nums2[j]:
                i += 1
            else:
                j += 1
        
        return result

class SolutionHashMap:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        """
        HashMap/Counter approach - more Pythonic
        Better for unsorted data or when sorting is expensive
        """
        # Ensure nums1 is smaller for space optimization
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
        
        # Count frequencies in smaller array
        count = Counter(nums1)
        result = []
        
        # Process larger array
        for num in nums2:
            if count[num] > 0:
                result.append(num)
                count[num] -= 1
        
        return result

class SolutionMostPythonic:
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        """
        Most Pythonic solution using Counter arithmetic
        Leverages Python's powerful built-in operations
        """
        count1 = Counter(nums1)
        count2 = Counter(nums2)
        
        # Counter intersection: min count for each element
        intersection_count = count1 & count2
        
        # Expand back to list with proper frequencies
        result = []
        for num, freq in intersection_count.items():
            result.extend([num] * freq)
        
        return result

# Alternative one-liner approaches
class SolutionOneLiners:
    
    def intersect_v1(self, nums1: List[int], nums2: List[int]) -> List[int]:
        """One-liner using Counter"""
        return list((Counter(nums1) & Counter(nums2)).elements())
    
    def intersect_v2(self, nums1: List[int], nums2: List[int]) -> List[int]:
        """One-liner with manual counting"""
        count = Counter(nums1)
        return [num for num in nums2 if count[num] > 0 and not count.update({num: count[num] - 1})]

# Performance comparison and testing
def test_solutions():
    """Test all solutions with various inputs"""
    test_cases = [
        ([1,2,2,1], [2,2]),
        ([4,9,5], [9,4,9,8,4]),
        ([1,2,3], [4,5,6]),  # No intersection
        ([1,1], [1,1,1]),    # Different frequencies
        ([], [1,2,3]),       # Empty array
        ([1], [1])           # Single elements
    ]
    
    solutions = [
        Solution().intersect,
        SolutionHashMap().intersect,
        SolutionMostPythonic().intersect,
        SolutionOneLiners().intersect_v1
    ]
    
    for i, (nums1, nums2) in enumerate(test_cases):
        print(f"\nTest case {i+1}: nums1={nums1}, nums2={nums2}")
        for j, solution in enumerate(solutions):
            try:
                result = solution(nums1.copy(), nums2.copy())
                print(f"  Solution {j+1}: {sorted(result)}")
            except Exception as e:
                print(f"  Solution {j+1}: Error - {e}")

# Demonstrate Python idioms used
def explain_python_features():
    """Explain Python-specific features used"""
    print("Python idioms demonstrated:")
    print("1. Tuple unpacking: nums1, nums2 = nums2, nums1")
    print("2. Multiple assignment: i = j = 0")
    print("3. Counter operations: count1 & count2")
    print("4. List extend: result.extend([num] * freq)")
    print("5. Counter elements(): converts back to list")
    print("6. Walrus operator (Python 3.8+): count.update() returns None")
    print("7. Type hints: List[int] -> List[int]")
    print("8. Collection methods: Counter, defaultdict behavior")

if __name__ == "__main__":
    test_solutions()
    print("\n" + "="*50)
    explain_python_features()