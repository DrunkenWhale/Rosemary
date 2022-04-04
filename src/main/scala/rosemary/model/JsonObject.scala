package rosemary.model

import rosemary.model.JsonValue

import scala.collection.mutable

final case class JsonObject(val obj: mutable.HashMap[String, JsonValue] = mutable.HashMap.empty)
    extends JsonValue {
}
