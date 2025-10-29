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
public class LoginPanel extends JPanel
{
    private final JTextField UserField;
    private guiWindow displayWindow;
    private UserTableManager userManager;
    
    public LoginPanel(guiWindow window)
    {
        this.displayWindow = window;
        this.userManager = displayWindow.getUserManager();
        this.setLayout(new BorderLayout(20, 20));
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(5, 5, 5, 5);
        
        JLabel nameLabel = new JLabel("Username");
        //setting how big typing space is
        UserField = new JTextField(11);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.WEST;
        panel.add(nameLabel, cons);
        
        cons.gridx = 1;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.EAST;
        panel.add(UserField, cons);
        
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 25));
        //setting layout of panels for now, will work login logic later
        loginButton.addActionListener(e ->
        {
            String username = UserField.getText().trim();
            try
            {
                UserData logUser = userManager.getUser(username);
                
                if(logUser != null)
                {    
                    displayWindow.setCurrentUser(logUser);
                    displayWindow.showPanel(guiWindow.CHOICE_PANEL);
                    //if you don't do this and the person logs out and returns name will still be there lmao
                    UserField.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "user not found: ", "username", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(this, "sql", "database error", JOptionPane.ERROR_MESSAGE);
            }            
        });
        cons.gridx = 1;
        cons.gridy = 2;
        cons.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, cons);
        
        this.add(panel, BorderLayout.CENTER);
        
        //return so user doesnt need to close if clicked wrong button
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
