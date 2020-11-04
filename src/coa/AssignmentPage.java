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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


/**
 *
 * @author Navjot kaur
 */
public class AssignmentPage extends JFrame implements ActionListener{
     // panel1 contains mst buttons, panel 2 - mapping of cos, panel3- attainment nd truncate btn, panel4-attainment of que and cos of each mst
    private JPanel panel1, panel2,panel3,panel4; 
   // cos is the passed argument in constructor and str is the absolute file path of the uploaded file, action is used to store the command on clicking the buttons of msts
    private String cos,str;       
    private int num;          // num is the cos represented in int format
    //declaration only bcoz we don't know to value of num yet
    // and jf[]- textfield where we do mapping in panel2, filetext is textfield where path is pasted of uploaded file in panel2 
    private JTextField jf[], filetext;   
    //declaration only bcoz we don't know to value of num yet
    //lb[] is used for COS label in panel2 and assi[] is used for Assignment no. label in panel4
    private JLabel lb[], assi[];     
    // declaration of the scrollpane used in mapping section   
    private JScrollPane scroll;   
    //array for panel4 
    private JLabel c[];    
    // array for panel4 
    private JTextField cosfield[];   
    // Stores the result of e.ActionCommand in actionPerformed method
    private String action;
    //// declaration of the upload button browsebtn 
    private JButton browsebtn;
    // ConnectionDB is the class defined in separate page and db is instance of this
    ConnectionDB db;
    //Connection object con1 is declared here
    Connection con1;
    // Declaring Statement object pst for executing sql queries
    Statement pst = null;
    // sql query is stored as String in loadQuery
    private String loadQuery;
    // assifield is textfield used to show attainment of questions
    private JTextField assifield[]= new JTextField[5];
    //arrout contains the attainment of all the assignments
    public static float arrout[];  //static so that we can access it directly from another class without cerating its object
          StringBuilder sb ;
    
