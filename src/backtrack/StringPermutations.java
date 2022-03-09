package backtrack;

class StringPermutations {

	public static void main(String[] args) {
		String ipStr = "ABC";
		getPermutations(ipStr, "");
	}

	private static void getPermutations(String ip, String op) {
		if (ip.length() == 0) {
			System.out.println(op);
			return;
		}

		for (int x = 0; x < ip.length(); x++) {
			char curr = ip.charAt(x);
			String remIp = ip.substring(0, x).concat(ip.substring(x + 1));
			getPermutations(remIp, op + curr);
		}
	}

	@SuppressWarnings("unused")
	private static void swap(char[] ip, int i, int c) {
		char t = ip[i];
		ip[i] = ip[c];
		ip[c] = t;
	}

}
