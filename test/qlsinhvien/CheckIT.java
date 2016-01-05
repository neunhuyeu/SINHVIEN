/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Date;
import java.text.SimpleDateFormat;
import static org.junit.Assert.*;

/**
 *
 * @author cao
 */
public class CheckIT {
    
    SINHVIEN sinhvien ;
    Check check;
    public CheckIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sinhvien = new SINHVIEN("12520430","Cao Thị Thương",new java.sql.Date(9,5,1994));
        check = new Check();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkName method, of class Check.
     */
    @Test
    public void testCheckName() {
        System.out.println("Test checkName");
        boolean expResult = true;
        boolean result = check.checkName(sinhvien.getTENSV());
        assertEquals("1-Error in CheckName()",expResult, result);
    }

    /**
     * Test of checkID method, of class Check.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        
        boolean expResult = true;
        boolean result = check.checkID(sinhvien.getMSSV());
        assertEquals("Error in CheckID",expResult, result);
      
    }

    /**
     * Test of checkDate method, of class Check.
     */
    @Test
    public void testCheckDate() {
        System.out.println("Test checkDate()");
        boolean expResult = true;
        boolean result = check.checkDate(sinhvien.getNgaySinh().toString());
        assertEquals("Error in CheckDate()",expResult, result);
    }
    
}
