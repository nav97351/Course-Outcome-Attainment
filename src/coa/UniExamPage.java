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
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Navjot kaur
 */
public class UniExamPage extends JFrame implements ActionListener{
   
    private JPanel panel1,panel2,panel3,panel4;
    // filetext contains absolute path of uploaded file
    String str;
    // filetext is the textfield which shows the absolute path of file from the local pc
    private JTextField filetext;
    // btn for upload file
    private JButton browsebtn;
    // ConnectionDB is the class defined in separate page and db is instance of this
    ConnectionDB db;
    //Connection object con1 is declared here
    Connection con1;
    // Declaring Statement object pst for executing sql queries
    Statement pst = null;
    // sql query is stored as String in loadQuery
    private String loadQuery;
    //label is used in panel4 
    private JLabel label;
    //field is used to show the attainment
    private JTextField field;
    // stores final attainment
    public static float res;
    
    public UniExamPage() throws ClassNotFoundException, SQLException
    {
         super("University exam");
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/resources/images/invertedcreenbg.jpg"))));
      
        // creating object of ConnectionDB class
         db = new ConnectionDB();
        //creating object of Connection 
         con1 = db.getConnection();
        
        //panel1 for creating university button
        panel1 = new JPanel();
        panel1.setBackground(new Color(0,0,0,0));
        panel1.setPreferredSize(new Dimension(400,60));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,80,10));
        panel1detailing();        // includes the University exam button
        add(panel1);
        
        //panel2 for creating upload list
        panel2 = new JPanel();
        panel2.setBackground(Color.decode("#50b2b3"));
        panel2.setPreferredSize(new Dimension(500,80));
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
        panel2detailing();        // includes the University exam result upload list
        
        panel3 = new JPanel();
        panel3.setBackground(Color.decode("#50b2b3"));
        panel3.setPreferredSize(new Dimension(500,60));
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,80,0));
        panel3detailing();        // includes the attainment button
        
        
        setLayout(new FlowLayout(FlowLayout.CENTER,30,40));
        setBounds(530,150,500,400);
        setVisible(true);
        setResizable(false);
    }
    
    void panel1detailing()
    {
         // creating button for Uni exams along with its properties
        JButton uniexam = new JButton("Uni exam");
        uniexam.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        uniexam.setForeground(Color.BLACK);
        uniexam.setBackground(Color.decode("#50b2b3")); 
        uniexam.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.decode("#50b2b3"), Color.LIGHT_GRAY));
        uniexam.setPreferredSize(new Dimension(160,50));
        uniexam.addActionListener(this);
        panel1.add(uniexam);
    }
    
    void panel2detailing()
    {
          // button to browse the file in pc
      browsebtn = new JButton("Upload file");
      browsebtn.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
      browsebtn.setBackground(Color.LIGHT_GRAY);
      browsebtn.setPreferredSize(new Dimension(150,35));
      browsebtn.addActionListener(this);
      panel2.add(browsebtn);
      
      // setting the path of file in this textbox
      filetext = new JTextField("");
      filetext.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
      filetext.setPreferredSize(new Dimension(150,35));
      filetext.setBackground(Color.WHITE);
      filetext.setForeground(Color.GRAY);
      panel2.add(filetext);
    }
    
    void panel3detailing()
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // getting the action command 
        String action = e.getActionCommand();
       // further code in if statement helps to clear the text in textfield and to add panel2 to the window.
        if(action.equals("Uni exam"))
        {
            if( filetext!=null)    // to remove all those fields which are already filled
            {
                filetext.setText("");
            }
           
           add(panel2);
           add(panel3);
           // these helps to refresh the output screen and show all the elements added on the go.
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
             // creating JFrame to add panel to display the result of the Attainment obtained in each question and in each course Outcome
             JFrame jf = new JFrame();
             jf.setBackground(Color.WHITE);
             jf.setBounds(600,200, 500,250);
             jf.setVisible(true);
             jf.setResizable(false);
                 
             panel4 = new JPanel();
             panel4.setLayout(new FlowLayout(FlowLayout.CENTER,50,80));
             panel4.setBackground(Color.decode("#50b2b3"));
             jf.add(panel4);
             panel4detailing();             // gives the attainment             
            try {
                uniattainment();   //method calculates the attainment of file uploaded
            } catch (SQLException ex) {
                Logger.getLogger(UniExamPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UniExamPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UniExamPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
         
        if(action == "Truncate DBs")
        {
             try {
                 // we need to truncate the table so that it can be used for next time
                 String query = "truncate table `theoryuni`";
                 pst = con1.prepareStatement(query);
                 pst.execute(query);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(MstPage.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
    }
    
    void panel4detailing()  // this method contains the label and textfield to represent the attainment obtained 
    {
        
         label = new JLabel("university result");
         label.setFont(new Font("Helvetica Neue", Font.BOLD,18));
         label.setPreferredSize(new Dimension(150,40));
         label.setBackground(Color.WHITE);
         panel4.add(label);
            
         field = new JTextField();
         field.setBackground(Color.WHITE);
         field.setPreferredSize(new Dimension(100,40));
         field.setForeground(Color.GRAY);
         field.setFont(new Font("Helvetica Neue", Font.PLAIN,18));
         field.setEditable(true);
         panel4.add(field);
            revalidate();
            repaint();
    }
    
    void uniattainment() throws SQLException, ClassNotFoundException, IOException
    {
         // writing the sql query to upload the data from csv file to the database 
        loadQuery = "LOAD DATA INFILE '" + str + "' INTO TABLE theoryuni FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' IGNORE 1 ROWS (`Serial no.`, `Name`,`Marks`)";
         
        // creating the prepareStatement to load the sql query
        pst = con1.prepareStatement(loadQuery);
        
        // executing the query by using execute method through the instance of prepareStatement
        pst.executeUpdate(loadQuery);
            
        // Query to generate the average of marks uploaded
        float avg=0; // stores the average 
        int n1=0; //num. of students above average
        int n2=0; // num of students below average
        int n=0; // num of students attended the exam
         ResultSet rs = pst.executeQuery("select avg(Marks) from `theoryuni`");
         if(rs.next())
            avg = rs.getFloat(1);
        
         ResultSet rs1 = pst.executeQuery("select count(*) from `theoryuni` where Marks>"+avg);
         if(rs1.next())
            n1 =rs1.getInt(1);
         ResultSet rs2 = pst.executeQuery("select count(*) from `theoryuni` where Marks<"+avg);
         if(rs2.next())
            n2 = rs2.getInt(1);
         ResultSet rs3 = pst.executeQuery("select count(*) from `theoryuni`");
         if(rs3.next())
            n = rs3.getInt(1);
         
         // to calculate the attainment by using the formula
         res = ((float)(n1*100) + (n2*50))/n;
         
         field.setText(Float.toString(res));
         field.setEditable(false);
         
    }
    
    
}
