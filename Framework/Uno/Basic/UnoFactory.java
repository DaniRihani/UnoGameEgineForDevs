package Framework.Uno.Basic;

public class UnoFactory {

    public static Uno createUnoGame(int numPlayers) {
        return new StandardUnoGame(numPlayers, RuleFactory.createDefaultRules());
    }
}

