package dow.codex.ircbot.commands.deadlands;

import dow.codex.ircbot.commands.Command;
import dow.codex.ircbot.commands.CommandMessage;
import dow.codex.ircbot.util.DeckManager;

public class DeadlandsDraw implements Command {
	String argument;
	String sender;

	public DeadlandsDraw(CommandMessage cmd) {

		argument = cmd.getArguments();
		sender = cmd.getSender();
	}

	@Override
	public String execute() {
		if (argument.matches("\\s*\\d+")) {
			String[] spl = argument.split("\\s");
			return formatOutput( DeckManager.draw(Integer.parseInt(spl[0])));
		}
		String[] args = argument.split(" ");

		if (args[0].matches("-?\\d+")){ // numbar is first
			return formatOutput(DeckManager.draw(Integer.parseInt(args[0]), args.length>1?args[1]:"" ));
		} else if (args.length >1 && args[1].matches("-?\\d+")){
			return formatOutput(DeckManager.draw(Integer.parseInt(args[1]), args[0]));
		}

		return sender + " I cannot comprende. Please specify number and deck to draw";
	}


	private String formatOutput (String theDraw) {
	    return sender + " : " +theDraw;
    }
}
