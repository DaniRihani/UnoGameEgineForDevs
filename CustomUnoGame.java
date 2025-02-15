import Framework.Uno.Basic.*;
import java.util.List;
import java.util.Scanner;

public class CustomUnoGame extends Uno {

    public CustomUnoGame(int numPlayers, PlayableRules rules) {
        super(numPlayers, rules);
    }

    public CustomUnoGame(int numPlayers, PlayableRules rules, List<PlayableCard> cards) {
        super(numPlayers,rules);
        this.deck=DeckFactory.createCustomDeck(cards);
    }

    @Override
    protected void initializeGame() {
        System.out.println("Initializing the Custom Uno game...");


        System.out.println("Dealing 7 cards to each player...");
        for (PlayablePlayer player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            }
        }

        PlayablePlayer lastPlayer = players.get(players.size() - 1);
        List<PlayableCard> lastPlayerHand = lastPlayer.getHand();
        lastPlayedCard = lastPlayerHand.remove((int) (Math.random() * lastPlayerHand.size()));

        System.out.println("The last played card (by the last player) is: " + lastPlayedCard);
        System.out.println("Custom Uno game initialized and ready to play!");
    }

    @Override
    protected void play() {
        System.out.println("Starting Custom Uno game!");

        boolean gameOver = false;
        int currentPlayerIndex = 0;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            PlayablePlayer currentPlayer = players.get(currentPlayerIndex);


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

            System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!");
            System.out.println("Top card on the pile: " + lastPlayedCard);

            boolean validMove = false;

            while (!validMove) {
                System.out.println("\nChoose a card index to play (or type -1 to draw):");
                int choice = scanner.nextInt();

                if (choice == -1) {
                    PlayableCard drawnCard = deck.drawCard();
                    currentPlayer.addCard(drawnCard);
                    System.out.println(currentPlayer.getName() + " drew: " + drawnCard);
                    break;
                } else if (choice >= 0 && choice < currentPlayer.getHand().size()) {

                    PlayableCard playedCard = currentPlayer.getHand().get(choice);

                    if (rules.isPlayable(playedCard, lastPlayedCard)) {
                        currentPlayer.getHand().remove(choice);
                        System.out.println(currentPlayer.getName() + " played: " + playedCard);

                        if (playedCard instanceof SwapHandsCardDecorator) {
                            System.out.println("Choose a player to swap hands with: ");
                            for (int i = 0; i < players.size(); i++) {
                                if (i != currentPlayerIndex) {
                                    System.out.println(i + ": " + players.get(i).getName());
                                }
                            }
                            int targetIndex = scanner.nextInt();
                            if (targetIndex >= 0 && targetIndex < players.size() && targetIndex != currentPlayerIndex) {
                                PlayablePlayer targetPlayer = players.get(targetIndex);
                                ((SwapHandsCardDecorator) playedCard).swap(currentPlayer, targetPlayer);
                            } else {
                                System.out.println("Invalid player choice. Skipping swap.");
                            }
                        }

                        int action = rules.applyCardEffect(playedCard, currentPlayer, players, deck);

                        lastPlayedCard = playedCard;

                        if (action == 1) {
                            currentPlayerIndex = (currentPlayerIndex + 2) % players.size();
                        } else {

                            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                        }

                        validMove = true;
                    } else {
                        System.out.println("Card cannot be played. Please choose another card.");
                    }
                } else {
                    System.out.println("Invalid choice. Please choose a valid card index.");
                }
            }


            if (currentPlayer.hasWon()) {
                System.out.println(currentPlayer.getName() + " has won the game!");
                gameOver = true;
            }
        }

        System.out.println("Game Over! Thanks for playing Custom Uno.");
    }
}
