package Framework.Uno.Basic;

class Card implements PlayableCard {
    private String color;
    private String value;

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setColor(String color) {
        this.color=color;
    }

    @Override
    public void setValue(String value) {
       this.value=value;
    }


    @Override
    public String toString() {
        return color + " " + value;
    }


}

