package application;

import org.jibble.pircbot.*;

public class MyBotMain {
  // private Stokenizer tokenizer;  
  //   Parser parse;
    public static void main(String[] args) throws Exception {
     //tokenizer = new Stokenizer();
   //  parse = new Parser(tokenizer);
      String name="General_Sturnn";
      // Now start our bot up.
      MyBot bot = new MyBot(name);
      
      // Enable debugging output.
      bot.setVerbose(true);
      
      boolean b=false;
      // Connect to the IRC server.
      while(!b){
        try {
      bot.connect("irc.gamesurge.net", 6667);
          //bot.connect("TAL.DE.EU.gamesurge.net", 6667);
      b=true;
        }
        catch(Exception e){
        System.out.println("timed out, trying again"+e.toString());
        }
      }
      
      // Join the #pircbot channel.
      bot.joinChannel("#codexdh");
      //bot.joinChannel("#codexdl");
      //bot.joinChannel("#rpgcodex");
      
      // Load table from txts
      bot.load("D:/DrJava/ircbot");
    }
    
}