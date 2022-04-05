package rosemary.stringify.conv

import rosemary.parser.model.{JsonBoolean, JsonNull, JsonNumber, JsonString}

object ValueToJsonType {

  given intToJsonNumber: Conversion[Int, JsonNumber] = JsonNumber(_)

  given longToJsonNumber: Conversion[Long, JsonNumber] = JsonNumber(_)

  given doubleToJsonNumber: Conversion[Double, JsonNumber] = JsonNumber(_)

  given floatToJsonNumber: Conversion[Float, JsonNumber] = JsonNumber(_)

  given stringToJsonString: Conversion[String, JsonString] = JsonString(_)

  given booleanToJsonBoolean: Conversion[Boolean, JsonBoolean] = JsonBoolean(_)

}
