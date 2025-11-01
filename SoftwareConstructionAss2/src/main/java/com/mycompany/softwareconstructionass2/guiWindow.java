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
public class guiWindow extends JFrame
{
    private CardLayout panelLayout = new CardLayout();
    private JPanel mainCard = new JPanel(panelLayout);
    private ChoicePanel choicePanel;
    private VenueAPanel aVenuePanel;
    private TicketPanel ticketPanel;
    
    final static String INITIAL_PANEL = "start panel";
    final static String LOGIN_PANEL = "user login";
    final static String NEW_USER_PANEL = "new user stuff";
    final static String CHOICE_PANEL = "CHOICE_PANEL";
    final static String PURCHASE_PANEL = "purchase tickets";
    final static String TICKET_PANEL = "view purchased tickets";
    //condensed all venue panels
    final static String VENUE_A_PANEL = "All venue panels";
    
    private UserData currentUser;
    private UserTableManager userManager;
    private VenueManager venueManager;
    private TicketTableManager ticketManager;
    
    private static final Dimension PanelSize = new Dimension(1000, 500);
    //need some cariables held for updating panels
    private boolean[][] testSeats;
    private double discount = 0.5;
    private double price = 1;
    private String venue = "GWYN";
    
    public guiWindow(String name)
    {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userManager = new UserTableManager();
        this.venueManager = new VenueManager();
        this.ticketManager = new TicketTableManager();
        //setup for firstime if no db
        try
        {
            this.userManager.createUserTable();
            this.venueManager.createVenueTable();
            this.venueManager.populateTable();
            this.ticketManager.createTicketTable();
            if(this.userManager.getdbManager().conn != null)
            {
                this.userManager.getdbManager().conn.commit();
            }
        }
        catch(SQLException e)
        {
            System.err.println("Error: " +e.getMessage());
        }       
        
        InitialPanel initialPanel = new InitialPanel(this);
        initialPanel.setPreferredSize(PanelSize);
        mainCard.add(initialPanel, INITIAL_PANEL);
        
        LoginPanel loginPanel = new LoginPanel(this);      
        loginPanel.setPreferredSize(PanelSize);        
        mainCard.add(loginPanel, LOGIN_PANEL);
        
        NewUserPanel newUserPanel = new NewUserPanel(this, this.userManager);
        newUserPanel.setPreferredSize(PanelSize);        
        mainCard.add(newUserPanel, NEW_USER_PANEL);        
        
        this.choicePanel = new ChoicePanel(this);
        choicePanel.setPreferredSize(PanelSize);
        mainCard.add(choicePanel, CHOICE_PANEL);
        
        PurchasePanel purchasePanel = new PurchasePanel(this, this.venueManager);
        purchasePanel.setPreferredSize(PanelSize);
        mainCard.add(purchasePanel, PURCHASE_PANEL);
        
        ticketPanel = new TicketPanel(this, this.ticketManager);
        ticketPanel.setPreferredSize(PanelSize);
        mainCard.add(ticketPanel, TICKET_PANEL);
        
        this.aVenuePanel = new VenueAPanel(this, this.venueManager);
        aVenuePanel.setPreferredSize(PanelSize);
        mainCard.add(aVenuePanel, VENUE_A_PANEL);
        
        this.add(mainCard, BorderLayout.CENTER);        
        this.pack();
        //locking gui size so end user doesnt drag it and maybe our code doesnt work lmao
        this.setResizable(false);        
        
        panelLayout.show(mainCard, INITIAL_PANEL);
    }
    //switch between panels
    public void showPanel(String panelName)
    {
        panelLayout.show(mainCard, panelName);
        
        //need to refresh user and ticket stuff for panels
        if(panelName.equals(guiWindow.CHOICE_PANEL))
        {
            this.choicePanel.updateCurrentUser(this.getCurrentUser());
        }
        if(panelName.equals(guiWindow.TICKET_PANEL))
        {
            this.ticketPanel.updateTickets();
        }
        if(panelName.equals(guiWindow.VENUE_A_PANEL))
        {
            this.aVenuePanel.updatePanel(this.getVenueSeats(), this.getDiscount(), this.getPrice(), this.getVenue());
        }
    }
    
    //getters + setters
    public void setCurrentUser(UserData user)
    {
        this.currentUser = user;
    }
    
    public UserData getCurrentUser()
    {
        return this.currentUser;
    }
    
    public UserTableManager getUserManager()
    {
        return this.userManager;
    }
    
    public void setVenueManager(VenueManager venue)
    {
        this.venueManager = venue;
    }
    
    public VenueManager getVenueManager()
    {
        return this.venueManager;
    }
    
    public void setVenueSeats(boolean[][] seats)
    {
        this.testSeats = seats;
    }
    
    public boolean[][] getVenueSeats()
    {
        return this.testSeats;
    }
    
    public void setDiscount(double disc)
    {
        this.discount = disc;
    }
    
    public double getDiscount()
    {
        return this.discount;
    }
    
    public void setPrice(double priceupdate)
    {
        this.price = priceupdate;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public void setVenue(String venueupdate)
    {
        this.venue = venueupdate;
    }
    
    public String getVenue()
    {
        return this.venue;
    } 
    
    public void logout()
    {
        this.currentUser = null;
        this.venue = "";
        
        showPanel(guiWindow.INITIAL_PANEL);
    }
}
