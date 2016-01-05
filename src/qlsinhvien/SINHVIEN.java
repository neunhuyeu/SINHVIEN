/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;
import java.sql.Date;
/**
 *
 * @author cao
 */
public class SINHVIEN {
    private String MSSV;
    private String TENSV;
    private java.sql.Date NgaySinh;
    
    
    public SINHVIEN(String msv, String tensv, java.sql.Date ngaysinh)
    {
        this.MSSV = msv;
        this.TENSV = tensv;
        this.NgaySinh = ngaysinh;
    }

    /**
     * @return the MSSV
     */
    public String getMSSV() {
        return MSSV;
    }

    /**
     * @param MSSV the MSSV to set
     */
    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    /**
     * @return the TENSV
     */
    public String getTENSV() {
        return TENSV;
    }

    /**
     * @param TENSV the TENSV to set
     */
    public void setTENSV(String TENSV) {
        this.TENSV = TENSV;
    }

    /**
     * @return the NgaySinh
     */
    public Date getNgaySinh() {
        return NgaySinh;
    }

    /**
     * @param NgaySinh the NgaySinh to set
     */
    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
    
    
     public static void main(String[] args) {
        // TODO code application logic here
         new QLTHONGTIN().setVisible(true);
    }
}
