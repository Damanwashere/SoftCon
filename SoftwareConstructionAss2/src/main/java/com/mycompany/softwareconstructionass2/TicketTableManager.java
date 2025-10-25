/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author GGPC
 */
public class TicketTableManager {
    Set<Ticket> tickets = new HashSet();
    DataBaseManager DBManage = new DataBaseManager();
    
    //when using this function you will need a try catch case for the sql exception that prints the error
    public void createTicketTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE TICKET(TICKET_ID VARCHAR(10), USER_ID INT,  VENUE VARCHAR(50), SEAT VARCHAR(10))");
            System.out.println("Venue Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Venue Table already exists.");
            } else {
                throw e;
            }
        }   
    }
    
    //using a ticket object 
    public void addTicket(Ticket ticket) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO TICKET VALUES ('" + ticket.getTicketID() + "', " + ticket.getUserID() + ", '" + ticket.getSeat() + "', '" + ticket.getVenue() + "')");
            System.out.println("User added sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    public Ticket getTickets(int userID) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            ResultSet RS = stmt.executeQuery("SELECT TICKET_ID, USER_ID, VENUE, SEAT FROM TICKET where USER_ID = " + userID );
            while(RS.next()){
                Ticket current = new Ticket(RS.getString(1), RS.getInt(2), RS.getString(3), RS.getString(4));
                System.out.println(current);
                tickets.add(current);
            }
            System.out.println("Ticket/s retrieved sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
        return null;
    }
    
    public void deleteTicket(int userID) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeQuery("DELETE FROM USERS WHERE USER_ID = " + userID);
            System.out.println("Ticket deleted sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    public void deleteEverythingTicket() throws SQLException {
        try(Statement stmt = DBManage.conn.createStatement()){
            stmt.executeUpdate("DELETE FROM TICKET");
            System.out.println("successfully deleted everyrthing from ticket");
        } catch (SQLException e) {
            throw e;
        }   
    }
}
