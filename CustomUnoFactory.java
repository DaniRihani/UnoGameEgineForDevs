import Framework.Uno.Basic.PlayableRules;
import Framework.Uno.Basic.Uno;
import Framework.Uno.Basic.UnoFactory;

public class CustomUnoFactory extends UnoFactory {

    public static Uno createUnoGame(int numPlayers, PlayableRules rules){
        return new CustomUnoGame(numPlayers,rules);
    }
}
