package dow.codex.ircbot.commands.deadlands;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import dow.codex.ircbot.util.Logger;

public class Bank {

	private static ArrayList<Chip> chips; // 50 white, 25 red, 10 blue
	private static Properties props;
	private static File fil = new File("./Resources/PersistentStorage/Chipbank.txt");

	public Bank() {
	}

	public static void init() {
		chips = new ArrayList<Chip>();
		props = new Properties();
		if (!fil.exists()) {
			try {
				fil.getParentFile().mkdirs();
				fil.createNewFile();
				Logger.debug("Creating chip storage file");
				// generate all the chipser
				// 50 white, 25 red, 10 blue
				int redAmount = 25;
				int blueAmount = 10;
				int whiteAmount = 50;
				add(whiteAmount, redAmount, blueAmount);

				updateProperties();
				saveProperties();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				props.load(new FileReader(fil));

				add(Integer.parseInt(props.getProperty("white")), Integer.parseInt(props.getProperty("red")),
						Integer.parseInt(props.getProperty("blue")));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static String draw() {
		int rng = (int) (Math.random() * chips.size());
		String s = chips.remove(rng).toString();
		updateProperties();
		saveProperties();
		return s;
	}

	private static void updateProperties() {
		long whites = chips.stream().map(Chip::getValue).filter(i -> i == Chip.ChipColors.WHITE.value()).count();
		long blues = chips.stream().map(Chip::getValue).filter(i -> i == Chip.ChipColors.BLUE.value()).count();
		long reds = chips.stream().map(Chip::getValue).filter(i -> i == Chip.ChipColors.RED.value()).count();
		props.setProperty("red", "" + reds);
		props.setProperty("white", "" + whites);
		props.setProperty("blue", "" + blues);
	}

	private static void saveProperties() {
		try {
			props.store(new PrintWriter(fil), "Stored chip amounts for Deadlands");
		} catch (IOException e) {
			Logger.error("Unable to store chip totals" + e.toString());
		}
	}

	private static void add(int whites, int reds, int blues) {
		for (int i = 0; i < whites; i++) {
			chips.add(new Chip(Chip.ChipColors.WHITE.value()));
		}
		for (int j = 0; j < reds; j++) {
			chips.add(new Chip(Chip.ChipColors.RED.value()));
		}
		for (int k = 0; k < blues; k++) {
			chips.add(new Chip(Chip.ChipColors.BLUE.value()));
		}
	}

}
