package pl.sda.pl.sda.jvm.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemoryLeakSample {
    private static final long LENGTH = 1024 * 2;

    public void runSample() {
        Map<Key, String> map = new HashMap<>();
        while (true) {
            for (int i = 0; i < 100; i++) {
                Key key = new Key(i, LENGTH);
                if (!map.containsKey(key)) {
                    map.put(key, "Numer:" + i);
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }

    public static void main(String[] args) {
        MemoryLeakSample sample = new MemoryLeakSample();
        sample.runSample();
    }
}

class Key {
    private String key;

    public Key(long number, long length) {
        String binaryString = Long.toBinaryString(number);
        StringBuilder keyBuilder = new StringBuilder();
        for (long i = 0; i < length - binaryString.length(); i++) {
            keyBuilder.append('0');
        }
        keyBuilder.append(binaryString);
        key = keyBuilder.toString();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
