/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author GGPC
 */
public class TableManage {
    
    TableManager DBManage = new TableManager();
    
    public void CreateUserTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE USERS(USER_ID INT, NAME VARCHAR(50), DISCOUNT VARCHAR(50))");
            System.out.println("Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Table already exists.");
            } else {
                throw e;
            }
        }   
    }
    
    
    public static void main(String[] args) {
        TableManage test = new TableManage();
        try{
            test.CreateUserTable();
        } catch(SQLException e) {
            System.out.println("something went wrong in table creation thats not table does not exist" + e);
        }
            
    }
}
