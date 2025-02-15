package Framework.Uno.Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Uno {

    private final int numPlayers;
    protected final PlayableRules rules;
    protected PlayableDeck deck;
    protected PlayableCard lastPlayedCard;
    protected List<PlayablePlayer> players;

    public Uno(int numPlayers, PlayableRules rules) {

        this.numPlayers = numPlayers;
        this.rules = rules;
        this.deck=DeckFactory.createDeck();
        this.players = new ArrayList<>();

        for (int i = 0; i < getNumPlayers(); i++) {
            players.add(new Player("Player " + (i + 1)));
        }

        Random random=new Random(System.currentTimeMillis());
        int value= random.nextInt(10)+1;

        String[] colors= new String[]{"red", "green", "blue", "yellow"};

        String color=colors[ random.nextInt(colors.length) ];

        this.lastPlayedCard=CardFactory.createCard(Integer.toString(value),color);
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    abstract protected void play();
    abstract protected void initializeGame();
}
