package com.dice;

import com.dice.exception.DiceParserException;
import com.dice.roller.DiceRoller;

public class Main {

	public static void main(String[] args) {
		String[] examples = { "3D8 - 2D4 + 5 D8 + 3D12 - 5D10" };
		DiceRoller diceRoller = new DiceRoller();
		for (String example : examples) {

			try {
				diceRoller.parse(example);
				diceRoller.roll();
			} catch (DiceParserException e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

}
