package org.twbraam.test.modelsignal

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaConsumerBase}

object ModelSignalKafka {
  val properties = new Properties()
  properties.setProperty("bootstrap.servers", "localhost:9092")
  properties.setProperty("group.id", "modelSignal")
  val consumer: FlinkKafkaConsumerBase[String] = new FlinkKafkaConsumer[String]("modelSignal", new SimpleStringSchema(), properties)
    .setStartFromEarliest()


}