    AssignmentPage(String co) throws ClassNotFoundException, SQLException, IOException
    {
        
        super("Assignments");
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/images/invertedcreenbg.jpg"))));
        cos = co;
        num = Integer.parseInt(cos);      //converting the string co to int to use it in code
        
        //initializing the various jlabels and textfields
        jf = new JTextField[num];
        lb= new JLabel[num];
        assi = new JLabel[5];
        c = new JLabel[num];
        cosfield = new JTextField[num];
        arrout =new float[num];
        
        // creating object of ConnectionDB class
         db = new ConnectionDB();
        //creating object of Connection 
         con1 = db.getConnection();
         
        // creating a panel for adding buttons for mst and its properties
        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setPreferredSize(new Dimension(400,90));
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
    
   void panel1detailing()        // adding buttons to panel1 for Assignment 1,2,3.
   {
       // adding a note for usres by creating the textarea
       JTextArea note = new JTextArea("** note the format should be strictly in form of "
               + "serial no., student name,Assignment1 grades,Assignment2 grades, Assignment3 grades");
       note.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
       note.setBackground(Color.WHITE);
       note.setForeground(Color.DARK_GRAY);
       note.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
       note.setPreferredSize(new Dimension(700,30));
       note.setEditable(false);
       panel1.add(note);
       
       // creating button for Assignment1 along with its properties
        JButton assignment = new JButton("Assignment");
        assignment.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        assignment.setForeground(Color.BLACK);
        assignment.setBackground(Color.decode("#50b2b3")); 
        assignment.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#50b2b3"), Color.LIGHT_GRAY));
        assignment.setPreferredSize(new Dimension(160,40));
        assignment.addActionListener(this);
        panel1.add(assignment);
         
   }
   @Override
    public void actionPerformed(ActionEvent e) {
        // getting the action command 
         action = e.getActionCommand();
       
        if(action == "Assignment")
        {
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
          int ret = chooser.showOpenDialog(null);
          if(ret == JFileChooser.APPROVE_OPTION)
          {
          File file = chooser.getSelectedFile(); //get the selected file
          str = chooser.getSelectedFile().toString();
          str = file.getAbsolutePath();     // obtaining the path of the selected file
          filetext.setText(str);                   // setting the file path
          filetext.setEditable(false);
          }
           
        }
        
        if(action == "Attainment")
        {
            // creating JFrame to add panel to display the result of the Attainment obtained in each assignmrnt and in each course Outcome
                 
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
             try {
                 assignattainment();  //calling assignattainment method to calculate the attainment
             } catch (SQLException ex) {
                 Logger.getLogger(AssignmentPage.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(AssignmentPage.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(AssignmentPage.class.getName()).log(Level.SEVERE, null, ex);
             }
       
        }
        
        if(action == "Truncate DB")
        {
             try {
                 // we need to truncate the table so that it can be used for next time
                 String query = "truncate table `assign`";
                 pst = con1.prepareStatement(query);
                 pst.execute(query);
                
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
          jf[i].setToolTipText("mapp the assignments by placing commas in between to respective co");
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
    
    void panel3detailing()           // method contains attainment button and truncate the assign database button
    {
           // creating the attainment button to calculate the attainment
          JButton truncate = new JButton("Truncate DB");
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
        for(int i=1;i<4;i++)  // to represent assignment attainment
        {
            assi[i] = new JLabel("A"+(i));
            assi[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            assi[i].setPreferredSize(new Dimension(25,20));
            assi[i].setBackground(Color.WHITE);
            panel4.add(assi[i]);
            
            assifield[i] = new JTextField();
            assifield[i].setBackground(Color.WHITE);
            assifield[i].setPreferredSize(new Dimension(50,20));
            assifield[i].setForeground(Color.GRAY);
            assifield[i].setFont(new Font("Helvetica Neue", Font.PLAIN,16));
            assifield[i].setEditable(true);
            panel4.add(assifield[i]);
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
    void assignattainment() throws SQLException, ClassNotFoundException, IOException
      {
        // writing the sql query to upload the data from csv file to the database 
         loadQuery = "LOAD DATA INFILE '" + str + "' INTO TABLE assign FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' IGNORE 1 ROWS (`Serial no.`, `Name`,`A1`,`A2`,`A3`)";
         
         // creating the prepareStatement to load the sql query
         pst = con1.prepareStatement(loadQuery);
         
         // executing the query by using execute method through the instance of prepareStatement
         pst.executeUpdate(loadQuery);
         
         // Query to count the graders A,B,C.
         int[] a = new int[5]; // stores the count of A graders in all the 3 assignments
         for(int i=1;i<4;i++)
         {
           ResultSet rs = pst.executeQuery("select count(*) from `assign` where A"+i+"= 'A'");
            if(rs.next())
             a[i] = rs.getInt(1);   
         }
         
         int[] b = new int[5]; // stores the count of B graders in all the 3 assignments
         for(int i=1;i<4;i++)
         {
            ResultSet rs = pst.executeQuery("select count(*) from `assign` where A"+i+"= 'B'");
            if(rs.next())
             b[i] = rs.getInt(1);
         }
         
         int[] c = new int[5]; // stores the count of A graders in all the 3 assignments
         for(int i=1;i<4;i++)
         {
            ResultSet rs = pst.executeQuery("select count(*) from `assign` where A"+i+"= 'C'");
            if(rs.next())
             c[i] = rs.getInt(1);
         }
         
         int[] d = new int[5]; // stores the count of students who have not submittted in all the 3 assignments
         for(int i=1;i<4;i++)
         {
            ResultSet rs = pst.executeQuery("select count(*) from `assign` where A"+i+"='F'");
            if(rs.next())
             d[i] = rs.getInt(1);
         }

        // executing query to calculate the number of rows in the database
        int n=0;   
        // the below query will include list of all the students
        ResultSet rs = pst.executeQuery("select count(*) from `assign`");
        if(rs.next())        
           n = rs.getInt(1);  //resultset returns the result by executing this statement along with the .next()
        
        float[] res= new float[5];    //float array to store the result of the attainment of each assignment
        for(int i=1;i<4;i++)
        {
            res[i] = ((float)((a[i]*100)+ (b[i]*50) + (c[i]*25))/(float)(n - d[i]));   // formula to calculate the attainment of assignment
        }
     
       for(int i=1;i<4;i++)
      {
          assifield[i].setText(Float.toString(res[i]));   // displaying the result on the panel4 textfields by converting the float value to the string
          assifield[i].setEditable(false);
      }
      
      //calling the method which contains the formulas and everthing to calculate the course outcome attainment
       coMapping(res);
    }
  
    // creating a method coMapping which is called in every mstattainment method to output the attainment of COs
    void coMapping(float[] pass)     // res[i] array is passed which contains the attaiment of questions
    {
        float sum[]= new float[num];
        float arrout[]=new float[num];
        for(int i=0;i<num;i++)
         {
             if(jf[i].getText().equals(""))
             {
                 cosfield[i].setText("0");
                 cosfield[i].setEnabled(false);
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
                    
                 }
                 
                 for(int j=0;j<storeint.length;j++)
                 {
                     sum[i] = sum[i]+storeint[j];
                 }
                 
                 cosfield[i].setText(Float.toString(sum[i]/len));
                 cosfield[i].setEditable(false);
                 arrout[i] = sum[i]/len; // used to store final attainment
            }
         }
        
    }
  
}  

 






    
