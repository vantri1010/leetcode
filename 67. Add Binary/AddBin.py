class Solution:
    def addBinary(self, a: str, b: str) -> str:
        output = []
        carry = 0
        a_len, b_len = len(a) - 1, len(b) - 1

        while a_len >= 0 or b_len >= 0 or carry == 1:
            if a_len >= 0:
                carry += int(a[a_len])
                a_len -= 1
            if b_len >= 0:
                carry += int(b[b_len])
                b_len -= 1
            output.append(str(carry % 2))
            carry //= 2

        return ''.join(reversed(output))