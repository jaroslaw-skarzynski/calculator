package pl.sda.pl.sda.jvm.demo;

import java.util.HashMap;
import java.util.Map;

public class DataCache {
    private static final long VALUE_SIZE = 1024;
    private Map<Key, Value> map = new HashMap<>();
    private Statistics statistics = new Statistics();

    public String getValue(int id) {
        Key key = new Key(id);
        if (map.containsKey(key)) {
            statistics.increaseCacheHit();
            return map.get(key).getValue();
        } else {
            statistics.increaseCacheMiss();
            String value = computeValue(id);
            map.put(key, new Value(value));
            return value;
        }
    }

    public String getStatisticsString() {
        return statistics.getStatisticsString();
    }

    private String computeValue(int id) {
        StringBuilder valueBuilder = new StringBuilder(id + "");
        for (long i = 0; i < VALUE_SIZE; i++) {
            valueBuilder.append('0');
        }
        return valueBuilder.toString();
    }

    private class Key {
        private int id;

        public Key(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "id=" + id +
                    '}';
        }
    }

    private class Value {
        private String value;

        public Value(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }

    private class Statistics {
        private int totalCount = 0;
        private int cacheHitCount = 0;
        private int cacheMissCount = 0;

        void increaseCacheHit() {
            cacheHitCount++;
            totalCount++;
        }

        void increaseCacheMiss() {
            cacheMissCount++;
            totalCount++;
        }

        String getStatisticsString() {
            return String.format("total: %d, hits: %d, miss: %d, cache size: %d", totalCount, cacheHitCount, cacheMissCount, DataCache.this.map.size());
        }
    }
}