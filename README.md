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

compose `arr` and `obj` and wrapped them in `json`
to get a complex json string

```scala


@main
def test(): Unit = {
  import rosemary.stringify.Generate.*
  import rosemary.stringify.conv.ValueToJsonType.given
  val r: String = json {
    arr(
      1, 4, 1, obj {
        "114514" -> "1919810"
        "1919810" -> 1919810
      }
    )
  }
  println(r)
}

```

this example will output it to console

```json
[
  1,
  4,
  1,
  {
    "1919810": 1919810
  }
]
```

This is it, Thanks for your reading

Ah, Is it too formal ? It's not my style

emm, fragile json library

give me a star if it is worth ?

