class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        int totalSum = 0;
        for (int num : nums) {
            if (num < 0) return false; // Problem assumes positive integers
            totalSum += num;
        }
        if (totalSum % 2 == 1) return false;

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }

        return dp[target];
    }
}