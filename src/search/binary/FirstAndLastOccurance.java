package search.binary;

import java.util.Arrays;

class FirstAndLastOccurance {

	public static void main(String[] args) {
		int[] nums = { 5, 7, 7, 8, 8, 10 };
		int target = 8;

		int[] result = searchRange(nums, target);

		Arrays.stream(result).forEach(t -> System.out.print(t + " "));
	}

	public static int[] searchRange(int[] nums, int target) {
		if (nums.length == 0)
			return new int[] { -1, -1 };

		int[] res = new int[2];

		res[0] = firstOccurance(nums, target);
		res[1] = lastOccurance(nums, target);

		return res;
	}

	private static int firstOccurance(int[] nums, int k) {
		int l = 0, r = nums.length - 1, f = -1;

		while (l <= r) {
			int m = l + (r - l) / 2;

			if (nums[m] == k) {
				f = m;
				r = m - 1;
			} else if (nums[m] < k)
				l = m + 1;
			else
				r = m - 1;
		}

		return f;
	}

	private static int lastOccurance(int[] nums, int k) {
		int l = 0, r = nums.length - 1, f = -1;

		while (l <= r) {
			int m = l + (r - l) / 2;

			if (nums[m] == k) {
				f = m;
				l = m + 1;
			} else if (nums[m] < k)
				l = m + 1;
			else
				r = m - 1;
		}

		return f;
	}

}
