package rosemary.parser

import rosemary.parser.Parser.*
import rosemary.parser.model.{JsonArray, JsonBoolean, JsonNull, JsonNumber, JsonObject, JsonString, JsonValue}
import rosemary.parser.tokenizer.TokenType.*
import rosemary.parser.tokenizer.{Token, TokenListReader, TokenType, Tokenizer}
import rosemary.parser.tokenizer.TokenType

import java.util
import scala.annotation.tailrec

class Parser(str: String) {

  private val reader: TokenListReader = TokenListReader(new Tokenizer(str).tokenizer())

  def parse(): JsonValue = {
    val token = reader.next()
    if (token.tokenType == BEGIN_OBJECT) {
      parseObject()
    } else if (token.tokenType == BEGIN_ARRAY) {
      parseArray()
    } else {
      throw new Exception("Json string start with illegal character")
    }
  }

  private def parseObject(): JsonObject = {
    val res = JsonObject()
    var key: String = ""
    var value: Any = null
    var expectToken = STRING_TOKEN | END_OBJECT_TOKEN
    while (reader.hasNext) {
      val token = reader.next()
      val tokenType = token.tokenType
      val tokenValue = token.tokenValue
      tokenType match {
        case BEGIN_OBJECT =>
          checkExpectToken(tokenType, expectToken)
          res.value.put(key, parseObject())
          expectToken = SEP_COMMA_TOKEN | END_OBJECT_TOKEN
        case END_OBJECT =>
          checkExpectToken(tokenType, expectToken)
          return res
        case BEGIN_ARRAY =>
          checkExpectToken(tokenType, expectToken)
          res.value.put(key, parseArray())
          expectToken = SEP_COMMA_TOKEN | END_OBJECT_TOKEN
        case NULL =>
          res.value.put(key, parseNull(token))
          expectToken = SEP_COMMA_TOKEN | END_OBJECT_TOKEN
        case BOOLEAN =>
          res.value.put(key, parseBoolean(token))
          expectToken = SEP_COMMA_TOKEN | END_OBJECT_TOKEN
        case NUMBER =>
          res.value.put(key, parseNumber(token))
          expectToken = SEP_COMMA_TOKEN | END_OBJECT_TOKEN
        case STRING =>
          val preToken = reader.peekPrevious()
          if (preToken.tokenType == TokenType.SEP_COLON) {
            //this string as value
            res.value.put(key, parseString(token))
            expectToken = SEP_COMMA_TOKEN | END_OBJECT_TOKEN
          } else {
            // this string as key
            key = token.tokenValue
            expectToken = SEP_COLON_TOKEN
          }
        case SEP_COLON =>
          checkExpectToken(tokenType, expectToken)
          expectToken = NULL_TOKEN
              | NUMBER_TOKEN
              | BOOLEAN_TOKEN
              | STRING_TOKEN
              | BEGIN_OBJECT_TOKEN
              | BEGIN_ARRAY_TOKEN;
        case SEP_COMMA =>
          checkExpectToken(tokenType, expectToken)
          expectToken = STRING_TOKEN
        case _ => throw new Exception("Parse Error")
      }
    }
    throw new Exception("Parser Object Failed")
  }

  private def parseArray(): JsonArray = {
    var expectToken = BEGIN_ARRAY_TOKEN
        | END_ARRAY_TOKEN
        | BEGIN_OBJECT_TOKEN
        | NULL_TOKEN
        | NUMBER_TOKEN
        | BOOLEAN_TOKEN
        | STRING_TOKEN;
    val res = JsonArray()
    while (reader.hasNext) {
      val token = reader.next()
      val tokenType = token.tokenType
      val tokenValue = token.tokenValue
      tokenType match {
        case BEGIN_OBJECT =>
          checkExpectToken(tokenType, expectToken)
          res.value.addOne(parseObject())
          expectToken = SEP_COMMA_TOKEN | END_ARRAY_TOKEN
        case BEGIN_ARRAY =>
          checkExpectToken(tokenType, expectToken)
          res.value.addOne(parseArray())
          expectToken = SEP_COMMA_TOKEN | END_ARRAY_TOKEN
        case END_ARRAY =>
          checkExpectToken(tokenType, expectToken)
          return res
        case NULL =>
          res.value.addOne(parseNull(token))
          expectToken = SEP_COMMA_TOKEN | END_ARRAY_TOKEN
        case BOOLEAN =>
          res.value.addOne(parseBoolean(token))
          expectToken = SEP_COMMA_TOKEN | END_ARRAY_TOKEN
        case NUMBER =>
          res.value.addOne(parseNumber(token))
          expectToken = SEP_COMMA_TOKEN | END_ARRAY_TOKEN
        case STRING =>
          res.value.addOne(parseString(token))
          expectToken = SEP_COMMA_TOKEN | END_ARRAY_TOKEN
        case SEP_COLON =>
          checkExpectToken(tokenType, expectToken)
          expectToken = NULL_TOKEN
              | NUMBER_TOKEN
              | BOOLEAN_TOKEN
              | STRING_TOKEN
              | BEGIN_OBJECT_TOKEN
              | BEGIN_ARRAY_TOKEN;
        case SEP_COMMA =>
          checkExpectToken(tokenType, expectToken)
          expectToken = STRING_TOKEN
              | NULL_TOKEN
              | NUMBER_TOKEN
              | BOOLEAN_TOKEN
              | BEGIN_ARRAY_TOKEN
              | BEGIN_OBJECT_TOKEN
        case _ => throw new Exception("Parse Error")
      }
    }
    throw new Exception("Parser Array Failed")
  }

  private def parseNumber(token: Token): JsonNumber = {
    checkExpectToken(token.tokenType, NUMBER_TOKEN)
    if (token.tokenValue.contains('.')) {
      JsonNumber(java.lang.Double.valueOf(token.tokenValue))
    } else {
      JsonNumber(java.lang.Long.valueOf(token.tokenValue))
    }
  }

  private def parseString(token: Token): JsonString = {
    checkExpectToken(token.tokenType, STRING_TOKEN)
    JsonString(token.tokenValue)
  }

  private def parseNull(token: Token): JsonNull = {
    checkExpectToken(token.tokenType, NULL_TOKEN)
    new JsonNull
  }

  private def parseBoolean(token: Token): JsonBoolean = {
    checkExpectToken(token.tokenType, BOOLEAN_TOKEN)
    new JsonBoolean(java.lang.Boolean.valueOf(token.tokenValue))
  }

  private def checkExpectToken(tokenType: TokenType, expectToken: Int): Unit = {
    if ((tokenType.code & expectToken) == 0) {
      throw new Exception(s"Unexpect TokenType: $tokenType")
    }
  }

}

object Parser {

  private val BEGIN_OBJECT_TOKEN = 1
  private val END_OBJECT_TOKEN = 2
  private val BEGIN_ARRAY_TOKEN = 4
  private val END_ARRAY_TOKEN = 8
  private val NULL_TOKEN = 16
  private val NUMBER_TOKEN = 32
  private val STRING_TOKEN = 64
  private val BOOLEAN_TOKEN = 128
  private val SEP_COLON_TOKEN = 256
  private val SEP_COMMA_TOKEN = 512

}