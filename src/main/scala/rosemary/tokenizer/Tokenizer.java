package rosemary.tokenizer;

import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
    
    private final CharReader reader;

    public Tokenizer(String str) {
        this.reader = new CharReader(str);
    }

    public List<Token> tokenizer() throws Exception {

        var list = new LinkedList<Token>();
        while (true) {
            var token = analyzeToken();
            if (token.tokenType == TokenType.END_DOCUMENT) {
                return list;
            } else {
                list.add(token);
            }
        }

    }

    private Token analyzeToken() throws Exception {
        char ch;
        while (true) {
            if (reader.nonNext()) {
                return new Token(null, TokenType.END_DOCUMENT);
            }
            ch = reader.next();
            if (!isBlankChar(ch)) {
                break;
            }
        }
        switch (ch) {
            case '{':
                return new Token(String.valueOf(ch), TokenType.BEGIN_OBJECT);
            case '}':
                return new Token(String.valueOf(ch), TokenType.END_OBJECT);
            case '[':
                return new Token(String.valueOf(ch), TokenType.BEGIN_ARRAY);
            case ']':
                return new Token(String.valueOf(ch), TokenType.END_ARRAY);
            case ',':
                return new Token(String.valueOf(ch), TokenType.SEP_COMMA);
            case ':':
                return new Token(String.valueOf(ch), TokenType.SEP_COLON);
            case 'n':
                return readNull();
            case 't':
            case 'f':
                return readBoolean();
            case '"':
                return readString();
            case '-':
                return readNumber();
        }
        if (ch <= '9' && ch >= '0') {
            return readNumber();
        }
        throw new Exception("Illegal Character");
    }

    private Token readString() throws Exception {
        StringBuilder builder = new StringBuilder();
        while (true) {
            char ch = reader.next();

            if (ch == '\\') {
                if (!isEscape(reader.next())) {
                    throw new Exception("Invalid Escape Character");
                }
                builder.append("\\");
                builder.append(reader.peek());

                if (reader.peek() == 'u') {
                    for (int j = 1; j <= 4; ++j) {
                        char c = reader.next();
                        if (!isHex(c)) {
                            throw new Exception("Invalid Unicode Character");
                        }
                        builder.append(c);
                    }
                }
            } else if (ch == '"') {
                return new Token(builder.toString(), TokenType.STRING);
            } else if (ch == '\r' || ch == '\n') {
                throw new Exception("Invalid \\r or \\n Character");
            } else {
                builder.append(ch);
            }
        }
    }

    private Token readNumber() throws Exception {
        StringBuilder builder = new StringBuilder();
        char ch = reader.peek();
        builder.append(ch);
        while (ch != '.') {
            ch = reader.next();
            if (ch == ',') {
                reader.back();
                return new Token(builder.toString(), TokenType.NUMBER);
            }
            if ((ch >= '0' && ch <= '9') || ch == '.') {
                builder.append(ch);
            } else {
                reader.back();
                return new Token(builder.toString(), TokenType.NUMBER);
            }

        }
        while (true) {
            ch = reader.next();
            if (ch == ',') {
                reader.back();
                return new Token(builder.toString(), TokenType.NUMBER);
            }
            if ((ch >= '0' && ch <= '9') || ch == '.') {
                builder.append(ch);
            } else {
                reader.back();
                return new Token(builder.toString(), TokenType.NUMBER);
            }
        }
    }

    private Token readBoolean() throws Exception {
        if (reader.next() == 't') {
            if (reader.next() == 'r' && reader.next() == 'u' && reader.next() == 'e') {
                return new Token("true", TokenType.BOOLEAN);
            } else {
                throw new Exception("Illegal Boolean Value");
            }
        } else {
            if (reader.next() == 'a' && reader.next() == 'l' && reader.next() == 's' && reader.next() == 'e') {
                return new Token("false", TokenType.BOOLEAN);
            } else {
                throw new Exception("Illegal Boolean Value");
            }
        }
    }

    private Token readNull() throws Exception {
        if (reader.next() == 'u' || reader.next() == 'l' || reader.next() == 'l') {
            return new Token(null, TokenType.NULL);
        } else {
            throw new Exception("Invalid Null Value!");
        }
    }


    private static boolean isBlankChar(char ch) {
        return ch == '\n' || ch == '\t' || ch == ' ' || ch == '\r';
    }

    private static boolean isEscape(char ch) {
        return (ch == '"' ||
                ch == '\\' ||
                ch == 'u' ||
                ch == 'r' ||
                ch == 'n' ||
                ch == 'b' ||
                ch == 't' ||
                ch == 'f' ||
                ch == '/');
    }

    private static boolean isHex(char ch) {
        return ((ch >= '0' && ch <= '9')
                || ('a' <= ch && ch <= 'f')
                || ('A' <= ch && ch <= 'F'));
    }

}
