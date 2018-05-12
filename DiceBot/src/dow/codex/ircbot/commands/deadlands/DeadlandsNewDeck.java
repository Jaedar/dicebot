package dow.codex.ircbot.commands.deadlands;

import dow.codex.ircbot.commands.Command;
import dow.codex.ircbot.commands.CommandMessage;
import dow.codex.ircbot.util.DeckManager;

public class DeadlandsNewDeck implements Command {
	
	private CommandMessage cmd;

	public DeadlandsNewDeck(CommandMessage cmd) {
		this.cmd=cmd;
	}

	@Override
	public String execute() {
		if(cmd.getChannel().isPresent()) {
		return DeckManager.newDeck(cmd.getArguments());
		} else {
			return "Deck creation is not allowed via private message";
		}
	}

}
