/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import org.junit.jupiter.api.Test;

/**
 *
 * @author GGPC
 */
public class TableManager {
    private static final String user = "pdc"; //your DB username
    private static final String pass = "pdc"; //your DB password
    private static final String url = "jdbc:derby:SoftConDB; create=true"; //url of the DB host
    
    public TableManager(){
        establishConnection();
    }
    
    Connection conn;
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public void establishConnection() {
        try{
            conn=DriverManager.getConnection(url, user, pass);
            System.out.println( url +" connected");
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    
    
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void UserTable(){
        
    }
    
    public static void main(String[] args) {
        TableManager dbManager = new TableManager();
        System.out.println(dbManager.getConnection());
    }
    
//    @Test
//    public void testConnection() {
//        TableManager db = new TableManager();
//        assertNotNull(db.getConnection(), "Database connection should not be null");
//    }
}
