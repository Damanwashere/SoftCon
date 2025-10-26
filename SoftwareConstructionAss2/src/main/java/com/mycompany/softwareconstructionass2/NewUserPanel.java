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
public class NewUserPanel extends JPanel 
{
    private JTextField nameField = new JTextField(20);
    private JTextField ageField = new JTextField(3);
    
    private final JCheckBox studentCheckBox = new JCheckBox("Student");
    
    private static final Dimension newUserPanelSize = new Dimension(1000, 500);
    
    public NewUserPanel()
    {
        this.setPreferredSize(newUserPanelSize);        
        this.setLayout(new GridBagLayout());
        
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
        
        this.add(wrapper, new GridBagConstraints());        
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
    private void holdData()
    {
        
    }
}
