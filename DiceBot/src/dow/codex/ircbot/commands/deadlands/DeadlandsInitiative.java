package dow.codex.ircbot.commands.deadlands;

import dow.codex.ircbot.commands.Command;
import dow.codex.ircbot.commands.CommandMessage;
import dow.codex.ircbot.util.DeckManager;
import dow.codex.ircbot.util.Dice;

public class DeadlandsInitiative implements Command {

    private String arguments;
    private String sender;
    private String message;

    public DeadlandsInitiative(CommandMessage cmd) {
        this.sender = cmd.getSender();
        this.arguments = cmd.getArguments();
        this.message = cmd.getMessage();
    }

    @Override
    // 12d8+4 junk
    public String execute() {
        if (!arguments.matches("\\d*d\\d+[+-]?\\d?.*"))
            return "Unknown arguments, exected on form \\d*d\\d+[+-]?\\d?.*";

        int n = 1;
        int size = 6;
        int mod = 0;
        String[] mes = message.split("\\s"); // [,droll][3d4+4][junk]
        String[] s;
        if (mes[1].matches("d\\d*")) { // d4
            s = mes[1].split("d");
            n = 1;
            size = Integer.parseInt(s[0]);
            mod = 0;
        } else if (mes[1].matches("\\d*d\\d*")) { // 3d4
            s = mes[1].split("d");
            n = Integer.parseInt(s[0]);
            size = Integer.parseInt(s[1]);
            mod = 0;
        } else if (mes[1].matches("d\\d*[+-]\\d*")) { // d4+5
            s = mes[1].split("[d\\+-]");
            n = 1;
            size = Integer.parseInt(s[0]);
            mod = Integer.parseInt(s[2]);
            if (mes[1].matches(".*-.*")) {
                mod = mod * -1;
            }
        } else if (mes[1].matches("\\d*d\\d*[+-]\\d*")) { // 3d4+4
            s = mes[1].split("[d\\+-]");
            n = Integer.parseInt(s[0]);
            size = Integer.parseInt(s[1]);
            mod = Integer.parseInt(s[2]);
            if (mes[1].matches(".*-.*")) {
                mod = mod * -1;
            }
        }

        Dice die = new Dice(size);
        int highest = -4000;
        int roll;
        String output = "";
        int ones = 0;
        for (int i = 1; i <= n; i++) {
            roll = die.rolle() + mod;
            if (roll > highest)
                highest = roll;
            output = output + roll + ",";
            if (roll == (1 + mod))
                ones++;
        }
        String junk = "";
        if (mes.length < 3)
            junk = "";
        else {
            for (int i = 2; i < mes.length; i++) {
                junk = junk + " " + mes[i];
            }
        } // "\u0002Bold\u000F and gone"

        int cardno = 1 + highest / 5;
        if (ones > (int) ((n + 1) / 2.0))
            return "Bust! " + output;
        String deck = junk.trim();

        output = sender + ": \u0002" + highest + "\u000F: " + DeckManager.draw(cardno, deck) + " " + junk + " (" + n + "d" + size + "+" + mod
                + ",deck="+deck + ". " + output + ")";

        return output;
    }

}
