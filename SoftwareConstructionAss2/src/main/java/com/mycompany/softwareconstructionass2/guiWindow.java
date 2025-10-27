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
    final static String CHOICE_PANEL = "user selection";
    final static String PURCHASE_PANEL = "purchase tickets";
    final static String TICKET_PANEL = "view purchased tickets";
    
    
    private static final Dimension PanelSize = new Dimension(1000, 500);
    
    public guiWindow(String name)
    {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              
        
        InitialPanel initialPanel = new InitialPanel(this);
        initialPanel.setPreferredSize(PanelSize);
        mainCard.add(initialPanel, INITIAL_PANEL);
        
        LoginPanel loginPanel = new LoginPanel(this);      
        loginPanel.setPreferredSize(PanelSize);        
        mainCard.add(loginPanel, LOGIN_PANEL);
        
        NewUserPanel newUserPanel = new NewUserPanel();
        newUserPanel.setPreferredSize(PanelSize);        
        mainCard.add(newUserPanel, NEW_USER_PANEL);        
        
        ChoicePanel choicePanel = new ChoicePanel(this);
        choicePanel.setPreferredSize(PanelSize);
        mainCard.add(choicePanel, CHOICE_PANEL);
        
        PurchasePanel purchasePanel = new PurchasePanel(this);
        purchasePanel.setPreferredSize(PanelSize);
        mainCard.add(purchasePanel, PURCHASE_PANEL);
        
        TicketPanel ticketPanel = new TicketPanel(this);
        ticketPanel.setPreferredSize(PanelSize);
        mainCard.add(ticketPanel, TICKET_PANEL);
        
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
