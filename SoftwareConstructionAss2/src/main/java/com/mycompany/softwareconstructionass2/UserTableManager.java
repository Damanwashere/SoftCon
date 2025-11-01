/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author GGPC
 */
public class UserTableManager {
    private final DataBaseManager DBManage;
    //QM: made changes
    public UserTableManager()
    {
        this.DBManage = new DataBaseManager();
    }    
    //QM: adding getter to grab it for my panels
    public DataBaseManager getdbManager()
    {
        return DBManage;
    }
    
    //when using this function you will need a try catch case for the sql exception that prints the error
    public void createUserTable() throws SQLException
    {
        //using Connection impoirt as I was getting null in all my panel tests
        Connection conn = this.DBManage.conn;
        
        try (Statement stmt = conn.createStatement()) {
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
    
    //when using this function you will need a try catch case for the sql exception that prints the error
    public boolean addUser(UserData user) throws SQLException
    {
        Connection conn = this.DBManage.conn;
        ResultSet RS;
        try (Statement stmt = conn.createStatement()) {
            RS = stmt.executeQuery("SELECT NAME FROM USERS where NAME = '" + user.getName() + "'");
            if(RS.next()){
                return false;
            }
            System.out.println("user check successful");
        } catch (SQLException e) {
            System.out.println("user check failed");
            System.out.println(e);
        }
        
        
        
        String discount = user.getClass().getSimpleName();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO USERS VALUES (" + user.getUserID() + ", '" + user.getName() + "', '" + discount + "')");
            System.out.println("User added sucessfully");
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }
        //failing all my panel login tests
//    public UserData getUser(String userName) throws SQLException
//    {
//        Connection conn = this.DBManage.conn;
//        
//        try (Statement stmt = conn.createStatement()) {
//            ResultSet RS = stmt.executeQuery("SELECT USER_ID, NAME, DISCOUNT FROM USERS where NAME = '" + userName + "'");
//                
//            RS.next();
//            System.out.println("User ID: " + RS.getInt(1) + " User Name: " + RS.getString(2) + " User type: " + RS.getString(3));
//            switch(RS.getString(3)){
//                case "ADULT" -> {
//                    Adult login = new Adult(RS.getInt(1), RS.getString(2));
//                    return login;
//                }
//                case "STUDENT" -> {
//                    Student login1 = new Student(RS.getInt(1), RS.getString(2));
//                    return login1;
//                }
//                case "CHILD" -> {
//                    Child login2 = new Child(RS.getInt(1), RS.getString(2));
//                    return login2;
//                }
//                }
//            System.out.println("User retrieved sucessfully");
//        } catch (SQLException e) {
//            throw e;
//        }   
//        return null;
//    }
    
    public UserData getUser(String username) throws SQLException
    {
        String sql = "SELECT USER_ID, NAME, DISCOUNT FROM USERS WHERE UPPER(NAME) = UPPER(?)";
        Connection conn = this.DBManage.conn;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username.trim());
            
            try(ResultSet rs = stmt.executeQuery())
            {
                if(!rs.next())
                {
                    System.out.println("User does not exist in DB: " + username);
                    return null;
                }
                
                switch(rs.getString(3).toUpperCase())
                {
                    case "ADULT" -> 
                    {
                        return new Adult(rs.getInt(1), rs.getString(2));
                    }
                    case "STUDENT" ->
                    {
                        return new Student(rs.getInt(1), rs.getString(2));
                    }
                    case "CHILD" ->
                    {
                        return new Child(rs.getInt(1), rs.getString(2));
                    }
                    default -> {
                        System.err.println("user subclass not found in DB");
                        return null;
                    }
                }                
            }
            catch(SQLException e)
            {
                throw e;
            }
        }
    }
    
    public int newUserID() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            ResultSet RS = stmt.executeQuery("SELECT MAX(USER_ID) FROM USERS");
            System.out.println("User ID Found");
            RS.next();
            int j = RS.getInt(1) + 1;
            System.out.println(j);
            return j;
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    
    
    public void deleteUser(int userID) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            //deletes user and also deletes all their tickets from the other table, idk if you can clear the thing i can change it if you want
            stmt.executeQuery("DELETE FROM USERS WHERE USER_ID = " + userID);
            System.out.println("User deleted sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    public void deleteEverythingUser() throws SQLException {
        try(Statement stmt = DBManage.conn.createStatement()){
            stmt.executeUpdate("DELETE FROM USERS");
            System.out.println("successfully deleted everyrthing from user");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    
    
    
    public static void main(String[] args) {
        UserTableManager test = new UserTableManager();
        TicketTableManager testit = new TicketTableManager();
        Adult tester = new Adult(1, "testing");
        Adult tester2 = new Adult(33, "testing2");
        Ticket test1 = new Ticket(11, 1, "venue", "seat");
        Ticket test2 = new Ticket(12, 1, "venue 2", "seat");
        Ticket test3 = new Ticket(13, 11, "venue 3", "seat");
        Ticket test4 = new Ticket(14, 1, "venue 4", "seat");
        Ticket test5 = new Ticket(15, 4, "venue 5", "seat");
        try{
            test.createUserTable();
            testit.createTicketTable();
        } catch(SQLException e) {
            System.out.println("something went wrong in table creation thats not table does not exist" + e);
        }
        try{
            test.deleteEverythingUser();
            testit.deleteEverythingTicket();
        } catch(SQLException e) {
            System.out.println("failed to delete everything " + e);
        }
        
        try{
            test.newUserID();
        } catch (SQLException e) {
            System.out.println("failed to get new highest user id");
        }
        try{
            if(test.addUser(tester2)){
                System.out.println("user added successfully");
            } else {
                System.out.println("user already exists");
            }
            if(test.addUser(tester)){
                System.out.println("user added successfully");
            } else {
                System.out.println("user already exists");
            }
        } catch (SQLException e) {
            System.out.println("something went wrong with adding the user");
        }
        
        try{
            testit.addTicket(test1);
            testit.addTicket(test2);
            testit.addTicket(test3);
            testit.addTicket(test4);
            testit.addTicket(test5);
        } catch (SQLException e) {
            System.out.println("something went wrong with adding the tickets" + e);
        }
        
        try{
            test.getUser("testing3");
        } catch (SQLException e) {
            System.out.println("User does not exist");
        }
        
        try{
            testit.getTickets(1);
        } catch (SQLException e) {
            System.out.println("failed to get ticket");
        }
        
        
            
    }
}
