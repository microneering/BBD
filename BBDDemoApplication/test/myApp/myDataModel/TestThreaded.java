/**
 *
 */
package myApp.myDataModel;
/*
 * Copyright 2007 microneering, Inc and James Gamber
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * BBDDemoApplication/myApp.myDataModel/TestThreaded.java
 */

import java.util.ArrayList;
import java.util.logging.Logger;


import bbd.BBDUnitTestCase;

/**
 *  Multithreaded unit testing is not supported in JUNIT and only runs under Eclipse.
 *  Under Netbeans the test just exits and it's child threads disappear.
 *  
 *  However, this important unit test shows that mySQL and BBD are in fact running as multithreaded and 
 *  are not generating any SQL errors.
 *  
 * @author james gamber
 *
 */
public class TestThreaded extends BBDUnitTestCase {
    
    private static Logger log = Logger.getLogger(TestThreaded.class.getName());

    static private final String name = "test name";
    
    static private final String address = "test address";
    
    static private final String city = "test city";
    
    static private final String state = "NA";
    
    static private final Integer zip = 12345;
    
    static private final TestDataModelRow testRow = new TestDataModelRow (name,
            address, city, state, zip);
    
    static private final ArrayList<Thread> threads = new ArrayList<Thread>();
    
    private static boolean debug = false;
    
 
    static        int running = 0;
    
    static synchronized void done() {
    	running--;
    	threads.remove(Thread.currentThread());
    }
    
    static synchronized int getRunningCount() {
    	return running;
    }
    

//    @Test
//    public synchronized void testSelect () {
//        System.out.println ("select threads");
//        setTestMethod();
//        
//        TestDataModelRow row = testRow;
//        TestDataModelBroker<TestDataModelRow<BBDDefaultField>, 
//                                  BBDRowArrayList<TestDataModelRow<BBDDefaultField>>> instance = 
//                        new TestDataModelBroker<TestDataModelRow<BBDDefaultField>, 
//                                        BBDRowArrayList<TestDataModelRow<BBDDefaultField>>>();
//        
//        if (debug) System.out.println ("run insert "
//                + Thread.currentThread ().getName ());
//        // delete rows so you start with no rows of this type before the test
//        instance.delete(row);
//
//        instance = null;
//        System.gc();
//        final int numThreads = 400;
//        
//       for (int i = 0; i < numThreads; i++) {
//            Thread th = new Thread (new Runnable () {
//                public void run () {
//                	
//                	try {
//                    
//                		TestDataModelBroker<TestDataModelRow, BBDRowArrayList<TestDataModelRow>> instance = new TestDataModelBroker<TestDataModelRow, BBDRowArrayList<TestDataModelRow>>();
//
//                		if (debug) System.out.println ("run "
//                                + Thread.currentThread ().getName ());
//                        // make sure there is at least one row
//                        TestDataModelRow row = testRow;
//                        if (debug) System.out.println ("run insert "
//                                + Thread.currentThread ().getName ());
//                        int resultInsert = instance.insert (row);
//                        addInsertedRows(resultInsert);
//                        if (debug) System.out.println ("runa "
//                                + Thread.currentThread ().getName ());
//                        assertEquals (1, resultInsert);
//                        if (debug) System.out.println ("runb "
//                                + Thread.currentThread ().getName ());
//                        
//                        BBDRowArrayList<TestDataModelRow> expResult = new BBDRowArrayList<TestDataModelRow>();
//                        expResult.add (testRow);
//                        BBDRowArrayList<TestDataModelRow> result = instance
//                                .select (testRow);
//                        
//                        assertTrue (result.size () > 0);
//                        
//                        assertEquals (expResult.get (0), result.get (0));
//                        addSelectedRows (result.size ());
//                        if (debug) System.out.println ("done "
//                                + Thread.currentThread ().getName ());
//                        done();
//                        
//                	} catch (Exception e) {
//                		log.log (Level.SEVERE, "thread failed ", e);
//                	}
//                	
//                	// just let run complete normally, even after exceptions
//                }
//            }, "testThreaded" + i);
//            threads.add (th);
//        }
//        
//        for (Thread th : threads) {
//            if (debug) System.out.println ("start " + th.getName ());
//            th.start ();
//            running++;
//        }
//        
//        while(getRunningCount() > 0) {
//            try {
//                wait(500);
//                log.log (Level.WARNING, "still running "+getRunningCount());
////                System.gc();
//            } catch (Exception ex) {
//                log.log (Level.SEVERE, null, ex);
//            }
//        }
//
//        threads.clear();  // let threads finalize normally
//        
//        assertEquals("Incorrect number of rows inserted", numThreads, getInsertedRows());
//        // the exact number of rows selected varies.  It would be an exact number if all
//        // inserts and select ran in an exact sequence, but they dont
//        assertTrue("incorrect number of rows selected",  getSelectedRows()>1000);
//        
//        System.out.println("All threads done");
//
//    }
//    
 
}
