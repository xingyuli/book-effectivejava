package org.swordess.effectivejava;

import java.util.Scanner;

public class Utils {

	private Utils() {
		throw new AssertionError();
	}
	
	public static void exit(int exitStatus) {
		exit("q", exitStatus);
	}

	public static void exit(String exitStr, int exitStatus) {
		Scanner in = new Scanner(System.in);
		do {
			System.out.print("Type " + exitStr + " to exit the program: ");
		} while (in.hasNextLine() && !exitStr.equals(in.nextLine()));
		System.exit(exitStatus);
	}
	
}
