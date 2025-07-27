class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Min-heap: stores [sum, i, j]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<String> visited = new HashSet<>();
        
        // Start with the smallest possible pair
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add("0,0");
        
        List<List<Integer>> result = new ArrayList<>();
        
        while (result.size() < k && !minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int i = current[1], j = current[2];
            
            result.add(Arrays.asList(nums1[i], nums2[j]));
            
            // Add adjacent pairs: (i+1, j) and (i, j+1)
            if (i + 1 < nums1.length && !visited.contains((i + 1) + "," + j)) {
                minHeap.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                visited.add((i + 1) + "," + j);
            }
            
            if (j + 1 < nums2.length && !visited.contains(i + "," + (j + 1))) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                visited.add(i + "," + (j + 1));
            }
        }
        
        return result;
    }
}