package rosemary.model

import rosemary.model.JsonValue

final case class JsonNumber(override val value: Number)
    extends JsonValue {

}
