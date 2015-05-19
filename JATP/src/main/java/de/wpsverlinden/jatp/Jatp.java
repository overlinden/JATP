/*
 * JATP - A simple Java Application Timing Profiler
 * Copyright (C) 2015 Oliver Verlinden (http://wps-verlinden.de)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.wpsverlinden.jatp;

import java.util.Arrays;

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
        while (filled < times.length && times[filled] != -1) {
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
