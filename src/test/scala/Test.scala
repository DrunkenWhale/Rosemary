import rosemary.parser.model.JsonObject
import rosemary.parser.Parser.*
import rosemary.parser.tokenizer.Tokenizer

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
  val r = parse("{\"name\":\"114\",\"age\":514,\"d\":{\"gender\":true,\"number\":114514},\"list\":[1,4,1,5,4,5,\"ddd\"],\"map\":{\"1919810\":[1,1,4,5,1,4],\"1\":1,\"114514\":1919810}}")
  println(r / "name")
}

@main
def test4(): Unit = {
  import rosemary.stringify.Generate.*
  import rosemary.stringify.conv.ValueToJsonType.given
  val r = obj(
    "name" -> "",
    "sss" -> 114514,
    "array" -> arr(1, 1, 4, 51, 4, 7, obj {
      "114514" -> 1919810
    })
  )
  println(r)
}