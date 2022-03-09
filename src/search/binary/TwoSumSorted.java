package search.binary;

import java.util.Arrays;

class TwoSumSorted {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        int[] res = twoSum(numbers, target);
        Arrays.stream(res).forEach(e -> System.out.print(e + " "));
    }

    private static int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        int l = 0, r = numbers.length - 1;

        while (l < r) {
            int t = numbers[l] + numbers[r];

            if (t == target) {
                return new int[]{l + 1, r + 1};
            } else if (target < t)
                r = r - 1;
            else
                l = l + 1;
        }

        return new int[]{-1, -1};
    }
}
