package Framework.Uno.Basic;

public class RuleFactory {

    public static PlayableRules createDefaultRules() {
        return new StandardRules();
    }

    public static PlayableRules createCustomRules(PlayableRules customImplementation) {
        return customImplementation;
    }
}
