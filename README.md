# Rosemary

A lightweight json library

you think I'd say that?

no way

This is a delicate json library

## Parse

if you have a json string, like this

```json
{
  "name": "114",
  "age": 514,
  "d": {
    "gender": true,
    "number": 114514
  },
  "list": [
    1,
    4,
    1,
    5,
    4,
    5,
    "ddd"
  ],
  "map": {
    "1919810": [
      1,
      1,
      4,
      5,
      1,
      4
    ],
    "1": 1,
    "114514": 1919810
  }
}

```

use this to parse it

```scala
import rosemary.parser.Parser.parse

@main
def test(): Unit = {
  import rosemary.parser.conv.JsonTypeToValue.given
  val r = parse("{\"name\":\"114\",\"age\":514,\"d\":{\"gender\":true,\"number\":114514},\"list\":[1,4,1,5,4,5,\"ddd\"],\"map\":{\"1919810\":[1,1,4,5,1,4],\"1\":1,\"114514\":1919810}}")
  // you can use Parser(string).parser to get the same result
  val number: Int = r / "map" / "1919810" / 3
  // output 5 in console
}


```

### Stringify

In a word, there are two ways to stringify a scala class to json string in this json library but i hate too more
configuration, that will make me feel grumpy to my dear toy

so, It is unsafe but have more free place

#### with CaseClass / Map / Seq

```scala


@main
def test(): Unit = {
  import rosemary.stringify.Stringify.*
  case class A(a: Int, b: String, c: B)
  case class B(d: Boolean, e: Map[String, List[Int]])
  val t = A(114, "514", B(true, Map(
    "114" -> List(5, 1, 4),
    "1919" -> List(8, 1, 0)
  )))
  println(t.toJson)
}

```

the output of this code:

```json
{
  "a": 114,
  "b": "514",
  "c": {
    "d": true,
    "e": {
      "114": [
        5,
        1,
        4
      ],
      "1919": [
        8,
        1,
        0
      ]
    }
  }
}
```

this is an easy example, you can use any `Map[String,_]` or any collection extends `Seq` to call their `toJson` method
and get json string from `toJson` method's return

### without any exist case class / Map / Seq

this way just like `gin.H{}` or `jsonify()`

~~but fat less than them~~

compose `obj`
to get a complex json string

```scala


@main
def test(): Unit = {
  import rosemary.stringify.Generate.{*, given}
  val r = obj {
    obj(
      1, 4, 1, obj(
        "114514" -> "1919810",
        "1919810" -> 1919810
      )
    )
  }
  val rString: String = r
  println(rString)
  import rosemary.stringify.Pretty.pretty
  println(r.pretty())
}

```

this example will output it to console

```text
[[1,4,1,{"114514":"1919810","1919810":1919810}]]
[
    [
        1,
        4,
        1,
        {
            "114514":"1919810",
            "1919810":1919810
        }
    ]
]
```

another example

```scala

@main
def test(): Unit = {
  val json: JsonValue = obj(
    "sss", obj(
      "sss" -> obj(
        "114" -> "1919810",
        "514" -> "7894654",
        "59964" -> 644542
      )
    ), obj(
      "sss", 14514
      , obj(
        Map("1" -> 114,
          "1" -> 514,
          "4" -> 1919)
      )
    ), null, true, false
  )
  println(json.pretty())
}

```

this example will output into console

```json

[
  "sss",
  {
    "sss": {
      "514": "7894654",
      "114": "1919810",
      "59964": 644542
    }
  },
  [
    "sss",
    14514,
    {
      "1": 514,
      "4": 1919
    }
  ],
  null,
  true,
  false
]


```
