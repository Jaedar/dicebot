package dow.codex.ircbot.util;

import java.util.*;
public class DeckManager {
  
  private static Map<String, Stack<Card>> decks = new HashMap<>();
//private int value; // 2-14. 20 red joker, 21 black joker
//private int suit; // 1 clubs. 2 diamons, 3 hearts, 4 spades,
  
  private DeckManager() {

    this.newDeck("");
  }
  
  public static String newDeck(String deckName){
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
    Stack<Card> deck = new Stack<Card>();
    int k;
    while ( !list.isEmpty()){
      k= (int)(Math.random()*list.size());
      deck.push(list.remove(k));
      
    }
    decks.put(deckName, deck);
    return "Deck "+deckName+" generated";
  }
  
  public static String draw(int i, String deckName){
    if (decks.get(deckName) == null) {
      newDeck(deckName);
    }
    Stack<Card> deck = decks.get(deckName);

    if (deck.empty()) {
      newDeck(deckName);
    }
    
    String s="";
    for(int j=0; j<i; j++){
      s=s+ deck.pop().toString()+", ";
      
    }
   return s; 
  }

  public static String draw(int i){
    return draw(i, "");
  }

  
}
