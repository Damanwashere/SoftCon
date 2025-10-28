/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Prize
 */
public class NewUserPanel extends JPanel 
{
    private JTextField nameField = new JTextField(20);
    private JTextField ageField = new JTextField(3);
    
    private final JCheckBox studentCheckBox = new JCheckBox("Student");
    guiWindow displayWindow;
    
    //will change dimension stuff later
    public NewUserPanel(guiWindow window)
    {
        displayWindow = window;        
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
        String name = nameField.getText().trim();
        //should hopefully convert input into grabbable int
        int age = 0;
        //will fix logic later
        try
        {
             age = Integer.parseInt(ageField.getText().trim());
        }
        catch(NumberFormatException g)
        {
            System.err.println("User didnt input a number");
        }
        boolean isStudent = studentCheckBox.isSelected();
        
        //test
        String testMessage = String.format("testing storage \nName: %s \nAge: %d \nStudent: %b", name, age, isStudent);
        
        JOptionPane.showMessageDialog(this, testMessage);
    }
}
