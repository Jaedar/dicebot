package commands;

import util.Dice;

public class EclipsePhaseRoll extends AbstractTestCommand {

	@Override
	public String execute() {
		int roll = new Dice(100).roll() - 1;

		// failure
		if (roll > (getTargetNumber() + getModifier()) || roll == 99) {
			if (isCrit(roll)) {
				return getSender() + ":" + roll + " CRITICAL Failure!"+ commentOutput();
			}
			int margin = roll - getTargetNumber();
			if (margin >= 30) {
				return getSender() + ":" + roll + " Severe Failure!"+ commentOutput();
			}
			return getSender() + ":" + roll + " failure with MoF " + margin + commentOutput();
		}

		// success
		if (isCrit(roll)) {
			return getSender() + ":" + roll + " CRITICAL Success!"+ commentOutput();
		}
		int margin = roll;
		if (margin >= 30) {
			return getSender() + ":" + roll + " Excellent Success!"+ commentOutput();
		}
		return getSender() + ":" + roll + " Success with MoS " + margin +  commentOutput();

	}

	// format: ,eproll TN mods text mods text
	public EclipsePhaseRoll(CommandMessage cmd) throws IllegalArgumentException {
		super(cmd);
	}

	private boolean isCrit(int roll) {
		return roll % 11 == 0;
	}
	
	private String commentOutput(){
		if (getComment().equals("")) {
			return ".";
		}
		return " for "+getComment().trim()+".";
	}

}
