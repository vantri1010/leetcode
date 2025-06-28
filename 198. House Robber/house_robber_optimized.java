class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        // prev2: max money up to i-2, prev1: max money up to i-1
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);
        
        // For each house starting from index 2, decide whether to rob it
        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}