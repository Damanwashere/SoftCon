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
    private JLabel userLabel = new JLabel();
    private JPanel userPanel;
    
    ChoicePanel(guiWindow window)
    {
        this.displayWindow = window;
        this.setLayout(new BorderLayout(20, 20));
        
        //will set this to output username in 
        this.userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        
        JLabel messageLabel = new JLabel("Welcome User: ");
        messageLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0));
        
        userLabel.setText(" test");
        userLabel.setFont(new Font("Monospaced", Font.PLAIN, 24));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
        
        userPanel.add(messageLabel);
        userPanel.add(userLabel);
        
        this.add(userPanel, BorderLayout.NORTH);
        
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
        JButton returnButton = new JButton("Logout");
        returnButton.setPreferredSize(new Dimension(150, 25));
        returnButton.addActionListener(e ->
        {
           displayWindow.logout();
        });
        exitPanel.add(returnButton);
        
        this.add(exitPanel, BorderLayout.SOUTH);
    }
    
    public void updateCurrentUser(UserData userd)
    {
        UserData user = userd;
        
        if(user != null)
        {
            this.userLabel.setText(user.getName());
        }
    }
}
