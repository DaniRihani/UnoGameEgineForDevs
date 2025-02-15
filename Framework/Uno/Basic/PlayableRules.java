package Framework.Uno.Basic;
import java.util.List;

public interface PlayableRules {

      boolean isPlayable(PlayableCard cardToPlay, PlayableCard currentCard);
      int applyCardEffect(PlayableCard card, PlayablePlayer currentPlayer, List<PlayablePlayer> players, PlayableDeck deck);

}

