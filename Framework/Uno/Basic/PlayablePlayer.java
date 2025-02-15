package Framework.Uno.Basic;

import java.util.List;

public interface PlayablePlayer {

    String getName();
    List<PlayableCard> getHand();
    void addCard(PlayableCard card);
    PlayableCard playCard(int index);
    boolean hasWon();
    void setHand(List<PlayableCard> anotherHand);

}