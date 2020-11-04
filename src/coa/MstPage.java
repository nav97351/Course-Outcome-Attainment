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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


/**
 *
 * @author Navjot kaur
 */
public class MstPage extends JFrame implements ActionListener{ 
    // panel1 contains mst buttons, panel 2 - mapping of cos, panel3- attainment nd truncate btn, panel4-attainment of que and cos of each mst, panel5- final attainment of msts
    private JPanel panel1, panel2,panel3,panel4,panel5 ; 
   // cos is the passed argument in constructor and str is the absolute file path of the uploaded file, action is used to store the command on clicking the buttons of msts
    private String cos,str;       
    private int num;          // num is the cos represented in int format
    //declaration only bcoz we don't know to value of num yet
    // and jf[]- textfield where we do mapping in panel2, filetext is textfield where path is pasted of uploaded file in panel2 
    private JTextField jf[], filetext;   
    //declaration only bcoz we don't know to value of num yet
    //lb[] is used for COS label in panel2 and que[] is used for Q label in panel4
    private JLabel lb[], que[];     
    // declaration of the scrollpane used in mapping section   
    private JScrollPane scroll;   
    //array for panel4 and panel5 for label of co
    private JLabel c[],cfinal[];    
    // array for panel4 and panel5 to print the attainment of co
    private JTextField cosfield[],cosfieldfinal[];   
    // Stores the result of e.ActionCommand in actionPerformed method
    private String action;
    // used to indicate which of the mst buttons is clicked
    private boolean flag1 = false, flag2 = false, flag3 = false;    
    //// declaration of the upload button browsebtn and finalatt for Final attainment calculation 
    private JButton browsebtn,finalatt;
    // ConnectionDB is the class defined in separate page and db is instance of this
    ConnectionDB db;
    //Connection object con1 is declared here
    Connection con1;
    // Declaring Statement object pst for executing sql queries
    Statement pst = null;
    // sql query is stored as String in loadQuery
    private String loadQuery;
    // arrays finalres1[],finalres2[],and finalres3[] declared here and are used to put values in panel5 to store attainment       
    private float finalres1[],finalres2[],finalres3[];
    // quefield is textfield used to show attainment of questions
    private JTextField quefield[]= new JTextField[5];
    //arrout contains the attainment of all the msts
    public static float arrout[];
    
