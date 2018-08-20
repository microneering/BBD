/*
 * BBDDefaultRowTest.java
 * JUnit based test
 *
 * Created on January 22, 2007, 4:00 PM
 */
package bbd;
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
 * BBD/bbd/BBDDefaultRowTest.java
 */

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author james gamber
 */
public class BBDDefaultRowTest extends BBDUnitTestCase {

    /**
     * Test of getObject method, of class bbd.DefaultBBDRow.
     */
    // public void testGetObject () {
    // System.out.println("getObject");
    //
    // int index = 0;
    // BBDDefaultRow instance = new BBDDefaultRow();
    // BBDDefaultField field = new BBDDefaultField();
    // instance.add (field);
    //
    // Object expResult = field;
    // Object result = instance.getObject(index);
    // Assert.Assert.assertEquals(expResult, result);
    // }
    /**
     * Test of getBBDField method, of class bbd.BBDDefaultRow.
     */
    @Test
    public void testGetBBDField() {
        System.out.println( "getBBDField" );
        setTestMethod();

        int index = 0;
        BBDDefaultRow<BBDDefaultField> instance = new BBDDefaultRow<BBDDefaultField>();
        BBDDefaultField field = new BBDDefaultField( "test" );
        instance.add( field );

        BBDDefaultField expResult = field;
        IBBDField result = instance.getBBDField( index );
        Assert.assertEquals( expResult, result );
    }

    /**
     * Test of getString method, of class bbd.BBDDefaultRow.
     */
    @Test
    public void testGetString() {
        System.out.println( "getString" );
        setTestMethod();

        int index = 0;
        BBDDefaultRow<BBDDefaultField> instance = new BBDDefaultRow<BBDDefaultField>();
        BBDDefaultField field = new BBDDefaultField( "test value" );
        instance.add( field );

        String expResult = "test value";
        String result = instance.getString( index );
        Assert.assertEquals( expResult, result );
    }

    /**
     * Test of getInteger method, of class bbd.BBDDefaultRow.
     */
    @Test
    public void testGetInteger() {
        System.out.println( "getInteger" );
        setTestMethod();

        int index = 0;
        BBDDefaultRow<BBDDefaultField> instance = new BBDDefaultRow<BBDDefaultField>();
        BBDDefaultField field = new BBDDefaultField( Integer.valueOf( 1 ) );
        instance.add( field );

        Integer expResult = 1;
        Integer result = instance.getInteger( index );
        Assert.assertEquals( expResult, result );
    }

    /**
     * Test of remove method, of class bbd.BBDDefaultRow.
     */
    @Test
    public void testRemove() {
        System.out.println( "remove" );
        setTestMethod();

        BBDDefaultRow<BBDDefaultField> instance = new BBDDefaultRow<BBDDefaultField>();
        BBDDefaultField field = new BBDDefaultField( Integer.valueOf( 1 ) );
        boolean add = instance.add( field );
        Assert.assertTrue( add );

            boolean remove = instance.remove( field );
            Assert.assertTrue( remove );

        boolean result = instance.remove( field );
        Assert.assertFalse( result );
    }

    /**
     * test the inherited add method
     */
    @Test
    public void testAdd() {
        System.out.println( "add" );
        setTestMethod();

        BBDDefaultRow<BBDDefaultField> instance = new BBDDefaultRow<BBDDefaultField>();

        BBDDefaultField field = new BBDDefaultField( Integer.valueOf( 1 ) );
        boolean result = instance.add( field );
        Assert.assertTrue( result );

    }

    /**
     * Test of getBBDField method, of class BBDDefaultRow.
     */
    @Test
    public void getBBDField() {
        System.out.println( "getBBDField" );
        setTestMethod();

        BBDDefaultRow<BBDDefaultField> instance = new BBDDefaultRow<BBDDefaultField>();

        BBDDefaultField field = new BBDDefaultField( Integer.valueOf( 1 ) );
        instance.add( field );
        int index = 0;
        BBDDefaultField expResult = field;
        BBDDefaultField result = instance.getBBDField( index );
        Assert.assertEquals( expResult, result );

    }

    /**
     * Test of getString method, of class BBDDefaultRow.
     */
    @Test
    public void getString() {
        System.out.println( "getString" );
        setTestMethod();

        int index = 0;
        BBDDefaultRow instance = new BBDDefaultRow();
        BBDDefaultField df = new BBDDefaultField( "test" );
        instance.add( df );
        String expResult = "test";
        String result = instance.getString( index );
        Assert.assertEquals( expResult, result );

    }

