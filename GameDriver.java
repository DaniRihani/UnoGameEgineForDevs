import Framework.Uno.Basic.GameFacade;
import Framework.Uno.Basic.RuleFactory;
import Framework.Uno.Basic.UnoFactory;


public class GameDriver {
    public static void main(String[] args) {

        GameFacade game=GameFacade.getInstance(UnoFactory.createUnoGame(4 ));
      //  GameFacade game=GameFacade.getInstance(CustomUnoFactory.createUnoGame(4, RuleFactory.createCustomRules(new CustomRules())));

        game.startGame();

    }
}

