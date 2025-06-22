from typing import List, Optional

class ListNode:
    def __init__(self, val: int = 0, next: Optional['ListNode'] = None):
        self.val = val
        self.next = next

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        # Handle edge cases
        if not lists:
            return None
        
        # Collect all nodes into a list
        nodes = []
        for head in lists:
            current = head
            while current:
                nodes.append(current)
                current = current.next
        
        # Sort nodes by value
        nodes.sort(key=lambda x: x.val)
        
        # Build the merged list
        dummy = ListNode(0)
        tail = dummy
        for node in nodes:
            tail.next = node
            tail = node
            node.next = None  # Disconnect original next pointers
        
        return dummy.next