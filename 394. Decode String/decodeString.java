class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()){
            if (c != ']'){
                stack.push(c);
            } else {
                StringBuilder ca = new StringBuilder();
                while(!stack.isEmpty() && Character.isLetter(stack.peek())){
                    ca.insert(0, stack.pop());
                }
                String tmpCa = ca.toString();
                stack.pop();

                StringBuilder digit = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())){
                    digit.insert(0, stack.pop());
                }

                int count = Integer.valueOf(digit.toString());

                while (count > 0) {
                    for (char i : tmpCa.toCharArray()){
                        stack.push(i);
                    }
                    count--;
                }
            }
        }

        StringBuilder output = new StringBuilder();
        while(!stack.isEmpty()){
            output.insert(0, stack.pop());
        }
        return output.toString();
    }
}