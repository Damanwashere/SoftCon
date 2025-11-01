import com.mycompany.softwareconstructionass2.DataBaseManager;
import com.mycompany.softwareconstructionass2.VenueManager;
import java.sql.SQLException;
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
public class VenueManagerTest {
    DataBaseManager db;
    VenueManager vm;
    @BeforeEach
    void setup() throws SQLException{
        vm = new VenueManager();
        db = new DataBaseManager();
        
        db.closeConnection();
        
        db.establishTestConnection();
        
        vm.createVenueTable();
        
    }
    
    @Test
    void testPopulatingTable() throws SQLException{
        boolean created = false;
        vm.populateTable();
    }
    
    @Test
    void testBookingSeat(){
        String name = "GWYN";
        String col = "C2";
        String row = "R2";
        
    }
}
