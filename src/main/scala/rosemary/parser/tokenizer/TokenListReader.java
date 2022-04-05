package rosemary.parser.tokenizer;

import java.util.List;

public class TokenListReader {

    private List<Token> list;
    private int index;

    public TokenListReader(List<Token> list) {
        this.list = list;
        this.index = -1;
    }

    public boolean nonNext() {
        return !hasNext();
    }

    public boolean hasNext() {
        return index < list.size() - 1;
    }

    public Token next() {
        return list.get((++index));
    }

    public Token peek() {
        return list.get(index);
    }

    public Token peekPrevious() {
        return list.get(index - 1);
    }

    public int size() {
        return list.size();
    }

    public void back() {
        --index;
    }

}
