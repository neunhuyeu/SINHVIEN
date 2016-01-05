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
public class CheckTest {
    
    public CheckTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    /**
     * Test of checkID method, of class Check.
     */
    @Test
    public void testCheckIDPass() {
        System.out.println("Test checkID()_Pass");
       // String n = "";
        boolean expResult = true;
        //Kiểm tra ID dạng đúng khi nhập vào có chữ lẫn số hoặc không có chữ
        boolean result = Check.checkID("12520430");
        boolean result_1 = Check.checkID("UIT1235467");
        boolean result_2 = Check.checkID("UIT");
        assertEquals("1-Error in CheckID", expResult, result);
        assertEquals("2-Error in CheckID", expResult, result_1);
        assertEquals("3-Error in CheckID", expResult, result_2);
    }

     @Test
    public void testCheckIDFail() {
        System.out.println("Test checkID()_Fail");
       // String n = "";
        boolean expResult = false;
        //Kiểm tra chiều dài kí tự nhập vào không nằm trong khoảng 2-10
        boolean result = Check.checkID("UIT12520430");
        boolean result_2 = Check.checkID("7");
        //Kiểm tra ID có kí tự đặc biệt
        boolean result_1 = Check.checkID("#12520169");
        boolean result_3 = Check.checkID("$$12520169");
        assertEquals("1-Error in CheckID", expResult, result);
        assertEquals("2-Error in CheckID", expResult, result_1);
        assertEquals("3-Error in CheckID", expResult, result_2);
        assertEquals("4-Error in CheckID", expResult, result_3);
    }
    /**
     * Test of checkDate method, of class Check.
     */
    @Test
    public void testCheckDatePass() {
        System.out.println("Test checkDate()_Pass");
       //String n = "";
        boolean expResult = true;
        //Kiểm tra các ngày tháng nhập vào đúng định dạng
        boolean result = Check.checkDate("09/05/1994");
        boolean result_2 = Check.checkDate("01/01/2015");
        boolean result_1 = Check.checkDate("31/05/1960");
        boolean result_3 = Check.checkDate("31/12/2010");
        boolean result_4 = Check.checkDate("29/2/2000");
        assertEquals("1-Error CheckDate",expResult, result);
        assertEquals("2-Error CheckDate",expResult, result_1);
        assertEquals("3-Error CheckDate",expResult, result_2);
        assertEquals("4-Error CheckDate",expResult, result_3);
        assertEquals("5-Error CheckDate",expResult, result_4);
    }
     @Test
    public void testCheckDateFail() {
        System.out.println("Test checkDate()_Fail");
       //String n = "";
        boolean expResult = false;
        //Kiểm tra các kiểu ngày tháng nhập vào sai định dạng
        boolean result_1 = Check.checkDate("05");
        boolean result_2 = Check.checkDate("mười hai");
        //Kiểu ngày tháng có ký tự đạc biệt
        boolean result_3 = Check.checkDate("05*04*1994");
         boolean result_6 = Check.checkDate("05&04&1994");
         //Kiểu ngày có tháng vượt quá 12 tháng
        boolean result_4 = Check.checkDate("30/13/1994");
        //Kiểu ngà có tháng vượt quá 31 ngày
        boolean result_5 = Check.checkDate("32/12/1994");
        
        assertEquals("1-Error CheckDate",expResult, result_1);
        assertEquals("2-Error CheckDate",expResult, result_2);
        assertEquals("3-Error CheckDate",expResult, result_3);
        assertEquals("4-Error CheckDate",expResult, result_4);
        assertEquals("5-Error CheckDate",expResult, result_5);
        assertEquals("7-Error CheckDate",expResult, result_6);
   
    }

    /**
     * Test of checkName method, of class Check.
     */
    @Test
    public void testCheckNamePass() {
        System.out.println("Test checkName()_Pass");
        boolean expResult = true;
        //Kiểm tra các tên nhập vào đúng
        boolean result = Check.checkName("Cao Thị Thương");
        boolean result_1 = Check.checkName("Nguyễn Thị Hương");
        boolean result_2 = Check.checkName("Hoàng Phúc Anh");
        assertEquals("1-Error in CheckName()",expResult, result);
       assertEquals("2-Error in CheckName()",expResult, result_1);
       assertEquals("3-Error in CheckName()",expResult, result_2);
      
        
    }
     @Test
    public void testCheckNameFail() {
        System.out.println("Test checkName()_Fail");
        boolean expResult = false;
        //Kiểm tra tên nhập vào có cả số lẫn chữ
        boolean result = Check.checkName("Cao Thị Thương2");
        //Kiểm tra tên nhập vào chỉ có khoảng trắng
        boolean result_1 = Check.checkName("");
        //Kiểm tra tên nhập vào có kí tự đặc biệt
        boolean result_2 = Check.checkName("Cao-Thị-Thương");
        boolean result_5 = Check.checkName("##Cao Thị Thương");
        boolean result_4 = Check.checkName("@Cao Thị Thương");
        //Kiểm tra tên nhập vào có chiều dài >50 kí tự
        boolean result_3 = Check.checkName("Lê Cao Nguyễn Hoàng Hiếu Nghĩa Đệ Nhất Thương Tâm Nhân");
        //Kiểm tra tên nhập vào có chiều dài <5 kí tự
        boolean result_6 = Check.checkName("Hiếu");
        assertEquals("1-Error in CheckName()",expResult, result);
        assertEquals("2-Error in CheckName()",expResult, result_1);
        assertEquals("3-Error in CheckName()",expResult, result_2);
        assertEquals("4-Error in CheckName()",expResult, result_3);
        assertEquals("5-Error in CheckName()",expResult, result_4);
        assertEquals("6-Error in CheckName()",expResult, result_5);
        assertEquals("7-Error in CheckName()",expResult, result_6);
    }

    /**
     * Test of checkName method, of class Check.
     */
    @Test
    public void testCheckName() {
        System.out.println("checkName");
        String n = "";
        boolean expResult = false;
        boolean result = Check.checkName(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkID method, of class Check.
     */
    @Test
    public void testCheckID() {
        System.out.println("checkID");
        String n = "";
        boolean expResult = false;
        boolean result = Check.checkID(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkDate method, of class Check.
     */
    @Test
    public void testCheckDate() {
        System.out.println("checkDate");
        String date = "";
        boolean expResult = false;
        boolean result = Check.checkDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
}
