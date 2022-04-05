package rosemary.stringify

import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

class Stringify {

}

object Stringify {

  def stringify[T <: Product](x: T): String = {
    stringifyCaseClass(x)
  }

  def stringify[T <: Map[_, _]](x: T): String = {
    stringifyMap(x)
  }

  def stringify[T <: Seq[_]](x: T): String = {
    stringifySeq(x)
  }

  private def stringifyCaseClass[T <: Product](x: T): String = {
    val product: Product = x.asInstanceOf[Product]
    (0 until product.productArity).map(i =>
      val key: String = product.productElementName(i)
      val value: String = valueMatch(product.productElement(i))
      s"\"$key\":$value"
    ).mkString("{", ",", "}")
  }

  private def stringifyMap(x: Map[_, _]): String = {
    x.toSeq.map((key, value) => {
      s"\"$key\":${valueMatch(value)}"
    }).mkString("{", ",", "}")
  }

  private def stringifySeq(x: Seq[_]): String = {
    x.map(key =>
      s"${valueMatch(key)}"
    ).mkString("[", ",", "]")
  }

  private def valueMatch(x: Any): String = {
    x match {
      case t: Int => t.toString
      case t: Long => t.toString
      case t: Double => t.toString
      case t: Float => t.toString
      case t: Boolean => t.toString
      case t: String => s"\"$t\""
      case t: Seq[_] => stringifySeq(t)
      case t: Map[_, _] => stringifyMap(t)
      case t: Product => stringifyCaseClass(t)
      case t => throw new Exception(s"Can't Stringify Type: ${t.getClass.getSimpleName}")
    }
  }

}
