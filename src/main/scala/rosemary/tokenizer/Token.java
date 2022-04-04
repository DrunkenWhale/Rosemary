package rosemary.tokenizer;

public class Token {

    public final String tokenValue;
    public final TokenType tokenType;

    public Token(String tokenValue, TokenType tokenType) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    @Override
    public String toString() {
        return "Token(" + "tokenValue: <" +
                tokenValue + ">, tokenValue: "
                + tokenType.name() + ")";
    }
}
