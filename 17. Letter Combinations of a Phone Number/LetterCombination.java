class Solution {
    public String[] table = { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        LinkedList<String> output = new LinkedList<>();

        if (digits == null || digits.length() == 0) {
            return output;
        }

        output.add("");
        while (output.peek().length() != digits.length()) {
            String cur = output.poll();
            //In ASCII '0' = 48, 1='49' etc.
            int num = digits.charAt(cur.length()) - '0';
            for (char c : table[num].toCharArray()) {
                output.add(cur + c);
            }
        }
        return output;
    }
}