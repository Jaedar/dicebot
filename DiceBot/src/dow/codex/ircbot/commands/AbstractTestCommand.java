package dow.codex.ircbot.commands;


import dow.codex.ircbot.util.Logger;

public abstract class AbstractTestCommand implements Command {

	private final String sender;
	private int targetNumber;
	private int modifier;
	private String comment;
	
	protected AbstractTestCommand(CommandMessage cmd) {
		this.sender=cmd.getSender();
		targetNumber=0;
		modifier=0;
		comment="";
		parseParameters(cmd);
	}
	
	//Roll commands on form: ,[command] [TN] [Optional mods] [optional text]
	// ex ,eproll 35+10-20 programming bot
	protected void parseParameters(CommandMessage cmd) {
		String[] args = cmd.getArguments().split("(?=[\\s+-])");
		
		
		try{this.targetNumber= Integer.parseInt(args[0]);}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			// can't parse tn, something is wrong
			targetNumber=-1;
			return;
		}
		
		for (int i = 1; i < args.length; i++) {
			try{modifier += Integer.parseInt(args[i]);}
			catch (NumberFormatException e) {
				comment = comment + args[i];
			}
		}
		
		Logger.debug("Parsed TN as: "+targetNumber+". Modifiers total: "+modifier);
	}

	public String getSender() {
		return sender;
	}

	public int getTargetNumber() {
		return targetNumber;
	}

	public int getModifier() {
		return modifier;
	}

	public String getComment() {
		return comment;
	}
	
	
	

}
