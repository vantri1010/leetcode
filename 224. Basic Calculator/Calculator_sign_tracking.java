class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1; // 1 for positive, -1 for negative
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                // Push current result and sign onto stack
                stack.push(result);
                stack.push(sign);
                // Reset for new sub-expression
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Complete current number
                result += sign * number;
                number = 0;
                
                // Pop sign and previous result
                result *= stack.pop(); // This is the sign before '('
                result += stack.pop(); // This is the result before '('
            }
            // Ignore spaces automatically
        }
        
        // Add the last number
        result += sign * number;
        return result;
    }
}