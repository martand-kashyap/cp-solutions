package dp.pattern.knapsack.zeroone;

import java.io.PrintWriter;

public class SubsetSumPartitionDifferenceGiven {
	private SubsetSumCountOfSubsetsWithGivenSum subsetSumCount;

	public SubsetSumPartitionDifferenceGiven() {
		subsetSumCount = new SubsetSumCountOfSubsetsWithGivenSum();
	}

	private int countSubsetsWithGivenDifferenceR(int[] items, int n, int difference) {
		int sumAll = 0;
		for (int i : items)
			sumAll += i;

		/*-
		  s1 + s2 = sumAll    ......(i)
		  s1 - s2 = difference......(ii)
		  adding (i) & (ii), we get		  
		  => 2s1 = sumAll + difference 
		  => s1 = (sumAll + difference)/2;
		 */

		int s1 = (sumAll + difference) / 2;
		return subsetSumCount.countSubsetsWithSumR(items, s1, n);
	}
	
	private int countSubsetsWithGivenDifferenceM(int[] items, int n, int difference) {
		int sumAll = 0;
		for (int i : items)
			sumAll += i;

		/*-
		  s1 + s2 = sumAll    ......(i)
		  s1 - s2 = difference......(ii)
		  adding (i) & (ii), we get		  
		  => 2s1 = sumAll + difference 
		  => s1 = (sumAll + difference)/2;
		 */

		int s1 = (sumAll + difference) / 2;
		return subsetSumCount.countSubsetsWithSumM(items, s1, n);
	}
	
	private int countSubsetsWithGivenDifferenceT(int[] items, int n, int difference) {
		int sumAll = 0;
		for (int i : items)
			sumAll += i;

		/*-
		  s1 + s2 = sumAll    ......(i)
		  s1 - s2 = difference......(ii)
		  adding (i) & (ii), we get		  
		  => 2s1 = sumAll + difference 
		  => s1 = (sumAll + difference)/2;
		 */

		int s1 = (sumAll + difference) / 2;
		return subsetSumCount.countSubsetsWithSumT(items, s1, n);
	}

	public static void main(String[] args) {
		int[] items = { 2, 3, 1, 4 };
		int n = items.length;
		int difference = 5;

		SubsetSumPartitionDifferenceGiven differenceGiven = new SubsetSumPartitionDifferenceGiven();

		String sb = "Recursive : " + differenceGiven.countSubsetsWithGivenDifferenceR(items, n, difference) + "\n"
				+ "Memoized (Top-Down) : " + differenceGiven.countSubsetsWithGivenDifferenceM(items, n, difference)
				+ "\n" + "Tabulation (Bottom-Up) : "
				+ differenceGiven.countSubsetsWithGivenDifferenceT(items, n, difference);

		PrintWriter pw = new PrintWriter(System.out);
		pw.println(sb);

		pw.close();
	}
}
