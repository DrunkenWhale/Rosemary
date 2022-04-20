import rosemary.parser.model.JsonObject
import rosemary.parser.Parser.*
import rosemary.parser.tokenizer.Tokenizer
import rosemary.stringify.Stringify.*
import rosemary.stringify.Generate.{*, given}

@main def test1(): Unit = {
  //  val obj = new Parser("{\"student\":{\"gender\":false},\"age\":114514,\"number\":[114514,1919810,1,1,4,5,1,4,8,{},1145141919810,{\"s123\":\"sss\"}]}").result()
  val obj = parse(
    """{ "programmers": [

    { "firstName": "Brett", "lastName":"McLaughlin", "email": "aaaa" },

    { "firstName": "Jason", "lastName":"Hunter", "email": "bbbb" },

    { "firstName": "Elliotte", "lastName":"Harold", "email": "cccc" }

    ],

    "authors": [

    { "firstName": "Isaac", "lastName": "Asimov", "genre": "science fiction" },

    { "firstName": "Tad", "lastName": "Williams", "genre": "fantasy" },

    { "firstName": "Frank", "lastName": "Peretti", "genre": "christian fiction" }

    ],

    "musicians": [

    { "firstName": "Eric", "lastName": "Clapton", "instrument": "guitar" },

    { "firstName": "Sergei", "lastName": "Rachmaninoff", "instrument": "piano" }

    ] }""""")


  val number = (obj / "programmers" / 0 / "firstName")

  println(number)
}

@main
def test2(): Unit = {
  val r = parse("{\"name\":null}")
  println(r / "name")
}

@main
def test3(): Unit = {
  import rosemary.parser.conv.JsonTypeToValue.given
  val r = parse("{\"name\":\"114\",\"age\":514,\"d\":{\"gender\":true,\"number\":114514},\"list\":[1,4,1,5,4,5,\"ddd\"],\"map\":{\"1919810\":[1,1,4,5,1,4],\"1\":1,\"114514\":1919810}}")
  val number: Int = (r / "map" / "1919810" / 3)
  println(number)
}
//
//@main
//def test4(): Unit = {
//  import rosemary.stringify.Generate.*
//  import rosemary.stringify.conv.ValueToJsonType.given
//  val r: String = json {
//    arr(
//      1, 4, 1, obj {
//        "114514" -> "1919810"
//        "1919810" -> 1919810
//      }
//    )
//  }
//  println(r)
//}

@main
def test5(): Unit = {
  import rosemary.stringify.Stringify.*
  case class A(a: Int, b: String, c: B)
  case class B(d: Boolean, e: Map[String, List[Int]])
  val t = A(114, "514", B(true, Map(
    "114" -> List(5, 1, 4),
    "1919" -> List(8, 1, 0)
  )))
  println(t.toJson)
}

@main
def test6(): Unit = {
  val str = "{\"114514\":\"114514\",\"1919810\":[1,8,9,5,7,8,5,9,5,{\"sss\":114514},\"ssss\"]}"
  val json = obj(
    "sss", obj(
      "sss" -> obj(
        "114" -> "1919810",
        "514" -> "7894654",
        "59964" -> 644542
      )
    ), obj(
      "sss", 14514
      , obj(
        1, 1, 4, 5, 14, 7
      )
    ),null
  )
  println(json)
  val res: String = json
  println(res)
}