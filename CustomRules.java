

import Framework.Uno.Basic.*;

import java.util.List;
import java.util.Scanner;

public class CustomRules implements PlayableRules {

    @Override
    public boolean isPlayable(PlayableCard cardToPlay, PlayableCard currentCard) {

        return cardToPlay.getColor().equals(currentCard.getColor()) ||
                cardToPlay.getValue().equals(currentCard.getValue()) ||
                cardToPlay instanceof CardDecorator ||
                cardToPlay.getColor().equals("Wild");

    }

    @Override
    public int applyCardEffect(PlayableCard card, PlayablePlayer currentPlayer, List<PlayablePlayer> players, PlayableDeck deck) {
        switch (card.getValue()) {
            case "Skip":
                System.out.println("Skipping the next player's turn!");
                return 1;

            case "Reverse":
                System.out.println("Reversing the order of play!");
                reversePlayerOrder(players);
                return 0;

            case "Draw Two":
                System.out.println("Next player draws two cards!");
                PlayablePlayer nextPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
                for (int i = 0; i < 2; i++) {
                    nextPlayer.addCard(deck.drawCard());
                }
                return 0;

            case "Wild Draw Four":
                System.out.println("Next player draws four cards!");
                PlayablePlayer nextPlayerFour = players.get((players.indexOf(currentPlayer) + 1) % players.size());
                for (int i = 0; i < 4; i++) {
                    nextPlayerFour.addCard(deck.drawCard());
                }

                Scanner scanner = new Scanner(System.in);
                System.out.println("Choose a new color (red, green, blue, yellow):");
                String newColor = scanner.nextLine().toLowerCase();
                card.setColor(newColor);
                System.out.println("Color changed to: " + newColor);

                return 0;

            case "Swap Hands":
                if (card instanceof SwapHandsCardDecorator) {
                    System.out.println("Swap Hands card played!");


                    PlayablePlayer targetPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
                    ((SwapHandsCardDecorator) card).swap(currentPlayer, targetPlayer);

                    return 0;
                }
                break;

            default:
                System.out.println("No special effect for this card.");
                return 0;
        }
        return 0;
    }

    private void reversePlayerOrder(List<PlayablePlayer> players) {
        int size = players.size();
        for (int i = 0; i < size / 2; i++) {
            PlayablePlayer temp = players.get(i);
            players.set(i, players.get(size - i - 1));
            players.set(size - i - 1, temp);
        }
    }
}
