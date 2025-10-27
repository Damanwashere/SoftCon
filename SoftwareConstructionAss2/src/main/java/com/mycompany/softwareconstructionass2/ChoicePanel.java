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
public class ChoicePanel extends JPanel
{
    private guiWindow displayWindow;
    
    ChoicePanel(guiWindow window)
    {
        this.displayWindow = window;
        this.setLayout(new BorderLayout(20, 20));
        
        //will set this to output username in 
        JLabel messageLabel = new JLabel("Group 105 Ticket Service");
        messageLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        
        this.add(messageLabel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();        
        
        JButton purchaseButton = new JButton("Purchase new Ticket");        
        purchaseButton.setPreferredSize(new Dimension(200, 100));
        purchaseButton.addActionListener(e ->
        {
            displayWindow.showPanel(guiWindow.PURCHASE_PANEL);
        });
        cons.insets = new Insets(5, 5, 5, 25);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        panel.add(purchaseButton, cons);        
        
        JButton ticketButton = new JButton("View Purchased Tickets");
        ticketButton.setPreferredSize(new Dimension(200, 100));
        ticketButton.addActionListener(e ->
        {
            displayWindow.showPanel(guiWindow.TICKET_PANEL);
        });
        cons.insets = new Insets(5, 25, 5, 5);
        cons.gridx = 1;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        panel.add(ticketButton, cons);
        
        this.add(panel, BorderLayout.CENTER);
        
        //copy and pasting my return from LoginPanel, will check if this can just be a function later, but idk
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton returnButton = new JButton("Return to Homepage");
        returnButton.setPreferredSize(new Dimension(150, 25));
        returnButton.addActionListener(e ->
        {
           displayWindow.showPanel(guiWindow.INITIAL_PANEL);
        });
        exitPanel.add(returnButton);
        
        this.add(exitPanel, BorderLayout.SOUTH);
    }
}
