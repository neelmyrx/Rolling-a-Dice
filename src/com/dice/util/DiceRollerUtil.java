package com.dice.util;

public class DiceRollerUtil {
	public static String removeUnnecessarySpace(String example) {
		String[] arr = example.split("\\+");
		StringBuilder r = new StringBuilder();
		boolean b = true;
		for (String p : arr) {
			StringBuilder sbm = new StringBuilder();
			String[] arr1 = p.split("-");
			boolean first = true;
			for (String m : arr1) {
				if (first) {
					sbm.append(m.trim());
				} else {
					sbm.append("-");
					sbm.append(m.trim());
				}
				first = false;
			}
			if (b) {
				r.append(sbm.toString());
			} else {
				r.append("+");
				r.append(sbm.toString());
			}
			b = false;
		}
		return r.toString();
	}
}
