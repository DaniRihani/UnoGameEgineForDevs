package Framework.Uno.Basic;

import java.util.List;
import java.util.ArrayList;

public class DeckBuilder {
    private final List<PlayableCard> cards;
    private boolean includeSpecialCards;
    private boolean includeWildCards;

    public DeckBuilder() {
        this.cards = new ArrayList<>();
        this.includeSpecialCards = true;
        this.includeWildCards = true;
    }

    public DeckBuilder addBaseCards() {
        for (String color : new String[]{"Red", "Green", "Blue", "Yellow"}) {
            for (int i = 0; i <= 9; i++) {
                cards.add(new Card(color, String.valueOf(i)));
            }
        }
        return this;
    }

    public DeckBuilder includeSpecialCards(boolean include) {
        this.includeSpecialCards = include;
        return this;
    }

    public DeckBuilder includeWildCards(boolean include) {
        this.includeWildCards = include;
        return this;
    }

    public DeckBuilder addCustomCards(List<PlayableCard> customCards) {
        this.cards.addAll(customCards);
        return this;
    }

    public DeckBuilder addSingleCard(PlayableCard card) {
        this.cards.add(card);
        return this;
    }

    public PlayableDeck build() {
        Deck deck = new Deck();

        if (!cards.isEmpty()) {
            deck.addCustomCards(cards);
        }

        if (includeSpecialCards) {
            for (String color : new String[]{"Red", "Green", "Blue", "Yellow"}) {
                deck.addCustomCards(List.of(
                        new Card(color, "Reverse"),
                        new Card(color, "Skip"),
                        new Card(color, "Draw Two")
                ));
            }
        }

        if (includeWildCards) {
            deck.addCustomCards(List.of(
                    new Card("Wild", "Wild Draw Four"),
                    new Card("Wild", "Wild Draw Four"),
                    new Card("Wild", "Wild Draw Four"),
                    new Card("Wild", "Wild Draw Four")
            ));
        }

        deck.shuffle();
        return deck;
    }
}
