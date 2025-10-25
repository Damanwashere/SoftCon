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
public class guiWindow extends JFrame
{
    private CardLayout panelLayout = new CardLayout();
    private JPanel mainCard = new JPanel(panelLayout);
    
    final static String LOGIN_PANEL = "login";
    final static String NEW_USER_PANEL = "new user stuff";
    
    public guiWindow(String name)
    {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 500); 
        
        JPanel loginWrapper = new JPanel(new BorderLayout());
        
        LoginPanel contentPanel = new LoginPanel();
        loginWrapper.add(contentPanel, BorderLayout.CENTER);        
        loginWrapper.add(new InitialPanel(this), BorderLayout.SOUTH);
                     
        mainCard.add(loginWrapper, LOGIN_PANEL);
        
        NewUserPanel newUserPanel = new NewUserPanel();
        mainCard.add(newUserPanel, NEW_USER_PANEL);
        
        this.add(mainCard, BorderLayout.CENTER);
        
        panelLayout.show(mainCard, LOGIN_PANEL);
    }
    
    public void showPanel(String panelName)
    {
        panelLayout.show(mainCard, panelName);
    }
}
