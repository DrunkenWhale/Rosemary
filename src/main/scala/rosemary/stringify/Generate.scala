package rosemary.stringify

import rosemary.parser.model.{JsonArray, JsonBoolean, JsonNull, JsonNumber, JsonObject, JsonString, JsonValue}

import scala.annotation.targetName
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Generate {

  private type LegalJsonBasicDataType = String | Number | Int | Long | Float | Double | Boolean
  private type LegalJsonSeniorDataType = JsonValue
  private type LegalJsonDataType = LegalJsonSeniorDataType | LegalJsonBasicDataType

  private type LegalJsonObjectType = (String, LegalJsonDataType)
  private type LegalJsonArrayType = LegalJsonDataType

  @targetName("obj")
  def obj(dataObj: LegalJsonObjectType*): JsonValue = {
    val kvSeq = dataObj.map((k, v) => (k, jsonElementConvert(v)))
    JsonObject(mutable.HashMap.from(kvSeq))
  }

  @targetName("arr")
  def obj(dataArray: LegalJsonArrayType*): JsonValue = {
    val xSeq = dataArray.map(x => jsonElementConvert(x))
    JsonArray(ListBuffer.from(xSeq))
  }

  def obj(): JsonValue = {
    JsonObject()
  }

  private val jsonElementConvert = (x: LegalJsonDataType) => {
    x match
      case x: String => JsonString(x)
      case x: (Number | Int | Long | Float | Double) => JsonNumber(x.asInstanceOf[Number])
      case x: Boolean => JsonBoolean(x)
      case x: JsonValue => x
      case null => JsonNull()
  }

  given jsonValueToString: Conversion[JsonValue, String] = Stringify.jsonValueStringify(_)


  //  def json(map: Map[String, _]): String = {
  //    Stringify.stringify(map)
  //  }
  //
  //  def json(seq: Seq[_]): String = {
  //    Stringify.stringify(seq)
  //  }
  //
  //    def obj(seq: (String, _)*): Map[String, _] = {
  //      Map.from(seq)
  //    }
  //
  //  def arr(seq: Any*): Seq[_] = {
  //    seq
  //  }

}
