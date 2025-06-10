class Solution {
    /**
     * Returns an array where ans[i] is the number of 1's in the binary representation of i.
     * @param n The upper bound integer (0 <= n <= 10^5)
     * @return Array of size n+1 with counts of 1's in binary for each i from 0 to n
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 0; // Explicitly set for clarity
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1); // Number of 1's in i = number of 1's in i/2 + LSB
        }
        return ans;
    }
}