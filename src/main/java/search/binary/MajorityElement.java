package search.binary;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class MajorityElement {
	public static void main(String[] args) {
		int[] arr = { 4, 7, 4, 4, 3, 4, 6, 4 };

		MajorityElement me = new MajorityElement();
		String res = "Majority Element in\n" + Arrays.toString(arr) + "\n\n" + "Bruteforce T(n) = O(n^2) : "
				+ me.bruteforce(arr) + "\n" + "Using Map T(n) = O(n) & S(n) = O(n) : " + me.usingMap(arr) + "\n"
				+ "Using Moore's Voting Algorithm T(n) = O(n) & S(n) = O(1) : " + me.usingMooresVotingMethod(arr);

		PrintWriter pw = new PrintWriter(System.out);
		pw.println(res);
		pw.close();
	}

	private int bruteforce(int[] arr) {
		int result = -1, n = arr.length;

		for (int i = 0; i < n; i += 1) {
			int candidate = arr[i], count = 0;
			for (int j = 0; j < n; j += 1) {
				if (candidate == arr[j])
					count += 1;

				if (count > n / 2)
					result = candidate;
			}
		}

		return result;
	}

	private int usingMap(int[] arr) {
		int n = arr.length, result = -1;
		Map<Integer, Integer> elementFrequency = new HashMap<>();
		for (int element : arr) {
			elementFrequency.put(element, elementFrequency.getOrDefault(element, 0) + 1);

			if (elementFrequency.get(element) > n / 2)
				result = element;
		}

		return result;
	}

	private int usingMooresVotingMethod(int[] nums) {
		int n = nums.length;
		int candidate = 0, votes = 0;

		// find a candidate
		for (int x : nums) {
			if (votes == 0)
				candidate = x;

			votes += (x == candidate ? 1 : -1);
		}

		// verify the candidate
		int count = 0;
		for (int x : nums) {
			if (x == candidate)
				count++;

			if (count > n / 2)
				return candidate;
		}

		return -1;
	}
}
