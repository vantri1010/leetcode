class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] output = {-1, -1};
        if (nums == null || nums.length == 0) return output;
        
        output[0] = findFirst(nums, target);
        output[1] = findLast(nums, target);
        return output;
    }

    private int findFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end) { // Changed to start < end
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else { // nums[mid] >= target
                end = mid; // Narrow to left, keeping mid in range
            }
        }
        // After loop, start == end, check if it's the target
        return (nums[start] == target) ? start : -1;
    }

    private int findLast(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end) { // Changed to start < end
            int mid = start + (end - start + 1) / 2; // Bias toward right
            if (nums[mid] > target) {
                end = mid - 1;
            } else { // nums[mid] <= target
                start = mid; // Narrow to right, keeping mid in range
            }
        }
        // After loop, start == end, check if it's the target
        return (nums[start] == target) ? start : -1;
    }
}