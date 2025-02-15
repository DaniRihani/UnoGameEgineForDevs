package Framework.Uno.Basic;

public class GameFacade {
    private static GameFacade instance;
    private final Uno uno;

    private GameFacade(Uno uno) {
        this.uno = uno;
    }

    public static synchronized GameFacade getInstance(Uno uno) {
        if (instance == null) {
            instance = new GameFacade(uno);
        }
        return instance;
    }

    public void startGame() {
        UnoFactory.createUnoGame(uno.getNumPlayers());
        uno.initializeGame();
        uno.play();
    }
}
