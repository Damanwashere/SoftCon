/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import java.util.LinkedList;

/**
 *
 * @author memel
 */
public class Child extends UserData {//simply a user constructor that has a specific discount depending on the type
    public Child(int userID, String name){
        super(userID, name);
    }
    @Override
    public double getDiscount(double price){
        return price*0.5;
    }
}
