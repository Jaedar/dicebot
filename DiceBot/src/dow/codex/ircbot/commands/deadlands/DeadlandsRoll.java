package dow.codex.ircbot.commands.deadlands;

import dow.codex.ircbot.commands.AbstractTestCommand;
import dow.codex.ircbot.commands.CommandMessage;
import dow.codex.ircbot.util.Dice;

public class DeadlandsRoll extends AbstractTestCommand {
	private String message; // on form ,dlroll 3d4+4

	public DeadlandsRoll(CommandMessage cmd) {
		super(cmd);
		message = cmd.getMessage();
	}

	public String out() { // check msg is on form
		if (!message.matches("dlroll\\s\\d*d\\d+[+-]?\\d?.*"))
			return "error";

		int n = 1;
		int size = 6;
		int mod = 0;
		String[] mes = message.split("\\s"); // [,droll][3d4+4][junk]
		String[] s;
		if (mes[1].matches("d\\d*")) { // d4
			s = mes[1].split("d");
			n = 1;
			size = Integer.parseInt(s[1]);
			mod = 0;
		} else if (mes[1].matches("\\d*d\\d*")) { // 3d4
			s = mes[1].split("d");
			n = Integer.parseInt(s[0]);
			size = Integer.parseInt(s[1]);
			mod = 0;
		} else if (mes[1].matches("d\\d*[+-]\\d*")) { // d4+5
			s = mes[1].split("[d\\+-]");
			n = 1;
			size = Integer.parseInt(s[1]);
			mod = Integer.parseInt(s[2]);
			if (mes[1].matches(".*-.*")) {
				mod = mod * -1;
			}
		} else if (mes[1].matches("\\d*d\\d*[+-]\\d*")) { // 3d4+4
			s = mes[1].split("[d\\+-]");
			n = Integer.parseInt(s[0]);
			size = Integer.parseInt(s[1]);
			mod = Integer.parseInt(s[2]);
			if (mes[1].matches(".*-.*")) {
				mod = mod * -1;
			}
		}

		Dice die = new Dice(size);
		int highest = -4000;
		int roll;
		int sum = 0;
		float ones= 0;
		String output = "";
		for (int i = 1; i <= n; i++) {
			roll = die.rolle() + mod;
			sum = sum + roll;
			if (roll > highest)
				highest = roll;
			output = output + roll + ",";
			
			if(roll-mod == 1 ){
				ones++;
			}
		}
		String junk = "";
		if (mes.length < 3)
			junk = "";
		else {
			for (int i = 2; i < mes.length; i++) {
				junk = junk + " " + mes[i];
			}
		} // "\u0002Bold\u000F and gone"
			output = "\u0002" + highest + "\u000F: " + output + " " + junk + " (" + n + "d" + size + "+" + mod + ")";
			if (ones > n/2.0) {
				output = "You went Bust! " + output;
			}
		return getSender() + ": " + output;

	}

	@Override
	public String execute() {
		return out();
	}

}