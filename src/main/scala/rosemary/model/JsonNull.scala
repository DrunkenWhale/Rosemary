package rosemary.model

import rosemary.model.JsonValue

final case class JsonNull() extends JsonValue {
  override val value: Null = null
}
