# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Codec:
    def serialize(self, root: Optional[TreeNode]) -> str:
        if not root:
            return "null"
        return f"{root.val},{self.serialize(root.left)},{self.serialize(root.right)}"
    
    def deserialize(self, data: str) -> Optional[TreeNode]:
        if not data:
            return None
        nodes = deque(data.split(","))
        return self.helper(nodes)
    
    def helper(self, nodes: deque) -> Optional[TreeNode]:
        val = nodes.popleft()
        if val == "null":
            return None
        node = TreeNode(int(val))
        node.left = self.helper(nodes)
        node.right = self.helper(nodes)
        return node
        

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))