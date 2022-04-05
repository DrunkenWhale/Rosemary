package rosemary.model

import rosemary.model.JsonValue

final case class JsonString(override val value: String)
    extends JsonValue {

}
