/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbd;

import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jamesgamber
 * @param <R>
 * @param <L>
 */
public class BBDDefaultBrokerTest<R extends IBBDRow<IBBDField>, 
                                             L extends BBDRowArrayList<R>> 
                extends BBDUnitTestCase<R,L> {

    private BBDDefaultBroker<R,L> instance;
    private R testRow;

    /**
     *
     */
    public BBDDefaultBrokerTest() {
    }

    /**
     * Does the setup before each test
     * @throws Exception
     */
    @Before
    public void setUpMethod() throws Exception {

        instance = new BBDDefaultBroker<>();
        testRow = (R)new BBDDefaultRow();
        instance.setPrincipal(testUser, testPW );

        // set apis
        IBBDAPI<R> selectApi = BBDAbstractFactory.makeBBDAPI("test", "testSelect");
        instance.setSelectAPI(selectApi);
        IBBDAPI<R> insertApi = BBDAbstractFactory.makeBBDAPI("test", "testInsert");
        instance.setInsertAPI(insertApi);
        IBBDAPI<R> insertGKApi = BBDAbstractFactory.makeBBDAPI("test", "testInsertGK");
        instance.setInsertGkAPI(insertGKApi);
        IBBDAPI<R> updateApi = BBDAbstractFactory.makeBBDAPI("test", "testUpdate");
        instance.setUpdateAPI(updateApi);
        IBBDAPI<R> deleteApi = BBDAbstractFactory.makeBBDAPI("test", "testDelete");
        instance.setDeleteAPI(deleteApi);
                
        testRow.add(new BBDDefaultField("test user"));
        testRow.add(new BBDDefaultField("test street"));
        testRow.add(new BBDDefaultField("test city"));
        testRow.add(new BBDDefaultField("ts"));
        testRow.add(new BBDDefaultField("12345"));

    }

    /**
     * Test of select method, of class BBDDefaultBroker.
     */
    @Test
    public void testSelect() {
        System.out.println("select");

        IBBDRow<IBBDField> row = new BBDDefaultRow<>();
        row.add(new BBDDefaultField("test user"));

        L result;
        result = instance.select(( R ) row);

        assertTrue(result.size() > 0);
        assertEquals(result.get(0).get(0).getValue(), "test user");
        assertEquals(result.get(0).get(1).getValue(), "test street");
        assertEquals(result.get(0).get(2).getValue(), "test city");
        assertEquals(result.get(0).get(3).getValue(), "ts");
    }


    /**
     * Test of insert method, of class BBDDefaultBroker.
     * @throws java.sql.SQLException
     */
    @Test
    public void testInsert_1args_1() throws SQLException {
        System.out.println("insert");

        int expResult = 1;
        int result = instance.insert(testRow);

        assertEquals(expResult, result);
    }

    /**
     * Test of insert method, of class BBDDefaultBroker.
     * @throws java.sql.SQLException
     */
    @Test
    public void testInsert_1args_2() throws SQLException {
        System.out.println("insert");
        L list = (L)new BBDRowArrayList<>();

        list.add(testRow);
        list.add(testRow);

        int expResult = 2;
        int result = instance.insert(list);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertGetGeneratedKeys method, of class BBDDefaultBroker.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertGetGeneratedKeys_1args_1() throws Exception {
        System.out.println("insertGetGeneratedKeys");

        List<Integer> result = instance.insertGetGeneratedKeys(testRow);

        assertTrue(result.size() == 2);
        assertTrue(result.get(0) == 1);

    }

    /**
     * Test of insertGetGeneratedKeys method, of class BBDDefaultBroker.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertGetGeneratedKeys_1args_2() throws Exception {
        System.out.println("insertGetGeneratedKeys");

        L list = (L)new BBDRowArrayList<>();
        
        list.clear();

        list.add(testRow);
        list.add(testRow);

        List<Integer> result = instance.insertGetGeneratedKeys(list);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(2), result.get(0));
        assertTrue(result.get(1) > 0);
    }

    /**
     * Test of update method, of class BBDDefaultBroker.
     */
    @Test
    public void testUpdate_1args_1() {
        System.out.println("update");

        int result = instance.update(testRow);
        assertTrue(result > 0);
    }

    /**
     * Test of update method, of class BBDDefaultBroker.
     */
    @Test
    public void testUpdate_1args_2() {
        System.out.println("update");

         L list = (L)new BBDRowArrayList<>();

        list.add(testRow);
        list.add(testRow);


        int result = instance.update(list);
        assertTrue(result > 0);
        
    }

    /**
     * Test of delete method, of class BBDDefaultBroker.
     */
    @Test
    public void testDelete_1args_1() {
        System.out.println("delete");

        R row = (R)new BBDDefaultRow<>();
        row.add(new BBDDefaultField("test user"));

        int result = instance.delete(row);
        assertTrue(result > 0);

        instance.insert(testRow);
    }

    /**
     * Test of delete method, of class BBDDefaultBroker.
     */
    @Test
    public void testDelete_1args_2() {
        System.out.println("delete");

        L list = (L) new BBDRowArrayList<>();
        R row = (R) new BBDDefaultRow<>();
        row.add(new BBDDefaultField("test user"));


        list.add(row);

        instance.delete(list);
        instance.insert(testRow);
        instance.insert(testRow);
        instance.insert(testRow);

        int result = instance.delete(list);
        assertEquals(3, result);
        instance.insert(testRow);

    }



}