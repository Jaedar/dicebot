package commands;

import util.Table;

public class TableCommand implements Command {
	
	private final CommandMessage message;
	private final Table table;

	public TableCommand(CommandMessage cmd, Table table) {
		message=cmd;
		this.table=table;
	}

	@Override
	public String execute() {
		return table.get();
	}

}
