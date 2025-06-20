class Solution:
    def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
        max_depth = self.get_max_depth(nestedList)
        return self.compute_weighted_sum(nestedList, 1, max_depth)
    
    def get_max_depth(self, nested_list: List[NestedInteger]) -> int:
        max_depth = 1
        for ni in nested_list:
            if not ni.isInteger():
                max_depth = max(max_depth, 1 + self.get_max_depth(ni.getList()))
        return max_depth
    
    def compute_weighted_sum(self, nested_list: List[NestedInteger], depth: int, max_depth: int) -> int:
        total = 0
        for ni in nested_list:
            if ni.isInteger():
                total += ni.getInteger() * (max_depth - depth + 1)
            else:
                total += self.compute_weighted_sum(ni.getList(), depth + 1, max_depth)
        return total