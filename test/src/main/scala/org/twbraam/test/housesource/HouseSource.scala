package org.twbraam.test.housesource

import org.apache.flink.annotation.Public
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction

@Public
@SerialVersionUID(1L)
object HouseSource {

  @SerialVersionUID(1L)
  private class RateLimitedIterator[T] private(val inner: Iterator[T]) extends Iterator[T] with Serializable {
    override def hasNext: Boolean = inner.hasNext

    override def next: T = {
      try Thread.sleep(100)
      catch {
        case e: InterruptedException =>
          throw new RuntimeException(e)
      }
      inner.next
    }
  }

}

@Public
@SerialVersionUID(1L)
class HouseSource() extends FromIteratorFunction[House](HouseIterator.bounded) {
}

