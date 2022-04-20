package rosemary.stringify

import rosemary.parser.model.JsonValue

import scala.collection.mutable

object Pretty {

  private val SPACE_UNIT = 4;

  extension (self: JsonValue) {
    def pretty(): String = {
      val normalJsonString = Stringify.jsonValueStringify(self)

      val stringBuilder: mutable.StringBuilder = new mutable.StringBuilder()
      var spaceLength: Int = 0
      normalJsonString.foreach {
        case ',' =>
          stringBuilder.append("," + "\n" + (" " * spaceLength))
        case '[' =>
          spaceLength = spaceLength + SPACE_UNIT
          stringBuilder.append("[" + "\n" + (" " * spaceLength))

        case '{' =>
          spaceLength = spaceLength + SPACE_UNIT
          stringBuilder.append("{" + "\n" + (" " * spaceLength))

        case ']' =>
          spaceLength = spaceLength - SPACE_UNIT
          stringBuilder.append("\n" + (" " * spaceLength) + "]")
        case '}' =>
          spaceLength = spaceLength - SPACE_UNIT
          stringBuilder.append("\n" + (" " * spaceLength) + "}")

        case x => stringBuilder.append(x)
      }
      stringBuilder.result()
    }
  }

}
