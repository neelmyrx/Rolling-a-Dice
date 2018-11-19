package com.dice.roller;

//immutable Class

public final class Dice {

	private int sides;
	private int numberOfDice;
	private int operation;

	public Dice(int sides, int numberOfDice, int operation) {
		super();
		this.sides = sides;
		this.numberOfDice = numberOfDice;
		this.operation = operation;
	}

	public int getSides() {
		return sides;
	}

	public int getNumberOfDice() {
		return numberOfDice;
	}

	public int getOperation() {
		return operation;
	}
	
	public int min() {
		return numberOfDice;
	}

	public int max() {
		return (numberOfDice * this.getSides());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfDice;
		result = prime * result + operation;
		result = prime * result + sides;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dice other = (Dice) obj;
		if (numberOfDice != other.numberOfDice)
			return false;
		if (operation != other.operation)
			return false;
		if (sides != other.sides)
			return false;
		return true;
	}
	
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		if (numberOfDice != 1)
			builder.append(numberOfDice);
		builder.append('d');
		builder.append(this.getSides());
		return builder.toString();
	}
}
