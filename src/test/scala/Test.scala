import rosemary.parser.model.JsonObject
import rosemary.parser.Parser
import rosemary.parser.tokenizer.Tokenizer

@main def test1(): Unit = {
  //  val obj = new Parser("{\"student\":{\"gender\":false},\"age\":114514,\"number\":[114514,1919810,1,1,4,5,1,4,8,{},1145141919810,{\"s123\":\"sss\"}]}").parse()
  val obj = new Parser(
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

    ] }""""").parse()


  val number = (obj / "programmers" / 0 / "firstName")

  println(number)
}

@main
def test2(): Unit = {
  val r = new Parser("{\"name\":null}").parse()
  println((r / "name"))
}