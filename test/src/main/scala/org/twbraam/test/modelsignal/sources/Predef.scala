package org.twbraam.test.modelsignal.sources

import java.io.Serializable
import java.util


@SerialVersionUID(1L)
final class Predef private(val bounded: Boolean) extends util.Iterator[String] with Serializable {
  private var index: Int = 0

  override def hasNext: Boolean =
    if (index < 20) true
    else if (!bounded) {
      index = 0
      true
    }
    else false

  override def next: String = {
    Thread.sleep(3500)
    val signal = index
    index += 1
    signal.toString
  }
}


@SerialVersionUID(1L)
object Predef {

  private[modelsignal] def bounded: Predef = new Predef(true)

  private[modelsignal] def unbounded: Predef = new Predef(false)

}