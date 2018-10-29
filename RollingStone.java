import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;  

/**
 * Simulates a game of Rolling Stone.  See
 * https://www.activityvillage.co.uk/rolling-stone
 * for basic play rules.
 */
public class RollingStone {

    private ArrayList<Player> playerPool;
    private Hand discardPile;
    private Scanner in;
    
    /**
     * Initializes the state of the game.
     */
    public RollingStone() throws IOException {
        int playerCount = enterPlayerCount();
        playerPool = createPool(playerCount);
        Deck deck = new Deck("Deck", playerCount);
        deck.shuffle();
     
        // deal cards to each player
        
        int handSize = 8;
        for (int i = 0; i < playerPool.size(); i++){
            Player player = playerPool.get(i);        
            deck.deal(player.getHand(), handSize);
        }
        discardPile = new Hand("Discards");
        in = new Scanner(System.in); 
    }
    
    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {
        for (int i = 0; i < playerPool.size(); i++){
            Player player = playerPool.get(i);
            if (player.getHand().empty()){
                System.out.println("This game won " + player.getName());
                return true;
            }
        } return false;
        
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {
        for(int i =0; i < playerPool.size(); i++){
                if (current.equals(playerPool.get(i))) {
                if( i == playerPool.size() -1) {
                    return playerPool.get(0);
                } else {
                return playerPool.get(i+1);
                }
            }
        } return null;
    }
    
    /**
     * Displays the state of the game.
     */
    public void displayState() {
        for(int i =0; i < playerPool.size(); i++){
            Player player = playerPool.get(i);
            player.display();
        }
        if (discardPile.empty()){
            System.out.println("Discard pile is empty at the moment");
        } else discardPile.display();
    }

    /**
     * Waits for the user to press enter.
     */
    public void waitForUser() {
        in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
            Card prev = discardPile.last();
            Card next = player.play(this, prev);
            
            if (next != null){
                discardPile.addCard(next);
                System.out.println(player.getName() + " plays " + next);
                System.out.println();
            } else {
                int tempSuit = prev.getSuit();
                discardPile.dealAll(player.getHand());
                prev = player.drawFirstCard(tempSuit);
                discardPile.addCard(prev);
                System.out.println(player.getName() + " plays " +prev);
                System.out.println();
            }
    }
    
      /**
     * First player drops first cart in to the discard pile
     * and starts the game.
     */
    
    public void takeFirstTurn(Player player){
            Card card = player.drawFirstCard();
            System.out.println(player.getName() + " plays " + card);
            discardPile.addCard(card);
    
    }

    /**
     * Plays the game.
     */
    public void playGame() {
        Player player = playerPool.get(0);
        displayState();
        waitForUser();
        takeFirstTurn(player);
        player = nextPlayer(player);
        
// keep playing until there's a winner
        while (!isDone()) {
            displayState();
            waitForUser();
            takeTurn(player);
            player = nextPlayer(player);
        }

        // display the final score
        for(int i =0; i < playerPool.size(); i++){
            player = playerPool.get(i);
        }
    }
    
     /**
     *  Gets from the user the number of players
     */
  
    public static int enterPlayerCount() throws IOException {
        boolean flag = false;
        int playerCount = 0;
      
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter how many players will play: ");    
        while (flag == false){
        try{  
            playerCount = Integer.parseInt(reader.readLine());
                if (playerCount < 4 ||playerCount > 6) {
                    System.out.println("It can't be less than 4 and more than 6 players. Please enter again.");
                } else {
                    flag = true;
                }
        } catch (NumberFormatException e) {
            System.out.println("That’s not an integer. Try again: ");
        }
        }
        reader.close();
        return playerCount;
    }
    
     /**
     * Creates the the players pool and adds names
     */
    
    public static ArrayList<Player> createPool(int playerCount)throws IOException {
      
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));        
        String name="";  
        ArrayList<Player> playerPool = new ArrayList<Player>();
        int enter = 1;
        while(playerCount > 0){    
            System.out.println("Enter Player's " +enter + " name: ");    
            name=reader.readLine();  
            Player player = new Player(name);
            playerPool.add(player);
            playerCount--;
            enter++;
        }              
        reader.close(); 
        return playerPool;
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) throws IOException {      
            RollingStone game = new RollingStone();
            game.playGame();
        }
    }
