package org.twbraam.test.modelsignal

import java.io.Serializable
import java.util


@SerialVersionUID(1L)
final class ModelSignalIterator private(val bounded: Boolean) extends util.Iterator[ModelSignal] with Serializable {
  private var index: Int = 0

  override def hasNext: Boolean =
    if (index < 20) true
    else if (!bounded) {
      index = 0
      true
    }
    else false

  override def next: ModelSignal = {
    Thread.sleep(3500)
    val signal = ModelSignal(index)
    index += 1
    signal
  }
}


@SerialVersionUID(1L)
object ModelSignalIterator {

  private[modelsignal] def bounded: ModelSignalIterator = new ModelSignalIterator(true)

  private[modelsignal] def unbounded: ModelSignalIterator = new ModelSignalIterator(false)

}