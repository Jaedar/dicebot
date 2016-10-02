package deadlands;

public class Chip{

private int value; // 50 white, 25 red, 10 blue

 public Chip(int val) {
  value=val;
 }
 
 public String toString() {
   if (value == 50)
     return "White Chip";
   if (value == 25)
     return "Red Chip";
   if (value == 10)
     return "Blue chip";

   
   return "wrong chip";
 }
 
}
