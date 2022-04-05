package rosemary.stringify

import scala.reflect.ClassTag

class Stringify {

}

object Stringify {
  def main(args: Array[String]): Unit = {
    println(stringify(Demo("114", 514)))
  }

  def stringify[T <: Product](x: T): String = {
    val product: Product = x.asInstanceOf[Product]
    (0 until product.productArity).map(i =>
      val key: String = product.productElementName(i)
      val value: String = product.productElement(i) match {
        case t: Int => t.toString
        case t: Long => t.toString
        case t: Double => t.toString
        case t: Float => t.toString
        case t: Boolean => t.toString
        case t: String => s"\"$t\""
        case t: Product => stringify(t)
        case t => throw new Exception(s"Can't Stringify Type: ${t.getClass.getSimpleName}")
      }
      s"\"$key\":$value"
    ).mkString("{", ",", "}")
  }

}
