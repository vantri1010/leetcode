class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        Arrays.fill(output, 1);

        int factor = 1;
        for(int i = 0; i < nums.length; i++){
            output[i] = output[i] * factor;
            factor = factor * nums[i];
        }

        factor = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            output[i] = output[i] * factor;
            factor = factor * nums[i];
        }

        return output;
    }
}