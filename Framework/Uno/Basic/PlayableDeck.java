package Framework.Uno.Basic;

public interface PlayableDeck  {
    void initializeDeck();
    void shuffle();
    PlayableCard drawCard();
}
