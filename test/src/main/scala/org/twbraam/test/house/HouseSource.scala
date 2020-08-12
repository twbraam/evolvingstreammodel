package org.twbraam.test.house

import org.apache.flink.annotation.Public
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase

object HouseSource {

  @Public
  @SerialVersionUID(1L)
  case object Predef extends FromIteratorFunction[String](HousePredef.unbounded)

  lazy val Kafka: FlinkKafkaConsumerBase[String] = HouseKafka.consumer
}
