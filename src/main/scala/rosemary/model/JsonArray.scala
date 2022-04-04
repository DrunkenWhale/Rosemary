package rosemary.model

import rosemary.model.JsonValue

import scala.collection.mutable.ListBuffer

class JsonArray(val obj: ListBuffer[JsonValue] = ListBuffer.empty)
    extends JsonValue {
}
