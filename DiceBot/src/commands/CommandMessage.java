package commands;

import java.util.Optional;

import util.Logger;

public class CommandMessage {
	private final String sender;
	private final String login;
	private final String hostname;
	private final String message;
	private final String channel;
	
	private final int COMMAND_TOKEN_LENGTH=1;
	
	public CommandMessage(String sender,String login, String hostname, String message) {
		this.sender= sender;
		this.login = login;
		this.hostname = hostname;
		this.message = message;
		this.channel = null;
		Logger.debug("Command Received" + this);
	}

	public CommandMessage(String channel, String sender, String login, String hostname, String message) {
		this.sender= sender;
		this.login = login;
		this.hostname = hostname;
		this.message = message;
		this.channel  = channel;
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
		if(message.indexOf(" ") != -1) {
		return message.substring(COMMAND_TOKEN_LENGTH, message.indexOf(" "));
		} 
		return message;
	}
	
	public String getArguments(){
		if(message.indexOf(" ") == -1) {
			return "";
		}
		return message.substring(message.indexOf(" ")+1);
	}

	public Optional<String> getChannel(){
		return Optional.of(channel);
	}

}
