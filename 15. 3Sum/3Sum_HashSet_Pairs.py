def threeSum(nums):
    result = set()
    nums.sort()
    for i in range(len(nums) - 2):
        if i > 0 and nums[i] == nums[i - 1]:
            continue
        seen = set()
        for j in range(i + 1, len(nums)):
            target = -(nums[i] + nums[j])
            if target in seen:
                result.add((nums[i], target, nums[j]))
            seen.add(nums[j])
    return [list(triplet) for triplet in result]