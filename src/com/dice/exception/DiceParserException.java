package com.dice.exception;

import static java.text.MessageFormat.format;

import com.dice.parser.DiceParser;

public class DiceParserException extends Exception {

	private static final long serialVersionUID = 1L;
	private CharSequence characters;
	private int offset;

	public DiceParserException(DiceParser parser) {
		this.characters = parser.getChars();
		this.offset = parser.getOffset();
	}

	public DiceParserException(DiceParser parser, String message) {
		super(message);
		this.characters = parser.getChars();
		this.offset = parser.getOffset();
	}

	public DiceParserException(DiceParser parser, String message, Throwable throwable) {
		super(message, throwable);
		this.characters = parser.getChars();
		this.offset = parser.getOffset();
	}

	public DiceParserException(DiceParser parser, Throwable throwable) {
		super(throwable);
		this.characters = parser.getChars();
		this.offset = parser.getOffset();
	}

	@Override
	public String getMessage() {
		return format("Error parsing \"{0}\" at offset {1}: {2}", characters, offset, super.getMessage());
	}
}
