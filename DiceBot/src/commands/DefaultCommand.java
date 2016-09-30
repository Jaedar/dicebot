package commands;

import util.Logger;

public class DefaultCommand implements Command {

	@Override
	public String execute(CommandMessage cmd) {
		Logger.debug("Unknown command received");
		return "Unknown Command";
	}



}
