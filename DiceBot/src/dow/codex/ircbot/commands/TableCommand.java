package dow.codex.ircbot.commands;

import dow.codex.ircbot.util.Table;

public class TableCommand implements Command {
	
	private final CommandMessage message;
	private final Table table;

	public TableCommand(CommandMessage cmd, Table table) {
		message=cmd;
		this.table=table;
	}

	@Override
	public String execute() {
		return message.getSender() +": " + table.get();
	}

}
