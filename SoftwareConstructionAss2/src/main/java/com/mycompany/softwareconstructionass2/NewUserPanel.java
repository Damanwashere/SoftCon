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
    private final JLabel createLabel = new JLabel("enter details and submit");
    
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
        createUserData(wrapper, new JLabel("Name:"), 0, 0, GridBagConstraints.EAST);
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
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Create Account");
        confirmButton.addActionListener(this::holdData);
        buttonPanel.add(confirmButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        
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
    
    //hold til I work out storing logic
    private void holdData(ActionEvent e)
    {
        createLabel.setText("");
        
        String name = nameField.getText();
        int age = 0;
        
        try
        {
             age = Integer.parseInt(ageField.getText().trim());
             if(age <= 0 || age >120)
             {
                 createLabel.setText("Please enter a valid age");
                 return;
             }
        }
        catch(NumberFormatException ex)
        {
            System.err.println("User didnt input a number");
        }
        
        if(name.isEmpty())
        {
            createLabel.setText("username is empty");
            return;
        }
        if(name.contains(" "))
        {
            createLabel.setText("username may not contain spaces");
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
            createLabel.setText("No valid ID's");
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
            }
            else
            {
                createLabel.setText("Username already exists");
            }
        }
        catch(SQLException ex)
        {
            createLabel.setText("Couldn't find user table");
        }        
    }
}
