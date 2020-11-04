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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.sql.*;
import javax.swing.JTextArea;


/**
 *
 * @author Navjot kaur
 */

/*
    This is the very first page of the application which includes the teacher's name, subject name 
    and subject code. It calls the SecondPage() further in the code.
*/


/*
    Creating the FirstPage class which includes only the main static method which
    eventually calls the Interface class with the help of object.
*/

public class FirstPage {
    public static void main(String args[]) throws ClassNotFoundException, SQLException
    {
        Interface interface1 = new Interface();
        //ConnectionDB db = new ConnectionDB();
    }
    
}


/*
    Creating the Interface class which implements all the code needed for FirstPage.
*/

class Interface extends JFrame implements ActionListener{
    
    // declaring JPanels outside of any method so that they can be accessed by any method.
    // declaring them as private as they need to be accessed only within this class.
    private JPanel jpanel1,jpanel2,jpanel3;
    private JButton next,guidelines;
    // defining the constructor
    Interface(){
        
        /*super keyword is used here to provide the title for the page. It calls the JFrame constructor
            with the title. */
        super("Course Outcome Attainment");
        
        
        // adding image to the contentPane, and the image should be added to the resource part of the project.
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/images/firstscreenbg.jpg"))));
   
        panel1();  // calling jpanel1
        
        // properties of JFrame.
        setBounds(500,100,550,470);       // This sets the size and position of our output screen.
        setVisible(true);               // setting the visibility of the components, without this nothing will appear on the screen..
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to make the application exit on pressing X.
        setResizable(false);          // making maximize button non working.

    }
    
   
    void panel1()
    {
        // jpanel1 is the background panel placed on the image and other 2 panels are added on top of this.
        jpanel1 = new JPanel();          // creating new jpanel1.
        add(jpanel1);                   // adding panel to contentPane
        
        /*setting the layout to the FlowLayout with its properties of position of placing content, space between 
        the components w.r.t. horizontal and vertical*/
        setLayout(new FlowLayout(FlowLayout.CENTER,55,50));  
        jpanel1.setPreferredSize(new Dimension(420,330));   // fixing the size of jpanel1.
        jpanel1.setBackground(Color.WHITE);
        
        // Creating compound border which combines two borders
        Border one = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.WHITE, Color.LIGHT_GRAY); //createEtchedBorder(int type, Color highlight, Color shadow)
        Border two = BorderFactory.createLineBorder(Color.GRAY,3);    //this includes BorderFactory.createLineBorder(Color of border, thickness)
        jpanel1.setBorder(BorderFactory.createCompoundBorder(two,one));    //First component passed to parameter will be placed first.
        
