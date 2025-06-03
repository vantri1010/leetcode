/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * Validates if a binary tree is a valid binary search tree (BST).
     * @param root Root node of the binary tree
     * @return True if the tree is a valid BST, false otherwise
     */
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Helper method to validate BST using range-based recursion.
     * @param node Current node
     * @param min Minimum allowable value (exclusive)
     * @param max Maximum allowable value (exclusive)
     * @return True if the subtree is a valid BST, false otherwise
     */
    private boolean helper(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;

        return helper(node.left, min, node.val) && helper(node.right, node.val, max);
    }
}