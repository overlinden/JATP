/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.wpsverlinden.jatp;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author OVerlinden
 */
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
