package Framework.Uno.Basic;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck implements PlayableDeck {
    private final Stack<PlayableCard> cards;

    public Deck() {
        cards = new Stack<>();
        initializeDeck();
        shuffle();

    }

    public void initializeDeck() {
        for (String color : new String[]{"Red", "Green", "Blue", "Yellow"}) {
            for (int i = 0; i <= 9; i++) {
                cards.add(new Card(color, String.valueOf(i)));
            }
            cards.add(new Card(color, "Reverse"));
            cards.add(new Card(color, "Skip"));
            cards.add(new Card(color, "Draw Two"));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Card("Wild", "Wild Draw Four"));
        }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public PlayableCard drawCard() {
        return cards.pop();
    }

    public void addCustomCards(List<PlayableCard> customCards) {
        cards.addAll(customCards);
        shuffle();
    }
}

