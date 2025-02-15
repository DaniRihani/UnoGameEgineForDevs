package Framework.Uno.Basic;

import java.util.List;

 public class DeckFactory {
    public static PlayableDeck createDeck() {
        return new DeckBuilder()
                .addBaseCards()
                .includeSpecialCards(true)
                .includeWildCards(true)
                .build();
    }

    public static PlayableDeck createCustomDeck(List<PlayableCard> customCards) {
        return new DeckBuilder()
                .addBaseCards()
                .addCustomCards(customCards)
                .includeSpecialCards(true)
                .includeWildCards(true)
                .build();
    }

    public static PlayableDeck createCustomDeckNoWildCards(List<PlayableCard> customCards) {
        return new DeckBuilder()
                .addCustomCards(customCards)
                .includeSpecialCards(false)
                .includeWildCards(false)
                .build();
    }
}
