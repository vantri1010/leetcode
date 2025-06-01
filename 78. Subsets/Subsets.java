class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        backtrack(nums, output, 0, new ArrayList<>());
        return output;
    }

    private void backtrack(int[] nums, List<List<Integer>> output, int start, List<Integer> path) {
        output.add(new ArrayList<>(path)); // Add a copy of the current subset
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]); // Include nums[i]
            backtrack(nums, output, i + 1, path); // Recurse
            path.remove(path.size() - 1); // Backtrack (exclude nums[i])
        }
    }
}