    MstPage(String co) throws ClassNotFoundException, SQLException, IOException
    {
        
        super("MSTs");
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/images/invertedcreenbg.jpg"))));
        cos = co;
        num = Integer.parseInt(cos);      //converting the string co to int to use it in code
        
        //initializing the various jlabels and textfields
        jf = new JTextField[num];
        lb= new JLabel[num];
        que = new JLabel[5];
        c = new JLabel[num];
        cosfield = new JTextField[num];
        cfinal = new JLabel[num];
        cosfieldfinal = new JTextField[num];
        arrout =new float[num];
        
        //initializing the arrays to store the course outcome result of each mst
        finalres1 = new float[num];
        finalres2 = new float[num];
        finalres3 = new float[num];
        
        // creating object of ConnectionDB class
         db = new ConnectionDB();
        //creating object of Connection 
         con1 = db.getConnection();
         
        // creating a panel for adding buttons for mst and its properties
        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setPreferredSize(new Dimension(700,90));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,40,5));
        panel1detailing();        // includes the mst buttons
        add(panel1);
        
        // creating panel2 for doing the mapping and uploading the file containing the list of students
        panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        //no need to set the size of the panel2 as scrollpane will automatically set it.
        panel2.setLayout(new GridLayout(0,2,10,10));    // 0 here for rows means that they can be any number of rows but this is not same for column.
        Border one = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.WHITE, Color.LIGHT_GRAY); //createEtchedBorder(int type, Color highlight, Color shadow)
        Border two = BorderFactory.createLineBorder(Color.GRAY,3);
        panel2.setBorder(BorderFactory.createCompoundBorder(two,one));
        panel2detailing();      // includes details for mapping and uploading file
        
        panel3 = new JPanel();
        panel3.setBackground(Color.decode("#50b2b3"));
        panel3.setPreferredSize(new Dimension(550,70));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,80,0));
        panel3detailing();        // includes the attainment button
        
        
       // JFrame properties
        setLayout(new FlowLayout(FlowLayout.CENTER,30,22));
        setBounds(430,50,700,650);
        setVisible(true);
        setResizable(false);
        
    }
    
   void panel1detailing()        // adding buttons to panel1 for mst 1,2,3.
   {
       // adding a note for usres by creating the textarea
       JTextArea note = new JTextArea("** note the format should be strictly in form of "
               + "serial no., student name,marks obt in Q1, Q2, Q3, Q4");
       note.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
       note.setBackground(Color.WHITE);
       note.setForeground(Color.DARK_GRAY);
       note.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
       note.setPreferredSize(new Dimension(550,30));
       note.setEditable(false);
       panel1.add(note);
       
       // creating button for mst1 along with its properties
        JButton mst1 = new JButton("MST1");
        mst1.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        mst1.setForeground(Color.BLACK);
        mst1.setBackground(Color.decode("#50b2b3")); 
        mst1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#50b2b3"), Color.LIGHT_GRAY));
        mst1.setPreferredSize(new Dimension(100,40));
        mst1.addActionListener(this);
        panel1.add(mst1);
        
        // creating button for mst2 along with its properties
        JButton mst2 = new JButton("MST2");
        mst2.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        mst2.setForeground(Color.BLACK);
        mst2.setBackground(Color.decode("#50b2b3")); 
        mst2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#50b2b3"), Color.LIGHT_GRAY));
        mst2.setPreferredSize(new Dimension(100,40));
        mst2.addActionListener(this);
        panel1.add(mst2);
        
        // creating button for mst3 along with its properties
        JButton mst3 = new JButton("MST3");
        mst3.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        mst3.setForeground(Color.BLACK);
        mst3.setBackground(Color.decode("#50b2b3")); 
        mst3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#50b2b3"), Color.LIGHT_GRAY));
        mst3.setPreferredSize(new Dimension(100,40));
        mst3.addActionListener(this);
        panel1.add(mst3);
              
        // creating button for final attainment along with its properties
        finalatt = new JButton("Final Att.");
        finalatt.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        finalatt.setForeground(Color.BLACK);
        finalatt.setBackground(Color.decode("#50b2b3")); 
        finalatt.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#50b2b3"), Color.LIGHT_GRAY));
        finalatt.setPreferredSize(new Dimension(100,40));
        finalatt.addActionListener(this);
        panel1.add(finalatt);
         
   }
   @Override
    public void actionPerformed(ActionEvent e) {
        // getting the action command 
         action = e.getActionCommand();
       
        switch(action)
        {
            case "MST1": 
                
                //makes the rest of the flags as false if they are still true except flag1
                flag1 = true;
                flag2 = false;
                flag3 = false;
                
                if(scroll!= null && filetext!=null)    // to remove all those fields which are already filled
                {
                    remove(scroll);
                    filetext.setText("");
                }
                for(int i=0;i<jf.length;i++)
               {
                        jf[i].setText("");
               }
                add(panel2);
                addscrollpane();
                revalidate();
                repaint();
                break;

            case "MST2":
                
                //makes the rest of the flags as false if they are still true except flag2
                flag2 = true;
                flag1 = false;
                flag3 = false;
                
                if(scroll!= null && filetext!=null)    // to remove all those fields which are already filled
                {
                    remove(scroll);
                    filetext.setText("");
                }
                for(int i=0;i<jf.length;i++)
               {
                        jf[i].setText("");
               }
                add(panel2);
                addscrollpane();
                revalidate();
                repaint();
                break;
            case "MST3":
               
                //makes the rest of the flags as false if they are still true except flag3
                flag3 = true;
                flag1 = false;
                flag2 = false;
                
                if(scroll!= null && filetext!=null)    // to remove all those fields which are already filled
                {
                    remove(scroll);
                    filetext.setText("");
                }
                for(int i=0;i<jf.length;i++)
               {
                        jf[i].setText("");
               }
                add(panel2);
                addscrollpane();
                revalidate();
                repaint();
                
        }
        
        // action taken when upload file button is clicked to browse the file in local pc
        if(e.getSource() == browsebtn)
        {
          JFileChooser chooser = new JFileChooser(); 
          chooser.showOpenDialog(null);
          File file = chooser.getSelectedFile(); //get the selected file
           str = file.getAbsolutePath();     // obtaining the path of the selected file
           filetext.setText(str);                   // setting the file path
           filetext.setEditable(false);
        }
        
        if(action == "Attainment")
        {
            
             try {
                 // creating JFrame to add panel to display the result of the Attainment obtained in each question and in each course Outcome
                 
                 JFrame jf = new JFrame();
                 jf.setBackground(Color.WHITE);
                 jf.setBounds(600,200, 500,250);
                 jf.setVisible(true);
                 jf.setResizable(false);
                 
                 panel4 = new JPanel();
                 panel4.setLayout(new GridLayout(0,4,20,15));
                 panel4.setBackground(Color.decode("#50b2b3"));
                 jf.add(panel4);
                 panel4detailing();             // gives the attainment for each question and course outcome                           
                 
                 if(flag1 == true)
                     mst1attainment();
                 else if(flag2 == true)
                     mst2attainment();
                 else if(flag3 == true)
                     mst3attainment();
             } catch (SQLException ex) {
                 Logger.getLogger(MstPage.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(MstPage.class.getName()).log(Level.SEVERE, null, ex);
             }
            
        }
        
        if(action == "Final Att.")
        {
             JFrame jf = new JFrame();
                 jf.setBackground(Color.WHITE);
                 jf.setBounds(600,200, 500,250);
                 jf.setVisible(true);
                 jf.setResizable(false);
                 
                 panel5 = new JPanel();
                 panel5.setLayout(new GridLayout(0,2,20,15));
                 panel5.setBackground(Color.decode("#50b2b3"));
                 jf.add(panel5);
             try {   
                 finalattdetailing();
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(MstPage.class.getName()).log(Level.SEVERE, null, ex);
             } catch (SQLException ex) {
                 Logger.getLogger(MstPage.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(MstPage.class.getName()).log(Level.SEVERE, null, ex);
             }
                 
        }
        
        if(action == "Truncate DBs")
        {
             try {
                 // we need to truncate the table so that it can be used for next time
                 String query = "truncate table `mst1`";
                 pst = con1.prepareStatement(query);
                 pst.execute(query);
                 pst.execute("truncate table `mst2`");
                 pst.execute("truncate table `mst3`");
                 
             } catch (SQLException ex) {
                 Logger.getLogger(MstPage.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
       
    }
    
    void panel2detailing()  // contains the mapping ,Attainment button and uploading file
    {
      // using for loop to create the number of labels and textfields according to the value of number 
      // of co's needed that were initialized in SeconPage.java
      for(int i=0;i<num;i++)
      {
          
          lb[i] = new JLabel("CO"+(i+1),SwingConstants.CENTER);
          lb[i].setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
          lb[i].setPreferredSize(new Dimension(50,20));
          lb[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
          panel2.add(lb[i]);
          
          jf[i] = new JTextField("eg: 1,3");
          jf[i].setBackground(Color.WHITE);
          jf[i].setEditable(true);
          jf[i].setMaximumSize(new Dimension(30,20));
          jf[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
          jf[i].setToolTipText("mapp the questions by placing commas in between to respective co");
          panel2.add(jf[i]);          
          revalidate();
          repaint();
 
      }        
      
      // button to browse the file in pc
      browsebtn = new JButton("Upload file");
      browsebtn.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
      browsebtn.setBackground(Color.LIGHT_GRAY);
      browsebtn.setPreferredSize(new Dimension(40,20));
      browsebtn.addActionListener(this);
      panel2.add(browsebtn);
      
      // setting the path of file in this textbox
      filetext = new JTextField("csv file only");
      filetext.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
      filetext.setBackground(Color.WHITE);
      filetext.setForeground(Color.GRAY);
      panel2.add(filetext);
    
    }
    
    void addscrollpane()
    {
         scroll = new JScrollPane(panel2);          // parameter panel2 means adding scollpane to panel2
         scroll.setPreferredSize(new Dimension(550,380));         // setting the same size as the panel
         scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         add(scroll);
         add(panel3);
    }  
    
    void panel3detailing()           // method contains attainment button and truncate all the databases button
    {
           // creating the attainment button to calculate the attainment
          JButton truncate = new JButton("Truncate DBs");
          truncate.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
          truncate.setForeground(Color.BLACK);
          truncate.setBackground(Color.WHITE); //Color.decode(String) is used to decode the hex code
          truncate.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
          truncate.setPreferredSize(new Dimension(150,50));
          truncate.addActionListener(this);
          panel3.add(truncate);
          
          // creating the attainment button to calculate the attainment
          JButton calculate = new JButton("Attainment");
          calculate.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
          calculate.setForeground(Color.BLACK);
          calculate.setBackground(Color.WHITE); //Color.decode(String) is used to decode the hex code
          calculate.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.LIGHT_GRAY));
          calculate.setPreferredSize(new Dimension(150,50));
          calculate.addActionListener(this);
          panel3.add(calculate);
          
          revalidate();
          repaint();
    }
    
    void panel4detailing()  // this method contains the labels and textfields to represent the attainment obtained 
    {
        for(int i=1;i<5;i++)  // to represent question attainment
        {
            que[i] = new JLabel("Q"+(i));
            que[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            que[i].setPreferredSize(new Dimension(25,20));
            que[i].setBackground(Color.WHITE);
            panel4.add(que[i]);
            
            quefield[i] = new JTextField();
            quefield[i].setBackground(Color.WHITE);
            quefield[i].setPreferredSize(new Dimension(50,20));
            quefield[i].setForeground(Color.GRAY);
            quefield[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            quefield[i].setEditable(true);
            panel4.add(quefield[i]);
            revalidate();
            repaint();
        }
        
        for(int i=0;i<num;i++)  // to rep. CO attainments
        {
            c[i] = new JLabel("CO"+(i+1));
            c[i].setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
            c[i].setPreferredSize(new Dimension(25,20));
            c[i].setBackground(Color.WHITE);
            panel4.add(c[i]);
            
            cosfield[i] = new JTextField();
            cosfield[i].setBackground(Color.WHITE);
            cosfield[i].setForeground(Color.GRAY);
            cosfield[i].setPreferredSize(new Dimension(50,20));
            cosfield[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            panel4.add(cosfield[i]);
        }
    }
    
    // this following method - executing sql queries and placing the text in textfield for mst1
    void mst1attainment() throws SQLException, ClassNotFoundException
      {
        // writing the sql query to upload the data from csv file to the database 
        loadQuery = "LOAD DATA INFILE '" + str + "' INTO TABLE mst1 FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' IGNORE 1 ROWS (`Serial no.`, `Name`,`Q1`,`Q2`,`Q3`,`Q4`)";
         
        // creating the prepareStatement to load the sql query
        pst = con1.prepareStatement(loadQuery);
        
        // executing the query by using execute method through the instance of prepareStatement
        pst.executeUpdate(loadQuery);
            
        // Query to generate the average of questions
        float[] q = new float[5]; // stores the avarage of all the questions
       for(int i=1;i<5;i++)
       {
            ResultSet rs = pst.executeQuery("select avg(Q"+i+") from `mst1`");
            if(rs.next())
             q[i] = rs.getFloat(1);
       }
      
       int[] n1 =new int[5];  // array that holds the count of greater than average
       int[] n2 =new int[5];   // array that holds the count of lesser than average
      // executing query to find the number of items greater than the average
      for(int i=1;i<5;i++)
      {
      ResultSet rs1 = pst.executeQuery("select count(*) from `mst1` where Q"+i+">="+q[i]);
      if(rs1.next())
      {
          n1[i]=rs1.getInt(1);
          System.out.println("> n"+i+n1[i]);
      }
      ResultSet rs2 = pst.executeQuery("select count(*) from `mst1` where Q"+i+"<"+q[i]);
      if(rs2.next())
      {
          n2[i]=rs2.getInt(1);
          System.out.println(n2[i]);
      }     
      }
      // executing query to calculate the number of rows in the database
      int n=0;   
      ResultSet rs = pst.executeQuery("select count(*) from `mst1`");
      if(rs.next())        
         n = rs.getInt(1);  //resultset returns the result by executing this statement along with the .next()
      
      float[] res= new float[5];    //float array to store the result of the attainment of each questions
      for(int i=1;i<5;i++)
      {
          res[i] = (((float)(n1[i]*100)+ (n2[i]*50))/(float)n);   // formula to calculate the attainment of mst1
      }
      
       for(int i=1;i<5;i++)
      {
          quefield[i].setText(Float.toString(res[i]));   // displaying the result on the panel4 textfields by converting the float value to the string
           quefield[i].setEditable(false);
      }
      
      //calling the method which contains the formulas and everthing to calculate the course outcome attainment
       coMapping(res);
    }
    
     // this following method - executing sql queries and placing the text in textfield for mst2
    void mst2attainment() throws SQLException
    {
        // writing the sql query to upload the data from csv file to the database 
        loadQuery = "LOAD DATA INFILE '" + str + "' INTO TABLE mst2 FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' IGNORE 1 ROWS (`Serial no.`, `Name`,`Q1`,`Q2`,`Q3`,`Q4`)";
         
        // creating the prepareStatement to load the sql query
        pst = con1.prepareStatement(loadQuery);
        
        // executing the query by using execute method through the instance of prepareStatement
        pst.executeUpdate(loadQuery);
            
        // Query to generate the average of questions
        float[] q = new float[5]; // stores the avarage of all the questions
       for(int i=1;i<5;i++)
       {
            ResultSet rs = pst.executeQuery("select avg(Q"+i+") from `mst2`");
            if(rs.next())
             q[i] = rs.getFloat(1);
       }
      
       int[] n1 =new int[5];  // array that holds the count of greater than average
       int[] n2 =new int[5];   // array that holds the count of lesser than average
      // executing query to find the number of items greater than the average
      for(int i=1;i<5;i++)
      {
      ResultSet rs1 = pst.executeQuery("select count(*) from `mst2` where Q"+i+">"+q[i]);
      if(rs1.next())
          n1[i]=rs1.getInt(1);
      ResultSet rs2 = pst.executeQuery("select count(*) from `mst2` where Q"+i+"<"+q[i]);
      if(rs2.next())
          n2[i]=rs2.getInt(1);
      }     
     
      // executing query to calculate the number of rows in the database
      int n=0;   
      ResultSet rs = pst.executeQuery("select count(*) from `mst2`");
      if(rs.next())        
         n = rs.getInt(1);  //resultset returns the result by executing this statement along with the .next()
      
      float[] res= new float[5];    //float array to store the result of the attainment of each questions
      for(int i=1;i<5;i++)
      {
          res[i] = (((n1[i]*100)+ (n2[i]*50))/n);   // formula to calculate the attainment of mst1
      }
      
       for(int i=1;i<5;i++)
      {
          quefield[i].setText(Float.toString(res[i]));   // displaying the result on the panel4 textfields by converting the float value to the string
           quefield[i].setEditable(false);
      }
     
      //calling the method which contains the formulas and everthing to calculate the course outcome attainment
      coMapping(res);
    }
    
     // this following method - executing sql queries and placing the text in textfield for mst3
    void mst3attainment() throws SQLException
    {
        // writing the sql query to upload the data from csv file to the database 
        loadQuery = "LOAD DATA INFILE '" + str + "' INTO TABLE mst3 FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' IGNORE 1 ROWS (`Serial no.`, `Name`,`Q1`,`Q2`,`Q3`,`Q4`)";
         
        // creating the prepareStatement to load the sql query
        pst = con1.prepareStatement(loadQuery);
        
        // executing the query by using execute method through the instance of prepareStatement
        pst.executeUpdate(loadQuery);
            
        // Query to generate the average of questions
        float[] q = new float[5]; // stores the avarage of all the questions
        for(int i=1;i<5;i++)
        {
            ResultSet rs = pst.executeQuery("select avg(Q"+i+") from `mst3`");
            if(rs.next())
             q[i] = rs.getFloat(1);
        }
      
       int[] n1 =new int[5];  // array that holds the count of greater than average
       int[] n2 =new int[5];   // array that holds the count of lesser than average
        // executing query to find the number of items greater than the average
        for(int i=1;i<5;i++)
        {
        ResultSet rs1 = pst.executeQuery("select count(*) from `mst3` where Q"+i+">"+q[i]);
        if(rs1.next())
            n1[i]=rs1.getInt(1);
        ResultSet rs2 = pst.executeQuery("select count(*) from `mst3` where Q"+i+"<"+q[i]);
        if(rs2.next())
            n2[i]=rs2.getInt(1);
        }     

        // executing query to calculate the number of rows in the database
        int n=0;   
        ResultSet rs = pst.executeQuery("select count(*) from `mst3`");
        if(rs.next())        
           n = rs.getInt(1);  //resultset returns the result by executing this statement along with the .next()

        float[] res= new float[5];    //float array to store the result of the attainment of each questions
        for(int i=1;i<5;i++)
        {
            res[i] = (((n1[i]*100)+ (n2[i]*50))/n);   // formula to calculate the attainment of mst3
        }

         for(int i=1;i<5;i++)
        {
            quefield[i].setText(Float.toString(res[i]));   // displaying the result on the panel4 textfields by converting the float value to the string
            quefield[i].setEditable(false);
        }

        // we need to truncate the table so that it can be used for next time
        // pst.execute("truncate table `mst3`");   

         //calling the method which contains the formulas and everthing to calculate the course outcome attainment
        coMapping(res);
    }
    
    // creating a method coMapping which is called in every mstattainment method to output the attainment of COs
    void coMapping(float[] pass)         // res[i] array is passed which contains the attaiment of questions
    {
        float sum[]= new float[num];
        for(int i=0;i<num;i++)
         {
             if(jf[i].getText().equals(""))
             {
                 cosfield[i].setText("0");
                 cosfield[i].setEnabled(false);
                 if(flag1==true)
                     finalres1[i] =0;
                 if(flag2==true)
                     finalres2[i] =0;
                 if(flag3==true)
                     finalres3[i] =0;
                
             }
             else {
                 String[] arr=jf[i].getText().split(",");
                 float storeint[] = new float[arr.length];
                 int len = storeint.length;
                 for(int j=0;j<storeint.length;j++)
                 {
                     storeint[j] = Float.parseFloat(arr[j]);
                     
                 }
                 for(int j=0;j<storeint.length;j++)
                 {
                     if(storeint[j]==1)
                     {
                         storeint[j] = pass[1];
                     }
                     else if(storeint[j]==2)
                     {
                         storeint[j] = pass[2];
                     }
                     else if(storeint[j]==3)
                     {
                         storeint[j] = pass[3];
                     }
                     else if(storeint[j]==4)
                     {
                         storeint[j] = pass[4];
                     }
                     
                 }
                 
                 for(int j=0;j<storeint.length;j++)
                 {
                     sum[i] = sum[i]+storeint[j];
                 }
                 
                 cosfield[i].setText(Float.toString(sum[i]/len));
                 cosfield[i].setEditable(false);
                 
                 if(flag1==true)
                     finalres1[i] = sum[i]/len;
                 if(flag2==true)
                     finalres2[i] = sum[i]/len;
                 if(flag3==true)
                     finalres3[i] = sum[i]/len;
            }
         }
    }
    
    void finalattdetailing() throws ClassNotFoundException, SQLException, IOException
    {
        
        for(int i=0;i<num;i++)
        {
            cfinal[i] = new JLabel("CO"+(i+1));
            cfinal[i].setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
            cfinal[i].setPreferredSize(new Dimension(25,20));
            cfinal[i].setBackground(Color.WHITE);
            panel5.add(cfinal[i]);
            
            cosfieldfinal[i] = new JTextField();
            cosfieldfinal[i].setBackground(Color.WHITE);
            cosfieldfinal[i].setForeground(Color.GRAY);
            cosfieldfinal[i].setPreferredSize(new Dimension(50,20));
            cosfieldfinal[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            panel5.add(cosfieldfinal[i]);
        
        }
        
        for(int i=0;i<num;i++)
        {
            int len1,len2,len3;
            if(finalres1[i]==0 && finalres2[i]==0 && finalres3[i]==0)
            {
                arrout[i]=0;
            }
            else if((finalres1[i]==0 && finalres2[i]!=0 && finalres3[i]!=0)|| 
                    (finalres1[i]!=0 && finalres2[i]==0 && finalres3[i]!=0)
                  ||  (finalres1[i]!=0 && finalres2[i]!=0 && finalres3[i]==0))
            {
                arrout[i]=(finalres1[i]+finalres2[i]+finalres3[i])/2;
            }
            else if((finalres1[i]==0 && finalres2[i]==0 && finalres3[i]!=0)|| 
                    (finalres1[i]!=0 && finalres2[i]==0 && finalres3[i]==0)
                  ||  (finalres1[i]==0 && finalres2[i]!=0 && finalres3[i]==0))
            {
                arrout[i]=(finalres1[i]+finalres2[i]+finalres3[i]);
            }
            else if(finalres1[i]!=0 && finalres2[i]!=0 && finalres3[i]!=0)
            {
                arrout[i]=(finalres1[i]+finalres2[i]+finalres3[i])/3;
            }  
        }
        
        for(int i=0;i<num;i++)
        {
            cosfieldfinal[i].setText(Float.toString(arrout[i]));
        }
        
    }
   
}  

 






    
