/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author GGPC
 */
public class TicketTableManager {
    private List<Ticket> tickets = new ArrayList<>();
    DataBaseManager DBManage = new DataBaseManager();
    
    //when using this function you will need a try catch case for the sql exception that prints the error
    public void createTicketTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE TICKET("
            + "TICKET_ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
            + "USER_ID INT, "
            + "VENUE VARCHAR(50), "
            + "Seat VARCHAR(10)"
            +")");
            System.out.println("Venue Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Venue Table already exists.");
            } else {
                throw e;
            }
        }   
    }
    
    //using a ticket object add a ticket to the database
    public void addTicket(Ticket ticket) throws SQLException{
        String sql = "INSERT INTO TICKET (USER_ID, VENUE, SEAT) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = DBManage.conn.prepareStatement(sql);) {
            pstmt.setInt(1, ticket.getUserID());
            pstmt.setString(2, ticket.getVenue());
            pstmt.setString(3, ticket.getSeat());
            pstmt.executeUpdate();
            System.out.println("Ticket added sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    //updating for ticket panel
    public List<Ticket> getTickets(int userID) throws SQLException
    {
        this.tickets.clear();
        
        String stmt = "SELECT TICKET_ID, USER_ID, VENUE, SEAT FROM TICKET WHERE USER_ID = ?";
        
        try(PreparedStatement pstmt = DBManage.conn.prepareStatement(stmt))
        {
            pstmt.setInt(1, userID);
            
            try(ResultSet rs = pstmt.executeQuery())
            {
                while(rs.next())
                {
                    Ticket current = new Ticket
                    (
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4)
                    );
                    this.tickets.add(current);
                }
            }
            System.out.println("Ticketlist aquired");
        }
        catch(SQLException e)
        {
            throw e;
        }
        return this.tickets;
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
