/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cao
 */
public class ConnectsqlTest {
   
    
    private Connectsql connect;
    public ConnectsqlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        connect = new Connectsql();
    }
    
    @After
    public void tearDown() throws SQLException{
        
        connect.close();
    }

    /**
     * Test of executeupdate method, of class Connectsql.
     */
    @Test
    public void testExecuteupdate() throws Exception {
        System.out.println("Test executeupdate");
        //String sql = "";
        Connectsql instance = new Connectsql();
        int expResult = 1;
        int result = instance.executeupdate("insert into SINHVIEN values ('12520799','Đặng Thị Kim Luyến','07/04/1994')");
        int result_3 = instance.executeupdate("insert into SINHVIEN values ('12520088','Dương Thị Ngọc Xuân','01/20/1994')");
        int result_1 = instance.executeupdate("delete from SINHVIEN where MSSV ="+12520799);
        int result_2 = instance.executeupdate("delete from SINHVIEN where MSSV ="+12520088);
        assertEquals("1-Error in Executeupdate",expResult, result_1);
        assertEquals("2-Error in Executeupdate",expResult, result_2);
        assertEquals("3-Error in Executeupdate",expResult, result);
        assertEquals("4-Error in Executeupdate",expResult, result_3);
    }

    /**
     * Test of execute method, of class Connectsql.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
       // String sql = "";
        Connectsql instance = new Connectsql();
        ResultSet expResult = null;
        ResultSet result = instance.execute("select *from SINHVIEN where MSSV="+12520430);
        ResultSet result_1 = instance.execute("select *from SINHVIEN where MSSV="+12520169);
        assertNotNull("1-Error in execute",result);
        assertNotNull("2-Error in execute",result_1);
      
    }


    /**
     * Test of kiemtraConnect method, of class Connectsql.
     */
    @Test
    public void testKiemtraConnect() {
        System.out.println("Test kiemtraConnect() ");
        boolean expResult = true;
        boolean result = connect.kiemtraConnect();
        assertEquals(expResult, result);
    }

    /**
     * Test of kiemtraDisConnect method, of class Connectsql.
     */
    @Test
    public void testKiemtraDisConnect() {
        System.out.println("kiemtraDisConnect");
        Connectsql instance = new Connectsql();
        try{
        instance.close();
        }catch(Exception Ex){Ex.printStackTrace(); };
        boolean expResult = false;
        boolean result = instance.kiemtraConnect();
        assertEquals(expResult, result);
    }

    /**
     * Test of close method, of class Connectsql.
     */
    @Test
    public void testClose() throws Exception {
        System.out.println("close");
        Connectsql instance = new Connectsql();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
