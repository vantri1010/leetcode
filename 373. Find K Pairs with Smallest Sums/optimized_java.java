class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Max-heap to maintain k smallest pairs
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] + b[1]) - (a[0] + a[1]));
        
        int m = nums1.length, n = nums2.length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = nums1[i] + nums2[j];
                
                if (pq.size() < k) {
                    pq.offer(new int[]{nums1[i], nums2[j]});
                } else if (sum < pq.peek()[0] + pq.peek()[1]) {
                    pq.poll();
                    pq.offer(new int[]{nums1[i], nums2[j]});
                } else {
                    // Since nums2 is sorted, all remaining pairs in this row will have larger sums
                    break;
                }
            }
            
            // Early termination: if current minimum possible sum >= heap's maximum
            if (pq.size() == k && nums1[i] + nums2[0] >= pq.peek()[0] + pq.peek()[1]) {
                break;
            }
        }
        
        // Convert to required format
        List<List<Integer>> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            result.add(Arrays.asList(pair[0], pair[1]));
        }
        
        Collections.reverse(result); // Reverse to get ascending order
        return result;
    }
}