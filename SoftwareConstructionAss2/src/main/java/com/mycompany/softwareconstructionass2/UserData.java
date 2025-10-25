/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;


/**
 *
 * @author memel
 */
public abstract class UserData { // this is an abstract class used as the base of all other person type classes

    private int userID;
    private String name;
    

    public UserData(int userID, String name) {
        this.userID = userID;
        this.name = name;
        
    }

    int getUserID() {
        return userID;
    }
    
    String getName() {
        return name;
    }    

    //this is the abstract function which is edited by other versions of user
    public abstract double getDiscount(double price);

    // this is for checking if two objects have the same type 
    @Override
    public boolean equals(Object obj) {
        //is it an object at all
        if (this == obj) {
            return true;
        }
        //is it specifically an object of type userdata
        if (!(obj instanceof UserData)) {
            return false;
        }
        // if yes then return 
        UserData test = (UserData) obj;
        return this.name.equals(test.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.getUserID() + " " + this.getName();
    }
}
