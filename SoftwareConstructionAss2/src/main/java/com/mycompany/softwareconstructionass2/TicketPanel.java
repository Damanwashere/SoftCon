/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.sql.SQLException;
/**
 *
 * @author Prize
 */
public class TicketPanel extends JPanel
{
    private guiWindow displayWindow;
    private TicketTableManager ticketManager;
    private JPanel ticketPanel;
    
    TicketPanel(guiWindow window, TicketTableManager tManager)
    {
        this.displayWindow = window;
        this.ticketManager = tManager;
        this.setLayout(new BorderLayout(10, 10));
        
        JLabel headerLabel = new JLabel("Tickets Purchased", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
        this.add(headerLabel, BorderLayout.NORTH);
        
        //adding scrollable option so I dont need to worry about how to adjust stuff based on tickets user has
        ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        
        JScrollPane scrollPane = new JScrollPane(ticketPanel);
        //only need vertical
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        this.add(scrollPane, BorderLayout.CENTER);      
        
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
    //update for panel switch
    public void updateTickets()
    {
        ticketPanel.removeAll();
        UserData currentUser = displayWindow.getCurrentUser();
        
        if(currentUser == null)
        {
            emptyTicketCase("Error: no user logged");
            ticketPanel.revalidate();
            ticketPanel.repaint();
            return;
        }
        
        try
        {
            int currentId = currentUser.getUserID();
            
            List<Ticket> currentTickets = ticketManager.getTickets(currentId);
            
            if(currentTickets == null || currentTickets.isEmpty())
            {
                emptyTicketCase("No tickets purchased");
            }
            else
            {
                for(Ticket ticket : currentTickets)
                {
                    addTicketToScroll(ticket);
                }
            }
        }
        catch(SQLException e)
        {
            System.err.println("Tickets didn't load");
        }
        ticketPanel.revalidate();
        ticketPanel.repaint();        
    }
    //showing no tickets to the user if no tickets
    private void emptyTicketCase(String message)
    {
        JLabel noTicketsLabel = new JLabel(message, SwingConstants.CENTER);
        noTicketsLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(noTicketsLabel);
        ticketPanel.add(panel);
    }
    //adding tickets using panel to scroll and view accordingly
    private void addTicketToScroll(Ticket ticket)
    {
        JPanel ticketScroll = new JPanel();
        ticketScroll.setLayout(new BoxLayout(ticketScroll, BoxLayout.X_AXIS));
        
        ticketScroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        String details = String.format("Venue: %s | Seat: %s", ticket.getVenue(), ticket.getSeat());
        JLabel detailsLabel = new JLabel(details);
        JButton detailsButton = new JButton("View Ticket");
        detailsButton.addActionListener(e -> showTickets(ticket));
        
        ticketScroll.add(detailsLabel);
        ticketScroll.add(Box.createHorizontalGlue());
        ticketScroll.add(detailsButton);
        
        ticketPanel.add(ticketScroll);
    }
    
    //show selected ticket to whoever is using
    private void showTickets(Ticket ticket)
    {
        String username = displayWindow.getCurrentUser().getName();
        JOptionPane.showMessageDialog(this, 
        String.format("Group 105 Ticket\n Ticket: %s\nUsername: %s\nVenue: %s\n Seat: %s",
                ticket.getTicketID(),
                username,
                ticket.getVenue(),
                ticket.getSeat()),
        "Ticket", JOptionPane.INFORMATION_MESSAGE);
    }
}
