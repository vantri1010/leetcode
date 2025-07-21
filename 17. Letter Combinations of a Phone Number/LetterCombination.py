class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []
        
        table = [[], [], ['a', 'b', 'c'], ['d', 'e', 'f'], ['g', 'h', 'i'],
                 ['j', 'k', 'l'], ['m', 'n', 'o'], ['p', 'q', 'r', 's'],
                 ['t', 'u', 'v'], ['w', 'x', 'y', 'z']]
        
        output = [""]
        while output[0].length < len(digits):
            cur = output.pop(0)
            num = int(digits[len(cur)])
            for c in table[num]:
                output.append(cur + c)
        
        return output