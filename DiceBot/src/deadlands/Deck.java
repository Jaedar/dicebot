package deadlands;

import java.util.*;
public class Deck{
  
  private Stack<Card> dek;
//private int value; // 2-14. 20 red joker, 21 black joker
//private int suit; // 1 clubs. 2 diamons, 3 hearts, 4 spades,
  
  public Deck() {
    this.newDeck();
  }
  
  public void newDeck(){
    ArrayList<Card> list= new ArrayList<Card>();
    
    // generate all the cards
    for (int i=2; i<15; i++){
      for (int j=1; j<5; j++){
        list.add(new Card(i,j));
      }
    }
    // jokers
    list.add(new Card(20,1));
    list.add(new Card(21,1));
    dek = new Stack<Card>();
    int k;
    while ( !list.isEmpty()){
      k= (int)(Math.random()*list.size());
      dek.push(list.remove(k));
      
    }
    
  }
  
  public String draw(int i){
    if (dek.empty())
      this.newDeck();
    
    String s="";
    for(int j=0; j<i; j++){
      s=s+dek.pop().toString()+", ";
      
    }
   return s; 
  }
  
 /* public String hand(int i){
     ArrayList<Card> list= new ArrayList<Card>();
    
    // generate all the cards
    for (int i=2; i<15; i++){
      for (int j=1; j<5; j++){
        list.add(new Card(i,j));
      }
    }
    // jokers
    list.add(new Card(20,1));
    list.add(new Card(21,1));
    dek = new Stack<Card>();
    int k;
    while ( !list.isEmpty()){
      k= (int)(Math.random()*list.size());
      dek.push(list.remove(k));
    }
     // 2-14. 20 red joker, 21 black joker
 // 1 clubs. 2 diamons, 3 hearts, 4 spades,
    int[] sizes=new int[15];
    int[] suits=new int[4];
    Card card;
    boolean joker=false;
    for (int j=0; j<i; j++){
       list.add(dek.pop());
          
    }
    String s="";
    boolean bool=true;
     ArrayList<Card> temp=list.clone();
    // check for royal straight flush
    for(int color=1; color<5; color++){
      temp=list.clone();
      int j=0; 
       while (temp.size>4){
        if (temp.get(j).suit != color) 
          temp.remove(j);
        else
          j++;
       }
          
    
    }
    
  }*/
  
  
}
