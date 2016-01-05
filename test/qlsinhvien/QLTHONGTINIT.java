/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

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
public class QLTHONGTINIT {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String regexDDMMYYYY = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    SINHVIEN sinhvien;
    Timkiem timkiem;
    QLTHONGTIN instance;
    public QLTHONGTINIT() {
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
       timkiem = new Timkiem();
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkinfo method, of class QLTHONGTIN.
     */
    @Test
    public void testCheckinfo() {
        System.out.println("Test checkinfo");
        sinhvien = new SINHVIEN("12520169","Nguyễn Thị Hương",new java.sql.Date(20,4,1994));
        boolean expResult = true;
        instance.tsvtf.setText(sinhvien.getTENSV());
        instance.msvtf.setText(sinhvien.getMSSV());
        instance.nstf.setText(sinhvien.getNgaySinh().toString());
        boolean result = instance.checkinfo();
        assertEquals("1-Error in Checkinfo",expResult, result);
    }

    /**
     * Test of loaddata method, of class QLTHONGTIN.
     */
    @Test
    public void testLoaddata() {
    System.out.println("Test loaddata");
    instance.loadtable();
    String msv = (String.valueOf (instance.tbsinhvien.getValueAt(2, 0)));
    String ten = (String.valueOf(instance.tbsinhvien.getValueAt(2, 1)));
    String ngaysinh = (String.valueOf(instance.tbsinhvien.getValueAt(2, 2)));

    instance.msvtf.setText(msv);
    instance.tsvtf.setText(ten);
    instance.nstf.setText(ngaysinh);
    assertEquals("1-Error in Checkinfo",msv, instance.msvtf.getText());
    assertEquals("2-Error in Checkinfo",ten, instance.tsvtf.getText());
    assertEquals("3-Error in Checkinfo",ngaysinh, instance.nstf.getText());
    }

    /**
     * Test of TK method, of class QLTHONGTIN.
     */
    @Test
    public void testTK() {
        System.out.println("TK");
        instance.loadtable();
        //Kiểm tra sinh viên có tồn tại trong bảng
        timkiem.masv = "430";
        timkiem.tensv = "Thương";
        instance.TK(timkiem);
        assertTrue("1-Erroe in TK()",instance.tbsinhvien.getRowCount()>=1);
        //Kiểm tra sinh viên không tồn tại trong bảng
        instance.loadtable();
        timkiem.masv = "123";
        timkiem.tensv = "";
        instance.TK(timkiem);
        assertFalse("2-Error in TK()",instance.tbsinhvien.getRowCount()>=1);
          timkiem.masv = "";
        timkiem.tensv = "Phương";
        instance.TK(timkiem);
        assertFalse("3-Error in TK()",instance.tbsinhvien.getRowCount()>=1);
       
    }

    /**
     * Test of main method, of class QLTHONGTIN.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        QLTHONGTIN.main(args);
    }
    
}
