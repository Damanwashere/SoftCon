/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 *
 * @author Prize
 */
public class NewUserPanel extends JPanel 
{
    private JTextField nameField = new JTextField(20);
    private JTextField ageField = new JTextField(3);
    
    private final JCheckBox studentCheckBox = new JCheckBox("Student");
    
    private UserData newUser;    
    private guiWindow displayWindow;
    private UserTableManager userManager;
    
    //will change dimension stuff later
    public NewUserPanel(guiWindow window, UserTableManager manager)
    {
        this.displayWindow = window;
        this.userManager = manager;
        this.setLayout(new BorderLayout());
        
        JPanel contentPanel = new JPanel(new GridBagLayout());
        JPanel wrapper = new JPanel(new GridBagLayout());
        
        //user input name here
        createUserData(wrapper, new JLabel("Username:"), 0, 0, GridBagConstraints.EAST);
        createUserData(wrapper, nameField, 1, 0, GridBagConstraints.WEST);
        //age here
        createUserData(wrapper, new JLabel("Age:"), 0, 1, GridBagConstraints.EAST);
        createUserData(wrapper, ageField, 1, 1, GridBagConstraints.WEST);
        
        //student discount
        GridBagConstraints guiCons = new GridBagConstraints();
        guiCons.insets = new Insets(5, 5, 5, 5);
        guiCons.gridx = 0;
        guiCons.gridwidth = 0;
        guiCons.anchor = GridBagConstraints.CENTER;        
        guiCons.gridy = 2;
        wrapper.add(studentCheckBox, guiCons);
        
        contentPanel.add(wrapper, new GridBagConstraints());
        this.add(contentPanel, BorderLayout.CENTER);
        
        //comfirm button to create user
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));        
        JButton confirmButton = new JButton("Create Account");
        confirmButton.setPreferredSize(new Dimension(200, 50));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(this::holdData);
        
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Return to start");
        logoutButton.setPreferredSize(new Dimension(150, 25));
        logoutButton.addActionListener(e ->
        {
            displayWindow.showPanel(guiWindow.INITIAL_PANEL);
            nameField.setText("");
            ageField.setText("");
        });
        exitPanel.add(logoutButton); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(Box.createVerticalStrut(25));
        buttonPanel.add(exitPanel);
        
        this.add(buttonPanel, BorderLayout.SOUTH);        
    }
    
    private void createUserData(JPanel panel, Component component, int x, int y, int anchor)
    {
        GridBagConstraints con = new GridBagConstraints();
        con.insets = new Insets(5, 5, 5, 5);
        con.gridx = x;
        con.gridy = y;
        con.anchor = anchor;
        
        if(component instanceof JTextField)
        {
            con.fill = GridBagConstraints.HORIZONTAL;
        }
        
        panel.add(component, con);
    }
    
    //added options to meet marking criteria, now relays info back
    private void holdData(ActionEvent e)
    {
        String name = nameField.getText();
        int age = 0;
        
        try
        {
             age = Integer.parseInt(ageField.getText().trim());
             if(age <= 0 || age >120)
             {
                 JOptionPane.showMessageDialog(null, "Please enter a Valide age", "(1 - 120)", JOptionPane.ERROR_MESSAGE);
                 return;
             }
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Must input a number for Age", "user input error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(name.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please input valid username", "Input empty", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(name.contains(" "))
        {
            JOptionPane.showMessageDialog(null, "username may not have spaces", "Input invalid", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isStudent = studentCheckBox.isSelected();
        
        newUser = null;
        int newUserId;
        
        try
        {
            newUserId = this.userManager.newUserID();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Database username error", "Database invalid", JOptionPane.ERROR_MESSAGE);
            System.err.println("DB id retrieval error" + ex);
            return;
        }
        
        if(isStudent)
        {
            newUser = new Student(newUserId, name);
        }
        else if(age < 18)
        {
            newUser = new Child(newUserId, name);
        }
        else
        {
            newUser = new Adult(newUserId, name);
        }
        
        try
        {
            if(userManager.addUser(newUser))
            {
                displayWindow.setCurrentUser(newUser);
                displayWindow.showPanel(guiWindow.CHOICE_PANEL);
                nameField.setText("");
                ageField.setText("");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Username: " + name + " is already taken", "Failed to create new user", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Database connection error", "Database invalid", JOptionPane.ERROR_MESSAGE);
        }        
    }
}
