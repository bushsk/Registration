/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author Bushra1
 */
public class test1 extends JFrame implements ActionListener
{ 
     
        JFrame frame=new JFrame("Login"); 
        JLabel l1 = new JLabel("Enter Id:");
        JLabel l2 = new JLabel("Enter Password:");
        JTextField tf1 = new JTextField();
        JTextField tf2 = new JTextField();
        JButton button = new JButton("Login"); 
        JButton button2 = new JButton("Cancel");
    test1() 
    { 
        // creating instance of JFrame 
        
        l1.setBounds(80, 70, 200, 30);
        l2.setBounds(80, 110, 200, 30);
        tf1.setBounds(200,70,200,30);
        tf2.setBounds(200,110,200,30);
        button.setBounds(50, 150, 100, 30); 
        button2.setBounds(200, 150, 100, 30); 
        button.addActionListener(this);
        button2.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(l1);
        frame.add(l2);
        frame.add(button); 
        frame.add(button2);
        frame.add(tf1);
        frame.add(tf2);
        frame.setSize(500, 600); 
        frame.setLayout(null); 
        frame.setVisible(true); 
        
    } 
    public void actionPerformed(ActionEvent e) 
     {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs =null;
        if (e.getSource() == button )
         {          
            int id=Integer.parseInt(tf1.getText().trim());
            String p = tf2.getText().trim();
            System.out.println("id"+ id +"pass"+p);
            try {
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/SIES","postgres","123");
                System.out.println("Connected to the PostgreSQL server successfully.");
                stmt = conn.createStatement();
                //conn.setAutoCommit(false);
                rs = stmt.executeQuery( "select * from company where id ="+id);
            while(rs.next())
            {
                int user = rs.getInt(1);
                String pass = rs.getString(6);
                   System.out.println("id"+ user +"pass"+pass);
                if((id == user)&&(p.equals(pass) ))
                {
                     JOptionPane.showMessageDialog(button, "Login Successfully");
                }
                 else
                {
                     JOptionPane.showMessageDialog(button, "Invalid USER");
                }

            }
            stmt.close();
            conn.close();
        }   catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        else if(e.getSource()==button2)
        {
                      
            //new Registration();
            System.exit(0);
        }
     }
    
     }

