/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class QLTHONGTINTest {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String regexDDMMYYYY = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    QLTHONGTIN instance ;
    public QLTHONGTINTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         instance = new QLTHONGTIN();
    }
    
    @After
    public void tearDown() {
       instance.setVisible(false);
    }

    /**
     * Test of checkinfo method, of class QLTHONGTIN.
     */
    @Test
    public void testCheckinfoPass() {
        System.out.println("Test checkinfo");
       //Kiểm tra thông tin nhập vào các textBox đúng định dạng
        instance.tsvtf.setText("Cao Thị Thương");
        instance.msvtf.setText("12520430");
        instance.nstf.setText("09/05/1994");
        boolean expResult = true;
        boolean result = instance.checkinfo();
        assertEquals("1-Error in Checkinfor",expResult, result);

    }
  @Test
    public void testCheckinfoFail() {
        System.out.println("Test checkinfo");
       //Kiểm tra thông tin nhập vào các textBox sai định dạng
        instance.tsvtf.setText("Cao-Thị-Thương");
        instance.msvtf.setText("#12520430");
        instance.nstf.setText("32/12/1994");
        boolean expResult = false;
        boolean result = instance.checkinfo();
        assertEquals("1-Error in Checkinfor",expResult, result);
    }
    
    /**
     * Test of CheckMS method, of class QLTHONGTIN.
     */
    @Test
    public void testCheckMS() {
        System.out.println("CheckMS");
        //Kiểm tra mã sinh viên tồn tại trong CSDL
        ArrayList<String> result = instance.CheckMS("12520430");
        assertNotNull("1-Error in CheckMS",result);
        ArrayList<String> result_1 = instance.CheckMS("12520169");
        assertNotNull("2-Error in CheckMS",result_1);
        //Kiểm tra MSV không tồn tại trong CSDL
        ArrayList<String> result_2 = instance.CheckMS("1152001");
        assertNull("3-Error in CheckMS", result_2);
      
    }

    /**
     * Test of Them method, of class QLTHONGTIN.
     */
    @Test
    public void testThem() throws Exception {
        System.out.println("Test Them()");
        Date date = sdf.parse("20/04/1994"); 
        Date ngay = new java.sql.Date(date.getTime());
        int expResult = 1;
        int result = instance.Them("1252001", "Nguyễn Văn A",ngay);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of Xoa method, of class QLTHONGTIN.
     */
    @Test
    public void testXoaPass() throws Exception {
        System.out.println("Test Xoa()_Pass"); 
        int expResult = 1;
        //Kiểm tra trường hợp msv tồn tại
        int result = instance.Xoa("12520001");
        int result_1 = instance.Xoa("12520002");
        assertEquals("1-Error in Xoa()",expResult, result);
        assertEquals("2-Error in Xoa()",expResult, result_1);
        assertEquals("3-Error in Xoa()",expResult, result_1);
     
    }
       
    @Test
    public void testXoaFail() throws Exception {
        System.out.println("Test Xoa()_Fail"); 
        int expResult = 0;
        //Kiểm tra msv không tồn tại
        int result_2 = instance.Xoa("12520000");
        assertEquals("3-Error in Xoa()",expResult, result_2);
     
    }

    /**
     * Test of Sua method, of class QLTHONGTIN.
     */
    @Test
    public void testSuaPass() throws Exception {
        System.out.println("Test Sua()_Pass");
        //Cập nhật sinh viên tồn tại
        SINHVIEN sv = new SINHVIEN("12520430","Cao Thị Thương",new java.sql.Date(1994,5,10));
        int expResult = 1;
        int result = instance.Sua(sv);
        assertEquals(expResult, result);
       
    }
     @Test
    public void testSuaFail() throws Exception {
        System.out.println("Test Sua()_Fail");
        //Cập nhật sinh viên tồn tại
        SINHVIEN sv = new SINHVIEN("12520120","Cao Thị Hương",new java.sql.Date(1994,5,10));
        int expResult = 0;
        int result = instance.Sua(sv);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of loadtable method, of class QLTHONGTIN.
     */
    @Test
    public void testLoadtable() {
        System.out.println("Test loadtable");
        instance.loadtable();
        assertTrue("1-Erroe in Loadtable()",instance.tbsinhvien.getAutoResizeMode()!=0);
       
    }

    /**
     * Test of setTable method, of class QLTHONGTIN.
     */
    @Test
    public void testSetTable() {
        System.out.println("setTable");
        //Kiểm tra sinh viên có tồn tại trong bảng
        instance.loadtable();
        instance.setTable("430","Thương");
        assertTrue("1-Erroe in SetTable()",instance.tbsinhvien.getRowCount()>=1);
        //Kiểm tra sinh viên không tồn tại trong bảng
        instance.loadtable();
        instance.setTable("123","");
        assertFalse("2-Error in SetTable()",instance.tbsinhvien.getRowCount()>=1);
        instance.setTable("","Phuong");
        assertFalse("3-Error in SetTable()",instance.tbsinhvien.getRowCount()>=1);
        
    }

    /**
     * Test of checkinfo method, of class QLTHONGTIN.
     */
    @Test
    public void testCheckinfo() {
        System.out.println("checkinfo");
        QLTHONGTIN instance = new QLTHONGTIN();
        boolean expResult = false;
        boolean result = instance.checkinfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loaddata method, of class QLTHONGTIN.
     */
    @Test
    public void testLoaddata() {
        System.out.println("loaddata");
        QLTHONGTIN instance = new QLTHONGTIN();
        instance.loaddata();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Xoa method, of class QLTHONGTIN.
     */
    @Test
    public void testXoa() throws Exception {
        System.out.println("Xoa");
        String mssv = "";
        QLTHONGTIN instance = new QLTHONGTIN();
        int expResult = 0;
        int result = instance.Xoa(mssv);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Sua method, of class QLTHONGTIN.
     */
    @Test
    public void testSua() throws Exception {
        System.out.println("Sua");
        SINHVIEN sv = null;
        QLTHONGTIN instance = new QLTHONGTIN();
        int expResult = 0;
        int result = instance.Sua(sv);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of TK method, of class QLTHONGTIN.
     */
    @Test
    public void testTK() {
        System.out.println("TK");
        Timkiem tk = null;
        QLTHONGTIN instance = new QLTHONGTIN();
        instance.TK(tk);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class QLTHONGTIN.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        QLTHONGTIN.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
