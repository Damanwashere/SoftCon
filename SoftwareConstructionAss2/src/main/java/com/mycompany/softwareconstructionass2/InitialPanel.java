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
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        
        int offset = 20;
        this.setBorder(BorderFactory.createEmptyBorder(5, offset, 5, offset));
        
        JButton newUserButton = new JButton("New User");
        JButton loginButton = new JButton("login");        
        
        newUserButton.addActionListener(e -> 
        {
            displayWindow.showPanel(guiWindow.NEW_USER_PANEL);            
        });
        
        loginButton.addActionListener(e ->
        {
            displayWindow.showPanel(guiWindow.LOGIN_PANEL);          
        });
        
        this.add(newUserButton);
        this.add(loginButton);        
    }
}
