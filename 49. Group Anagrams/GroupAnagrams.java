 class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        List<List<String>> output = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String s: strs) {
            char[] cur = s.toCharArray();
            Arrays.sort(cur);
            String sortString = String.valueOf(cur);
            if (!map.containsKey(sortString)) {
                map.put(sortString, new ArrayList<String>());
            }
            map.get(sortString).add(s);
        }

        output = new ArrayList<>(map.values());
        return output;
    }
}