package commands;

import java.util.*;

import deadlands.Deck;
import util.Dice;
public class Initiative{

private Deck deck; // 50 white, 25 red, 10 blue

 public Initiative(Deck dek ) {
  deck=dek;
 }
 
 public String go(String message){
  if (!message.matches(",dlinit\\s\\d*d\\d+[+-]?\\d?.*")) 
      return "error";
    
    
    // todo, support double digit dice
    int n=1;
    int size=6;
    int mod=0;
    String[] mes=message.split("\\s"); // [,droll][3d4+4][junk]
    String[] s;
    if (mes[1].matches("d\\d*")){ //d4
      s=mes[1].split("d");
      n=1;
      size=Integer.parseInt(s[0]);
      mod=0;
    }
    else if (mes[1].matches("\\d*d\\d*")){ // 3d4
      s=mes[1].split("d");
      n =Integer.parseInt(s[0]);
      size=Integer.parseInt(s[1]);
      mod=0;
    }
    else if (mes[1].matches("d\\d*[+-]\\d*")){ //d4+5
      s=mes[1].split("[d\\+-]");
      n=1;
      size=Integer.parseInt(s[0]);
      mod=Integer.parseInt(s[2]);
      if (mes[1].matches(".*-.*")){
        mod=mod*-1;
      }
    }
    else if (mes[1].matches("\\d*d\\d*[+-]\\d*")) { //3d4+4
      s=mes[1].split("[d\\+-]");
      n =Integer.parseInt(s[0]);
      size=Integer.parseInt(s[1]);
      mod=Integer.parseInt(s[2]);
      if (mes[1].matches(".*-.*")){
        mod=mod*-1;
      }
    }
    
    Dice die= new Dice(size);
    int highest=-4000;
    int roll;
    String output="";
    int ones=0;
    for (int i=1; i<=n; i++){
      roll=die.rolle()+mod;
      if (roll>highest)
        highest=roll;
      output=output+roll+",";
      if (roll==(1+mod))
        ones++;
    }
    String junk="";
    if (mes.length <3)
      junk="";
    else {
      for (int i=2; i<mes.length; i++){
      junk=junk+" "+mes[i];
      }
    } // "\u0002Bold\u000F and gone"
    
    int cardno=1+ highest/5;
    if (ones > (int)((n+1)/2.0))
      return "Bust! "+output;
    
    output="\u0002"+highest+"\u000F: "+ deck.draw(cardno)+ " "+ junk +" ("+n+"d"+size+"+"+mod+" "+output+")";
    
    return output;
    
 }
}
 

