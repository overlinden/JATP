/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wpsverlinden.latencyprofiler;

import java.io.PrintStream;

/**
 *
 * @author OVerlinden
 */
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
