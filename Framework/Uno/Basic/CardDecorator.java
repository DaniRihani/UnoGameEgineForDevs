package Framework.Uno.Basic;

public abstract class CardDecorator implements PlayableCard {
    protected PlayableCard decoratedCard;

    public CardDecorator(PlayableCard decoratedCard) {
        this.decoratedCard = decoratedCard;
    }

    @Override
    public String getColor() {
        return decoratedCard.getColor();
    }

    @Override
    public String getValue() {
        return decoratedCard.getValue();
    }

    @Override
    public void setColor(String color) {
        decoratedCard.setColor(color);
    }

    @Override
    public void setValue(String value) {
        decoratedCard.setValue(value);
    }

    @Override
    public String toString() {
        return decoratedCard.toString();
    }
}
