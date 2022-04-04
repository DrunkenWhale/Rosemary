package rosemary.model

import rosemary.model.JsonValue

class JsonObject(val obj: Map[String, JsonValue])
    extends JsonValue {
}
