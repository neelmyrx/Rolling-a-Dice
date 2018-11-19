package com.dice.roller;

import static java.text.MessageFormat.format;

import java.util.List;
import java.util.Random;

import com.dice.exception.DiceParserException;
import com.dice.parser.DiceParser;
import com.dice.util.DiceRollerUtil;

public class DiceRoller {

	private List<Dice> diceList;
	private final Random random = new Random();
	private String toparse ;
	public DiceRoller(List<Dice> diceList) {
		this.diceList = diceList;
	}

	public DiceRoller() {
		super();
	}

	public int roll() {
		int result = 0;
		for (int i = 0; i < diceList.size(); i++) {
			Dice d = diceList.get(i);
			int rollResult = 0;
			System.out.println("Processing String :"+ d.toString());
			System.out.println(format("Sides : {0}, Number of Dice : {1} , min : {2}, max : {3}",
	                    d.getSides(), d.getNumberOfDice(),d.min(),d.max()));
			for (int j = 0; j < d.getNumberOfDice(); j++) {
				int x = random.nextInt(d.getSides());
				System.out.println("Dice count :"+(j+1)+", Read Value : "+ x);
				rollResult += x;
			}
			System.out.println("Sum : "+ rollResult);
			rollResult = rollResult*d.getOperation();
			result = result+ rollResult;
			
		}
		System.out.println("Total Sum after parsing the String:' "+toparse+"' is : " + result);
		return result;
	}
	
	public void parse(String string) throws DiceParserException {
		toparse = string;
    	String str = DiceRollerUtil.removeUnnecessarySpace(string);
    	diceList = new DiceParser(str).parse();
    }

	public List<Dice> getDiceList() {
		return diceList;
	}

	public void setDiceList(List<Dice> diceList) {
		this.diceList = diceList;
	}
	
	
    
}
