class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        // Optimization 1: Sort smaller array first to minimize space
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        
        // Optimization 2: Use ArrayList for dynamic sizing
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        
        // Two-pointer technique
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        // Optimization 3: Direct conversion to int array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

// Alternative HashMap approach for unsorted arrays
class SolutionHashMap {
    public int[] intersect(int[] nums1, int[] nums2) {
        // Optimization: Use smaller array for HashMap
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        
        // Count frequencies in smaller array
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums1) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        List<Integer> result = new ArrayList<>();
        
        // Check each element in larger array
        for (int num : nums2) {
            if (countMap.containsKey(num) && countMap.get(num) > 0) {
                result.add(num);
                countMap.put(num, countMap.get(num) - 1);
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}