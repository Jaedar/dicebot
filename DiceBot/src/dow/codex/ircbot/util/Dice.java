package dow.codex.ircbot.util;

import java.security.SecureRandom;

public class Dice {
	private int size;
	private static final SecureRandom random = new SecureRandom();

	public Dice(int sides) {
		size = sides;
	}

	public int roll() {

		return getRoll();
	}

	public int rolle() {
		int roll = 0;
		do {
			roll = roll + getRoll();
		} while (roll % size == 0 & size != 1);
		return roll;

	}

	public static void main(String[] args) {

		Dice die = new Dice(6);

		int lossCount = 0;
		int tries = 10000;
		for (int mod = 0; mod < 10; mod++) {
			int winCount = 0;
			int winDouble = 0;
			for (int i = 0; i < tries; i++) {
				int myRoll = die.roll() + die.roll() + mod;
				int hisRoll = die.roll() + die.roll();
				if (myRoll > hisRoll) {
					if (myRoll > hisRoll *2){
						winDouble++;
					} else {
					winCount++;
					}
				}
			}
			System.out.println("Won "+ (100.0*(winCount+winDouble))/tries + "% of games with mod "+mod+". Out of which "+100.0*winDouble/tries + " percentile units were doubles");
		}

	}

	private int getRoll() {
		return random.nextInt(size) + 1;
	}

}
