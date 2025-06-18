class Solution {
    /**
     * Evaluates a string expression with integers, +, -, and parentheses using recursion.
     * @param s The input expression string
     * @return The evaluated result
     */
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        return evaluate(s, new int[]{0})[0];
    }

    /**
     * Helper method to evaluate the expression from a given index.
     * @param s The input string
     * @param index Array containing the current index (passed as reference)
     * @return Array of [result, new_index]
     */
    private int[] evaluate(String s, int[] index) {
        int result = 0;
        int sign = 1;
        int num = 0;

        while (index[0] < s.length()) {
            char c = s.charAt(index[0]);
            index[0]++;

            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-' || c == ')') {
                result += sign * num;
                num = 0;
                if (c == '+') {
                    sign = 1;
                } else if (c == '-') {
                    sign = -1;
                } else if (c == ')') {
                    return new int[]{result, index[0]};
                }
            } else if (c == '(') {
                int[] subResult = evaluate(s, index);
                result += sign * subResult[0];
                sign = 1;
            }
        }

        result += sign * num;
        return new int[]{result, index[0]};
    }
}