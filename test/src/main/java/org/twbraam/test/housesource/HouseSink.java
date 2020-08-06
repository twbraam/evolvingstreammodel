package org.twbraam.test.housesource;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A sink for outputting alerts.
 */
@PublicEvolving
@SuppressWarnings("unused")
public class HouseSink implements SinkFunction<House> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(org.twbraam.test.housesource.HouseSink.class);

    @Override
    public void invoke(House value, Context context) {
        LOG.info(value.toString());
    }
}
