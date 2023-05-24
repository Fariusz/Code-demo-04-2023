package org.rloth;

import org.junit.jupiter.api.Test;
import org.rloth.exceptions.ProcessingFileException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ViewSpotFinderTest {
    private static final String FILE_10_000 = "src/main/resources/mesh_x_sin_cos_10000.json";
    private static final String FILE_20_000 = "src/main/resources/mesh_x_sin_cos_20000.json";

    @Test
    void shouldFindAllViewSpotsUnder7Seconds() {
        assertTimeoutPreemptively(Duration.ofSeconds(7), () -> {
            ViewSpotFinder.findViewSpots(FILE_10_000, 10000);
        }, "Processing 10 000 elements took too long (over 7 seconds)");
    }

    @Test
    public void shouldTwiceAsManyElementsBeNoSlowerThanThreeTimesLonger() throws ProcessingFileException, InterruptedException {
        int numIterations = 100;
        double sumRatio = 0.0;

        for (int i = 0; i < numIterations; i++) {
            long start1 = System.nanoTime();
            ViewSpotFinder.findViewSpots(FILE_10_000, 10000);
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            ViewSpotFinder.findViewSpots(FILE_20_000, 20000);
            long end2 = System.nanoTime();

            double ratio = (end1 - start1) / (double) (end2 - start2);
            sumRatio += ratio;
        }

        double avgRatio = sumRatio / numIterations;
        double expectedMaxRatio = 3.0;


        assertTimeoutPreemptively(Duration.ofSeconds(7), () -> {
            assertTrue(avgRatio <= expectedMaxRatio, "Average execution time of 10000 elements is more than " + expectedMaxRatio + " times higher than 20000 elements");
        });
    }

    @Test
    public void printAverageTimeOfExecution() throws ProcessingFileException {

        int numIterations = 100;
        long averageTime = 0;

        for (int i = 0; i < numIterations; i++) {
            long start = System.nanoTime();
            ViewSpotFinder.findViewSpots(FILE_20_000, 20000);
            long end = System.nanoTime();
            averageTime += (end - start);
            System.out.println("Execution time: " + (end - start) / 1000000 + " ms");
        }
        System.out.println("Average execution time: " + (averageTime / numIterations) / 1000000 + " ms");
    }
}