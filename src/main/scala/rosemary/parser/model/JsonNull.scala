package rosemary.parser.model

final case class JsonNull() extends JsonValue {
  override val value: Null = null
}
