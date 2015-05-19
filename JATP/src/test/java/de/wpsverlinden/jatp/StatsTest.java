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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatsTest {
    
    @Test
    public void getNumOfProbesShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(1, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(1, s.getNumOfProbes());
    }
    
    @Test
    public void getUsFor50PercentileShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 1, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(1, s.getUsFor50Percentile(), 0);
    }
    
    @Test
    public void getUsFor90PercentileShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 0, 1, 0, 0, 0, 0, 0, 0);
        assertEquals(1, s.getUsFor90Percentile(), 0);
    }
    
        
    @Test
    public void getUsFor99PercentileShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 0, 0, 1, 0, 0, 0, 0, 0);
        assertEquals(1, s.getUsFor99Percentile(), 0);
    }
    
        
    @Test
    public void getUsFor999PercentileShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 0, 0, 0, 1, 0, 0, 0, 0);
        assertEquals(1, s.getUsFor999Percentile(), 0);
    }
    
        
    @Test
    public void getUsFor9999PercentileShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 0, 0, 0, 0, 1, 0, 0, 0);
        assertEquals(1, s.getUsFor9999Percentile(), 0);
    }
    
        
    @Test
    public void getUsForBestRunShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 0, 0, 0, 0, 0, 1, 0, 0);
        assertEquals(1, s.getUsForBestRun(), 0);
    }
    
        
    @Test
    public void getUsForAvgRunShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 0, 0, 0, 0, 0, 0, 1, 0);
        assertEquals(1, s.getUsForAvgRun(), 0);
    }
    
        
    @Test
    public void getUsForWorstRunShouldReturnCorrectValueGivenByConstructor() {
        Stats s = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 1);
        assertEquals(1, s.getUsForWorstRun(), 0);
    }
    
    @Test
    public void printReturnsRelevantInformation() {
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
