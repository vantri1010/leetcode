class Solution:
    def calculate(self, s: str) -> int:
        """
        Evaluate a basic mathematical expression with +, -, parentheses.
        
        Args:
            s: String containing the mathematical expression
            
        Returns:
            Integer result of the expression
        """
        if not s:
            return 0
            
        stack = []
        result = 0
        number = 0
        sign = 1  # 1 for positive, -1 for negative
        
        for char in s:
            if char.isdigit():
                number = number * 10 + int(char)
            elif char == '+':
                result += sign * number
                number = 0
                sign = 1
            elif char == '-':
                result += sign * number
                number = 0
                sign = -1
            elif char == '(':
                # Save current state
                stack.extend([result, sign])
                result = 0
                sign = 1
            elif char == ')':
                # Complete current sub-expression
                result += sign * number
                number = 0
                
                # Restore previous state
                prev_sign = stack.pop()
                prev_result = stack.pop()
                result = prev_result + prev_sign * result
            # Spaces are automatically ignored
        
        # Handle the last number
        result += sign * number
        return result