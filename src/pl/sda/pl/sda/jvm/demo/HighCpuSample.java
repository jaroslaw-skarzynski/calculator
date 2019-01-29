package pl.sda.pl.sda.jvm.demo;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HighCpuSample {
    public void runSample() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(this::heavyWork);
        executorService.submit(this::lightWork);
        executorService.submit(this::lightWork);
        executorService.submit(this::heavyWork);
        executorService.submit(this::lightWork);

        executorService.awaitTermination(1, TimeUnit.DAYS);
        executorService.shutdown();
    }

    private void heavyWork() {
        while (true) {
            try {
                StringBuilder textToDigest = new StringBuilder("Ala ma kota:");
                for (int i = 0; i < 1000; i++) {
                    UUID uuid = UUID.randomUUID();
                    textToDigest.append(uuid.toString());
                }

                byte[] bytes = textToDigest.toString().getBytes();
                MessageDigest digest = MessageDigest.getInstance("MD5");
                digest.update(bytes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void lightWork() {
        Long counter = 0L;
        while (true) {
            counter++;
            try {
                Thread.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter == Long.MAX_VALUE) {
                counter = 0L;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HighCpuSample highCpuSample = new HighCpuSample();
        highCpuSample.runSample();
    }
}
