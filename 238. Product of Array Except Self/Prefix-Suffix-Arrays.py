def productExceptSelf(nums):
    n = len(nums)
    prefix = [1] * n
    suffix = [1] * n
    ans = [0] * n
    
    # Compute prefix products
    for i in range(1, n):
        prefix[i] = prefix[i-1] * nums[i-1]
    
    # Compute suffix products
    for i in range(n-2, -1, -1):
        suffix[i] = suffix[i+1] * nums[i+1]
    
    # Combine
    for i in range(n):
        ans[i] = prefix[i] * suffix[i]
    
    return ans