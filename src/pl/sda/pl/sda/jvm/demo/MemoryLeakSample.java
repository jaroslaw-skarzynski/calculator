package pl.sda.pl.sda.jvm.demo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MemoryLeakSample {
    LocalDateTime startTime;
    public void runSample() {
        startTime = LocalDateTime.now();
        DataCache dataCache = new DataCache();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> logCacheStatistics(dataCache), 10, 20, TimeUnit.SECONDS);
        while (true) {
            for (int i = 0; i < 100; i++) {
                dataCache.getValue(i);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }
    }

    private void logCacheStatistics(DataCache dataCache) {
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        System.out.printf("[%s] %s%n", duration, dataCache.getStatisticsString());
    }

    public static void main(String[] args) {
        MemoryLeakSample sample = new MemoryLeakSample();
        sample.runSample();
    }
}
