from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs):
        if not strs:
            return []
        
        anagram_map = defaultdict(list)
        
        for s in strs:
            # Sắp xếp các ký tự của chuỗi và dùng nó làm key
            sorted_str = ''.join(sorted(s))
            anagram_map[sorted_str].append(s)
        
        return list(anagram_map.values())