package rosemary.stringify

import rosemary.parser.model.{JsonArray, JsonNumber, JsonObject, JsonString, JsonValue}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Generate {

  type LegalBasicDataType = String | Number | Int | Long | Float | Double | Boolean
  type LegalSeniorDataType = Seq[JsonValue] | Seq[(String, JsonValue)] | JsonValue
  type LegalJsonDataType = LegalSeniorDataType | LegalBasicDataType


  def obj(data: LegalJsonDataType): JsonValue = {
    data match {
      case s: String => JsonString(s)
      case s: (Int | Long | Float | Double | Number) => JsonNumber(s.asInstanceOf[Number])
      case s: Seq[JsonValue] => JsonArray(ListBuffer.from(s))
      case s: Seq[(String, JsonValue)] => JsonObject(mutable.HashMap.from(s.toMap))
      case s: JsonValue => s
      case _ => throw new Exception("illegal type")
    }
  }

  def json(map: Map[String, _]): String = {
    Stringify.stringify(map)
  }

  def json(seq: Seq[_]): String = {
    Stringify.stringify(seq)
  }

  def obj(seq: (String, _)*): Map[String, _] = {
    Map.from(seq)
  }

  def arr(seq: Any*): Seq[_] = {
    seq
  }

}
