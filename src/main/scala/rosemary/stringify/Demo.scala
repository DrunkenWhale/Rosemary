package rosemary.stringify

case class Demo(name: String, age: Int, d: D = D(true, 114514))

case class D(gender: true, number: Long)