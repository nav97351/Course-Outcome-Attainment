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
public class FinalTheory extends JFrame {
    private JPanel panel1;
    private JLabel c[];
    private JTextField cfield[];
    private String cos;
    private int num;
    private float mst[];
    private float assignment[];
    private float tut[];
    private float uni;
    double finalarrout[];
    
    FinalTheory(String co) throws ClassNotFoundException, SQLException, IOException
    {
        super("Final Theory Attainment");
         cos = co;
         num = Integer.parseInt(cos);
         
        //initializing the c[] and cfield[]
        c = new JLabel[num];
        cfield = new JTextField[num];   
        mst = new float[num];
        assignment = new float[num];
        tut = new float[num];
        finalarrout = new double[num];
        
        // JFrame properties
        setBounds(520,180,500,300);
        setVisible(true);
        setResizable(false);
        
          // creating a panel for adding labels and textfields
        panel1 = new JPanel();
        panel1.setBackground(Color.decode("#50b2b3"));
        //panel1.setPreferredSize(new Dimension(500,250));
        panel1.setLayout(new GridLayout(0,2,30,20));
        panel1detailing(); 
        add(panel1);
        
        JScrollPane scroll = new JScrollPane(panel1);          // parameter panel1 means adding scollpane to panel1
        scroll.setPreferredSize(new Dimension(350,300));         // setting the same size as the panel
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
         
        calculation();
    }
    
    void panel1detailing() throws ClassNotFoundException, SQLException, IOException
    {
        
         
        for(int i=0;i<num;i++)
        {
            c[i] = new JLabel("CO"+(i+1));
            c[i].setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
            c[i].setPreferredSize(new Dimension(60,40));
            c[i].setBackground(Color.decode("#50b2b3"));
            panel1.add(c[i]);
            
            cfield[i] = new JTextField();
            cfield[i].setBackground(Color.WHITE);
            cfield[i].setForeground(Color.GRAY);
            cfield[i].setPreferredSize(new Dimension(150,40));
            cfield[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            panel1.add(cfield[i]);
        }
        
    }
    
     
    void calculation() throws ClassNotFoundException, SQLException, IOException
    {
       for(int i=0;i<num;i++)
       {
           mst[i] = MstPage.arrout[i];
           assignment[i] = AssignmentPage.arrout[i];
           tut[i] = TutorialPage.arrout[i];
           uni = UniExamPage.res;
       }
       
       for(int i=0;i<num;i++)
       {
           finalarrout[i] = (mst[i]*0.3) + (assignment[i]*0.05) + (tut[i]*0.05)+ (uni*0.6);
           cfield[i].setText(Double.toString(finalarrout[i]));
       }
      
       
    }
}