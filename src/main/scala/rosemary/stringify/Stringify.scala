package rosemary.stringify

import rosemary.parser.model.{JsonArray, JsonBoolean, JsonNull, JsonNumber, JsonObject, JsonString, JsonValue}

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Stringify {

  def jsonValueStringifyEntrance(jsonValue: JsonValue): String = {
    jsonValue match
      case jsonArray: JsonArray => jsonArrayParser(jsonArray)
      case jsonObject: JsonObject => jsonObjectParser(jsonObject)
      case _ => throw new Exception("Illegal JsonValue Instance")
  }

  private def jsonValueParser(jsonValue: JsonValue): String = {
    jsonValue match
      case jsonArray: JsonArray => jsonArrayParser(jsonArray)
      case jsonObject: JsonObject => jsonObjectParser(jsonObject)
      case jsonString: JsonString => jsonStringParser(jsonString)
      case jsonNull: JsonNull => jsonNullParser(jsonNull)
      case jsonNumber: JsonNumber => jsonNumberParser(jsonNumber)
      case jsonBoolean: JsonBoolean => jsonBooleanParser(jsonBoolean)
      case _ => throw new Exception("Illegal JsonValue Instance")
  }

  private def jsonArrayParser(jsonArray: JsonArray): String = {
    s"[${jsonArray.value.toList.map(x => jsonValueParser(x)).mkString(",")}]"
  }

  private def jsonObjectParser(jsonObject: JsonObject): String = {
    s"{${jsonObject.value.map((k, v) => s"\"$k\":${jsonValueParser(v)}").mkString(",")}}"
  }

  private def jsonStringParser(jsonString: JsonString): String = {
    "\"" + jsonString.value + "\""
  }

  private def jsonNullParser(jsonNull: JsonNull): String = {
    "null"
  }

  private def jsonNumberParser(jsonNumber: JsonNumber): String = {
    jsonNumber.value.toString
  }

  private def jsonBooleanParser(jsonBoolean: JsonBoolean): String = {
    jsonBoolean.value.toString
  }


  extension[T <: Product] (x: T) {
    def toJson: String = {
      stringify(x)
    }
  }

  extension[T <: Map[String, _]] (x: T) {
    def toJson: String = {
      stringify(x)
    }
  }

  extension[T <: Seq[_]] (x: T) {
    def toJson: String = {
      stringify(x)
    }
  }

  def stringify[T <: Product](x: T): String = {
    stringifyCaseClass(x)
  }

  def stringify[T <: Map[_, _]](x: T): String = {
    stringifyMap(x)
  }

  def stringify[T <: Seq[_]](x: T): String = {
    stringifySeq(x)
  }

  private def stringifyCaseClass[T <: Product](x: T): String = {
    val product: Product = x.asInstanceOf[Product]
    (0 until product.productArity).map(i =>
      val key: String = product.productElementName(i)
      val value: String = valueMatch(product.productElement(i))
      s"\"$key\":$value"
    ).mkString("{", ",", "}")
  }

  private def stringifyMap(x: Map[_, _]): String = {
    x.toSeq.map((key, value) => {
      s"\"$key\":${valueMatch(value)}"
    }).mkString("{", ",", "}")
  }

  private def stringifySeq(x: Seq[_]): String = {
    x.map(key =>
      s"${valueMatch(key)}"
    ).mkString("[", ",", "]")
  }

  private def valueMatch(x: Any): String = {
    x match {
      case t: Int => t.toString
      case t: Long => t.toString
      case t: Double => t.toString
      case t: Float => t.toString
      case t: Boolean => t.toString
      case t: String => s"\"$t\""
      case t: Seq[_] => stringifySeq(t)
      case t: Map[_, _] => stringifyMap(t)
      case t: Product => stringifyCaseClass(t)
      case t => throw new Exception(s"Can't Stringify Type: ${t.getClass.getSimpleName}")
    }
  }

}
