package rosemary.model

import rosemary.model.JsonValue

import scala.collection.mutable.ListBuffer

final case class JsonArray(val obj: ListBuffer[JsonValue] = ListBuffer.empty)
    extends JsonValue {
}
