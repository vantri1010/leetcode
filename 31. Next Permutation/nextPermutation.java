class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        int k = nums.length - 2;
        while (k >= 0) {
            if (nums[k] < nums[k + 1])
                break;
            k--;
        }

        if (k < 0) {
            Arrays.sort(nums);
            return;
        }

        for (int i = nums.length - 1; i > k; i--) {
            if (nums[i] > nums[k]) {
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;
                break;
            }
        }
        Arrays.sort(nums, k + 1, nums.length);
    }
}