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
    
    final static String INITIAL_PANEL = "start panel";
    final static String LOGIN_PANEL = "user login";
    final static String NEW_USER_PANEL = "new user stuff";
    
    public guiWindow(String name)
    {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel loginWrapper = new JPanel(new BorderLayout());
        loginWrapper.setPreferredSize(new Dimension(1000, 500));        
        
        InitialPanel initialPanel = new InitialPanel(this);
        mainCard.add(initialPanel, INITIAL_PANEL);
        
        LoginPanel contentPanel = new LoginPanel();
        loginWrapper.add(contentPanel, BorderLayout.CENTER);        
        loginWrapper.add(new InitialPanel(this), BorderLayout.SOUTH);        
        mainCard.add(loginWrapper, LOGIN_PANEL);
        
        NewUserPanel newUserPanel = new NewUserPanel();
        newUserPanel.setPreferredSize(new Dimension(1000, 500));        
        mainCard.add(newUserPanel, NEW_USER_PANEL);
        
        this.add(mainCard, BorderLayout.CENTER);        
        this.pack();
        //locking gui size so end user doesnt drag it and maybe our code doesnt work lmao
        this.setResizable(false);        
        
        panelLayout.show(mainCard, INITIAL_PANEL);
    }
    
    public void showPanel(String panelName)
    {
        panelLayout.show(mainCard, panelName);
    }
}
