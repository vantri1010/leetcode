/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        // Step 1: Find maximum depth
        int maxDepth = getMaxDepth(nestedList);
        // Step 2: Compute weighted sum with inverse depth
        return computeWeightedSum(nestedList, 1, maxDepth);
    }
    
    private int getMaxDepth(List<NestedInteger> list) {
        int maxDepth = 1;
        for (NestedInteger ni : list) {
            if (!ni.isInteger()) {
                maxDepth = Math.max(maxDepth, 1 + getMaxDepth(ni.getList()));
            }
        }
        return maxDepth;
    }
    
    private int computeWeightedSum(List<NestedInteger> list, int depth, int maxDepth) {
        int sum = 0;
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * (maxDepth - depth + 1);
            } else {
                sum += computeWeightedSum(ni.getList(), depth + 1, maxDepth);
            }
        }
        return sum;
    }
}