    /**
     * Test of getInteger method, of class BBDDefaultRow.
     */
    @Test
    public void getInteger() {
        System.out.println( "getInteger" );
        setTestMethod();

        int index = 0;
        BBDDefaultRow instance = new BBDDefaultRow();
        BBDDefaultField df = new BBDDefaultField( new Integer( 1 ) );
        instance.add( df );
        Integer expResult = 1;
        Integer result = instance.getInteger( index );
        Assert.assertEquals( expResult, result );

    }

    /**
     * Test of remove method, of class BBDDefaultRow.
     */
    @Test
    public void remove() {
        System.out.println( "remove" );
        setTestMethod();

        BBDDefaultField field = new BBDDefaultField( Integer.valueOf( 1 ) );
        BBDDefaultRow instance = new BBDDefaultRow();
        boolean result = instance.remove( field );
        Assert.assertFalse( result );
    }

    /**
     * Test of getDate method, of class BBDDefaultRow.
     */
    @Test
    public void getDate() {
        System.out.println( "getDate" );
        int index = 0;
        BBDDefaultRow instance = new BBDDefaultRow();
        BBDDefaultField df = new BBDDefaultField( new Date( 1 ) );
        instance.add( df );
        Date expResult = new Date( 1 );
        Date result = instance.getDate( index );
        Assert.assertEquals( expResult, result );

    }

    /**
     * Test of getTime method, of class BBDDefaultRow.
     */
    @Test
    public void getTime() {
        System.out.println( "getTime" );
        setTestMethod();

        int index = 0;
        BBDDefaultRow instance = new BBDDefaultRow();
        BBDDefaultField df = new BBDDefaultField( new Time( 1 ) );
        instance.add( df );
        Time expResult = new Time( 1 );
        Time result = instance.getTime( index );
        Assert.assertEquals( expResult, result );

    }

    /**
     * Test of getTimestamp method, of class BBDDefaultRow.
     */
    @Test
    public void getTimestamp() {
        System.out.println( "getTimeStamp" );
        setTestMethod();

        int index = 0;
        BBDDefaultRow instance = new BBDDefaultRow();
        BBDDefaultField df = new BBDDefaultField( new Timestamp( 1 ) );
        instance.add( df );
        Timestamp expResult = new Timestamp( 1 );
        Timestamp result = instance.getTimestamp( index );
        Assert.assertEquals( expResult, result );
    }

    /**
     * Test of toArray method, of class BBDDefaultRow.
     */
    @Test
    public void toArray() {
        System.out.println( "toArray" );
        setTestMethod();

        BBDDefaultRow instance = new BBDDefaultRow();
        BBDDefaultField df = new BBDDefaultField( new Integer( 1 ) );
        instance.add( df );
        Object[] expResult = {df};
        Object[] result = instance.toArray();
        Assert.assertTrue(Arrays.equals(expResult, result));
    }

    /**
     * Test of equalsNullable method, of class BBDDefaultRow.
     */
    @Test
    public void testEquals() {
        System.out.println( "equalsNullable" );
        setTestMethod();

        // null and not null fields
        IBBDField f1 = new BBDDefaultField( null );
        IBBDField f2 = new BBDDefaultField( null );

        IBBDField f3 = new BBDDefaultField( "test" );
        IBBDField f4 = new BBDDefaultField( "test" );


        BBDDefaultRow<IBBDField> row1 = new BBDDefaultRow();
        row1.add( f1 );

        BBDDefaultRow<IBBDField> row2 = new BBDDefaultRow();
        row2.add( f2 );

        Assert.assertEquals( row1, row2 );

        row1.add( f3 );
        row2.add( f4 );

        Assert.assertEquals( row1, row2 );

        row1.clear();
        row2.clear();

        row1.add( f4 );
        row1.add( f1 );
        row2.add( f3 );
        row2.add( f2 );

        Assert.assertEquals( row1, row2 );

        row1.clear();
        row2.clear();

        // try rows that have different arrangement of null and not null fields
        // so are not equal
        row1.add( f1 );
        row1.add( f4 );
        row2.add( f3 );
        row2.add( f2 );

        Assert.assertTrue( !row1.equals( row2 ) );

        // try collections, hashmaps rely on the hash and equals functions
        // use two rows that are not equal as keys on map
        Map<BBDDefaultRow<IBBDField>, String> list = new HashMap();
        list.put( row1, "text1" );
        list.put( row2, "text2" );

        Assert.assertEquals( list.get( row1 ), "text1" );
        Assert.assertEquals( list.get( row2 ), "text2" );

    }

    /**
     * Test of hashCodeNullable method, of class BBDDefaultRow.
     */
    @Test
    public void testHashCode() {
        System.out.println( "hashCodeNullable" );

        BBDDefaultRow<IBBDField> row1 = new BBDDefaultRow();
        IBBDField f1 = new BBDDefaultField( null );
        row1.add( f1 );

        Assert.assertEquals( 93, row1.hashCode() );
    }
}
