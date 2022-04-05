package rosemary.parser.model

import scala.collection.mutable.ListBuffer

final case class JsonArray(override val value: ListBuffer[JsonValue] = ListBuffer.empty)
    extends JsonValue {
}
