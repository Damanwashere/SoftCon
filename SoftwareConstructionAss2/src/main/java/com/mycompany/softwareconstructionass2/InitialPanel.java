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
public class InitialPanel extends JPanel
{
    private guiWindow displayWindow;
    
    public InitialPanel(guiWindow display)
    {
        this.displayWindow = display;        
        this.setLayout(new BorderLayout(20, 20));
        
        JLabel g105 = new JLabel("Group 105 ticket booking service");
        g105.setFont(new Font("Monospaced", Font.BOLD, 28));
        g105.setHorizontalAlignment(SwingConstants.CENTER);
        //pushing away fromtop
        g105.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        this.add(g105, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));
        JButton newUserButton = new JButton("New User");
        JButton loginButton = new JButton("login");
        
        newUserButton.setPreferredSize(new Dimension(150, 100));
        loginButton.setPreferredSize(new Dimension(150, 100));
        
        newUserButton.addActionListener(e -> 
        {
            displayWindow.showPanel(guiWindow.NEW_USER_PANEL);            
        });
        
        loginButton.addActionListener(e ->
        {
            displayWindow.showPanel(guiWindow.LOGIN_PANEL);          
        });
        
        buttonPanel.add(newUserButton);
        buttonPanel.add(loginButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        
        this.add(buttonPanel, BorderLayout.SOUTH);        
    }
}
