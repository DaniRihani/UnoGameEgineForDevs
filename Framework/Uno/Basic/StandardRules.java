package Framework.Uno.Basic;

import java.util.List;
import java.util.Scanner;

public class StandardRules implements PlayableRules {

    @Override
    public boolean isPlayable(PlayableCard cardToPlay, PlayableCard currentCard) {

        boolean isColorMatch = cardToPlay.getColor().equals(currentCard.getColor());
        boolean isNumberMatch = isNumeric(cardToPlay.getValue()) && cardToPlay.getValue().equals(currentCard.getValue());

        return isColorMatch || isNumberMatch;
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

            case "Wild":
                chooseNewColor(card);
                return 0;

            case "Wild Draw Four":
                System.out.println("Next player draws four cards!");
                PlayablePlayer nextPlayerFour = players.get((players.indexOf(currentPlayer) + 1) % players.size());
                for (int i = 0; i < 4; i++) {
                    nextPlayerFour.addCard(deck.drawCard());
                }
                chooseNewColor(card);
                return 0;

            default:

                return 0;
        }
    }

    private void chooseNewColor(PlayableCard card) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a new color (red, green, blue, yellow):");
        String newColor = scanner.nextLine().toLowerCase();
        card.setColor(newColor);
        System.out.println("Color changed to: " + newColor);
    }

    private void reversePlayerOrder(List<PlayablePlayer> players) {
        int size = players.size();
        for (int i = 0; i < size / 2; i++) {
            PlayablePlayer temp = players.get(i);
            players.set(i, players.get(size - i - 1));
            players.set(size - i - 1, temp);
        }
    }

    private boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
