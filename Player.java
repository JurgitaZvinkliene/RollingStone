//import java.util.Arrays;
//import java.util.IntSummaryStatistics;
//import java.util.Math;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Map.Entry; 

/**
 * A player in a game of crazy eights.
 */
public class Player {

    private String name;
    private Hand hand;

    /**
     * Constructs a player with an empty hand.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Hand(name);
    }

    /**
     * Gets the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Removes and returns a legal card from the player's hand.
     */
    
    public Card play(RollingStone stones, Card prev) {
        Card card = searchForMatch(prev);
        if (card == null) {
            //card = drawFirstCard(stones, prev.getSuit());
            return null;
        }
        return card;
    }
    
    
    /**
     * Searches the player's hand for a matching card.
     */
    public Card searchForMatch(Card prev) {
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            if (card.getSuit() == prev.getSuit()) {
                return hand.popCard(i);
            }
        }
        return null;
    }
    
    /** 
      * Choose a card from a player's hand
      */
    public Card drawFirstCard(int suit){
        
        Map<Integer, Integer> suitMap = new HashMap<Integer, Integer>(); 
          
        for(int i = 0; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            int key = card.getSuit(); 
            if(suitMap.containsKey(key)){ 
                int repeats = suitMap.get(key); 
                repeats++; 
                suitMap.put(key, repeats); 
            } else { 
                suitMap.put(key, 1); 
            } 
        }  
        int max = 0; 
        int result = -1; 
          
        for(Entry<Integer, Integer> val : suitMap.entrySet()) { 
            if (max < val.getValue() && val.getValue() != suit ){ 
                result = val.getKey(); 
                max = val.getValue(); 
            } 
        }  
        int j = 0;
        max = 0;
        while (j < hand.size()) {
          Card card = hand.getCard(j);
          if (card.getSuit() == result) {
              max =j;
              break;
          } else {
          j++;
        } 
    } return hand.popCard(max);
    }
        
        
        
    
      /** 
      * Starts the game by choosing the first card
      */
    
    public Card drawFirstCard(){
        Map<Integer, Integer> suitMap = new HashMap<Integer, Integer>(); 
          
        for(int i = 0; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            int key = card.getSuit(); 
            if(suitMap.containsKey(key)){ 
                int repeats = suitMap.get(key); 
                repeats++; 
                suitMap.put(key, repeats); 
            } else { 
                suitMap.put(key, 1); 
            } 
        }  
        int max = 0; 
        int result = -1; 
          
        for(Entry<Integer, Integer> val : suitMap.entrySet()) { 
            if (max < val.getValue()){ 
                result = val.getKey(); 
                max = val.getValue(); 
            } 
        }  
        int j = 0;
        max = 0;
        while (j < hand.size()) {
          Card card = hand.getCard(j);
          if (card.getSuit() == result) {
              max =j;
              break;
          } else {
          j++;
        } 
    } return hand.popCard(max);
  }
    
    
    
     /**
     * Displays the player's hand.
     */
    public void display() {
        hand.display();
    }


}
