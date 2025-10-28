/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Prize
 */
public class VenueAPanel extends JPanel
{
    private guiWindow displayWindow;
    private final boolean[][] seats;
    
    VenueAPanel(guiWindow window, boolean[][] test)
    {
        this.displayWindow = window;
        this.seats = test;       
        
        int rows = seats.length;        
        //practicinmg ternary operator
        int cols = (rows > 0) ? seats[0].length : 0;
        
        this.setLayout(new BorderLayout(5, 5));
        //test variables for now
        
        JPanel seatPanel = new JPanel(new GridLayout(rows, cols, 10, 10));
        
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
            this.add(seatPanel, BorderLayout.CENTER);
        
            //copy and pasting my return from LoginPanel, will check if this can just be a function later, but idk
            JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton returnButton = new JButton("Logout");
            returnButton.setPreferredSize(new Dimension(150, 25));
            returnButton.addActionListener(e ->
            {
            displayWindow.showPanel(guiWindow.INITIAL_PANEL);
            });
            exitPanel.add(returnButton);
        
            this.add(exitPanel, BorderLayout.SOUTH);
        }
    }    
}
