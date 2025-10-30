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
public class VenueManager {
    DataBaseManager DBManage = new DataBaseManager();
    
    public void createVenueTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE GWYN(SEATROWS int, C1 BOOLEAN,  C2 BOOLEAN, C3 BOOLEAN)");
//            stmt.executeUpdate("CREATE TABLE VERSO(ROWS, COL1,  COL2, COL3, COL4, COL5))");
//            stmt.executeUpdate("CREATE TABLE LUCY(ROWS, COL1,  COL2, COL3, COL4, COL5, COL6, COL7))");
            System.out.println("Venue Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Venue Table already exists.");
            } else {
                throw e;
            }
        }
    }
    
    
    
    public void populateTable(String venue) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            for(int i = 1; i <= 3; i++){
                stmt.executeUpdate("INSERT INTO GWYN VALUES (" + i + ", false, false, false)");
                System.out.println("inserted row: " + i);
            }
            System.out.println("Venue Table populated.");
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void getVenue(String venue) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            for(int i = 1; i <= 3; i++){
                stmt.executeUpdate("SELECT * FROM GWYN");
                System.out.println("inserted row: " + i);
            }
            System.out.println("Venue Table populated.");
        } catch (SQLException e) {
            throw e;
        }
    }
    
    
    public static void main(String[] args) {
        VenueManager vm = new VenueManager();
        try{
            vm.createVenueTable();
        } catch(SQLException e) {
            System.out.println("venue table failed to create" + e);
        }
        
        try{
            vm.populateTable("GWYN");
        } catch(SQLException e) {
            System.out.println("failed to add row" + e);
        }
    }
}