        jpanel1detailing(); // this method contains the components placed in jpanel
    }
    
    void jpanel1detailing()
    {
        /* creating jpanel2 which is placed on jpanel1 and defining properties for jpanel2 such as 
           // background color, setting the layout which will be needed for the components that are to be 
           // placed inside the jpanel2, setting the size of panel....*/
       jpanel2 = new JPanel();
       jpanel2.setBackground(Color.WHITE);
       jpanel2.setLayout(new FlowLayout(FlowLayout.CENTER,17,40));
       jpanel2.setPreferredSize(new Dimension(300,230));
       jpanel1.add(jpanel2);  
       jpanel2detailing();  // method contains components placed in jpanel2 i.e. name, subject etc...
      
       
       /* creating jpanel3 which is placed on jpanel1 and defining properties for jpanel3 such as 
           // background color, setting the layout which will be needed for the components that are to be 
           // placed inside the jpanel3, setting the size of panel....*/
       jpanel3 = new JPanel();
       jpanel3.setBackground(Color.WHITE);
       
       // setting the FlowLayout to Right as we want to place the button to the right of the screen and setting x need to be from right and y resp.
       jpanel3.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));    
       jpanel3.setPreferredSize(new Dimension(410,70));
       jpanel1.add(jpanel3);
       jpanel3detailing();  // method contains Button Next
      
       
    }

    void jpanel2detailing() 
    {
      /* //creating JLabel name nd its properties 
       JLabel name = new JLabel("Name");
       name.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
       name.setBorder(BorderFactory.createLineBorder(Color.white, 2));
       name.setPreferredSize(new Dimension(100,30));
       jpanel2.add(name);
       
       // creating JTextfield for name label along with its properties
       JTextField namefield = new JTextField();
       namefield.setEditable(true);
       namefield.setPreferredSize(new Dimension(150, 30));
       namefield.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
       namefield.setToolTipText("enter your name");
       jpanel2.add(namefield);
       
       // Creating JLabel subject nd its properties
       JLabel subject = new JLabel("Subject");
       subject.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
       subject.setBorder(BorderFactory.createLineBorder(Color.white, 2));
       subject.setPreferredSize(new Dimension(100,30));
       jpanel2.add(subject);
       
       // creating JTextfield for subject label along with its properties
       JTextField subjectfield = new JTextField();
       subjectfield.setEditable(true);
       subjectfield.setPreferredSize(new Dimension(150, 30));
       subjectfield.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
       subjectfield.setToolTipText("enter subject's name");
       jpanel2.add(subjectfield);
       
        // Creating JLabel code nd its properties
       JLabel code = new JLabel("Code");
       code.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
       code.setBorder(BorderFactory.createLineBorder(Color.white, 2));
       code.setPreferredSize(new Dimension(100,30));
       jpanel2.add(code);
       
       // creating codefield for code label along with its properties
       JTextField codefield = new JTextField();
       codefield.setEditable(true);
       codefield.setPreferredSize(new Dimension(150, 30));
       codefield.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
       codefield.setToolTipText("enter subject code");
       jpanel2.add(codefield);*/
      
       // String containing note in first screen
        String str = "Welcome to digitally calculating the COA."
                + "\nTo help you all the guidelines are provided "
                + "\non how to use this desktop application. Make "
                + "\nsure that you go through the guidelines so that"
                + " \nyou get the accurate result only";
        
        
       JTextArea codefield = new JTextArea(str);
       codefield.setEditable(false);
       codefield.setForeground(Color.GRAY);
       codefield.setPreferredSize(new Dimension(300, 100));
       codefield.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
       jpanel2.add(codefield);
       
        guidelines = new JButton("Guidelines");
        guidelines.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        guidelines.setForeground(Color.BLACK);
        guidelines.setBackground(Color.decode("#50b2b3")); //Color.decode(String) is used to decode the hex code
        guidelines.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
        guidelines.setPreferredSize(new Dimension(150,50));
        jpanel2.add(guidelines);
        guidelines.addActionListener(this);   // adding some action to the button
       
      
             
    }
    
    void jpanel3detailing()  // This method contains the Next Button
    {
        // Creating button Next nd setting its properties
        next = new JButton("Next");
        next.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        next.setForeground(Color.BLACK);
        next.setBackground(Color.decode("#50b2b3")); //Color.decode(String) is used to decode the hex code
        next.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
        next.setPreferredSize(new Dimension(100,50));
        jpanel3.add(next);
        next.addActionListener(this);   // adding some action to the button
        
    }
    
    /* The actionPerformed method is abstract method of ActionListener interface which 
    needs to be implemented by using implements.*/
    @Override
    public void actionPerformed(ActionEvent e) {
      
        if(e.getSource()==next)
        {
        // setting the visibility of both panels inside the panel1 to false and adding object of Second Page inside the jpanel1.
       jpanel2.setVisible(false);
       jpanel3.setVisible(false);
       SecondPage second = new SecondPage(); //Creating object of another .java file 
       jpanel1.add(second);   // adding object to make it visible.
       
        }
        
        if(e.getSource()==guidelines)
        {
            Guidelines gd = new Guidelines();
        }
       
}
}