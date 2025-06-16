# Approach 1: Stack-Based with Sign Tracking
# Time: O(n), Space: O(n)
# Best for: General use, handles nested parentheses efficiently

class Solution1:
    def calculate(self, s: str) -> int:
        stack = []
        result = 0
        number = 0
        sign = 1
        
        for char in s:
            if char.isdigit():
                number = number * 10 + int(char)
            elif char in '+-':
                result += sign * number
                number = 0
                sign = 1 if char == '+' else -1
            elif char == '(':
                stack.extend([result, sign])
                result = 0
                sign = 1
            elif char == ')':
                result += sign * number
                number = 0
                prev_sign = stack.pop()
                prev_result = stack.pop()
                result = prev_result + prev_sign * result
        
        return result + sign * number


# Approach 2: Recursive Descent Parser
# Time: O(n), Space: O(d) where d is max nesting depth
# Best for: Clean, mathematical approach, easy to extend

class Solution2:
    def calculate(self, s: str) -> int:
        self.index = 0
        return self._parse_expression(s)
    
    def _parse_expression(self, s: str) -> int:
        result = 0
        sign = 1
        
        while self.index < len(s):
            char = s[self.index]
            
            if char == ' ':
                self.index += 1
            elif char.isdigit():
                number = self._parse_number(s)
                result += sign * number
            elif char == '+':
                sign = 1
                self.index += 1
            elif char == '-':
                sign = -1
                self.index += 1
            elif char == '(':
                self.index += 1  # Skip '('
                sub_result = self._parse_expression(s)
                result += sign * sub_result
            elif char == ')':
                self.index += 1  # Skip ')'
                break
        
        return result
    
    def _parse_number(self, s: str) -> int:
        number = 0
        while self.index < len(s) and s[self.index].isdigit():
            number = number * 10 + int(s[self.index])
            self.index += 1
        return number


# Approach 3: Two-Stack Method (Operands + Operators)
# Time: O(n), Space: O(n)
# Best for: Understanding operator precedence, easy to extend for */

class Solution3:
    def calculate(self, s: str) -> int:
        operands = []
        operators = []
        i = 0
        
        while i < len(s):
            if s[i] == ' ':
                i += 1
                continue
            
            if s[i].isdigit():
                num = 0
                while i < len(s) and s[i].isdigit():
                    num = num * 10 + int(s[i])
                    i += 1
                operands.append(num)
            elif s[i] in '+-':
                while (operators and operators[-1] != '(' and 
                       operators[-1] in '+-'):
                    self._apply_operation(operands, operators)
                operators.append(s[i])
                i += 1
            elif s[i] == '(':
                operators.append(s[i])
                i += 1
            elif s[i] == ')':
                while operators and operators[-1] != '(':
                    self._apply_operation(operands, operators)
                operators.pop()  # Remove '('
                i += 1
        
        while operators:
            self._apply_operation(operands, operators)
        
        return operands[0] if operands else 0
    
    def _apply_operation(self, operands, operators):
        if len(operands) < 2 or not operators:
            return
        
        b = operands.pop()
        a = operands.pop()
        op = operators.pop()
        
        if op == '+':
            operands.append(a + b)
        elif op == '-':
            operands.append(a - b)


# Approach 4: String Replacement (Iterative)
# Time: O(n²), Space: O(n)
# Best for: Simple logic, but less efficient

class Solution4:
    def calculate(self, s: str) -> int:
        s = s.replace(' ', '')
        
        while '(' in s:
            # Find innermost parentheses
            start = -1
            for i in range(len(s)):
                if s[i] == '(':
                    start = i
                elif s[i] == ')':
                    # Evaluate expression between start and i
                    sub_expr = s[start + 1:i]
                    result = self._evaluate_simple(sub_expr)
                    s = s[:start] + str(result) + s[i + 1:]
                    break
        
        return self._evaluate_simple(s)
    
    def _evaluate_simple(self, expr: str) -> int:
        if not expr:
            return 0
        
        result = 0
        current_num = 0
        sign = 1
        
        for char in expr + '+':  # Add '+' to process last number
            if char.isdigit():
                current_num = current_num * 10 + int(char)
            else:
                result += sign * current_num
                current_num = 0
                sign = 1 if char == '+' else -1
        
        return result


# Approach 5: State Machine
# Time: O(n), Space: O(n)
# Best for: Complex parsing requirements, highly extensible

class Solution5:
    def calculate(self, s: str) -> int:
        stack = [0]  # Initialize with 0 for the outermost level
        sign = 1
        number = 0
        
        for char in s:
            if char.isdigit():
                number = number * 10 + int(char)
            elif char in '+-':
                stack[-1] += sign * number
                number = 0
                sign = 1 if char == '+' else -1
            elif char == '(':
                stack.append(sign * stack[-1])
                stack.append(0)
                sign = 1
            elif char == ')':
                stack[-1] += sign * number
                number = 0
                temp = stack.pop()
                stack[-1] += temp
        
        return stack[-1] + sign * number


# Approach 6: Single Pass without Stack (Space Optimized)
# Time: O(n²) worst case, Space: O(1)
# Best for: Memory-constrained environments

class Solution6:
    def calculate(self, s: str) -> int:
        def find_matching_paren(start: int) -> int:
            count = 1
            i = start + 1
            while i < len(s) and count > 0:
                if s[i] == '(':
                    count += 1
                elif s[i] == ')':
                    count -= 1
                i += 1
            return i - 1
        
        def evaluate_range(start: int, end: int) -> int:
            result = 0
            number = 0
            sign = 1
            i = start
            
            while i <= end:
                if s[i] == ' ':
                    i += 1
                elif s[i].isdigit():
                    number = number * 10 + int(s[i])
                    i += 1
                elif s[i] in '+-':
                    result += sign * number
                    number = 0
                    sign = 1 if s[i] == '+' else -1
                    i += 1
                elif s[i] == '(':
                    close_pos = find_matching_paren(i)
                    sub_result = evaluate_range(i + 1, close_pos - 1)
                    result += sign * sub_result
                    number = 0
                    i = close_pos + 1
            
            return result + sign * number
        
        return evaluate_range(0, len(s) - 1)


# Test all approaches
def test_solutions():
    test_cases = [
        "1 + 1",
        " 2-1 + 2 ",
        "(1+(4+5+2)-3)+(6+8)",
        "1-(     -2)",
        "2147483647",
        "1-11",
        "(7)-(0)+(4)"
    ]
    
    solutions = [Solution1(), Solution2(), Solution3(), 
                Solution4(), Solution5(), Solution6()]
    
    for i, test in enumerate(test_cases):
        print(f"Test {i + 1}: {test}")
        for j, sol in enumerate(solutions):
            try:
                result = sol.calculate(test)
                print(f"  Approach {j + 1}: {result}")
            except Exception as e:
                print(f"  Approach {j + 1}: Error - {e}")
        print()

# Uncomment to run tests
# test_solutions()