package org.twbraam.test.modelsignal

import org.apache.flink.api.common.functions.MapFunction

class ModelSignalToInt extends MapFunction[String, Int] {
  override def map(r: String): Int = r.toInt
}