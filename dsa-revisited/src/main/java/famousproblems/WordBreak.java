package famousproblems;

import java.io.PrintWriter;
import java.util.HashSet;

class WordBreak {
	/*-
	 	Given an input string and a dictionary of words, 
	 	find out if the input string can be segmented into a space-separated sequence of dictionary words. 
	 	
	 	Example: -
		 	Let the dictionary = { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
		 	
		 	Input:  ilike
		 	Output: Yes 
			The string can be segmented as "i like".
			
			Input:  ilikesamsung
			Output: Yes
			The string can be segmented as "i like samsung" or "i like sam sung".
	 */
	public static void main(String[] args) {
		String[] dictionary = { "leet", "code" };
		String input = "leetcode";

		WordBreak wb = new WordBreak();
		String res = "Recursive T(n) = O(n.2^n)) : " + wb.isSplittingPossible(dictionary, input);

		PrintWriter pw = new PrintWriter(System.out);
		pw.println(res);
		pw.close();
	}

	private boolean isSplittingPossible(String[] dict, String input) {
		HashSet<String> dictionary = new HashSet<>(dict.length);
		for (String str : dict)
			dictionary.add(str);

		return false;
	}
}
