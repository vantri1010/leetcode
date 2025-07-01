# Approach 1: Segmented Sieve (Memory Optimized)
class Solution:
    def countPrimes(self, n: int) -> int:
        if n <= 2:
            return 0
        
        # First find all primes up to sqrt(n)
        limit = int(n**0.5) + 1
        is_prime = [True] * limit
        is_prime[0] = is_prime[1] = False
        
        for i in range(2, int(limit**0.5) + 1):
            if is_prime[i]:
                for j in range(i * i, limit, i):
                    is_prime[j] = False
        
        primes = [i for i in range(2, limit) if is_prime[i]]
        
        # Count primes in segments
        segment_size = max(limit, 32768)  # Use at least 32KB segments
        count = len([p for p in primes if p < n])
        
        for low in range(limit, n, segment_size):
            high = min(low + segment_size, n)
            segment = [True] * (high - low)
            
            for prime in primes:
                # Find first multiple of prime in [low, high)
                start = max(prime * prime, ((low + prime - 1) // prime) * prime)
                for j in range(start, high, prime):
                    segment[j - low] = False
            
            count += sum(segment)
        
        return count

# Approach 2: Optimized Sieve with Odd Numbers Only
class Solution:
    def countPrimes(self, n: int) -> int:
        if n <= 2:
            return 0
        if n == 3:
            return 1
        
        # Only consider odd numbers (except 2)
        # is_prime[i] represents whether (2*i + 3) is prime
        size = (n - 3) // 2 + 1
        is_prime = [True] * size
        
        for i in range(int((n**0.5 - 3) / 2) + 1):
            if is_prime[i]:
                num = 2 * i + 3
                # Mark multiples starting from num²
                start = (num * num - 3) // 2
                for j in range(start, size, num):
                    is_prime[j] = False
        
        return 1 + sum(is_prime)  # +1 for prime number 2

# Approach 3: Wheel Factorization (2, 3, 5)
class Solution:
    def countPrimes(self, n: int) -> int:
        if n <= 2:
            return 0
        
        # Wheel factorization with 2, 3, 5
        # Only check numbers of form 30k + r where r is coprime to 30
        wheel = [4, 6, 10, 12, 16, 18, 22, 24]  # offsets for 30k + r
        
        # Count small primes manually
        small_primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
        count = sum(1 for p in small_primes if p < n)
        
        if n <= 30:
            return count
        
        # Sieve for numbers >= 30
        limit = ((n - 30) // 30 + 1) * len(wheel)
        is_prime = [True] * limit
        
        def get_number(idx):
            """Convert wheel index to actual number"""
            return 30 * (idx // len(wheel)) + wheel[idx % len(wheel)] + 30
        
        for i in range(limit):
            if is_prime[i]:
                num = get_number(i)
                if num * num >= n:
                    break
                
                # Mark multiples
                for j in range(i + num, limit, num):
                    if get_number(j) < n:
                        is_prime[j] = False
        
        # Count remaining primes
        for i in range(limit):
            if is_prime[i] and get_number(i) < n:
                count += 1
        
        return count

# Approach 4: Probabilistic Miller-Rabin (for very large n)
import random

class Solution:
    def countPrimes(self, n: int) -> int:
        if n <= 2:
            return 0
        
        def miller_rabin(n, k=5):
            """Miller-Rabin primality test"""
            if n == 2 or n == 3:
                return True
            if n < 2 or n % 2 == 0:
                return False
            
            # Write n-1 as 2^r * d
            r = 0
            d = n - 1
            while d % 2 == 0:
                r += 1
                d //= 2
            
            # Witness loop
            for _ in range(k):
                a = random.randint(2, n - 2)
                x = pow(a, d, n)
                
                if x == 1 or x == n - 1:
                    continue
                
                for _ in range(r - 1):
                    x = pow(x, 2, n)
                    if x == n - 1:
                        break
                else:
                    return False
            
            return True
        
        # For small n, use simple sieve
        if n <= 10000:
            is_prime = [True] * n
            is_prime[0] = is_prime[1] = False
            
            for i in range(2, int(n**0.5) + 1):
                if is_prime[i]:
                    for j in range(i * i, n, i):
                        is_prime[j] = False
            
            return sum(is_prime)
        
        # For large n, use Miller-Rabin
        count = 0
        for i in range(2, n):
            if miller_rabin(i):
                count += 1
        
        return count