/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

import java.sql.Date;
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
public class SINHVIENTest {
    
    SINHVIEN instance;
    String msv;
    String tensv;
    Date ngaysinh;
    public SINHVIENTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new SINHVIEN("12520430","Cao Thị Thương",new java.sql.Date(1994,5,9));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMSSV method, of class SINHVIEN.
     */
    @Test
    public void testGetMSSV() {
        System.out.println("Test getMSSV");
        String expResult = "12520430";
        String result = instance.getMSSV();
        assertEquals("1-Error in GetMSSV()",expResult, result);
       
    }
    /**
     * Test of setMSSV method, of class SINHVIEN.
     */
    @Test
    public void testSetMSSV() {
        System.out.println("Test setMSSV");
        String expResult = "uit1023";
        SINHVIEN ins = null;
        ins.setMSSV("uit1023");
        assertEquals("1-Error in GetMSSV()",expResult, ins.getMSSV());
    }

    /**
     * Test of getTENSV method, of class SINHVIEN.
     */
    @Test
    public void testGetTENSV() {
        System.out.println("Test getTENSV");
        String expResult = "Cao Thị Thương";
        String result = instance.getTENSV();
        assertEquals("1-Error in GetTENSV",expResult, result);
    }

    /**
     * Test of setTENSV method, of class SINHVIEN.
     */
    @Test
    public void testSetTENSV() {
        System.out.println("Test setTENSV");
        String expResult = "Nguyễn Thị Hương";
        SINHVIEN instance = null;
        instance.setTENSV("Nguyễn Thị Hương");
        assertEquals("1-Error in GetTENSV",expResult,instance.getTENSV());
    }

    /**
     * Test of getNgaySinh method, of class SINHVIEN.
     */
    @Test
    public void testGetNgaySinh() {
        System.out.println("Test getNgaySinh");
        Date expResult = new Date(1994,5,9);
        Date result = instance.getNgaySinh();
        assertEquals("Error in GetNgaySinh",expResult, result);
    }

    /**
     * Test of setNgaySinh method, of class SINHVIEN.
     */
    @Test
    public void testSetNgaySinh() {
        System.out.println("Test setNgaySinh");
        Date expResult = new Date(1994,10,10);
        Date NgaySinh = new Date(1994,10,10);
        SINHVIEN instance = null;
        instance.setNgaySinh(NgaySinh);
        assertEquals("Error in SetNgaySinh",expResult, instance.getNgaySinh());
    }

    /**
     * Test of main method, of class SINHVIEN.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        SINHVIEN.main(args);
    }
    
}
