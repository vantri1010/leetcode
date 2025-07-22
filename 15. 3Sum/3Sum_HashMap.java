import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Build frequency map
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.get(nums[i]) - 1); // Remove current element
            for (int j = i + 1; j < nums.length; j++) {
                freq.put(nums[j], freq.get(nums[j]) - 1); // Remove second element
                int target = -(nums[i] + nums[j]);
                if (freq.getOrDefault(target, 0) > 0) {
                    List<Integer> triplet = Arrays.asList(Math.min(nums[i], Math.min(nums[j], target)),
                                                         nums[i] + nums[j] + target - Math.min(nums[i], Math.min(nums[j], target)),
                                                         Math.max(nums[i], Math.max(nums[j], target)));
                    result.add(triplet);
                }
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1); // Restore
            }
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1); // Restore
        }
        return new ArrayList<>(result);
    }
}