import com.mycompany.softwareconstructionass2.Adult;
import com.mycompany.softwareconstructionass2.DataBaseManager;
import com.mycompany.softwareconstructionass2.UserData;
import com.mycompany.softwareconstructionass2.UserTableManager;
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
public class UserTableManagerTest {
    UserTableManager um;
    DataBaseManager db;
    
    @BeforeEach
    void setup() throws SQLException{
        um = new UserTableManager();
        db = new DataBaseManager();
        
        
        um.createUserTable();
    }
    
    @Test
    void testAddUser() throws SQLException{
        boolean added = false;
        String name = "TESTER";
        int ID = 1;
        Adult a = new Adult(ID, name);
        um.addUser(a);
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet RS = stmt.executeQuery("SELECT NAME FROM USERS WHERE NAME = 'TESTER'");
        if(RS.next()){
            if(RS.getString(1).equals(name)){
                added = true;
            }
        }
        
        Assertions.assertTrue(added, "successfully added the user");
        
        RS.close();
        stmt.close();
        
    }
    
    @Test
    void testGetUser() throws SQLException{
        boolean booked = false;
        String name = "TESTER";
        int ID = 1;
        Adult a = new Adult(ID, name);
        um.addUser(a);
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        UserData ud = um.getUser(name);
        
        Assertions.assertNotNull(ud, "successfully got the user with get user function");
        
        stmt.close();
        
    }
    
    @AfterEach
    void cleanup() throws SQLException {
        
        Connection conn = db.getConnection();
        Statement stmt = conn.createStatement();
        um.deleteEverythingUser();
        db.closeConnection();
    }
}
