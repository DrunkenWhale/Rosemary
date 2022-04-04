package rosemary.model

import rosemary.model.JsonValue

final case class JsonNull() extends JsonValue {
  val obj: Null = null
}
