class Solution {
    public int calculate(String str) {
        Stack<Character> stack = new Stack<>();
        if (str == null)
            return 0;

        int total = 0;
        for (char c : str.toCharArray()) {
            if (c == ' ')
                continue;
            if (c != ')') {
                stack.push(c);
            } else {
                String cur = "";
                while (!stack.isEmpty() && stack.peek() != '(') {
                    cur = stack.pop() + cur;
                }
                stack.pop();

                int curInt = cal(cur);
                if (!stack.isEmpty() && stack.peek() == '-' && curInt < 0) {
                    stack.pop();
                    stack.push('+');
                    curInt = -curInt;
                }

                String curString = curInt + ""; // "11"
                for (int i = 0; i < curString.length(); i++) {
                    stack.push(curString.charAt(i));
                }
            }
        }

        String last = "";
        while (!stack.isEmpty()) {
            last = stack.pop() + last;
        }
        total += cal(last);
        return total;
    }

    public int cal(String s) {
        if (s == null)
            return 0;

        int total = 0;
        int last = 0;
        int power = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char cur = s.charAt(i);
            if (cur == ' ')
                continue;
            if (cur == '+') {
                total += last;
                power = 0;
                last = 0;
            } else if (cur == '-') {
                total -= last;
                power = 0;
                last = 0;
            } else {
                last += (cur - '0') * Math.pow(10, power);
                power++;
            }
        }

        total += last;
        return total;
    }
}