package rosemary.model

import rosemary.model.JsonValue

class JsonArray(val obj: Array[JsonValue])
    extends JsonValue {
}
