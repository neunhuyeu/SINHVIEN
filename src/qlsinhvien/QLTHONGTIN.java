/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

import qlsinhvien.Timkiem;
import qlsinhvien.SINHVIEN;
import qlsinhvien.Connectsql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author cao
 */
public class QLTHONGTIN extends javax.swing.JFrame {
    
                Connectsql cnt;      
 public boolean checkinfo() {
    Check c = new Check();
    if (!c.checkID(msvtf.getText())) {
        JOptionPane.showMessageDialog(this, "Nhập mã số sinh viên sai", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
        msvtf.setText("");
        msvtf.requestFocus();
        return false;
    } else if (!c.checkName(tsvtf.getText())) {
        JOptionPane.showMessageDialog(this, "Nhập tên sai", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
        tsvtf.setText("");
        tsvtf.requestFocus();
        return false;

    } else if(c.checkDate(nstf.getText())== false){
            JOptionPane.showMessageDialog(null,"Ngày sinh không đúng định dạng. Ví dụ: 9/29/1987 ");
              nstf.setText("");
              nstf.requestFocus();
    }

    return true;
}
 
public void loaddata()
{
    int Row = tbsinhvien.getSelectedRow();
    String msv = (String.valueOf (tbsinhvien.getValueAt(Row, 0)));
    String ten = (String.valueOf(tbsinhvien.getValueAt(Row, 1)));
    String ngaysinh = (String.valueOf(tbsinhvien.getValueAt(Row, 2)));

    msvtf.setText(msv);
    tsvtf.setText(ten);
    nstf.setText(ngaysinh);
}
public ArrayList<String> CheckMS(String mssv)
{
   ArrayList list = null;

   try
   {
    ResultSet rl = cnt.execute("select *from SINHVIEN where MSSV="+mssv);
    list = new ArrayList<String>();
    while(rl.next())
    {
        list.add(rl.getString(1));
    }

   }catch(SQLException ex)
   {
       ex.printStackTrace();
   }
   return list;
}   
    public int Them(String mssv,String ten,Date ngay) throws Exception
    {
        cnt = new Connectsql();
        int kq =  cnt.executeupdate("insert into SINHVIEN values ('"+mssv+"','"+ten+"','"+ngay+"')");
        return kq;
    }
   public int Xoa(String mssv) throws Exception
    {
       cnt = new Connectsql();
        int kq =  cnt.executeupdate("delete from SINHVIEN where MSSV ="+mssv);
        return kq;
    }
    public int Sua(SINHVIEN sv) throws Exception
    {
        cnt = new Connectsql();
        PreparedStatement ps =null;
         ps = cnt.connectsql.prepareStatement("update SINHVIEN set TENSV = ?,NGAYSINH=? where MSSV = ?");
       
                ps.setString(3, sv.getMSSV());
                ps.setString(1, sv.getTENSV());
                ps.setDate(2,sv.getNgaySinh());
               
                int row = ps.executeUpdate();
                if (row < 1) {
                    sv = null;
                }
                return row;
    }
  public void loadtable() {
    Vector vtColum = new Vector();
    Vector vtRow = new Vector();
    Vector vtData = new Vector();
        
    vtColum.add("Mã sinh viên");
    vtColum.add("Tên sinh viên");
    vtColum.add("Ngày sinh");
    Format fm = new SimpleDateFormat("MM/dd/yyyy");
        try{
            
         
        ResultSet rl = cnt.execute("select *from SINHVIEN");
        while(rl.next())
        {
            vtRow = new Vector();
            vtRow.add(rl.getString(1));
            vtRow.add(rl.getString(2));
            String date = fm.format(rl.getDate(3)).toString();
            vtRow.add(date);
            vtData.add(vtRow);
            
        }
         
        tbsinhvien.setModel(new DefaultTableModel(vtData,vtColum));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
}
  
  public void setTable(String msv, String tensv)
  {
  if(msv != ""&& tensv == "")
      {
           DefaultTableModel model = (DefaultTableModel)tbsinhvien.getModel();
           model.fireTableDataChanged();
           TableRowSorter sort = new TableRowSorter(model);
           tbsinhvien.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(msv));
      }
              
 else if(tensv != "" && msv == "" )
      {
          DefaultTableModel model = (DefaultTableModel)tbsinhvien.getModel();
           model.fireTableDataChanged();
           TableRowSorter sort = new TableRowSorter(model);
           tbsinhvien.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(tensv));
      }
 else if(msv != "" && tensv != "")
      {
          DefaultTableModel model = (DefaultTableModel)tbsinhvien.getModel();
           model.fireTableDataChanged();
           TableRowSorter sort = new TableRowSorter(model);
           tbsinhvien.setRowSorter(sort);
           sort.setRowFilter(RowFilter.regexFilter(msv));
           sort.setRowFilter(RowFilter.regexFilter(tensv));
      }
  else
 {
     tbsinhvien.setModel(null);
     JOptionPane.showMessageDialog(null,"Không tìm thấy sinh viên");
 }
     
  }
 
  public void TK( Timkiem tk)
  {

       setTable(tk.getMasv(),tk.getTensv());
  }
  
public QLTHONGTIN() {
        initComponents();
        cnt = new Connectsql();
        bthuy.setText("Thêm");
        bthuy.setEnabled(false);
        btluu.setEnabled(false);
        btsua.setEnabled(false);
        btxoa.setEnabled(false);
        btMo.setEnabled(true);
        btHuy.setEnabled(true);
        bttimkiem.setEnabled(false);
        btthoat.setEnabled(true);
        msvtf.setEnabled(false);
        tsvtf.setEnabled(false);
        nstf.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlogTK = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbmsv = new javax.swing.JCheckBox();
        cbtsv = new javax.swing.JCheckBox();
        dlmsv = new javax.swing.JTextField();
        dltsv = new javax.swing.JTextField();
        cbcx = new javax.swing.JCheckBox();
        dltimkiem = new javax.swing.JButton();
        dlogthoat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        msvtf = new javax.swing.JTextField();
        tsvtf = new javax.swing.JTextField();
        nstf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        bthuy = new javax.swing.JButton();
        btluu = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsinhvien = new javax.swing.JTable();
        btMo = new javax.swing.JButton();
        btHuy = new javax.swing.JButton();
        btthoat = new javax.swing.JButton();
        bttimkiem = new javax.swing.JButton();

        dlogTK.setTitle("Tìm kiếm sinh viên");
        dlogTK.setAutoRequestFocus(false);

        jPanel3.setBackground(new java.awt.Color(0, 255, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nội dung tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Tìm kiếm theo:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("Mã:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel7.setText("Tên:");

        cbcx.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        cbcx.setText("Chính xác");

        dltimkiem.setText("Tìm kiếm");
        dltimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltimkiemActionPerformed(evt);
            }
        });

        dlogthoat.setText("Thoát");
        dlogthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlogthoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbmsv)
                                    .addComponent(cbtsv))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dlmsv, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                    .addComponent(dltsv)))
                            .addComponent(cbcx, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(dltimkiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(dlogthoat)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(cbmsv))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cbtsv)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(dlmsv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dltsv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(cbcx)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dlogthoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dltimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout dlogTKLayout = new javax.swing.GroupLayout(dlogTK.getContentPane());
        dlogTK.getContentPane().setLayout(dlogTKLayout);
        dlogTKLayout.setHorizontalGroup(
            dlogTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlogTKLayout.setVerticalGroup(
            dlogTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dlogTK.getAccessibleContext().setAccessibleParent(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý thông tin sinh viên");

        jPanel1.setBackground(new java.awt.Color(51, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thông tin sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Mã sinh viên:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Tên sinh viên: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Ngày sinh: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("(dd/mm/yy)");

        bthuy.setText("Hủy");
        bthuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthuyActionPerformed(evt);
            }
        });

        btluu.setText("Lưu");
        btluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluuActionPerformed(evt);
            }
        });

        btsua.setText("Sửa");
        btsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsuaActionPerformed(evt);
            }
        });

        btxoa.setText("Xóa");
        btxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msvtf)
                    .addComponent(nstf)
                    .addComponent(tsvtf))
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(bthuy, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btluu, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btsua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(msvtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addComponent(tsvtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nstf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bthuy, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btluu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 255, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Danh sách sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 14))); // NOI18N

        tbsinhvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Tên sinh viên", "Ngày sinh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbsinhvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbsinhvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbsinhvien);

        btMo.setText("Mở CSDL");
        btMo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMoActionPerformed(evt);
            }
        });

        btHuy.setText("Hủy");

        btthoat.setText("Thoát");
        btthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthoatActionPerformed(evt);
            }
        });

        bttimkiem.setText("Tìm kiếm");
        bttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btMo)
                .addGap(18, 18, 18)
                .addComponent(bttimkiem)
                .addGap(19, 19, 19)
                .addComponent(btHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btMo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthoatActionPerformed
        int select  = JOptionPane.showConfirmDialog(null,"Bạn có muốn thoát khỏi chương trình này không", "Warring", JOptionPane.YES_NO_OPTION);
        if(select == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btthoatActionPerformed

    private void btluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluuActionPerformed
 
        Check c = new Check();
        if (!checkinfo()) {
           return;
        }
        else if(msvtf.getText().trim().equals("") 
                || tsvtf.getText().trim().equals("") 
                || nstf.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Mã sinh viên, tên sinh viên,"
                    + " ngày sinh không được để trống!"
                    + " \n Vui lòng nhập đầy đủ thông tin.");
            bthuy.setEnabled(true);
            btluu.setEnabled(true);
            btsua.setEnabled(false);
            btxoa.setEnabled(false);
            btMo.setEnabled(false);
            btHuy.setEnabled(false);
            bttimkiem.setEnabled(false);
            btthoat.setEnabled(true);

         }
else{
        ArrayList<String> check = this.CheckMS(msvtf.getText());
        if(check.size() > 0)
        {
            JOptionPane.showConfirmDialog(null, "Mã sinh viên đã tồn tại!");
            msvtf.setText("");
            msvtf.requestFocus();
            bthuy.setEnabled(true);
            btluu.setEnabled(true);
            btsua.setEnabled(false);
            btxoa.setEnabled(false);
            btMo.setEnabled(false);
            btHuy.setEnabled(false);
            bttimkiem.setEnabled(false);
            btthoat.setEnabled(true);
   }
        else
        {

          try{
            DateFormat f = new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date date = f.parse(nstf.getText());
            java.sql.Date ns = new java.sql.Date( date.getTime());
            SINHVIEN sv = new SINHVIEN(msvtf.getText(),tsvtf.getText(),ns);
            int kq = Them(sv.getMSSV(),sv.getTENSV(),ns);
            bthuy.setText("Thêm");
            bthuy.setEnabled(true);
            btluu.setEnabled(true);
            btsua.setEnabled(true);
            btxoa.setEnabled(true);
            btMo.setEnabled(true);
            btHuy.setEnabled(true);
            bttimkiem.setEnabled(true);
            btthoat.setEnabled(true);
            msvtf.setEnabled(true);
            tsvtf.setEnabled(true);
            nstf.setEnabled(true);
          }catch(Exception ex)
          {
              ex.printStackTrace();
          }
            loadtable();
            JOptionPane.showMessageDialog(null,"Thêm sinh viên thành công");

        }
     }						
    }//GEN-LAST:event_btluuActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
         ArrayList<String> check = this.CheckMS(msvtf.getText());
         if(check.size() > 0)
         {
            try{
                DateFormat f = new SimpleDateFormat("dd/mm/yyyy");
                java.util.Date date = f.parse(nstf.getText());
                java.sql.Date ns = new java.sql.Date( date.getTime());
                SINHVIEN sv = new SINHVIEN(msvtf.getText(),tsvtf.getText(),ns);
                int select  = JOptionPane.showConfirmDialog(null,
                                "Bạn có muốn xóa sinh viên này không", 
                                "Warring", JOptionPane.YES_NO_OPTION);
                    if(select == JOptionPane.YES_OPTION)
                    {
                        int kq = Xoa(sv.getMSSV());
                        loadtable();  
                    }
          }catch(Exception ex)
            {
              ex.printStackTrace();
            }
          JOptionPane.showMessageDialog(null,"Xóa sinh viên thành công");
          msvtf.setText("");
          tsvtf.setText("");
          nstf.setText("");
     }
        
    }//GEN-LAST:event_btxoaActionPerformed

    private void tbsinhvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbsinhvienMouseClicked
        loaddata();
        bthuy.setText("Thêm");
        bthuy.setEnabled(true);
        btluu.setEnabled(false);
        btsua.setEnabled(true);
        btxoa.setEnabled(true);
        btMo.setEnabled(true);
        btHuy.setEnabled(true);
        bttimkiem.setEnabled(true);
        btthoat.setEnabled(true);
        msvtf.setEnabled(false);
        tsvtf.setEnabled(true);
        nstf.setEnabled(true);
    }//GEN-LAST:event_tbsinhvienMouseClicked

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed

        String mssv = msvtf.getText();
        String tensv = tsvtf.getText();
        String ngay = nstf.getText();
       
        ArrayList<String> check = this.CheckMS(msvtf.getText());
         if(check.size() > 0)
        {
          try{
               DateFormat f = new SimpleDateFormat("dd/mm/yyyy");
                java.util.Date date = f.parse(ngay);
                java.sql.Date ns = new java.sql.Date( date.getTime());
                SINHVIEN sv = new SINHVIEN(mssv,tensv,ns);
                Sua(sv);
                loadtable(); 
                JOptionPane.showMessageDialog(null,"Cập nhật sinh viên thành công");

            }catch(Exception ex)
             {
             ex.printStackTrace();
             }
        }                                   
    }//GEN-LAST:event_btsuaActionPerformed
    
    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimkiemActionPerformed

        dlogTK.setSize(400, 300);
        dlogTK.setLocation(500, 200);
        dlogTK.setVisible(true);

    }//GEN-LAST:event_bttimkiemActionPerformed

    private void btMoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMoActionPerformed
        cnt = new Connectsql();
        if(cnt.kiemtraConnect()== true)
            try{
        {
        JOptionPane.showMessageDialog(null,"Kết nối CSDL  thành công");
        loadtable();
        bthuy.setText("Thêm");
        bthuy.setEnabled(true);
        btluu.setEnabled(true);
        btsua.setEnabled(true);
        btxoa.setEnabled(true);
        btMo.setEnabled(true);
        btHuy.setEnabled(true);
        bttimkiem.setEnabled(true);
        btthoat.setEnabled(true);
        msvtf.setEnabled(false);
        tsvtf.setEnabled(false);
        nstf.setEnabled(false);
        }
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Kết nối CSDL thất bại");
            }
    }//GEN-LAST:event_btMoActionPerformed

    private void bthuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthuyActionPerformed

       if(bthuy.getText()=="Thêm"){  
        bthuy.setEnabled(true);
        btluu.setEnabled(true);
        btsua.setEnabled(false);
        btxoa.setEnabled(false);
        btMo.setEnabled(true);
        btHuy.setEnabled(true);
        bttimkiem.setEnabled(true);
        btthoat.setEnabled(true);
        msvtf.setEnabled(false);
        tsvtf.setEnabled(false);
        nstf.setEnabled(false);
       }
           msvtf.setEnabled(true);
           tsvtf.setEnabled(true);
           nstf.setEnabled(true);
           msvtf.setText("");
           tsvtf.setText("");
           nstf.setText("");  
            
    }//GEN-LAST:event_bthuyActionPerformed
    
    private void dlogthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlogthoatActionPerformed
         dlogTK.setVisible(false);
    }//GEN-LAST:event_dlogthoatActionPerformed

    private void dltimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dltimkiemActionPerformed
        
            DefaultTableModel model = (DefaultTableModel)tbsinhvien.getModel();
            model.fireTableDataChanged();
            TableRowSorter sort = new TableRowSorter(model);

        if(cbmsv.isSelected() == true)
        {
            sort.setRowFilter(RowFilter.regexFilter(dlmsv.getText()));
           
        }
        else 
        {
            if(cbtsv.isSelected() == true)
            {
                sort.setRowFilter(RowFilter.regexFilter(dltsv.getText()));

            }
           if ((cbmsv.isSelected() == true && cbtsv.isSelected() == true ) || cbcx.isSelected() ==true )
            {
                sort.setRowFilter(RowFilter.regexFilter(dlmsv.getText()));
                sort.setRowFilter(RowFilter.regexFilter(dltsv.getText()));
            }
        }
        tbsinhvien.setRowSorter(sort);
        this.getFocusListeners();
        dlogTK.setVisible(false);
        
    }//GEN-LAST:event_dltimkiemActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLTHONGTIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLTHONGTIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLTHONGTIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLTHONGTIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new QLTHONGTIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btHuy;
    public javax.swing.JButton btMo;
    public javax.swing.JButton bthuy;
    public javax.swing.JButton btluu;
    public javax.swing.JButton btsua;
    public javax.swing.JButton btthoat;
    public javax.swing.JButton bttimkiem;
    public javax.swing.JButton btxoa;
    public javax.swing.JCheckBox cbcx;
    public javax.swing.JCheckBox cbmsv;
    public javax.swing.JCheckBox cbtsv;
    public javax.swing.JTextField dlmsv;
    public javax.swing.JDialog dlogTK;
    public javax.swing.JButton dlogthoat;
    public javax.swing.JButton dltimkiem;
    public javax.swing.JTextField dltsv;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField msvtf;
    public javax.swing.JTextField nstf;
    public javax.swing.JTable tbsinhvien;
    public javax.swing.JTextField tsvtf;
    // End of variables declaration//GEN-END:variables
}
