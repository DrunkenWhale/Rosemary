package rosemary.conv

import rosemary.model.{JsonObject, JsonValue}

object Extractor {
  extension (jsonValue: JsonValue) {
    def /(name: String): JsonValue = {
      jsonValue.asInstanceOf[JsonObject].value(name)
    }
  }
}
