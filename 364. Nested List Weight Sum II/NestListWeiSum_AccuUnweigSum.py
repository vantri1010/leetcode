from collections import deque
from typing import List

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#     def __init__(self, value=None):
#         """
#         If value is not specified, initializes an empty list.
#         Otherwise initializes a single integer equal to value.
#         """
#
#     def isInteger(self):
#         """
#         @return True if this NestedInteger holds a single integer, rather than a nested list.
#         :rtype bool
#         """
#
#     def add(self, elem):
#         """
#         Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
#         :rtype void
#         """
#
#     def setInteger(self, value):
#         """
#         Set this NestedInteger to hold a single integer equal to value.
#         :rtype void
#         """
#
#     def getInteger(self):
#         """
#         @return the single integer that this NestedInteger holds, if it holds a single integer
#         Return None if this NestedInteger holds a nested list
#         :rtype int
#         """
#
#     def getList(self):
#         """
#         @return the nested list that this NestedInteger holds, if it holds a nested list
#         Return None if this NestedInteger holds a single integer
#         :rtype List[NestedInteger]
#         """

class Solution:
    def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
        """
        Calculate weighted sum with inverse depth weighting using BFS.
        
        Time Complexity: O(N) where N is total number of integers
        Space Complexity: O(W) where W is maximum width of any level
        """
        unweighted_sum = 0
        weighted_sum = 0
        
        queue = deque(nestedList)
        
        while queue:
            # Process entire current level
            for _ in range(len(queue)):
                nested_int = queue.popleft()
                
                if nested_int.isInteger():
                    unweighted_sum += nested_int.getInteger()
                else:
                    # Extend queue with nested elements for next level
                    queue.extend(nested_int.getList())
            
            # Accumulate unweighted sum - this gives inverse depth weighting
            weighted_sum += unweighted_sum
        
        return weighted_sum