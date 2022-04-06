package rosemary.stringify

import rosemary.parser.model.{JsonArray, JsonObject, JsonValue}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Generate {

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
