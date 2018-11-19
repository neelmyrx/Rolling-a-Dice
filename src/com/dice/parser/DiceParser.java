package com.dice.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dice.exception.DiceParserException;
import com.dice.roller.Dice;

public class DiceParser {
	private static final Set<Integer> set = new HashSet<>();

	private CharSequence chars;

	private int offset = 0;

	{
		set.add(4);
		set.add(8);
		set.add(10);
		set.add(12);
		set.add(20);
	}

	public CharSequence getChars() {
		return chars;
	}

	public void setChars(CharSequence chars) {
		this.chars = chars;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public DiceParser(CharSequence chars) {

		this.chars = chars;
		offset = 0;
	}

	public List<Dice> parse() throws DiceParserException {

		boolean first = true;
		int operation = 1;
		List<Dice> list = new ArrayList<>();
		while (hasNext()) {
			int numSides;
			int numDice;
			if (first) {
				numDice = isDigitNext() ? parseInteger() : 1;
				if (numDice < 1) {
					throw new DiceParserException(this, "Not enough dice.");
				}
				if (!isNextAnyOf('d', 'D')) {
					throw new DiceParserException(this, "Expecting 'D' character.");
				}
				next();
				numSides = parseInteger();
				if (!set.contains(numSides)) {
					throw new DiceParserException(this, numSides + " Sided dice are not supported.");
				}
				operation = 1;
				list.add(new Dice(numSides, numDice, operation));
				first = false;
			} else {
				if (isNextAnyOf('+', '-')) {
					char s = chars.charAt(offset);
					offset++;
					if (s == '-') {
						operation = -1;
					} else {
						operation = 1;
					}
				}
				numDice = isDigitNext() ? parseInteger() : 1;
				if (numDice < 1) {
					throw new DiceParserException(this, "Not enough dice.");
				}
				if (!isNextAnyOf('d', 'D')) {
					throw new DiceParserException(this, "Expecting 'D' character.");
				}
				next();
				numSides = parseInteger();
				if (!set.contains(numSides)) {
					throw new DiceParserException(this, numSides + " Sided dice are not supported.");
				}
				list.add(new Dice(numSides, numDice, operation));
			}

		}
		return list;
	}

	public int parseInteger() throws DiceParserException {
		if (!isDigitNext())
			throw new DiceParserException(this, "Expecting digit.");
		int value = 0;
		do {
			value = value * 10 + (next() - '0');
		} while (isDigitNext());
		return value;
	}

	public boolean isDigitNext() {
		return hasNext() && Character.isDigit(chars.charAt(offset));
	}

	public boolean isNextAnyOf(char... characters) {
		if (!hasNext())
			return false;
		for (char character : characters)
			if (chars.charAt(offset) == character)
				return true;
		return false;
	}

	public char next() throws DiceParserException {
		if (!hasNext())
			throw new DiceParserException(this, "Unexpected end of character sequence.");
		return chars.charAt(offset++);
	}

	public boolean hasNext() {
		return offset < chars.length();
	}

}
