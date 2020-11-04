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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Navjot kaur
 */
public class Guidelines extends JFrame {
    private JPanel panel1;
    private final String line="\n";
    
    Guidelines()
    {
        super("Guidelines");
        
        // JFrame properties
        setLayout(new FlowLayout(FlowLayout.CENTER,30,22));
        setBounds(430,50,700,650);
        setVisible(true);
        setResizable(false);
        
          // creating a panel for adding buttons for mst and its properties
        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setPreferredSize(new Dimension(700,650));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,40,5));
        panel1detailing();        // includes the mst buttons
        add(panel1);
        
        
    }
    
    void panel1detailing()
    {
        String str="Following points should be remembered while using this desktop application:"+ line+
                "#Make sure you click on the truncate database button on each page once you start.Mst Page "
                + "truncate"+line+" button will truncate all the databases of mst1, mst2 and mst3. So, be careful with this."+line+
                "#The uploading files should be strictly only in '.csv' format"+line+
                "#The csv file should start from the first row onwards with headers included for each column"+line+
                "# Make sure you upload all the theory and practical files before clicking on the button of "+line+
                "  calculating the total course attainment of theory and practical."+line+
                "# Also check the database regularly to see whether the file is uploaded correctly."+line+
                " and if something is wrong truncate the database and try again"+line+
                "# your uploading file should be placed inside drives only. eg. E,D Drives etc."+line+
                "# when mapping with COs just write the numbers. eg. 1,2 in mst is for q1 and q2 etc."+line+
                "# If there is no mapping for particular CO just keep it emoty."+line+line+
                "Formats of files:"+line+
                "# for MST format of csv file - serial no., names, Q1 Marks, Q2 Marks,Q3 marks, Q4 Marks."+line+
                "# for Assignment file format- serial no., names, Assign1 marks, Assign2 marks, Assign3 marks"+line+
                "# for Tut sheet file format- serial no., names, tut1 marks, tut2 marks, tut3 marks"+line+
                "# for Uni exams - serial no, names, marks"+line+
                "# for internal practical - serial no. names, total marks obtained in internal by students."+line+
                "# for external practical- serial no. , name, marks"+line+line+
                "Instructions while making csv file: "+line+
                "# In case of mst only add those students name in file who have attended the exam."+line+
                "# In case of assignments if any student have not submitted then grade them with 'F'"+line+
                "  as these grader students will be excluded from the total count."+line+
                "# In case of tut sheets also same case is followed as assignment by using F grade."+line+
                "# In university exam, internal and external pratical exclude the students name from csv list"+line+
                " who have not attended the respective exam or practical" +line+line
                       +"If any query then contact with the developer team through email id - kaur97351@gmail.com";
                
        
        JTextArea codefield = new JTextArea(str);
       codefield.setEditable(false);
       codefield.setForeground(Color.DARK_GRAY);
       codefield.setPreferredSize(new Dimension(650, 650));
       codefield.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
       panel1.add(codefield);
    }
}
