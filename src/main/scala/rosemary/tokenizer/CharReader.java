package rosemary.tokenizer;

public class CharReader {

    private String str;
    private int index;

    public CharReader(String str) {
        this.str = str;
        this.index = -1;
    }

    public boolean nonNext() {
        return !hasNext();
    }

    public boolean hasNext() {
        return index < str.length()-1;
    }

    public char next() {
        return str.charAt(++index);
    }

    public char peek() {
        return str.charAt(index);
    }

    public int size() {
        return str.length();
    }

    public void back() {
        --index;
    }

}
