package commands;


public class EclipsePhaseRoll extends AbstractRollCommand {

	private final int targetNumber;
	
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	// format: ,eproll TN mods text mods text
	public EclipsePhaseRoll(CommandMessage cmd) throws IllegalArgumentException{
		super(cmd);
		try {targetNumber = Integer.valueOf(cmd.getMessage().split("\\s", 2)[2]);} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

}
