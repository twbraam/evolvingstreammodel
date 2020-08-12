package org.twbraam.predictor.house

import org.apache.flink.annotation.Public
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase
import org.twbraam.predictor.house.sources.Kafka

object HouseSource {

  @Public
  @SerialVersionUID(1L)
  case object Predef extends FromIteratorFunction[String](sources.Predef.unbounded)

  lazy val Kafka: FlinkKafkaConsumerBase[String] = sources.Kafka.consumer
}
