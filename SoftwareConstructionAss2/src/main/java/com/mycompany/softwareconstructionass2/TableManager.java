/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author GGPC
 */
public class TableManager {
    
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
    public void createTicketTable() throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE TICKET(TICKET_ID INT, USER_ID INT,  VENUE VARCHAR(50), SEAT VARCHAR(5))");
            System.out.println("Venue Table created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Venue Table already exists.");
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
            //while(RS.next()){
                RS.next();
                System.out.println("User ID: " + RS.getInt(1) + "User Name: " + RS.getString(2) + " User type: " + RS.getString(3));
                switch(RS.getString(3)){
                    case "ADULT":
                        Adult login = new Adult(RS.getInt(1), RS.getString(2));
                        break;
                    case "STUDENT":
                        Student login1 = new Student(RS.getInt(1), RS.getString(2));
                        break;
                    case "CHILD":
                        Child login2 = new Child(RS.getInt(1), RS.getString(2));
                        break;
                }
            //}
            System.out.println("User retrieved sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
        return null;
    }
    
    public void deleteUser(int userID) throws SQLException{
        try (Statement stmt = DBManage.conn.createStatement()) {
            ResultSet RS = stmt.executeQuery("DELETE FROM USERS WHERE USER_ID = " + userID);
            //while(RS.next()){
                RS.next();
                System.out.println("User ID: " + RS.getInt(1) + "User Name: " + RS.getString(2) + " User type: " + RS.getString(3));
            //}
            System.out.println("User retrieved sucessfully");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    public void deleteEverything() throws SQLException {
        try(Statement stmt = DBManage.conn.createStatement()){
            stmt.executeUpdate("DELETE FROM USERS");
            System.out.println("successfully deleted everyrthing");
        } catch (SQLException e) {
            throw e;
        }   
    }
    
    
    public static void main(String[] args) {
        TableManager test = new TableManager();
        Adult tester = new Adult(1, "testing");
        try{
            test.createUserTable();
            test.createTicketTable();
        } catch(SQLException e) {
            System.out.println("something went wrong in table creation thats not table does not exist" + e);
        }
        try{
            test.deleteEverything();
        } catch(SQLException e) {
            System.out.println("failed to delete everything " + e);
        }
        try{
            test.addUser(tester);
        } catch (SQLException e) {
            System.out.println("something went wrong with adding the user");
        }
        
        try{
            test.getUser("testing");
        } catch (SQLException e) {
            System.out.println("something went wrong with getting the user");
        }
            
    }
}
