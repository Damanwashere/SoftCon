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
            stmt.executeUpdate("CREATE TABLE GWYN(SEATROWS int, C1 BOOLEAN,  C2 BOOLEAN, C3 BOOLEAN)");
//            stmt.executeUpdate("CREATE TABLE VERSO(ROWS, COL1 BOOLEAN,  COL2 BOOLEAN, COL3 BOOLEAN, COL4 BOOLEAN, COL5 BOOLEAN))");
//            stmt.executeUpdate("CREATE TABLE LUCY(ROWS, COL1 BOOLEAN,  COL2 BOOLEAN, COL3 BOOLEAN, COL4 BOOLEAN, COL5 BOOLEAN, COL6 BOOLEAN, COL7 BOOLEAN))");
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
    
    public boolean[][] getVenue(String venue) throws SQLException{
        List<boolean[]> rows = new ArrayList<>();
        ResultSet RS;
        try (Statement stmt = DBManage.conn.createStatement()) {
            
            if("GWYN".equals(venue)){
                RS = stmt.executeQuery("SELECT * FROM GWYN");
                while(RS.next()){
                    boolean[] row = new boolean[3];
                    row[0] = RS.getBoolean(1);
                    row[1] = RS.getBoolean(2);
                    row[2] = RS.getBoolean(3);
                    rows.add(row);
                }
                boolean[][] array = rows.toArray(new boolean[rows.size()][]);
            } else if("VERSO".equals(venue)){
                RS = stmt.executeQuery("SELECT * FROM VERSO");
                while(RS.next()){
                    boolean[] row = new boolean[3];
                    row[0] = RS.getBoolean(1);
                    row[1] = RS.getBoolean(2);
                    row[2] = RS.getBoolean(3);
                    row[3] = RS.getBoolean(4);
                    row[4] = RS.getBoolean(5);
                    rows.add(row);
                }
                boolean[][] array = rows.toArray(new boolean[rows.size()][]);
            } else if("LUCY".equals(venue)){
                RS = stmt.executeQuery("SELECT * FROM LUCY");
                while(RS.next()){
                    boolean[] row = new boolean[3];
                    row[0] = RS.getBoolean(1);
                    row[1] = RS.getBoolean(2);
                    row[2] = RS.getBoolean(3);
                    row[3] = RS.getBoolean(4);
                    row[4] = RS.getBoolean(5);
                    row[5] = RS.getBoolean(6);
                    row[6] = RS.getBoolean(7);
                    rows.add(row);
                }
                boolean[][] array = rows.toArray(new boolean[rows.size()][]);
            }
            
            System.out.println("Venue Table populated.");
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }
    
    public void bookSeat(String venue) throws SQLException{
        
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
