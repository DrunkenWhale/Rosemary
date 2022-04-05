package rosemary.model

import rosemary.model.JsonValue

import scala.collection.mutable

final case class JsonObject(override val value: mutable.HashMap[String, JsonValue] = mutable.HashMap.empty)
    extends JsonValue {
}
