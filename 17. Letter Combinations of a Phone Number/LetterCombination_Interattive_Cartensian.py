class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []
        
        table = [[], [], ['a', 'b', 'c'], ['d', 'e', 'f'], ['g', 'h', 'i'],
                 ['j', 'k', 'l'], ['m', 'n', 'o'], ['p', 'q', 'r', 's'],
                 ['t', 'u', 'v'], ['w', 'x', 'y', 'z']]
        
        result = [""]
        for digit in digits:
            num = int(digit)
            result = [prefix + c for prefix in result for c in table[num]]
        
        return result