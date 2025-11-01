/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

/**
 *
 * @author memel
 */
public class Ticket {

    private int ticketID;
    private int userID;
    private String venue;
    private String seat;
    //ticket constructor with all the relavent information in it
    public Ticket(int ticketID, int userID, String venue, String seat) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.venue = venue;
        this.seat = seat;
    }

    /**
     * @return the id
     */
    public int getTicketID() {
        return ticketID;
    }
    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }
    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }
    
    public String getSeat() {
        return seat;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Ticket)){
            return false;
        }
        Ticket test = (Ticket) obj;
        return this.ticketID == test.ticketID;
    }
    @Override
    // makes toString display some useful information instead of just some gibberish about its memory location
    // also makes the write function work when a ticket object is called for it
    public String toString() {
        return this.getTicketID() + " " + this.getUserID() + " " + this.getVenue() + " " + this.getSeat();
    }

    
    
}