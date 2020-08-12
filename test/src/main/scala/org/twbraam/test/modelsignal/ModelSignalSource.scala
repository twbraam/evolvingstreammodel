package org.twbraam.test.modelsignal

import org.apache.flink.annotation.Public
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase

object ModelSignalSource {

  @Public
  @SerialVersionUID(1L)
  case object Predef extends FromIteratorFunction[String](ModelSignalPredef.bounded)

  lazy val Kafka: FlinkKafkaConsumerBase[String] = ModelSignalKafka.consumer
}
