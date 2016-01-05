/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cao
 */
public class Connectsql {
    String URL = null;
    String user = null;
    String pass = null;
    public Connection connectsql = null;
    public Connectsql()
    {
        URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=QLSINHVIEN";
        user = "sa";
        pass = "sa";
        try{
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      
            this.connectsql = DriverManager.getConnection(URL,user,pass);  
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public boolean kiemtraConnect()
    {
        if(this.connectsql == null)
            return false;
        return true;
    }
    public int executeupdate(String sql) throws Exception
    {
        Statement query = this.connectsql.createStatement();
        int kq = query.executeUpdate(sql);
        return kq;
        
    }
    public ResultSet execute(String sql)
    {
        ResultSet rs = null;
        try{
            Statement query = this.connectsql.createStatement();
            rs = query.executeQuery(sql);
            
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return rs;
    }
    public void close() throws SQLException{
        if(this.connectsql!=null) this.connectsql.close();
    }
    
}
