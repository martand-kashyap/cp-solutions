package codeforces;

import java.util.Scanner;

public final class P4A_Watermelon {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.close();

		if (n>=1 && n<=100 && n % 2 == 0)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}
