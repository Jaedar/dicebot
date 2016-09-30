package deadlands;

import java.util.*;
public class Bank{

private ArrayList<Chip> chips; // 50 white, 25 red, 10 blue

 public Bank() {
  this.newBank();
 }
 public void newBank() {
   chips= new ArrayList<Chip>();
    
    // generate all the chipser
  // 50 white, 25 red, 10 blue
    for (int i=0; i<50; i++){
      chips.add(new Chip(50));
    }
    for (int j=0; j<25; j++){
      chips.add(new Chip(25));
    }
    for (int k=0; k<10; k++){
      chips.add(new Chip(10));
    }
 }
 
 public String draw() {
   int rng= (int)(Math.random()*chips.size());
   return chips.remove(rng).toString();
 }
 
}
