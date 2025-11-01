/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 *
 * @author Prize
 */
public class PurchasePanel extends JPanel
{
    private guiWindow displayWindow;
    private VenueManager venueSelect;
    private boolean[][] testM = testMatrix.test_matrix;
    boolean[][] venueSeats;
    private String venueName = ""; 
    
    PurchasePanel(guiWindow window, VenueManager venue)
    {
        this.displayWindow = window;
        this.venueSelect = venue;
        this.setLayout(new BorderLayout(20, 20));
        
        //reused code from choice
        JLabel messageLabel = new JLabel("Group 105 Ticket Service");
        messageLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        
        this.add(messageLabel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints(); 
        
        //also reused from choice, just changing variables and positions
        JButton aVenueButton = new JButton("Gwyn's Arena");        
        aVenueButton.setPreferredSize(new Dimension(200, 100));
        aVenueButton.addActionListener(e ->
        {    
            venueName = "GWYN";
            double price = 20.00;
            try
            {
                venueSeats = venueSelect.getVenue(venueName);
            }
            catch(SQLException ex)
            {
                System.err.println("problem getting venue seats");
            }
            displayWindow.setVenueSeats(venueSeats);
            displayWindow.setVenue(venueName);
            displayWindow.setPrice(price);
            venueName = "";
            displayWindow.showPanel(guiWindow.VENUE_A_PANEL);
        });
        cons.insets = new Insets(5, 5, 5, 25);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        panel.add(aVenueButton, cons);        
        
        JButton bVenueButton = new JButton("Verso's Concert Hall");
        bVenueButton.setPreferredSize(new Dimension(200, 100));
        bVenueButton.addActionListener(e ->
        {    
            venueName = "VERSO";
            double price = 50.00;
            try
            {
                venueSeats = venueSelect.getVenue(venueName);
            }
            catch(SQLException ex)
            {
                System.err.println("problem getting venue seats");
            }
            displayWindow.setVenueSeats(venueSeats);
            displayWindow.setVenue(venueName);
            displayWindow.setPrice(price);
            venueName = "";
            displayWindow.showPanel(guiWindow.VENUE_A_PANEL);
        });
        cons.insets = new Insets(5, 25, 5, 25);
        cons.gridx = 1;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        panel.add(bVenueButton, cons);
        
        JButton cVenueButton = new JButton("Lucy's Theatre");
        cVenueButton.setPreferredSize(new Dimension(200, 100));
        cVenueButton.addActionListener(e ->
        {    
            venueName = "LUCY";
            double price = 100.00;
            try
            {
                venueSeats = venueSelect.getVenue(venueName);
            }
            catch(SQLException ex)
            {
                System.err.println("problem getting venue seats");
            }
            displayWindow.setVenueSeats(venueSeats);
            displayWindow.setVenue(venueName);
            displayWindow.setPrice(price);
            venueName = "";
            displayWindow.showPanel(guiWindow.VENUE_A_PANEL);
        });
        cons.insets = new Insets(5, 25, 5, 5);
        cons.gridx = 2;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        panel.add(cVenueButton, cons);
        
        this.add(panel, BorderLayout.CENTER);
        
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
        
        this.add(exitPanel, BorderLayout.SOUTH);                
    }
}
