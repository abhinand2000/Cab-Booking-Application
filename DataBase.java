/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anuj Kharbanda
 */
import javax.swing.JOptionPane;
import java.sql.*;

public class DataBase {
    
    public int popup_message(String user) {
        Connection conn = null;
        int flag =0;
        try {
            // db parameters
            //String url = "jdbc:mysql:./data/customer.db";
            // create a connection to the database
            conn=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root");
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customerdata");
            while(rs.next()){
                String s = rs.getString("username");
                if(s.equals(user)==true){
                    JOptionPane.showMessageDialog(null, "UserID already exist. Please choose something different");
                    return 1;                    
                }
            }          
            //System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
           
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }
    
    public void insert_customer(String query) {
        Connection conn = null;
        try {
            // db parameters
            //String url = "jdbc:sqlite:./data/customer.db";
            // create a connection to the database
            conn=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root");
            Statement stmt = (Statement) conn.createStatement();
            
            stmt.executeUpdate(query);           
           // System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public int login(String id,String password) {
        Connection conn = null;
        int flag =0;
        try {
            // db parameters
            //String url = "jdbc:sqlite:./data/customer.db";
            // create a connection to the database
            conn=DriverManager.getConnection(  
                "jdbc:mysql://localhost:3306/CABAPPLICATION","root","root");
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customerdata");
            while(rs.next()){
                String a = rs.getString("username");
                String b = rs.getString("password");
                if(a.equals(id)==true && b.equals(password)==true){
//                    Details ab=new Details();
//                    ab.show_details(rs.getString("name"),rs.getString("userid"),rs.getString("email"),rs.getString("phone"),rs.getString("wallet"),rs.getString("password"));
                    return 1;                    
                }
            }           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
           
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }
    public static void main(String args[]){
    }
    
}
