class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int output = max;
        for (int i = 1; i < nums.length; i++) {
            int preMax = max;
            int cur = nums[i];
            max = Math.max(cur, Math.max(cur * preMax, cur * min));
            min = Math.min(cur, Math.min(cur * preMax, cur * min));
            output = Math.max(output, max);
        }
        return output;
    }
}