package deadlands;

public class Card{

public int value; // 2-14. 20 red joker, 21 black joker
public int suit; // 1 clubs. 2 diamons, 3 hearts, 4 spades,

 public Card(int val, int sui) {
  value=val;
  suit=sui;
 }
 
 public String toString() {
   String s="";
   if (value == 20)
     return "Red Joker";
   if (value == 21)
     return "Black Joker";
   if (value <11)
     s=s+value;
   if (value == 11)
      s=s+"Jack";
   if (value == 12)
      s=s+"Queen";
   if (value == 13)
     s=s+"King";
   if (value == 14)
     s=s+"Ace";
   
   s=s+" of ";
   
   if (suit == 1)
     s=s+"Clubs";
   if (suit == 2)
     s=s+"Diamonds";
   if (suit == 3)
     s=s+"Hearts";
   if (suit == 4)
     s=s+"Spades";
   return s;
 }
 
}
