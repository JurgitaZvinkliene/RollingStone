/**
 * A deck of playing cards.
 */
public class Deck extends CardCollection {
   // private int playerCount;

    /**
     * Constructs a standard deck of 52 cards.
     */
    public Deck(String label) {
        super(label);

        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                addCard(new Card(rank, suit));
            }
        }
    }
   
     /**
     * Constructs resized deck.
     */
    
     public Deck(String label, int playerCount) {
         super(label);
         if (playerCount < 6 && playerCount > 3){
             for (int suit = 0; suit <= 3; suit++) {
                 for (int rank = 1; rank <= 13; rank++) {
                     if (playerCount == 4){
                         if( !(rank >1 && rank <7)){
                             addCard(new Card(rank, suit));
                         }
                     }
                     if (playerCount == 5){
                         if( !(rank >1 && rank <5)){
                             addCard(new Card(rank, suit));
                         }
                     }
                     if (playerCount == 6){
                         if( rank !=2 ){
                             addCard(new Card(rank, suit));
                         }
                     }
                 }
             }
         } else{
             System.out.println("Please check if you have correct amount of players.");
         }
     }
}
