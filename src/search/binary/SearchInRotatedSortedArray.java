package search.binary;

class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int n = nums.length, target = 0;

        int minIndex = findMinimumIndex(nums, n);

        int r1 = binarySearch(nums, n, 0, minIndex - 1, target);
        int r2 = binarySearch(nums, n, minIndex, n - 1, target);

        int res = r1 == -1 && r2 == -1 ? -1 : Math.max(r1, r2);

        System.out.println(res);
    }

    private static int findMinimumIndex(int[] nums, int n) {
        int l = 0, r = n - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;
            int prevM = (m - 1 + n) % n;
            int nextM = (m + 1) % n;
            if (nums[prevM] > nums[m] && nums[m] < nums[nextM])
                return m;
            else if (nums[m] > nums[r])
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    private static int binarySearch(int[] nums, int n, int l, int r, int k) {
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == k)
                return m;
            else if (nums[m] > k)
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }
}
