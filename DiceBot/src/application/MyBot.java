package application;

import org.jibble.pircbot.PircBot;

import commands.CommandHandler;
import commands.CommandMessage;
import commands.Dlroll;
import commands.Initiative;
import deadlands.Bank;
import deadlands.Deck;
import util.Dieroll;
import util.Filter;
import util.Table;

import java.util.*;
import java.io.*;
import java.io.Console;
import java.util.regex.*;

public class MyBot extends PircBot {
	// private File[] tables;
	private Table[] tables;
	private Deck dek = new Deck();
	private Bank bank = new Bank();

	public MyBot(String name) {
		this.setName(name);
	}

	public void load(String dir) {
		File[] fil = Filter.finder(dir);
		tables = new Table[fil.length];
		for (int i = 0; i < fil.length; i++)
			try {
				tables[i] = new Table(fil[i]);
			} catch (IOException e) {
				System.out.println("File error");
			}
	}

	// greetings
	public void onJoin(String channel, String sender, String login, String hostname) {
		if (sender.equals("Jaedar")) {
			sendMessage(channel, "Greetings Master");
		}

		else if (!sender.equals(getNick())) {
			sendMessage(channel, "Hi " + sender + ", how are you doing today?");
		}
	}

	public void onDisonnect() {
		try {
			this.connect("irc.gamesurge.net");
			this.joinChannel("#codexdh");
		} catch (Exception e) {
		}
	}

	// commands
	@Override
	public void onPrivateMessage(String sender, String login, String hostname, String message) {
		if (message.startsWith(",")) {
			CommandMessage cmd = new CommandMessage(sender, login, hostname, message);
			CommandHandler.execute(cmd);
		}

		return;
	}

	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		// if (message.startsWith(",roll")){
		// sendMessage(channel, sender+ dice(message));
		// }
		if (message.startsWith(",")) {
			CommandMessage cmd = new CommandMessage(sender, login, hostname, message);
			CommandHandler.execute(cmd);
		}
		return;
		// String s="";
		// if (message.equalsIgnoreCase(",help")) {
		// sendMessage(channel, "Read the source code and educate thyself
		// scrub");
		// }
		// if (message.equalsIgnoreCase(",deso")) {
		// s=deso(sender);
		// sendMessage(channel, s);
		// }
		// else if (message.equalsIgnoreCase(",alex")) {
		// sendMessage(channel, alex(sender));
		// }
		//
		// else if (message.equalsIgnoreCase(",jaedar")) {
		// sendMessage(channel, "Jaedar is great");
		// }
		//
		// else if (message.equalsIgnoreCase(",root")) {
		// sendMessage(channel, root(sender));
		// }
		// else if (message.equalsIgnoreCase(",kaurava")) {
		// sendMessage(channel, "Boreale died there");
		// }
		//
		// else if (message.equalsIgnoreCase(",rej")) {
		// sendMessage(channel, "When I dabbled in programming I saw a gaping
		// abyss staring into my soul and I ran to music");
		// }
		// else if (message.equalsIgnoreCase(",ex")) {
		// sendMessage(channel, "That is p. banal "+sender);
		// }
		//
		// else if (message.equalsIgnoreCase(",roxor")) {
		// sendMessage(channel,"I care not for your nose, fewl");
		// }
		//
		// else if (message.equalsIgnoreCase(",hero")) {
		// sendMessage(channel,"derp, "+sender);
		// }
		//
		// else if (message.equalsIgnoreCase(",whatsnew")) {
		// sendMessage(channel, "Now supports dx+y rolls and sums by adding sum
		// after the roll. (Ex: ,roll 2d3+4 sum)");
		// }
		// else if (message.startsWith(",div")){
		// for (int i=0; i<tables.length; i++){
		// if (tables[i].getName().equals("div"))
		// sendMessage(channel,tables[i].get());
		// }
		// }
		// else if(message.startsWith(",")){
		// if (message.startsWith(",dlroll")) {
		// Dlroll d=new Dlroll(message);
		// sendMessage(channel, sender+" "+d.out());
		// return;
		// }
		// if (message.startsWith(",roll")) {
		// Dieroll dd=new Dieroll(message);
		// sendMessage(channel, sender+" "+dd.out());
		// return;
		// }
		//
		// if (message.startsWith(",dldraw")){
		// if (message.matches(",dldraw\\s*\\d+")){
		// String[] spl=message.split("\\s");
		// sendMessage(channel, sender+" :
		// "+dek.draw(Integer.parseInt(spl[1])));
		// return;
		// }
		// else if (message.matches(",dldraw.*"))
		// sendMessage(channel, sender+" : "+dek.draw(1));
		// else
		// sendMessage( channel, sender+ " I dunno what you want");
		// }
		//
		// else if (message.startsWith(",dlchip")) {
		//
		// sendMessage(channel, sender+" "+bank.draw());
		// return;
		// }
		//
		// else if (message.startsWith(",dlinit")) {
		// Initiative init= new Initiative(dek);
		// sendMessage(channel, sender+" "+init.go(message));
		// return;
		// }
		// else if (message.startsWith(",dlnewdeck")) {
		// dek.newDeck();
		// sendMessage(channel, "New Deck is shuffled and ready");
		// return;
		// }
		// else {
		// for (int i=0; i<tables.length; i++){
		// if( message.startsWith(","+tables[i].getName())){
		// sendMessage(channel, tables[i].get());
		// return;
		// }
		// }

	}

	public String deso(String sender) {
		int rand;
		String str = "";
		rand = (int) (5 * Math.random() + 1);
		switch (rand) {
		case 1:
			str = "I am tired";
			break;
		case 2:
			str = "This headache is annoying";
			break;
		case 3:
			str = sender + " you fucker";
			break;
		case 4:
			str = "I am going to take a nap";
			break;
		case 5:
			str = "I hope you get ass cancer! ";
			break;
		default:
			System.out.println("Jaedar cannot into programming");
			break;
		}
		return str;
	}

	public String alex(String sender) {
		int rand;
		String str = "";
		rand = (int) (3 * Math.random() + 1);
		switch (rand) {
		case 1:
			str = "Hello " + sender + ", how are you doing?";
			break;
		case 2:
			str = "Awesome, do you think Burning Wheel has rules for that?";
			break;
		case 3:
			str = "Hello " + sender + ", how is it going?";
			break;
		case 4:
			str = "I am going to take a nap";
			break;
		default:
			System.out.println("Jaedar cannot into programming");
			break;
		}
		return str;
	}

	public String root(String sender) {
		int rand;
		String str = "";
		rand = (int) (5 * Math.random() + 1);
		switch (rand) {
		case 1:
			str = "Have I ever told you the tragedy of Darth Plagueis the Wise?";
			break;
		case 2:
			str = sender + ", are you going to scarborough fair? ";
			break;
		case 3:
			str = sender + ", can you hear the clarion call? ";
			break;
		case 4:
			str = "You're dead to me, " + sender + "! You hear me!? Dead! ";
			break;
		case 5:
			str = "This channel lacks moral clarity. ";
			break;
		default:
			System.out.println("Jaedar cannot into programming");
			break;
		}
		return str;
	}

	// public String dice(String message){
	// tokenizer=new Stokenizer(message);
	// parse=new Parser(tokenizer, tables);
	// String s=parse.dice(5);
	//
	// return s;
	// }
	// public String commands(String channel, String sender,
	// String login, String hostname, String message){

}