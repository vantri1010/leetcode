def productExceptSelf(nums):
    n = len(nums)
    ans = [0] * n
    
    # First pass: Compute prefix products
    left = 1
    for i in range(n):
        ans[i] = left
        left *= nums[i]
    
    # Second pass: Compute suffix products and multiply
    right = 1
    for i in range(n-1, -1, -1):
        ans[i] *= right
        right *= nums[i]
    
    return ans