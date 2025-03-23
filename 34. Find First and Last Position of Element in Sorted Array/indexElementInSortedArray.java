class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] output = { -1, -1 };
        output[0] = leftSearch(nums, target);
        output[1] = rightSearch(nums, target);
        return output;
    }

    public int leftSearch(int[] nums, int target) {
        int index = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }

            if (nums[mid] == target)
                index = mid;
        }
        return index;
    }

    public int rightSearch(int[] nums, int target) {
        int index = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] <= target) {
                start = mid + 1;
            }

            if (nums[mid] == target)
                index = mid;
        }
        return index;
    }
}