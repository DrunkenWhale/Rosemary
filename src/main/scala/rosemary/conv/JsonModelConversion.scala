package rosemary.conv

import rosemary.model.{JsonArray, JsonBoolean, JsonNull, JsonNumber, JsonObject, JsonString, JsonValue}

object JsonModelConversion {

  given jsonArrayToArray: Conversion[JsonArray, Array[JsonValue]] with {
    def apply(jsonArray: JsonArray): Array[JsonValue] = {
      jsonArray.value.result().toArray
    }
  }

  given jsonObjectToObject: Conversion[JsonObject, Map[String, JsonValue]] with {
    def apply(jsonObject: JsonObject): Map[String, JsonValue] = {
      jsonObject.value.toMap
    }
  }

  given jsonNumberToLong: Conversion[JsonNumber, Long] with {
    def apply(jsonNumber: JsonNumber): Long = {
      jsonNumber.value.longValue()
    }
  }

  given jsonNumberToDouble: Conversion[JsonNumber, Double] with {
    def apply(jsonNumber: JsonNumber): Double = {
      jsonNumber.value.doubleValue()
    }
  }

  given jsonNullToNull: Conversion[JsonNull, Null] with {
    def apply(jsonNull: JsonNull): Null = {
      null
    }
  }

  given jsonStringToString: Conversion[JsonString, String] with {
    def apply(jsonString: JsonString): String = {
      jsonString.value
    }
  }

  given jsonBooleanToBoolean: Conversion[JsonBoolean, Boolean] with {
    def apply(jsonBoolean: JsonBoolean): Boolean = {
      jsonBoolean.value
    }
  }


}