/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Navjot kaur
 */
public class FinalPractical extends JFrame {
   private JPanel panel1;
    private JLabel c;
    private JTextField cfield;
    private float internalprac;
    private float externalprac;
    private double finalarrout;
    
    FinalPractical() throws ClassNotFoundException, SQLException, IOException
    {
         super("Final Practical Attainment");
      
        // JFrame properties
        setBounds(520,180,500,300);
        setVisible(true);
        setResizable(false);
        
          // creating a panel for adding labels nd textfields
        panel1 = new JPanel();
        panel1.setBackground(Color.decode("#50b2b3"));
        panel1.setPreferredSize(new Dimension(500,250));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,40,100));
        panel1detailing(); 
        add(panel1);
       
        calculation();
    }
    
    void panel1detailing() throws ClassNotFoundException, SQLException, IOException
    {
        
            c = new JLabel("CO");
            c.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
            c.setPreferredSize(new Dimension(60,40));
            c.setBackground(Color.decode("#50b2b3"));
            panel1.add(c);
            
            cfield = new JTextField();
            cfield.setBackground(Color.WHITE);
            cfield.setForeground(Color.GRAY);
            cfield.setPreferredSize(new Dimension(150,40));
            cfield.setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            panel1.add(cfield);
        }

    
     
    void calculation()
    {
       
          internalprac = PracticalInternal.res;
          externalprac = PracticalExternal.res;
      
           finalarrout = (internalprac*0.4) + (externalprac*0.6);
           cfield.setText(Double.toString(finalarrout));
       }
    
    
}
