package dow.codex.ircbot.commands.deadlands;

import dow.codex.ircbot.commands.Command;
import dow.codex.ircbot.commands.CommandMessage;

public class DeadlandsChip implements Command {

	private CommandMessage cmd;

	public DeadlandsChip(CommandMessage cmd) {
		this.cmd=cmd;
	}

	@Override
	public String execute() {
		return cmd.getSender()+ " you drew a "+ Bank.draw();
	}

}
