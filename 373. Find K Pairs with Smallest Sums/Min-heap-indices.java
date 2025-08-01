class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        for (int j = 0; j < nums2.length && j < k; j++) {
            pq.offer(new int[]{0, j});
        }
        
        while (k-- > 0 && !pq.isEmpty()) {
            int[] curr = pq.poll();
            res.add(Arrays.asList(nums1[curr[0]], nums2[curr[1]]));
            if (curr[0] + 1 < nums1.length) {
                pq.offer(new int[]{curr[0] + 1, curr[1]});
            }
        }
        return res;
    }
}