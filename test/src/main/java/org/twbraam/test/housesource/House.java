package org.twbraam.test.housesource;

import java.util.Objects;

/**
 * A simple transaction.
 */

@SuppressWarnings("unused")
public final class House {

    private String data;

    private long timestamp;

    public House() { }

    public House(String data, long timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setAccountId(String data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        org.twbraam.test.housesource.House that = (org.twbraam.test.housesource.House) o;
        return data.equals(that.data) &&
                timestamp == that.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, timestamp);
    }

    @Override
    public String toString() {
        return "House{" +
                "data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
