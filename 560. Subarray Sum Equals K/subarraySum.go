func subarraySum(nums []int, k int) int {
	var sum, result int
	preSum := make(map[int]int)
	preSum[0] = 1

	for _, num := range nums {
		sum += num
		if val, ok := preSum[sum-k]; ok {
			result += val
		}
		preSum[sum] += 1
	}

	return result
}