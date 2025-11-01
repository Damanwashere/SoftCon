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
            stmt.executeUpdate("CREATE TABLE VERSO(SEATROWS VARCHAR(5), C1 BOOLEAN,  C2 BOOLEAN, C3 BOOLEAN, C4 BOOLEAN, C5 BOOLEAN)");
            stmt.executeUpdate("CREATE TABLE LUCY(SEATROWS VARCHAR(5), C1 BOOLEAN,  C2 BOOLEAN, C3 BOOLEAN, C4 BOOLEAN, C5 BOOLEAN, C6 BOOLEAN, C7 BOOLEAN)");
            System.out.println("Venue Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Venue Table already exists.");
            } else {
                throw e;
            }
        }
    }
    
    
    
    public void populateTable() throws SQLException{
        ResultSet RS;
        boolean gwyn = false;
        boolean verso = false;
        boolean lucy = false;
        try (Statement stmt = DBManage.conn.createStatement()) {
                RS = stmt.executeQuery("SELECT SEATROWS FROM GWYN where C1 = false AND true");
                if(RS.next()){
                    System.out.println("GWYN already populated");
                    gwyn = true;
                }
                if(gwyn == false){
                    for(int i = 1; i <= 3; i++){
                        stmt.executeUpdate("INSERT INTO GWYN VALUES ('R" + i + "', false, false, false)");
                        System.out.println("inserted row: " + i);
                    }
                }
                RS = stmt.executeQuery("SELECT SEATROWS FROM VERSO where C1 = false AND true");
                if(RS.next()){
                    System.out.println("VERSO already populated");
                    verso = true;
                }
                if(verso == false){
                    for(int i = 1; i <= 5; i++){
                        stmt.executeUpdate("INSERT INTO VERSO VALUES ('R" + i + "', false, false, false, false, false)");
                        System.out.println("inserted row: " + i);
                    }
                }
                RS = stmt.executeQuery("SELECT SEATROWS FROM LUCY where C1 = false AND true");
                if(RS.next()){
                    System.out.println("LUCY already populated");
                    lucy = true;
                }
                    if(lucy == false){
                    for(int i = 1; i <= 5; i++){
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
                    boolean[] row = new boolean[5];
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
                    boolean[] row = new boolean[7];
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
    
    //updating for ticketPanel
    public void bookSeat(int userID, String venue, String col, String row) throws SQLException
    {        
        String seatLocation = col + row;
        java.sql.Connection conn = DBManage.conn;
        conn.setAutoCommit(false);
        try
        {
            String update = "UPDATE " + venue + " SET " + col + " = true WHERE SEATROWS =?";
            String insert = "INSERT INTO TICKET (USER_ID, VENUE, SEAT) VALUES (?, ?, ?)";
            
            try(java.sql.PreparedStatement upPstmt = conn.prepareStatement(update))
            {
                upPstmt.setString(1, row);
                upPstmt.executeUpdate();
                
                System.out.println(venue + " updated");
            }
            
            try(java.sql.PreparedStatement inPstmt = conn.prepareStatement(insert))
            {
                inPstmt.setInt(1, userID);
                inPstmt.setString(2, venue);
                inPstmt.setString(3, seatLocation);
                inPstmt.executeUpdate();
                
                System.out.println("Ticket inserted");
                conn.commit();
            }            
        }
        catch(SQLException e)
        {
            throw e;
        }
    }
}
