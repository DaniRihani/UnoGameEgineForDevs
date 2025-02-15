import Framework.Uno.Basic.*;

import java.util.ArrayList;
import java.util.List;

class SwapHandsCardDecorator extends CardDecorator {
    public SwapHandsCardDecorator(PlayableCard decoratedCard) {
        super(decoratedCard);
    }

    @Override
    public String toString() {
        return decoratedCard.toString() + " (Swap Hands)";
    }

    public void swap(PlayablePlayer currentPlayer, PlayablePlayer targetPlayer) {
        System.out.println(currentPlayer.getName() + " swaps hands with " + targetPlayer.getName());
        List<PlayableCard> tempHand = new ArrayList<>(currentPlayer.getHand());
        currentPlayer.setHand(targetPlayer.getHand());
        targetPlayer.setHand(tempHand);
    }
}

