package commands;

import util.Logger;

public class CommandMessage {
	private final String sender;
	private final String login;
	private final String hostname;
	private final String message;
	
	private final int COMMAND_TOKEN_LENGTH=1;
	
	public CommandMessage(String sender,String login, String hostname, String message) {
		this.sender= sender;
		this.login = login;
		this.hostname = hostname;
		this.message = message;
		Logger.debug("Command Received" + this);
	}

	public String getSender() {
		return sender;
	}

	public String getLogin() {
		return login;
	}

	public String getHostname() {
		return hostname;
	}

	public String getMessage() {
		return message;
	}
	
	public String getCommand() {
		return message.substring(COMMAND_TOKEN_LENGTH, message.indexOf(" "));
	}

}
