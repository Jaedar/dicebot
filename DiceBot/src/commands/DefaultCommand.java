package commands;

import util.Logger;

public class DefaultCommand implements Command {

	@Override
	public String execute() {
		Logger.debug("Unknown command received");
		return "Unknown Command";
	}



}
