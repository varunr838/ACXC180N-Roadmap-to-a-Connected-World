import java.util.HashMap;

public class SmartSubarray {
    public static int longestSubarrayWithSumK(int[] nums, int k) {
        // Map to store (prefixSum, earliest index)
        HashMap<Integer, Integer> prefixSumIndex = new HashMap<>();
        prefixSumIndex.put(0, -1); // For subarrays starting at index 0

        int maxLen = 0;
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // If (prefixSum - k) seen before, subarray [prevIndex+1, i] sums to k
            if (prefixSumIndex.containsKey(prefixSum - k)) {
                int prevIndex = prefixSumIndex.get(prefixSum - k);
                maxLen = Math.max(maxLen, i - prevIndex);
            }

            // Only store the earliest occurrence to maximize subarray length
            prefixSumIndex.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }

    // Example usage
    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        int k = 3;
        System.out.println(longestSubarrayWithSumK(nums, k)); // Output: 4
    }
}
