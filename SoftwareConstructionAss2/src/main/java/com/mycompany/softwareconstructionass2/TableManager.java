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
public class TableManager {
    
    DataBaseManager DBManage = new DataBaseManager();
    
    public void createUserTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE USERS(USER_ID INT, NAME VARCHAR(50), DISCOUNT VARCHAR(50))");
            System.out.println("User Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("User Table already exists.");
            } else {
                throw e;
            }
        }   
    }
    
    public void createTicketTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE TICKET(TICKET_ID INT, USER_ID INT,  VENUE VARCHAR(50), SEAT VARCHAR(5))");
            System.out.println("Venue Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Venue Table already exists.");
            } else {
                throw e;
            }
        }   
    }
    
    public void addUser()
    
    
    public static void main(String[] args) {
        TableManager test = new TableManager();
        try{
            test.CreateUserTable();
            test.
        } catch(SQLException e) {
            System.out.println("something went wrong in table creation thats not table does not exist" + e);
        }
            
    }
}
