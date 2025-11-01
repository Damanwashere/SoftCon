/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareconstructionass2;

import javax.swing.SwingUtilities;

/**
 *
 * @author Prize
 */
public class mainGUI 
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> 
        {
            guiWindow window = new guiWindow("GUI");
            window.setVisible(true);
        });                
    }
}
