package Framework.Uno.Basic;

import java.util.List;
import java.util.Scanner;

public class StandardUnoGame extends Uno {

    public StandardUnoGame(int numPlayers, PlayableRules rules) {
        super(numPlayers, rules);
    }

    @Override
    public void initializeGame() {
        System.out.println("Initializing the game...");

        dealInitialCards();
        setLastPlayedCard();

        System.out.println("Game initialized and ready to play!");
    }

    private void dealInitialCards() {
        System.out.println("Dealing 7 cards to each player...");
        for (PlayablePlayer player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            }
        }
    }

    private void setLastPlayedCard() {
        PlayablePlayer lastPlayer = players.get(players.size() - 1);
        List<PlayableCard> lastPlayerHand = lastPlayer.getHand();
        lastPlayedCard = lastPlayerHand.remove((int) (Math.random() * lastPlayerHand.size()));
        System.out.println("The last played card (by the last player) is: " + lastPlayedCard);
    }

    @Override
    public void play() {
        System.out.println("Starting the Uno game!");

        boolean gameOver = false;
        int currentPlayerIndex = 0;
        boolean reverseOrder = false;

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            PlayablePlayer currentPlayer = players.get(currentPlayerIndex);

            displayHands(currentPlayer);

            System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!");
            System.out.println("Top card on the pile: " + lastPlayedCard);

            boolean validMove = false;

            while (!validMove) {
                validMove = playerMove(currentPlayer, scanner);
            }

            if (currentPlayer.hasWon()) {
                System.out.println(currentPlayer.getName() + " has won the game!");
                gameOver = true;
                continue;
            }

            currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex, reverseOrder);
        }

        System.out.println("Game Over! Thanks for playing Uno.");
    }

    private void displayHands(PlayablePlayer currentPlayer) {
        System.out.println("\n=== Current Hands ===");
        for (PlayablePlayer player : players) {
            System.out.println(player.getName() + "'s hand: " + player.getHand().size() + " cards");
            if (player == currentPlayer) {
                System.out.println("Your hand: ");
                List<PlayableCard> hand = player.getHand();
                for (int i = 0; i < hand.size(); i++) {
                    System.out.println(i + ": " + hand.get(i));
                }
            }
        }
    }

    private boolean playerMove(PlayablePlayer currentPlayer, Scanner scanner) {
        System.out.println("\nChoose a card index to play (or -1 to draw a card):");
        int choice = scanner.nextInt();

        if (choice == -1) {
            drawCard(currentPlayer);
            return true;
        } else if (choice >= 0 && choice < currentPlayer.getHand().size()) {
            return playCard(currentPlayer, choice);
        } else {
            System.out.println("Invalid choice. Please choose a valid card index.");
            return false;
        }
    }

    private void drawCard(PlayablePlayer currentPlayer) {
        PlayableCard drawnCard = deck.drawCard();
        currentPlayer.addCard(drawnCard);
        System.out.println(currentPlayer.getName() + " drew: " + drawnCard);
    }

    private boolean playCard(PlayablePlayer currentPlayer, int choice) {
        PlayableCard playedCard = currentPlayer.getHand().get(choice);

        if (rules.isPlayable(playedCard, lastPlayedCard)) {
            currentPlayer.getHand().remove(choice);
            System.out.println(currentPlayer.getName() + " played: " + playedCard);

            lastPlayedCard = playedCard;

            int turnChange = rules.applyCardEffect(playedCard, currentPlayer, players, deck);
            if (turnChange == 1) {
                return true; // Skip next player's turn
            }
            return true;
        } else {
            System.out.println("Card cannot be played. Please choose another card.");
            return false;
        }
    }

    private int getNextPlayerIndex(int currentPlayerIndex, boolean reverseOrder) {
        if (reverseOrder) {
            return (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            return (currentPlayerIndex + 1) % players.size();
        }
    }
}
