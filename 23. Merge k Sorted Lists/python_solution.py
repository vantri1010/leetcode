import heapq
from typing import List, Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        """
        Merge k sorted linked lists using a min-heap approach.
        
        Args:
            lists: List of sorted linked list heads
            
        Returns:
            Head of the merged sorted linked list
        """
        # Early termination for edge cases
        if not lists:
            return None
        
        # Python's heapq requires tuples for comparison
        # We use (node_value, unique_id, node) to handle duplicate values
        min_heap = []
        
        # Add all non-null list heads to the heap
        for i, head in enumerate(lists):
            if head is not None:
                heapq.heappush(min_heap, (head.val, i, head))
        
        # Create dummy head for easier list construction
        dummy = ListNode(0)
        current = dummy
        
        # Counter for unique IDs when adding new nodes
        node_id = len(lists)
        
        # Process nodes until heap is empty
        while min_heap:
            # Get the node with minimum value
            _, _, min_node = heapq.heappop(min_heap)
            
            # Add this node to our result list
            current.next = min_node
            current = current.next
            
            # If this node has a next node, add it to heap
            if min_node.next is not None:
                heapq.heappush(min_heap, (min_node.next.val, node_id, min_node.next))
                node_id += 1
        
        return dummy.next

# Alternative Pythonic approach using a custom wrapper class
class NodeWrapper:
    """Wrapper class to make ListNode comparable for heapq."""
    
    def __init__(self, node: ListNode):
        self.node = node
    
    def __lt__(self, other):
        return self.node.val < other.node.val

class AlternativeSolution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        """
        Alternative implementation using a wrapper class for cleaner code.
        """
        if not lists:
            return None
        
        # Create heap with wrapped nodes
        min_heap = [NodeWrapper(head) for head in lists if head is not None]
        heapq.heapify(min_heap)
        
        dummy = ListNode(0)
        current = dummy
        
        while min_heap:
            # Get the wrapper with minimum node value
            min_wrapper = heapq.heappop(min_heap)
            min_node = min_wrapper.node
            
            # Add this node to result
            current.next = min_node
            current = current.next
            
            # Add next node if it exists
            if min_node.next is not None:
                heapq.heappush(min_heap, NodeWrapper(min_node.next))
        
        return dummy.next