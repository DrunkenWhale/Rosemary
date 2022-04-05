import rosemary.Parser
import rosemary.model.{JsonNumber, JsonObject, JsonValue}
import rosemary.tokenizer.Tokenizer
import rosemary.conv.Extractor.*
import rosemary.conv.JsonModelConversion.given

@main def test1(): Unit = {
  val obj = new Parser("{\"age\":114514,\"number\":[114514,1919810,1,1,4,5,1,4,8,{},1145141919810,{\"s123\":\"sss\"}]}").parse()

  val number: Long = (obj / "age").asInstanceOf[JsonNumber]
  println(number)
}