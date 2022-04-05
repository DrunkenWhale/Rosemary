package rosemary.model

import rosemary.model.JsonValue

final case class JsonBoolean(override val value:Boolean)
    extends JsonValue {
}
