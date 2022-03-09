package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class BeautifulNumbers {
	static long[] dp = new long[1000005];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter wr = new PrintWriter(System.out);

		// pre-process
		preProcess();

		int T = Integer.parseInt(br.readLine());
		assert (1 <= T && T <= 1000000);
		StringBuffer op = new StringBuffer();

		for (int t_i = 0; t_i < T; t_i++) {
			String[] ip = br.readLine().split(" ");
			int l = Integer.parseInt(ip[0]);
			int r = Integer.parseInt(ip[1]);
			op.append(dp[r] - dp[l - 1] + "\n");
		}
		br.close();
		wr.write(op.toString());
		wr.close();
	}

	static void preProcess() {
		for (int num = 1; num <= 1000000; num++) {
			if (isBeautiful(num)) {
				dp[num] = num;
			}
		}

		for (int i = 1; i < dp.length; i++)
			dp[i] += dp[i - 1];
	}

	static boolean isBeautiful(int u) {
		int cnt = 60;
		while (cnt-- > 0) {
			int ans = 0;
			while (u > 0) {
				ans += (u % 10) * (u % 10);
				u /= 10;
			}
			u = ans;
			if (u == 1)
				return true;
		}
		return false;
	}
}