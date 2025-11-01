/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GGPC
 */
public class VenueManager {
    DataBaseManager DBManage = new DataBaseManager();
    
    public void createVenueTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE GWYN(SEATROWS VARCHAR(5), C1 BOOLEAN,  C2 BOOLEAN, C3 BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE VERSO(SEATROWS VARCHAR(5), COL1 BOOLEAN,  COL2 BOOLEAN, COL3 BOOLEAN, COL4 BOOLEAN, COL5 BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE LUCY(SEATROWS VARCHAR(5), COL1 BOOLEAN,  COL2 BOOLEAN, COL3 BOOLEAN, COL4 BOOLEAN, COL5 BOOLEAN, COL6 BOOLEAN, COL7 BOOLEAN)");
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
        ResultSet RS;
        try (Statement stmt = DBManage.conn.createStatement()) {
            if("GWYN".equals(venue)){
                RS = stmt.executeQuery("SELECT SEATROWS FROM GWYN where C1 = false AND true");
                if(RS.next()){
                    System.out.println("GWYN already populated");
                    return;
                }
                for(int i = 1; i <= 3; i++){
                    stmt.executeUpdate("INSERT INTO GWYN VALUES ('R" + i + "', false, false, false)");
                    System.out.println("inserted row: " + i);
                }
            } else if("VERSO".equals(venue)) {
                RS = stmt.executeQuery("SELECT SEATROWS FROM VERSO where C1 = false AND true");
                if(RS.next()){
                    System.out.println("GWYN already populated");
                    return;
                }
                for(int i = 1; i <= 5; i++){
                    stmt.executeUpdate("INSERT INTO VERSO VALUES ('R" + i + "', false, false, false, false, false)");
                    System.out.println("inserted row: " + i);
                }
            } else if("LUCY".equals(venue)) {
                RS = stmt.executeQuery("SELECT SEATROWS FROM LUCY where C1 = false AND true");
                if(RS.next()){
                    System.out.println("GWYN already populated");
                    return;
                }
                for(int i = 1; i <= 7; i++){
                    stmt.executeUpdate("INSERT INTO LUCY VALUES ('R" + i + "', false, false, false, false, false, false, false)");
                    System.out.println("inserted row: " + i);
                }
            }
            System.out.println("Venue Table populated.");
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public boolean[][] getVenue(String venue) throws SQLException{
        List<boolean[]> rows = new ArrayList<>();
        boolean[][] array = {
            {true, true},
            {false, false}
        };
        ResultSet RS;
        try (Statement stmt = DBManage.conn.createStatement()) {
            
            if("GWYN".equals(venue)){
                RS = stmt.executeQuery("SELECT * FROM GWYN");
                while(RS.next()){
                    boolean[] row = new boolean[3];
                    row[0] = RS.getBoolean(2);
                    row[1] = RS.getBoolean(3);
                    row[2] = RS.getBoolean(4);
                    rows.add(row);
                }
                array = rows.toArray(new boolean[rows.size()][]);
            } else if("VERSO".equals(venue)){
                RS = stmt.executeQuery("SELECT * FROM VERSO");
                while(RS.next()){
                    boolean[] row = new boolean[3];
                    row[0] = RS.getBoolean(2);
                    row[1] = RS.getBoolean(3);
                    row[2] = RS.getBoolean(4);
                    row[3] = RS.getBoolean(5);
                    row[4] = RS.getBoolean(6);
                    rows.add(row);
                }
                array = rows.toArray(new boolean[rows.size()][]);
            } else if("LUCY".equals(venue)){
                RS = stmt.executeQuery("SELECT * FROM LUCY");
                while(RS.next()){
                    boolean[] row = new boolean[3];
                    row[0] = RS.getBoolean(2);
                    row[1] = RS.getBoolean(3);
                    row[2] = RS.getBoolean(4);
                    row[3] = RS.getBoolean(5);
                    row[4] = RS.getBoolean(6);
                    row[5] = RS.getBoolean(7);
                    row[6] = RS.getBoolean(8);
                    rows.add(row);
                }
                array = rows.toArray(new boolean[rows.size()][]);
            } 
            
            System.out.println("Venue Table populated.");
        } catch (SQLException e) {
            throw e;
        }
        return array;
    }
    
    public void deleteVenues() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE GWYN");
            stmt.executeUpdate("DROP TABLE VERSO");
            stmt.executeUpdate("DROP TABLE LUCY");
            System.out.println("all tables gone");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    public void bookSeat(String venue, String col, String row) throws SQLException{
        
        if("GWYN".equals(venue)){
                try (Statement stmt = DBManage.conn.createStatement()) {
                    stmt.executeUpdate("UPDATE GWYN SET " + col + " = true where SEATROWS = '" + row + "'");
                    System.out.println("seat booked at GWYN");
                } catch (SQLException e) {
                    throw e;
                }
            } else if("VERSO".equals(venue)){
                try (Statement stmt = DBManage.conn.createStatement()) {
                    stmt.executeUpdate("UPDATE VERSO SET " + col + " = true where SEATROWS = '" + row + "'");
                    System.out.println("seat booked at VERSO");
                } catch (SQLException e) {
                    throw e;
                }
            } else if("LUCY".equals(venue)){
                try (Statement stmt = DBManage.conn.createStatement()) {
                    stmt.executeUpdate("UPDATE LUCY SET " + col + " = true where SEATROWS = '" + row + "'");
                    System.out.println("seat booked at LUCY");
                } catch (SQLException e) {
                    throw e;
                }
            } 
    }
    
    
    public static void main(String[] args) {
        VenueManager vm = new VenueManager();
        try{
            vm.deleteVenues();
        } catch(SQLException e) {
            System.out.println("all tbales not gone" + e);
        }
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
        
        try{
            boolean[][] array = vm.getVenue("GWYN");
            System.out.println(array[0][0]);
            
        } catch(SQLException e) {
            System.out.println("failed to get table" + e);
        }
        
        try{
            vm.bookSeat("GWYN", "C1", "R1");
        } catch(SQLException e) {
            System.out.println("failed to bookSeat" + e);
        }
        try{
            boolean[][] array = vm.getVenue("GWYN");
            System.out.println(array[0][0]);
            
        } catch(SQLException e) {
            System.out.println("failed to get table" + e);
        }
    }
}
