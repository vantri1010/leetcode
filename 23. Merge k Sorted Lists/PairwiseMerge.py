from typing import List, Optional

class ListNode:
    def __init__(self, val: int = 0, next: Optional['ListNode'] = None):
        self.val = val
        self.next = next

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        if not lists:
            return None
        if len(lists) == 1:
            return lists[0]
        
        def merge_two_lists(l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
            dummy = ListNode(0)
            tail = dummy
            
            while l1 and l2:
                if l1.val <= l2.val:
                    tail.next = l1
                    l1 = l1.next
                else:
                    tail.next = l2
                    l2 = l2.next
                tail = tail.next
            
            tail.next = l1 if l1 else l2
            return dummy.next
        
        def merge_lists(start: int, end: int) -> Optional[ListNode]:
            if start == end:
                return lists[start]
            if start + 1 == end:
                return merge_two_lists(lists[start], lists[end])
            
            mid = (start + end) // 2
            left = merge_lists(start, mid)
            right = merge_lists(mid + 1, end)
            return merge_two_lists(left, right)
        
        return merge_lists(0, len(lists) - 1)