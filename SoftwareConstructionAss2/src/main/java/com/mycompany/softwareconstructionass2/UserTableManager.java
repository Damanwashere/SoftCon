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
public class UserTableManager {
    DataBaseManager DBManage = new DataBaseManager();
    
    //when using this function you will need a try catch case for the sql exception that prints the error
    public void createUserTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
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
    public void addUser(UserData user) throws SQLException{
        String discount = user.getClass().getSimpleName();
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO USERS VALUES (" + user.getUserID() + ", '" + user.getName() + "', '" + discount + "')");
            System.out.println("User added sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
    }

    public UserData getUser(String userName) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            ResultSet RS = stmt.executeQuery("SELECT USER_ID, NAME, DISCOUNT FROM USERS where NAME = '" + userName + "'");
                
            RS.next();
            System.out.println("User ID: " + RS.getInt(1) + " User Name: " + RS.getString(2) + " User type: " + RS.getString(3));
            switch(RS.getString(3)){
                case "ADULT" -> {
                    Adult login = new Adult(RS.getInt(1), RS.getString(2));
                    return login;
                }
                case "STUDENT" -> {
                    Student login1 = new Student(RS.getInt(1), RS.getString(2));
                    return login1;
                }
                case "CHILD" -> {
                    Child login2 = new Child(RS.getInt(1), RS.getString(2));
                    return login2;
                }
                }
            System.out.println("User retrieved sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
        return null;
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
    
    
    
    
//    public static void main(String[] args) {
//        UserTableManager test = new UserTableManager();
//        Adult tester = new Adult(1, "testing");
//        Ticket test1 = new Ticket("11", 1, "here", "also here");
//        Ticket test2 = new Ticket("12", 1, "here", "also here");
//        Ticket test3 = new Ticket("13", 11, "here", "also here");
//        Ticket test4 = new Ticket("14", 1, "here", "also here");
//        Ticket test5 = new Ticket("15", 4, "here", "also here");
//        try{
//            test.createUserTable();
//            test.createTicketTable();
//        } catch(SQLException e) {
//            System.out.println("something went wrong in table creation thats not table does not exist" + e);
//        }
//        try{
//            test.deleteEverythingUser();
//            test.deleteEverythingTicket();
//        } catch(SQLException e) {
//            System.out.println("failed to delete everything " + e);
//        }
//        try{
//            test.addUser(tester);
//        } catch (SQLException e) {
//            System.out.println("something went wrong with adding the user");
//        }
//        
//        try{
//            test.addTicket(test1);
//            test.addTicket(test2);
//            test.addTicket(test3);
//            test.addTicket(test4);
//            test.addTicket(test5);
//        } catch (SQLException e) {
//            System.out.println("something went wrong with adding the tickets");
//        }
//        
//        try{
//            test.getUser("testing");
//        } catch (SQLException e) {
//            System.out.println("User does not exist");
//        }
//        
//        try{
//            test.getTickets(1);
//        } catch (SQLException e) {
//            System.out.println("failed to get ticket");
//        }
//            
//    }
}
