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
import static org.junit.Assert.*;

/**
 *
 * @author cao
 */
public class TimkiemTest {
    Timkiem instance;
    public TimkiemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       instance = new Timkiem();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Timkiem.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Timkiem.main(args);
    }

    /**
     * Test of getMasv method, of class Timkiem.
     */
    @Test
    public void testGetMasv() {
        System.out.println("Test getMasv()");
        //Kiểm tra hàm getMasv
        instance.masv = "12520430";
        String expResult = "12520430";
        String result = instance.getMasv();
        assertEquals("1-Error in GetMasv()",expResult, result);
    }

    /**
     * Test of getTensv method, of class Timkiem.
     */
    @Test
    public void testGetTensv() {
        System.out.println(" Test getTensv");
        instance.tensv="Hoàng Phúc Anh";
        String expResult = "Hoàng Phúc Anh";
        String result = instance.getTensv();
        assertEquals("1-Error in GetTensv",expResult, result);
    }
    
}
