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

import org.junit.Test;
import static org.junit.Assert.*;

public class JatpTest {
    
    @Test(expected = RuntimeException.class)
    public void endMeasureThrowsExceptionOnCapacityExceeded() {
        Jatp profiler = new Jatp(1);
        
        profiler.startMeasure();
        profiler.endMeasure();
        
        profiler.startMeasure();
        profiler.endMeasure();
    }
    
    @Test(expected = RuntimeException.class)
    public void endNotRunningMeasurementCausesException() {
        Jatp profiler = new Jatp(0);
        profiler.endMeasure();
    }
    
    @Test(expected = RuntimeException.class)
    public void startRunningMeasurementCausesException() {
        Jatp profiler = new Jatp(2);
        profiler.startMeasure();
        profiler.startMeasure();
    }
    
    @Test
    public void numberOfProbesIsCorrect() {
        Jatp profiler = new Jatp(10);
        
        profiler.startMeasure();
        profiler.endMeasure();
        
        profiler.startMeasure();
        profiler.endMeasure();
        
        profiler.startMeasure();
        profiler.endMeasure();
        
        Stats stats = profiler.getStats();
        assertEquals(3, stats.getNumOfProbes());
    }
    
    @Test
    public void maxGreaterAvgGreaterMin() throws InterruptedException {
        Jatp profiler = new Jatp(10);
        
        profiler.startMeasure();
        Thread.sleep(100);
        profiler.endMeasure();
        
        profiler.startMeasure();
        Thread.sleep(200);
        profiler.endMeasure();
        
        profiler.startMeasure();
        Thread.sleep(300);
        profiler.endMeasure();
        
        Stats stats = profiler.getStats();
        assertTrue(stats.getUsForBestRun() > 90000 && stats.getUsForBestRun() < 110000);
        assertTrue(stats.getUsForAvgRun() > 190000 && stats.getUsForAvgRun() < 210000);
        assertTrue(stats.getUsForWorstRun() > 290000 && stats.getUsForBestRun() < 310000);
    }
}
