package util;

public class Dice{
private int size;

 public Dice(int i) {
  size=i;
 }
 
 
 public int roll(){
   int roll;
     roll=(int)(Math.random()*size+1);
   
    return roll;
  }
  public int rolle(){
   int roll=0;
   do {
     roll=roll+(int)(Math.random()*size+1);
   }while (roll%size==0 & size!=1); 
    return roll;
   
  }
 
}
