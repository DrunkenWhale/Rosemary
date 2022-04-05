package rosemary.parser.model

trait JsonValue() {
  
  val value: Any

  def /(name: String): JsonValue = {
    this.asInstanceOf[JsonObject].value(name)
  }

  def /(index: Int): JsonValue = {
    this.asInstanceOf[JsonArray].value(index)
  }

  def to[T <: JsonValue]: T = {
    this.asInstanceOf[T]
  }

}
