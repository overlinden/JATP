/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wpsverlinden.jatp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author OVerlinden
 */
public class StatsTest {
    
    @Test
    public void contructorAndGetterWorkingProperly() {
        Stats s = new Stats(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(1, s.getNumOfProbes());
        assertEquals(2, s.getUsFor50Percentile(), 0);
        assertEquals(3, s.getUsFor90Percentile(), 0);
        assertEquals(4, s.getUsFor99Percentile(), 0);
        assertEquals(5, s.getUsFor999Percentile(), 0);
        assertEquals(6, s.getUsFor9999Percentile(), 0);
        assertEquals(7, s.getUsForBestRun(), 0);
        assertEquals(8, s.getUsForAvgRun(), 0);
        assertEquals(9, s.getUsForWorstRun(), 0);
    }
    
    @Test
    public void printsRelevantInformation() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Stats s = new Stats(1, 2, 3, 4, 5, 6, 7, 8, 9);
        s.print(new PrintStream(output));
        assertEquals("Latency measured with 1 probe(s): \n" +
                    " 2,00 us for 50 percentile\n" +
                    " 3,00 us for 90 percentile\n" +
                    " 4,00 us for 99 percentile\n" +
                    " 5,00 us for 99.9 percentile\n" +
                    " 6,00 us for 99.99 percentile\n" +
                    " 7,00 us best run\n" +
                    " 8,00 us avg run\n" +
                    " 9,00 us worst run\n", 
                output.toString());
    }
}
