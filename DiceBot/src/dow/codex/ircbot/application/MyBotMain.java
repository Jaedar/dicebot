package dow.codex.ircbot.application;

import dow.codex.ircbot.commands.CommandHandler;
import dow.codex.ircbot.commands.deadlands.Bank;

@SuppressWarnings("unused")
public class MyBotMain {

    public static void main(String[] args) throws Exception {

      String name="General_Sturnn";
    
      
      // Load table from txts
      CommandHandler cmdh = new CommandHandler();
      cmdh.loadTables("./Resources/Tables");
      
      // Iniatialize things thatn eed to be
      Bank.init();
      
      // Now start our bot up.
      MyBot bot = new MyBot(name, cmdh);
      
      
      // Enable debugging output.
      bot.setVerbose(true);
      
      boolean b=false;
      // Connect to the IRC server.
      while(!b){
        try {
      bot.connect("irc.gamesurge.net", 6667);
      b=true;
        }
        catch(Exception e){
        System.out.println("timed out, trying again"+e.toString());
        }
      }

      bot.joinChannel("#general");

      
    }
    
}