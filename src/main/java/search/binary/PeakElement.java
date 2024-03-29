package search.binary;

class PeakElement {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};

        PeakElement pe = new PeakElement();
        int idx = pe.findPeakElement(nums);

        System.out.println("peak element is at index: " + idx);
        System.out.println("peak element is: " + nums[idx]);
    }

    int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;

        int n = nums.length - 1;
        int l = 0, r = n, m = 0;

        while (l <= r) {
            m = l + (r - l) / 2;

            if ((m == 0 || nums[m - 1] <= nums[m]) && (m == n || nums[m] >= nums[m + 1]))
                return m;
            else if (m > 0 && nums[m - 1] > nums[m])
                r = m - 1;
            else
                l = m + 1;
        }
        return m;
    }
}
