/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wpsverlinden.latencyprofiler;

import java.util.Arrays;

/**
 *
 * @author OVerlinden
 */
public class Jatp {

    private final long[] times;
    private long time;
    private int index = 0;
    private boolean isStarted = false;

    public Jatp(int maxCapacity) {
        times = new long[maxCapacity];
        Arrays.fill(times, -1);
    }

    public void startMeasure() {
        if (isStarted) {
            throw new RuntimeException("Measurement already started");
        }
        isStarted = true;
        time = System.nanoTime();
    }

    public void endMeasure() {
        if (!isStarted) {
            throw new RuntimeException("Measurement not started");
        }
        if (index >= times.length) {
            throw new RuntimeException("Profiler capacity exceeded");
        }

        times[index] = System.nanoTime() - time;
        index++;
        isStarted = false;
    }

    public Stats getStats() {
        int filled = 0;
        while (times[filled] != -1) {
            filled++;
        }

        long[] popTimes = new long[filled];
        System.arraycopy(times, 0, popTimes, 0, filled);
        Arrays.sort(popTimes);

        return new Stats(
                popTimes.length,
                (float) (popTimes[Math.min(popTimes.length / 2, popTimes.length - 1)] / 1e3),
                (float) (popTimes[Math.min(popTimes.length * 9 / 10, popTimes.length - 1)] / 1e3),
                (float) (popTimes[Math.min(popTimes.length - popTimes.length / 100, popTimes.length - 1)] / 1e3),
                (float) (popTimes[Math.min(popTimes.length - popTimes.length / 1000, popTimes.length - 1)] / 1e3),
                (float) (popTimes[Math.min(popTimes.length - popTimes.length / 10000, popTimes.length - 1)] / 1e3),
                (float) (popTimes[0] / 1e3),
                (float) (mean(popTimes) / 1e3),
                (float) (popTimes[popTimes.length - 1] / 1e3)
        );
    }

    private float mean(long[] m) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return (float) (sum / m.length);
    }
}
