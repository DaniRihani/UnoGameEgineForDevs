package Framework.Uno.Basic;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayablePlayer {
    private final String name;
    private final List<PlayableCard> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<PlayableCard> getHand() {
        return hand;
    }

    public void addCard(PlayableCard card) {
        hand.add(card);
    }

    public PlayableCard playCard(int index) {
        return hand.remove(index);
    }

    public boolean hasPlayableCard(PlayableRules rules, PlayableCard topCard) {
        for (PlayableCard card : hand) {
            if (rules.isPlayable(card, topCard)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasWon(){
        return hand.isEmpty();
    }

    public void setHand(List<PlayableCard> anotherhand){
        hand.clear();
        hand.addAll(anotherhand);
    }

}
