package org.twbraam.test.modelsignal

import org.apache.flink.annotation.Public
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction


@Public
@SerialVersionUID(1L)
class ModelSignalSource() extends FromIteratorFunction[ModelSignal](ModelSignalIterator.bounded)