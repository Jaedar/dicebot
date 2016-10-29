package commands;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import application.MyBot;
import util.Filter;
import util.Logger;
import util.Table;

public class CommandHandler {

	private Map<String, Table> tables;

	public void load(String dir) {
		tables = new HashMap<String, Table>();
		
		File[] fil = Filter.finder(dir);
		
		for (File file : fil) {
			try {
				Table table= new Table(file);
				tables.putIfAbsent(table.getName().toUpperCase(), table );
			} catch (IOException e) {
				Logger.warning("Unable to load file " + file.getName());
			}
			Logger.debug("Loaded command table: " + file.getName());
		}
	}

	private static DefaultCommand defaultCommand = new DefaultCommand();

	public void execute(CommandMessage cmd, MyBot bot) {
		String target;
		Optional<String> channel = cmd.getChannel();
		if (channel.isPresent()) {
			target = channel.get();
		} else {
			target = cmd.getSender();
		}

		try {
			String cmdReturn = getCommand(parse(cmd), cmd).execute();
			bot.sendMessage(target, cmdReturn);
		} catch (Exception e) {
			e.printStackTrace();
			bot.sendMessage(target, "Unhandled error occurred for command");
		}
	}

	private String parse(CommandMessage cmd) {
		String command = cmd.getCommand();
		Logger.debug("Command Parsed as: " + command);
		return command;
	}

	private Command getCommand(String s, CommandMessage cmd) {
		s = s.toUpperCase();
		if (tables.containsKey(s)) {
			return new TableCommand(cmd, tables.get(s));
		}
		switch (s) {
		case "DLROLL":
			return new DeadlandsRoll(cmd);
		case "ROLL":
			return new DieRoll(cmd);
		case "EPROLL":
			return new EclipsePhaseRoll(cmd);
		default:
			return defaultCommand;
		}
		// for (int i=0; i<tables.length; i++){
		// if( s.startsWith(","+tables[i].getName())){
		// sendMessage(sender, tables[i].get());
		// return;
		// }
		// }
		//

		// if (s.startsWith(",dldraw")){
		// if (s.matches(",dldraw\\s*\\d+")){
		// String[] spl=s.split("\\s");
		// sendMessage(sender, sender+" : "+dek.draw(Integer.parseInt(spl[1])));
		// return;
		// }
		// else if (s.matches(",dldraw.*"))
		// sendMessage(sender, sender+" : "+dek.draw(1));
		// else
		// sendMessage( sender, sender+ " I dunno what you want");
		// }

		// else if (s.startsWith(",dlchip")) {
		//
		// sendMessage(sender, sender+" "+bank.draw());
		// return;
		// }

		// else if (s.startsWith(",dlinit")) {
		// Initiative init= new Initiative(dek);
		// sendMessage(sender, sender+" "+init.go(s));
		// return;
		// }
		// else if (s.startsWith(",dlnewdeck")) {
		// dek.newDeck();
		// sendMessage(sender, "New Deck is shuffled and ready");
		// return;
		// }

		//
		// }
		// }
	}

}
