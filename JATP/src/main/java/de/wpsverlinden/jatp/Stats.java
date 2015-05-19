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

import java.io.PrintStream;

public class Stats {

    private final int numOfProbes;
    private final float usFor50Percentile, usFor90Percentile, usFor99Percentile, usFor999Percentile, usFor9999Percentile;
    private final float usForBestRun, usForWorstRun, usForAvgRun;

    public Stats(int numOfProbes,
            float usFor50Percentile,
            float usFor90Percentile,
            float usFor99Percentile,
            float usFor999Percentile,
            float usFor9999Percentile,
            float usForBestRun,
            float usForAvgRun,
            float usForWorstRun) {
        this.numOfProbes = numOfProbes;
        this.usFor50Percentile = usFor50Percentile;
        this.usFor90Percentile = usFor90Percentile;
        this.usFor99Percentile = usFor99Percentile;
        this.usFor999Percentile = usFor999Percentile;
        this.usFor9999Percentile = usFor9999Percentile;
        this.usForBestRun = usForBestRun;
        this.usForAvgRun = usForAvgRun;
        this.usForWorstRun = usForWorstRun;
    }

    public int getNumOfProbes() {
        return numOfProbes;
    }

    public float getUsFor50Percentile() {
        return usFor50Percentile;
    }

    public float getUsFor90Percentile() {
        return usFor90Percentile;
    }

    public float getUsFor99Percentile() {
        return usFor99Percentile;
    }

    public float getUsFor999Percentile() {
        return usFor999Percentile;
    }

    public float getUsFor9999Percentile() {
        return usFor9999Percentile;
    }

    public float getUsForBestRun() {
        return usForBestRun;
    }

    public float getUsForWorstRun() {
        return usForWorstRun;
    }

    public float getUsForAvgRun() {
        return usForAvgRun;
    }

    void print(PrintStream out) {
        out.printf("Latency measured with %d probe(s): \n"
                + " %.2f us for 50 percentile\n"
                + " %.2f us for 90 percentile\n"
                + " %.2f us for 99 percentile\n"
                + " %.2f us for 99.9 percentile\n"
                + " %.2f us for 99.99 percentile\n"
                + " %.2f us best run\n"
                + " %.2f us avg run\n"
                + " %.2f us worst run\n",
                numOfProbes,
                usFor50Percentile,
                usFor90Percentile,
                usFor99Percentile,
                usFor999Percentile,
                usFor9999Percentile,
                usForBestRun,
                usForAvgRun,
                usForWorstRun
        );
    }
}
