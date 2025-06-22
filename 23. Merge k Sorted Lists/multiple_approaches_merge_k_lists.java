import java.util.*;

/**
 * Multiple approaches to solve "Merge k Sorted Lists" problem
 * Each approach demonstrates different algorithmic thinking patterns
 */
public class MergeKListsApproaches {
    
    // =============================================================================
    // APPROACH 1: DIVIDE AND CONQUER (Most Efficient)
    // =============================================================================
    public static class DivideConquerSolution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            
            // Keep merging until we have only one list left
            while (lists.length > 1) {
                List<ListNode> mergedLists = new ArrayList<>();
                
                // Merge pairs of lists
                for (int i = 0; i < lists.length; i += 2) {
                    ListNode list1 = lists[i];
                    ListNode list2 = (i + 1 < lists.length) ? lists[i + 1] : null;
                    mergedLists.add(mergeTwoLists(list1, list2));
                }
                
                // Convert back to array for next iteration
                lists = mergedLists.toArray(new ListNode[0]);
            }
            
            return lists[0];
        }
        
        // Helper method to merge two sorted lists
        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    current.next = l1;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    l2 = l2.next;
                }
                current = current.next;
            }
            
            // Attach remaining nodes
            current.next = (l1 != null) ? l1 : l2;
            return dummy.next;
        }
    }
    
    // =============================================================================
    // APPROACH 2: BRUTE FORCE - COLLECT ALL VALUES AND SORT
    // =============================================================================
    public static class BruteForceSolution {
        public ListNode mergeKLists(ListNode[] lists) {
            List<Integer> allValues = new ArrayList<>();
            
            // Step 1: Collect all values from all lists
            for (ListNode head : lists) {
                ListNode current = head;
                while (current != null) {
                    allValues.add(current.val);
                    current = current.next;
                }
            }
            
            // Step 2: Sort all values
            Collections.sort(allValues);
            
            // Step 3: Build new linked list from sorted values
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            for (int val : allValues) {
                current.next = new ListNode(val);
                current = current.next;
            }
            
            return dummy.next;
        }
    }
    
    // =============================================================================
    // APPROACH 3: ONE BY ONE MERGING
    // =============================================================================
    public static class OneByOneSolution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            
            ListNode result = null;
            
            // Merge each list one by one with the result
            for (ListNode currentList : lists) {
                result = mergeTwoLists(result, currentList);
            }
            
            return result;
        }
        
        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    current.next = l1;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    l2 = l2.next;
                }
                current = current.next;
            }
            
            current.next = (l1 != null) ? l1 : l2;
            return dummy.next;
        }
    }
    
    // =============================================================================
    // APPROACH 4: PRIORITY QUEUE (Min-Heap) - Original Approach
    // =============================================================================
    public static class PriorityQueueSolution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            
            PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
            
            // Add all non-null list heads to heap
            for (ListNode head : lists) {
                if (head != null) {
                    minHeap.offer(head);
                }
            }
            
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            
            while (!minHeap.isEmpty()) {
                ListNode minNode = minHeap.poll();
                current.next = minNode;
                current = current.next;
                
                if (minNode.next != null) {
                    minHeap.offer(minNode.next);
                }
            }
            
            return dummy.next;
        }
    }
    
    // =============================================================================
    // DEMONSTRATION AND COMPARISON
    // =============================================================================
    public static void main(String[] args) {
        System.out.println("=== MERGE K SORTED LISTS - MULTIPLE APPROACHES ===\n");
        
        // Create test data
        ListNode[] testLists = createTestData();
        System.out.println("Test Input:");
        printAllLists(testLists);
        
        // Test each approach
        System.out.println("\n" + "=".repeat(60));
        System.out.println("APPROACH 1: DIVIDE AND CONQUER");
        System.out.println("=".repeat(60));
        demonstrateDivideConquer(deepCopyLists(testLists));
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("APPROACH 2: BRUTE FORCE");
        System.out.println("=".repeat(60));
        demonstrateBruteForce(deepCopyLists(testLists));
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("APPROACH 3: ONE BY ONE MERGING");
        System.out.println("=".repeat(60));
        demonstrateOneByOne(deepCopyLists(testLists));
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("APPROACH 4: PRIORITY QUEUE");
        System.out.println("=".repeat(60));
        demonstratePriorityQueue(deepCopyLists(testLists));
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY COMPARISON");
        System.out.println("=".repeat(60));
        compareComplexities();
    }
    
    // Demonstrate Divide and Conquer approach
    private static void demonstrateDivideConquer(ListNode[] lists) {
        System.out.println("Strategy: Recursively merge pairs of lists");
        System.out.println("Think of it like a tournament bracket!\n");
        
        DivideConquerSolution solution = new DivideConquerSolution();
        
        System.out.println("Step-by-step execution:");
        System.out.println("Round 1: Merge pairs");
        System.out.println("  [1->4->5] + [1->3->4] = [1->1->3->4->4->5]");
        System.out.println("  [2->6] + [null] = [2->6]");
        System.out.println();
        System.out.println("Round 2: Merge results");
        System.out.println("  [1->1->3->4->4->5] + [2->6] = [1->1->2->3->4->4->5->6]");
        
        ListNode result = solution.mergeKLists(lists);
        System.out.println("\nFinal Result: ");
        printList(result);
        
        System.out.println("\nKey Insight: Like a tournament, we reduce the problem size by half each round!");
    }
    
    // Demonstrate Brute Force approach
    private static void demonstrateBruteForce(ListNode[] lists) {
        System.out.println("Strategy: Collect all values, sort them, rebuild list");
        System.out.println("Simple but not memory efficient!\n");
        
        BruteForceSolution solution = new BruteForceSolution();
        
        System.out.println("Step-by-step execution:");
        System.out.println("Step 1: Extract all values");
        System.out.println("  From [1->4->5]: extract [1, 4, 5]");
        System.out.println("  From [1->3->4]: extract [1, 3, 4]");
        System.out.println("  From [2->6]: extract [2, 6]");
        System.out.println("  All values: [1, 4, 5, 1, 3, 4, 2, 6]");
        System.out.println();
        System.out.println("Step 2: Sort all values");
        System.out.println("  Sorted: [1, 1, 2, 3, 4, 4, 5, 6]");
        System.out.println();
        System.out.println("Step 3: Build new linked list");
        System.out.println("  Create: 1->1->2->3->4->4->5->6");
        
        ListNode result = solution.mergeKLists(lists);
        System.out.println("\nFinal Result: ");
        printList(result);
        
        System.out.println("\nKey Insight: Sometimes the simplest approach works, but at a cost!");
    }
    
    // Demonstrate One by One approach
    private static void demonstrateOneByOne(ListNode[] lists) {
        System.out.println("Strategy: Merge lists one by one with running result");
        System.out.println("Like adding ingredients to a recipe one at a time!\n");
        
        OneByOneSolution solution = new OneByOneSolution();
        
        System.out.println("Step-by-step execution:");
        System.out.println("Initial: result = null");
        System.out.println();
        System.out.println("Step 1: Merge result + [1->4->5]");
        System.out.println("  null + [1->4->5] = [1->4->5]");
        System.out.println();
        System.out.println("Step 2: Merge result + [1->3->4]");
        System.out.println("  [1->4->5] + [1->3->4] = [1->1->3->4->4->5]");
        System.out.println();
        System.out.println("Step 3: Merge result + [2->6]");
        System.out.println("  [1->1->3->4->4->5] + [2->6] = [1->1->2->3->4->4->5->6]");
        System.out.println();
        System.out.println("Step 4: Merge result + null");
        System.out.println("  [1->1->2->3->4->4->5->6] + null = [1->1->2->3->4->4->5->6]");
        
        ListNode result = solution.mergeKLists(lists);
        System.out.println("\nFinal Result: ");
        printList(result);
        
        System.out.println("\nKey Insight: Simple to understand, but inefficient for large k!");
    }
    
    // Demonstrate Priority Queue approach
    private static void demonstratePriorityQueue(ListNode[] lists) {
        System.out.println("Strategy: Use min-heap to always pick the smallest available element");
        System.out.println("Like managing multiple queues at once!\n");
        
        System.out.println("Step-by-step execution:");
        System.out.println("Initial heap: [1(list1), 1(list2), 2(list3)]");
        System.out.println();
        System.out.println("Step 1: Poll min=1(list1), add 4(list1)");
        System.out.println("  Result: [1], Heap: [1(list2), 2(list3), 4(list1)]");
        System.out.println();
        System.out.println("Step 2: Poll min=1(list2), add 3(list2)");
        System.out.println("  Result: [1,1], Heap: [2(list3), 4(list1), 3(list2)]");
        System.out.println();
        System.out.println("Step 3: Poll min=2(list3), add 6(list3)");
        System.out.println("  Result: [1,1,2], Heap: [3(list2), 4(list1), 6(list3)]");
        System.out.println();
        System.out.println("Continue until heap is empty...");
        
        PriorityQueueSolution solution = new PriorityQueueSolution();
        ListNode result = solution.mergeKLists(lists);
        System.out.println("\nFinal Result: ");
        printList(result);
        
        System.out.println("\nKey Insight: Heap automatically maintains order, very elegant!");
    }
    
    // Compare time and space complexities
    private static void compareComplexities() {
        System.out.println("N = total number of nodes, k = number of lists\n");
        
        System.out.println("1. DIVIDE AND CONQUER:");
        System.out.println("   Time:  O(N log k) - Best overall performance");
        System.out.println("   Space: O(log k) - Recursion stack space");
        System.out.println("   Pros:  Most efficient, good for large inputs");
        System.out.println("   Cons:  Slightly more complex to implement");
        System.out.println();
        
        System.out.println("2. BRUTE FORCE:");
        System.out.println("   Time:  O(N log N) - Depends on sorting algorithm");
        System.out.println("   Space: O(N) - Store all values");
        System.out.println("   Pros:  Simplest to understand and implement");
        System.out.println("   Cons:  Uses extra space, destroys original structure");
        System.out.println();
        
        System.out.println("3. ONE BY ONE:");
        System.out.println("   Time:  O(k * N) - Can be very slow for large k");
        System.out.println("   Space: O(1) - No extra space needed");
        System.out.println("   Pros:  Minimal space usage, preserves structure");
        System.out.println("   Cons:  Worst time complexity for large k");
        System.out.println();
        
        System.out.println("4. PRIORITY QUEUE:");
        System.out.println("   Time:  O(N log k) - Same as divide and conquer");
        System.out.println("   Space: O(k) - Heap stores at most k nodes");
        System.out.println("   Pros:  Elegant, good performance, easy to understand");
        System.out.println("   Cons:  Extra space for heap");
        System.out.println();
        
        System.out.println("RECOMMENDATION:");
        System.out.println("- Use DIVIDE AND CONQUER for best performance");
        System.out.println("- Use PRIORITY QUEUE for clarity and good performance");
        System.out.println("- Use BRUTE FORCE for simplicity when N is small");
        System.out.println("- Avoid ONE BY ONE when k is large");
    }
    
    // Helper methods for testing and display
    private static ListNode[] createTestData() {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);
        
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        
        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);
        
        return new ListNode[]{list1, list2, list3, null};
    }
    
    private static void printAllLists(ListNode[] lists) {
        for (int i = 0; i < lists.length; i++) {
            System.out.print("List " + (i + 1) + ": ");
            printList(lists[i]);
        }
    }
    
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    private static ListNode[] deepCopyLists(ListNode[] original) {
        ListNode[] copy = new ListNode[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = copyList(original[i]);
        }
        return copy;
    }
    
    private static ListNode copyList(ListNode head) {
        if (head == null) return null;
        
        ListNode newHead = new ListNode(head.val);
        ListNode current = newHead;
        head = head.next;
        
        while (head != null) {
            current.next = new ListNode(head.val);
            current = current.next;
            head = head.next;
        }
        
        return newHead;
    }
    
    // ListNode definition
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}