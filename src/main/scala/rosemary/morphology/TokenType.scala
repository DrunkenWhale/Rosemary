package rosemary.morphology

enum TokenType(val code: Int) {

  case BEGIN_OBJECT extends TokenType(1)

  case END_OBJECT extends TokenType(2)

  case BEGIN_ARRAY extends TokenType(4)

  case END_ARRAY extends TokenType(8)

  case NULL extends TokenType(16)

  case NUMBER extends TokenType(32)

  case STRING extends TokenType(64)

  case BOOLEAN extends TokenType(128)

  case SEP_COLON extends TokenType(256)

  case SEP_COMMA extends TokenType(512)

  case END_DOCUMENT extends TokenType(1024)

}
