
func threeSum(nums []int) [][]int {
	// Sort the input array for easier management
	sort.Ints(nums)
	n := len(nums)
	result := [][]int{}

	for i := 0; i < n-2; i++ {
		// Skip duplicates
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}

		low, high := i+1, n-1
		target := -nums[i]

		for low < high {
			sum := nums[low] + nums[high]

			if sum == target {
				result = append(result, []int{nums[i], nums[low], nums[high]})
				// Skip duplicates
				for low < high && nums[low] == nums[low+1] {
					low++
				}
				for low < high && nums[high] == nums[high-1] {
					high--
				}
				low++
				high--
			} else if sum < target {
				low++
			} else {
				high--
			}
		}
	}

	return result
}