/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    /**
     * Serializes a binary tree to a string using preorder traversal with StringBuilder.
     * @param root Root node of the binary tree
     * @return A comma-separated string representing the tree
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    /**
     * Helper method to serialize the tree recursively.
     * @param node Current node
     * @param sb StringBuilder to append node values
     */
    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    /**
     * Deserializes a string to a binary tree using preorder traversal with Iterator.
     * @param data Comma-separated string representing the tree
     * @return Root node of the reconstructed tree
     */
    public TreeNode deserialize(String data) {
        Iterator<String> tokens = Arrays.asList(data.split(",")).iterator();
        return deserializeHelper(tokens);
    }

    /**
     * Helper method to recursively construct the tree from an Iterator of node values.
     * @param tokens Iterator of string values representing nodes or "null"
     * @return Root node of the constructed subtree
     */
    private TreeNode deserializeHelper(Iterator<String> tokens) {
        if (!tokens.hasNext()) {
            return null;
        }
        String val = tokens.next();
        if ("null".equals(val)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(tokens);
        node.right = deserializeHelper(tokens);
        return node;
    }
}