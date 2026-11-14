import java.util.*;

public class Week4Arrays {

    // A1: product of array except self, no division
    static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        // left pass: ans[i] holds product of everything left of i
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        // right pass: multiply by running product of everything right of i
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * right;
            right = right * nums[i];
        }
        return ans;
    }

    // A2: maximum subarray (Kadane)
    static int maxSubArray(int[] nums) {
        int current = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // either extend the previous run or start fresh here
            current = Math.max(nums[i], current + nums[i]);
            best = Math.max(best, current);
        }
        return best;
    }

    // A3: 3Sum, sort + two pointers, skip duplicates
    static int[][] threeSum(int[] nums) {
        int[] a = nums.clone();
        Arrays.sort(a);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < a.length - 2; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue;
            int lo = i + 1, hi = a.length - 1;
            while (lo < hi) {
                int sum = a[i] + a[lo] + a[hi];
                if (sum == 0) {
                    res.add(new int[]{a[i], a[lo], a[hi]});
                    lo++;
                    hi--;
                    while (lo < hi && a[lo] == a[lo - 1]) lo++;
                    while (lo < hi && a[hi] == a[hi + 1]) hi--;
                } else if (sum < 0) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return res.toArray(new int[0][]);
    }

    // A4: subarray sum equals k using prefix sums + hashmap
    static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, 1); // empty prefix
        int sum = 0, count = 0;
        for (int x : nums) {
            sum += x;
            if (seen.containsKey(sum - k)) {
                count += seen.get(sum - k);
            }
            seen.put(sum, seen.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // A5: minimum in rotated sorted array, binary search
    static int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1; // min is on the right side
            } else {
                hi = mid;     // min is mid or on the left
            }
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        System.out.println("A1: " + Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println("A1: " + Arrays.toString(productExceptSelf(new int[]{-1, 1, 0, -3, 3})));

        System.out.println("A2: " + maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println("A2: " + maxSubArray(new int[]{-3, -1, -2}));

        System.out.println("A3: " + Arrays.deepToString(threeSum(new int[]{-1, 0, 1, 2, -1, -4})));
        System.out.println("A3: " + Arrays.deepToString(threeSum(new int[]{0, 0, 0})));

        System.out.println("A4: " + subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println("A4: " + subarraySum(new int[]{1, -1, 0}, 0));

        System.out.println("A5: " + findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println("A5: " + findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println("A5: " + findMin(new int[]{11, 13, 15, 17}));
    }
}
