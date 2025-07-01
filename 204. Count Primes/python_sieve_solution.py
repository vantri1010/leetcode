class Solution:
    def countPrimes(self, n: int) -> int:
        if n <= 2:
            return 0
        
        # Initialize all numbers as potentially prime
        is_prime = [True] * n
        is_prime[0] = is_prime[1] = False
        
        # Sieve of Eratosthenes
        for i in range(2, int(n**0.5) + 1):
            if is_prime[i]:
                # Mark multiples of i starting from i²
                for j in range(i * i, n, i):
                    is_prime[j] = False
        
        # Count primes
        return sum(is_prime)

# Alternative more Pythonic version
class Solution:
    def countPrimes(self, n: int) -> int:
        if n <= 2:
            return 0
        
        is_prime = [True] * n
        is_prime[0] = is_prime[1] = False
        
        for i in range(2, int(n**0.5) + 1):
            if is_prime[i]:
                is_prime[i*i::i] = [False] * len(range(i*i, n, i))
        
        return sum(is_prime)