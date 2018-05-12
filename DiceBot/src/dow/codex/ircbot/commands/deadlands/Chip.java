package dow.codex.ircbot.commands.deadlands;

public class Chip {

	private int value; // 50 white, 25 red, 10 blue

	public enum ChipColors {
		BLUE(10), WHITE(50), RED(25);

		private int value;

		private ChipColors(int value) {
			this.value = value;
		}

		public int value(){
			return this.value;
		}
	}

	public Chip(int val) {
		value = val;
	}

	public String toString() {
		if (value == 50)
			return "White Chip";
		if (value == 25)
			return "Red Chip";
		if (value == 10)
			return "Blue chip";

		return "wrong chip";
	}
	
	public int getValue(){
		return value;
	}

}
