package dow.codex.ircbot.commands;

import java.io.File;
import java.io.IOException;
import java.util.*;

import dow.codex.ircbot.application.MyBot;
import dow.codex.ircbot.commands.deadlands.DeadlandsChip;
import dow.codex.ircbot.commands.deadlands.DeadlandsDraw;
import dow.codex.ircbot.commands.deadlands.DeadlandsInitiative;
import dow.codex.ircbot.commands.deadlands.DeadlandsNewDeck;
import dow.codex.ircbot.commands.deadlands.DeadlandsRoll;
import dow.codex.ircbot.util.Filter;
import dow.codex.ircbot.util.Logger;
import dow.codex.ircbot.util.Table;

public class CommandHandler {

	private Map<String, Table> tables;

	public void loadTables(String dir) {
		tables = new HashMap<String, Table>();

		File[] fil = Filter.finder(dir);

		if (fil!= null && fil.length != 0) {
			for (File file : fil) {
				try {
					Table table = new Table(file);
					tables.putIfAbsent(table.getName().toUpperCase(), table);
				} catch (IOException e) {
					Logger.warning("Unable to load file " + file.getName());
				}
				Logger.debug("Loaded command table: " + file.getName());
			}
		} else {
			Logger.warning("No table files found, do you have a ./Resources/Tables folder?");
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
		if (tables.containsKey(s.toUpperCase())) {
			return new TableCommand(cmd, tables.get(s));
		}

		switch (CommandNames.valueOf(s.toUpperCase())) {
            case DLROLL:
                return new DeadlandsRoll(cmd);
            case ROLL:
                return new DieRoll(cmd);
            case EPROLL:
                return new EclipsePhaseRoll(cmd);
            case DLDRAW:
                return new DeadlandsDraw(cmd);
            case DLNEWDECK:
                return new DeadlandsNewDeck(cmd);
            case DLINIT:
                return new DeadlandsInitiative(cmd);
            case DLCHIP:
                return new DeadlandsChip(cmd);
            default:
			return defaultCommand;
		}

	}

}
