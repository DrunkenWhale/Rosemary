package rosemary.stringify

import rosemary.parser.model.{JsonArray, JsonObject, JsonValue}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class Generate {

}

object Generate {

  def obj(seq: (String, JsonValue)*): JsonValue = {
    JsonObject(mutable.HashMap.from(seq))
  }

  def arr(seq: JsonValue*): JsonValue = {
    JsonArray(ListBuffer.from(seq))
  }

}
