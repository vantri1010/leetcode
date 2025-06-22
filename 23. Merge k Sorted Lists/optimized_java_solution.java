/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Early termination for edge cases
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Create min-heap with custom comparator
        // Using Integer.compare for better overflow handling
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        
        // Add all non-null list heads to the heap
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
        
        // Create dummy head for easier list construction
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Process nodes until heap is empty
        while (!minHeap.isEmpty()) {
            // Get the node with minimum value
            ListNode minNode = minHeap.poll();
            
            // Add this node to our result list
            current.next = minNode;
            current = current.next;
            
            // If this node has a next node, add it to heap
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }
        
        return dummy.next;
    }
}