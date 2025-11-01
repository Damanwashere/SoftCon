/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Prize
 */
public class VenueAPanel extends JPanel
{
    private guiWindow displayWindow;
    private VenueManager venueManager;
    private String venue = "";
    private String venueTitle = "";
    JPanel seatPanel;
    private double price = 1.00;
    //initialise for null saftey
    private boolean[][] seats =
    {
        {true, false},
        {false, true}
    };
    
    //need a class to hold data for purchasing and booknig the seats according to our db logic
    private static class storeSeats
    {
        int seatRow;
        int seatCol;
        
        public storeSeats(int row1, int col1)
        {
            this.seatRow = row1;
            this.seatCol = col1;
        }
        
        public String getSeatName()
        {
            return "Seat" + (seatRow + 1) + "-" + (seatCol + 1);
        }
        public String getRowName()
        {
            return "R" + (seatRow + 1);
        }
        public String getColName()
        {
            return "C" + (seatCol + 1);
        }        
    }
    
    VenueAPanel(guiWindow window, VenueManager venue)
    {
        this.displayWindow = window;
        this.venueManager = venue;
        
        int rows = seats.length;        
        //practicinmg ternary operator
        int cols = (rows > 0) ? seats[0].length : 0;
        
        this.setLayout(new BorderLayout(5, 5));
        
        JLabel venueLabel = new JLabel(venueTitle, SwingConstants.CENTER);
        venueLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        venueLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        this.add(venueLabel, BorderLayout.NORTH);
        
        this.seatPanel = new JPanel(new GridLayout(rows, cols, 10, 10));        
        seatSetup();
        
        this.add(seatPanel, BorderLayout.CENTER);
        
        JPanel purchasePanel = new JPanel();
        purchasePanel.setLayout(new BoxLayout(purchasePanel, BoxLayout.Y_AXIS));
        JButton purchaseButton = new JButton("Purchase");
        purchaseButton.setPreferredSize(new Dimension(200, 50));
        purchaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        purchaseButton.addActionListener(e ->
        {
           purchaseSeat(); 
        });
        //copy and pasting my return from LoginPanel, will check if this can just be a function later, but idk
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));        
        JButton returnButton = new JButton("Return to home");
        returnButton.setPreferredSize(new Dimension(150, 25));
        returnButton.addActionListener(e ->
        {
        displayWindow.showPanel(guiWindow.CHOICE_PANEL);
        });
        exitPanel.add(returnButton);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(150, 25));
        logoutButton.addActionListener(e ->
        {
        displayWindow.logout();
        });
        exitPanel.add(logoutButton);
        
        purchasePanel.add(purchaseButton);
        purchasePanel.add(Box.createVerticalStrut(10));
        purchasePanel.add(exitPanel);
        
        this.add(purchasePanel, BorderLayout.SOUTH);        
    } 
    
    public void updatePanel(boolean[][] seat, double disc, double updateToPrice, String updatevenue)
    {
        this.seats = seat;
        this.price = updateToPrice * disc;
        this.venue = updatevenue;
        switch(updatevenue)
        {
            case "GWYN":
                this.venueTitle = "Gwyn's Arena";
                break;
            case "VERSO":
                this.venueTitle = "Verso's Concert Hall";
                break;
            case "LUCY":
                this.venueTitle = "Lucy's Theatre";
                break;
            default:
                break;
        }
        seatSetup();
        this.revalidate();
        this.repaint();
    }    
    
    //method uses storeSeat to hold til purchase or if cancel not add to db
    private void purchaseSeat()
    {
        List<storeSeats> holdSeats = new java.util.ArrayList<>();
        
        int rows = seats.length;
        int cols = (rows > 0) ? seats[0].length : 0;
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                int store = i * cols + j;
                java.awt.Component comp = seatPanel.getComponent(store);
                
                if(comp instanceof JCheckBox seatBox)
                {                    
                    if(seatBox.isSelected() && seatBox.isEnabled())
                    {
                        holdSeats.add(new storeSeats(i, j));
                    }
                }
            }
        }
        double showPrice = holdSeats.size() * price;
        //
        if(showPrice == 0)
        {
            JOptionPane.showMessageDialog(this.displayWindow, "No seats selected", "Please select a seat", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String seatBook = holdSeats.stream().map(s -> s.getSeatName()).collect(Collectors.joining(", "));
        String userMessage = String.format("Purchasing %d\nseat(s): %s\nTotal Price: $%.2f", holdSeats.size(), seatBook, showPrice);
        //using yes/no soi user cant cancel and mess up held seats for booking
        int checkout = JOptionPane.showConfirmDialog(this.displayWindow, userMessage,"Congirm Purchase", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(checkout == JOptionPane.YES_OPTION)
        {
            for(storeSeats seat : holdSeats)
            {
                try
                {
                    venueManager.bookSeat(venue, seat.getColName(), seat.getColName());
                    
                }
                catch(SQLException e)
                {
                    JOptionPane.showMessageDialog(this.displayWindow, "Error booking seatto database");
                    return;
                }
            }
            seatSetup();
            JOptionPane.showMessageDialog(this.displayWindow, "Tickets Purchased");
        }
        else
        {
            //setting cvheckboxes back to previous state
            for(storeSeats seat : holdSeats)
            {
                seats[seat.seatRow][seat.seatCol] = false;
            }
            seatSetup();
            JOptionPane.showMessageDialog(this.displayWindow, "Purchase cancelled");
        }
    }    
  
    //needed function to update outside of panel logic
    private void seatSetup()
    {
        this.seatPanel.removeAll();
        
        int rows = seats.length;        
        //practicinmg ternary operator
        int cols = (rows > 0) ? seats[0].length : 0;
        
        this.setLayout(new BorderLayout(5, 5));
        //test variables for now
        
        this.seatPanel.setLayout(new GridLayout(rows, cols, 10, 10));
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                JCheckBox seat = new JCheckBox("Seat" + (i + 1) + "-" + (j + 1));
                
                boolean booked = seats[i][j];                
                seat.setSelected(booked);
                if(booked)
                {
                    seat.setEnabled(false);
                }
                else
                {
                    final int row = i;
                    final int col = j;
                
                    seat.addActionListener(e ->
                    {
                        seats[row][col] = ((JCheckBox)e.getSource()).isSelected();
                    });                    
                }
                
                seatPanel.add(seat);
            }
        }
        this.seatPanel.revalidate();
        this.seatPanel.repaint();
    }
}
