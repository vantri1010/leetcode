class Solution {
    public int search(int[] nums, int target) {
        int start  = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;
            if (nums[mid] < nums[end]) {
                if(nums[mid] < target && nums[end] >= target){
                    start = mid + 1;
                }else{
                    end = mid;
                }
            }else{
                if (target > nums[mid] || target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}