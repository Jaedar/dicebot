package dow.codex.ircbot.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import dow.codex.ircbot.util.Dice;

public class DieRoll extends AbstractTestCommand {
	private CommandMessage message; // on form ,Roll 3d4+4+2d2+d10-24-2d4

	public DieRoll(CommandMessage cmd) {
		super(cmd);
		message = cmd;
	}

	public String out() { // check msg is on form

		String[] args = message.getArguments().split("(?=[ +-])");
		String junk = "";
		int modTotal = 0;

		Dices dices = new Dices();
		ArrayList<Dices> positiveDice = new ArrayList<>();
		ArrayList<Dices> negativeDice = new ArrayList<>();

		for (String string : args) {
			// constant or junk
			if (!string.contains("d")) {
				try {
					modTotal = modTotal + Integer.parseInt(string);
				} catch (NumberFormatException e) {
					junk = junk + string;
				}
			} else if (string.matches("[+-]?\\d*d\\d+")) { // starts with plus
															// or minus (maybe)
															// followed by one
															// or more digits
															// then a d, then
															// one or more
															// digits
				if (string.startsWith("-")) {
					negativeDice.add(dices.parseDice(string.substring(1)));
				} else if (string.startsWith("+")) {
					positiveDice.add(dices.parseDice(string.substring(1)));
				} else {
					positiveDice.add(dices.parseDice(string));
				}
			} else {
				junk = junk + string;
			}

		}

		ArrayList<Integer> negativeRolls = getRolls(negativeDice);
		ArrayList<Integer> positiveRolls = getRolls(positiveDice);

		int highRoll = 0;
		if (positiveRolls.size() > 0) {
			highRoll = positiveRolls.stream().max(Integer::compare).get();
		}

		long sum = positiveRolls.stream().collect(Collectors.summarizingInt(Integer::intValue)).getSum()
				- negativeRolls.stream().collect(Collectors.summarizingInt(Integer::intValue)).getSum() + modTotal;

		StringBuilder sb = new StringBuilder("\u0002" + sum + "\u000F: ");


		listRolls(sb, positiveRolls, highRoll);
		listRolls(sb, negativeRolls);
		
		if (modTotal != 0){
			sb.append("modifier: "+modTotal);
		}
		sb.append(junk);

		/*
		 * if (message.contains(" sum ") || message.endsWith(" sum")) output =
		 * "\u0002" + sum + "\u000F: " + output + " " + junk + " (" + n + "d" +
		 * size + "+" + mod + ")"; else output = "\u0002" + highest + "\u000F: "
		 * + output + " " + junk + " (" + n + "d" + size + "+" + mod + ")";
		 * return output;
		 */
		return getSender() + ": " + sb.toString();
	}

	private void listRolls(StringBuilder sb, ArrayList<Integer> list, int max) {

		for (Integer integer : list) {
			if (integer.intValue() == max) {
				sb.append("\u0002" + integer + "\u000F");
			} else {
				sb.append(integer);
			}
			sb.append(", ");
		}

	}

	private void listRolls(StringBuilder sb, ArrayList<Integer> list) {

		for (Integer integer : list) {
			sb.append("-"+integer);
			sb.append(", ");
		}

	}

	private ArrayList<Integer> getRolls(ArrayList<Dices> list) {
		Iterator<Dices> iter = list.iterator();
		ArrayList<Integer> rolls = new ArrayList();
		while (iter.hasNext()) {
			rolls.addAll(iter.next().roll());
		}
		return rolls;
	}

	@Override
	public String execute() {
		return out();
	}

	public static void main(String[] args) {
		DieRoll dr = new DieRoll(new CommandMessage("", "", "", ",Roll 3d4+4+2d2+d10-24-2d4 and random stuff"));

		System.out.println(dr.out());
	}

	class Dices {
		private int num;
		private Dice dice;

		Dices(int num, int size) {
			this.num = num;
			dice = new Dice(size);
		}

		Dices() {

		}

		Dices parseDice(String string) {
			String[] numbers = string.split("d");
			if (numbers[0].equalsIgnoreCase("")) {
				return new Dices(1, Integer.parseInt(numbers[1]));
			} else if (numbers.length == 2) {
				return new Dices(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
			}

			return null;
		}

		List<Integer> roll() {
			ArrayList<Integer> result = new ArrayList<>();
			for (int i = 0; i < num; i++) {
				result.add(dice.roll());
			}
			return result;
		}
	}
}