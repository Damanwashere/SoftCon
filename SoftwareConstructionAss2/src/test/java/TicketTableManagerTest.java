
import com.mycompany.softwareconstructionass2.DataBaseManager;
import com.mycompany.softwareconstructionass2.Ticket;
import com.mycompany.softwareconstructionass2.TicketTableManager;
import com.mycompany.softwareconstructionass2.VenueManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author GGPC
 */
public class TicketTableManagerTest {
    DataBaseManager db;
    TicketTableManager tm;
    @BeforeEach
    void setup() throws SQLException{
        db = new DataBaseManager();
        tm = new TicketTableManager();
        
        tm.createTicketTable();
    }
    
    @Test
    void testAddTicket() throws SQLException{
        String booked = null;
        int ticketID = 1;
        int userID = 1000;
        String venue = "GWYN";
        String Seat = "C3R3";
        Ticket ticket = new Ticket(ticketID, userID, venue, Seat);
        tm.addTicket(ticket);
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet RS = stmt.executeQuery("SELECT VENUE FROM TICKET WHERE USER_ID = 1000");
        if(RS.next()){
            booked = RS.getString(1);
        }
        Assertions.assertEquals("GWYN", booked);
        
        RS.close();
        stmt.close();
        
    }
    
    @AfterEach
    void cleanup() throws SQLException {
        
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        tm.deleteEverythingTicket();
        db.closeConnection();
    }
}
