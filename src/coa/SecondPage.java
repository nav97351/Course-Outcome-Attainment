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
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Navjot kaur
 */
/*
    SecondPage donot need the main method bcoz we are not calling it separately.
    It is called from FirstPage.java.
    This class contains methods which helps us to diplay number of Course Outcomes needed for the 
    course and to choose among the various options given to calculate the attainment of theory, practical exams.
*/
public class SecondPage extends JPanel implements ActionListener{ // we don't need the JFrame as we are using the previous one only
         // so directly extends JPanel
         // declaring all those things which needs to be accessible in whole class.
        private JPanel panel1;
        private JComboBox theory,practical1,finalop;
        private JTextField cofield;
        String count;
        
        //defining the constructor
        public SecondPage(){
         
         panel1();   // calling method in constructor
        }
        
        
        void panel1()    // creating panel which is added to jpanel1 of another .java file
        {
         panel1 = new JPanel();
         panel1.setBackground(Color.WHITE);
         panel1.setLayout(new FlowLayout(FlowLayout.CENTER,17,35));
         panel1.setPreferredSize(new Dimension(360,260));
         add(panel1); 
         setBackground(Color.WHITE);
         panel1detailing();
        }
        
        void panel1detailing()
        {
            // creating jlabel co(course outcomes) along with its properties 
            JLabel co = new JLabel("Num. of Theory CO's");
            co.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
            co.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            co.setPreferredSize(new Dimension(180,30));
            
            // creating text field to fill number of co's needed
            cofield = new JTextField();
            cofield.setEditable(true);
            cofield.setPreferredSize(new Dimension(130, 30));
            cofield.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
            cofield.setToolTipText("enter the number of course outcomes needed");
            cofield.addActionListener(this);
            
           
            //creating label for theoretical dropdown
            JLabel theoretical = new JLabel("Theory selection");
            theoretical.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
            theoretical.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            theoretical.setPreferredSize(new Dimension(180,30));
            
            // Creating combobox(dropdown) for theory- MSTs, Assignments, Tut sheets, University exams..
            String[] options = {"MSTs","Assignments","Tutorial sheets","University exams"};   //we can also use theory.addItem("item")
            theory = new JComboBox(options);
            theory.setBackground(Color.WHITE);
            theory.setPreferredSize(new Dimension(130,30));
            theory.addActionListener(this);    // need to go to the MstPage on clicking on it.
            
            //creating label for practical dropdown
            JLabel practical = new JLabel("Practical selection");
            practical.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
            practical.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            practical.setPreferredSize(new Dimension(180,30));
            
            //Creating combobox for practical- internal nd external
            String[] options2 = {"Internal", "External"};   //we can also use theory.addItem("item")
            practical1 = new JComboBox(options2);
            practical1.setBackground(Color.WHITE);
            practical1.setPreferredSize(new Dimension(130,30));
            practical1.addActionListener(this);
            
            //creating label for final theory and practical Course Outcome dropdown
            JLabel final1 = new JLabel("Final attainment");
            final1.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
            final1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
            final1.setPreferredSize(new Dimension(180,30));
            
            // Creating combobox(dropdown) for final theory and final practical
            String[] finaloptions = {"Final Theory","Final Practical"};   //we can also use theory.addItem("item")
            finalop = new JComboBox(finaloptions);
            finalop.setBackground(Color.WHITE);
            finalop.setPreferredSize(new Dimension(130,30));
            finalop.addActionListener(this);    
            
            panel1.add(co);
            panel1.add(cofield);
            panel1.add(theoretical);
            panel1.add(theory);
            panel1.add(practical);
            panel1.add(practical1 );
            panel1.add(final1);
            panel1.add(finalop);
        }

    /* 
        creating actionPerformed method which implements the action needed*/
    @Override
             public void actionPerformed(ActionEvent e) 
            {
                // geeting the text from number of Course Outcome and assigning it to count 
                 count = cofield.getText();
                // Selecting the particular item from the theory combobox by theory.getSelectedItem().
       
                if(e.getSource()== theory)
                {
                
                 
                switch(theory.getSelectedItem().toString())   // using switch statement to react according to the action performed.
                {
                    case "MSTs": 
                    {
                        try {
                            // creating object of MstPage class to call it with count passed as parameter to the constructor.
                            MstPage mstpage = new MstPage(count);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case "Assignments":
                    {
                        try {
                            AssignmentPage assignmentpage = new AssignmentPage(count);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case "Tutorial sheets":
                    {
                        try {
                            TutorialPage tutorialpage = new TutorialPage(count);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case "University exams":
                    {
                        try {
                            UniExamPage uniexampage = new UniExamPage();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;  
  
                        
                }
                }
                else if(e.getSource()== practical1)
                {
                // selecting the item from practical list
                switch(practical1.getSelectedItem().toString())
                {
                    case "Internal":
                    {
                        try {
                            PracticalInternal pracinternal = new PracticalInternal();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                    case "External":
                    {
                        try {
                            PracticalExternal pracexternal = new PracticalExternal();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        break;

                }
             }
                 else if(e.getSource()== finalop)
                {
                // selecting the item from practical list
                switch(finalop.getSelectedItem().toString())
                {
                    case "Final Theory":
                    {
                    try {
                        FinalTheory ft = new FinalTheory(count);
                        
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                          break;
                    }
                    
                     
                     case "Final Practical":
                    {
                          try {
                        FinalPractical ft = new FinalPractical();
                        
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                          break;
                    }

                }
                }
               
            }
             
}
