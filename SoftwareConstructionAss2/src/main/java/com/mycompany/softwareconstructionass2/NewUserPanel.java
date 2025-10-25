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
    private JTextField userField;
    
    public NewUserPanel()
    {
        this.setLayout(new GridBagLayout());
        
        this.add(new JLabel("new User test"));
    }
    
    public String getUsername()
    {
        return userField.getText();
    }
}
