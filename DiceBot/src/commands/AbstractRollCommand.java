package commands;

import java.util.List;

import util.RollParameter;

public abstract class AbstractRollCommand implements Command {

	private final String sender;
	private final List<RollParameter> params;
	
	protected AbstractRollCommand(CommandMessage cmd) {
		this.sender=cmd.getSender();
		this.params = parseParameters(cmd);
	}
	
	protected List<RollParameter> parseParameters(CommandMessage cmd) {
		
	}

}
