class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 == 1)
            return false;

        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i : nums) {
            for (int j = sum; j >= 0; j--) {
                if (j >= i)
                    dp[j] = dp[j] || dp[j - i];
            }
        }

        return dp[sum];
    }
}