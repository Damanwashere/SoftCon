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
public class LoginPanel extends JPanel
{
    private JTextField UserField;
    private guiWindow displayWindow;
    
    public LoginPanel(guiWindow login)
    {
        this.displayWindow = login;
        setLayout(new BorderLayout(20, 20));
        
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
            displayWindow.showPanel(guiWindow.CHOICE_PANEL);
        });
        cons.gridx = 1;
        cons.gridy = 2;
        cons.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, cons);
        
        this.add(panel, BorderLayout.CENTER);
        
        //return so user doesnt need to close if clicked wrong button
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

    //need code to check if user exists before going to next panel
//    someFunction()
//    {
//        
//    }
}
