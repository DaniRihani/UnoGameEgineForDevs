package Framework.Uno.Basic;


public class CardFactory {

    public static PlayableCard createCard(String color, String value) {

        return new Card(color, value);

    }
}