package rosemary.parser.conv

import rosemary.parser.model.{JsonArray, JsonBoolean, JsonNumber, JsonObject, JsonString, JsonValue}

object JsonTypeToValue {

  given jsonValueToMap: Conversion[JsonValue, Map[String, JsonValue]] with {
    def apply(jsonValue: JsonValue): Map[String, JsonValue] = {
      jsonValue.asInstanceOf[JsonObject].value.toMap
    }
  }

  given jsonValueToList: Conversion[JsonValue, List[JsonValue]] with {
    def apply(jsonValue: JsonValue): List[JsonValue] = {
      jsonValue.asInstanceOf[JsonArray].value.result()
    }
  }

  given jsonValueToArray: Conversion[JsonValue, Array[JsonValue]] with {
    def apply(jsonValue: JsonValue): Array[JsonValue] = {
      jsonValue.asInstanceOf[JsonArray].value.result().toArray
    }
  }

  given jsonValueToBoolean: Conversion[JsonValue, Boolean] with {
    def apply(jsonValue: JsonValue): Boolean = {
      jsonValue.asInstanceOf[JsonBoolean].value
    }
  }

  given jsonValueToLong: Conversion[JsonValue, Long] with {
    def apply(jsonValue: JsonValue): Long = {
      jsonValue.asInstanceOf[JsonNumber].value.longValue()
    }
  }

  given jsonValueToDouble: Conversion[JsonValue, Double] with {
    def apply(jsonValue: JsonValue): Double = {
      jsonValue.asInstanceOf[JsonNumber].value.doubleValue()
    }
  }

  given jsonValueToInt: Conversion[JsonValue, Int] with {
    def apply(jsonValue: JsonValue): Int = {
      jsonValue.asInstanceOf[JsonNumber].value.intValue()
    }
  }

  given jsonValueToFloat: Conversion[JsonValue, Float] with {
    def apply(jsonValue: JsonValue): Float = {
      jsonValue.asInstanceOf[JsonNumber].value.floatValue()
    }
  }

  /* json value convert above this line */

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

  given jsonNumberToInt: Conversion[JsonNumber, Int] with {
    def apply(jsonNumber: JsonNumber): Int = {
      jsonNumber.value.intValue()
    }
  }

  given jsonNumberToFloat: Conversion[JsonNumber, Float] with {
    def apply(jsonNumber: JsonNumber): Float = {
      jsonNumber.value.floatValue()
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