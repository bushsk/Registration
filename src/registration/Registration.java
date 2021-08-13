package registration;
//1.Import JDBC packages.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Registration extends JFrame implements ActionListener 
  { 
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField tf1, tf2, tf3,tf5, tf6, tf7;
    JButton btn1, btn2,bt3,bt4,bt5;
    JPasswordField p1, p2;
    Integer s1,s3; Float s6; 
    String s2,s5,pass;
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs =null;
    Registration()
     {
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registration Form");
 
        l1 = new JLabel("SignUp Form:");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l1.setHorizontalAlignment(JLabel.CENTER);
        
        JPanel panel = new JPanel(); 
        l2 = new JLabel("Id:");
        l3 = new JLabel("Name:");
        l4 = new JLabel("Age");
        l5 = new JLabel("Address");
        l6 = new JLabel("Salary");
        l7 = new JLabel("Enter Id to be searched:");
        l8 = new JLabel("Enter Password:");
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();
        tf7 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Create");
        btn2 = new JButton("Reset");
        bt3 = new JButton("Search");
        bt4 = new JButton("Login");
        bt5 = new JButton("Save");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        bt3.addActionListener(this);
        bt4.addActionListener(this);
        bt5.addActionListener(this);
         l1.setBounds(100, 30, 400, 30);
         l2.setBounds(80, 70, 200, 30);
         l3.setBounds(80, 110, 200, 30);
         l4.setBounds(80, 150, 200, 30);
         l5.setBounds(80, 190, 200, 30);
         l6.setBounds(80, 230, 200, 30);
         l7.setBounds(80, 530, 200, 30);
         l8.setBounds(80, 280, 200, 30);
         tf1.setBounds(300, 70, 200, 30);
         tf2.setBounds(300, 110, 200, 30);
         tf3.setBounds(300, 150, 200, 30);
         tf5.setBounds(300, 190, 200, 30);
         tf6.setBounds(300, 230, 200, 30);
         p1.setBounds(300, 280, 200, 30);
         btn1.setBounds(50, 350, 100, 30);
         btn2.setBounds(170, 350, 100, 30);
         bt3.setBounds(550, 530, 100, 30);
         bt5.setBounds(300, 350, 100, 30);
         bt4.setBounds(450, 350, 100, 30);
         tf7.setBounds(300, 530, 200, 30);
         add(l1);
         add(l2);
         add(tf1);
         add(l3);
         add(tf2);
         add(l4);
         add(tf3);
         add(l5);
         add(tf5);
         add(l6);
         add(tf6);
         add(l7);
         add(l8);
         add(tf7);
         add(btn1);
         add(btn2);
         add(bt3);
         add(bt4);
         add(bt5);
         add(p1);
//        panel.add(l1);
//        panel.add(l2); // Components Added using Flow Layout
//        panel.add(l3); // Components Added using Flow Layout
//        panel.add(l5);
//        panel.add(l6);
//        panel.add(l7);
//        panel.add(l8);
//        getContentPane().add(BorderLayout.EAST, panel);
//       setVisible(true);
    }
 
    public void actionPerformed(ActionEvent e) 
     {
        if (e.getSource() == btn1)
         {
            insert();
           
            }
        else if(e.getSource()==bt3)
        {
            search(Integer.parseInt(tf7.getText()));
            
        }
        else if(e.getSource()==bt4)
        {
            nextframe();
        }
        else if(e.getSource()==bt5)
        {
            update(Integer.parseInt(tf7.getText()));
        }
          else
           {           
             clear();
          
        }
     }
    public void insert()
    {
        int x = 0;
             s1 = Integer.parseInt(tf1.getText());
             s2 = tf2.getText();
             s3 = Integer.parseInt(tf3.getText());
             s5 = tf5.getText();
             s6 = Float.parseFloat(tf6.getText());
             char[] pass = p1.getPassword();
             String s8 = new String(pass);
          try
               {
                   //2.Load and register the JDBC driver.Open a connection to the database.
                   // Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/SIES","postgres","123");
                    //3.Create a statement object to perform a query.
                    PreparedStatement ps = con.prepareStatement("insert into company values (?,?,?,?,?,?)");
                    ps.setInt(1, s1);
                    ps.setString(2, s2);
                    ps.setInt(3, s3);
                    ps.setString(4, s5);
                    ps.setFloat(5, s6);
                    ps.setString(6, s8);
                    //4.Execute the statement object
                    ps.executeUpdate();
                    
                        JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
                    
                    clear();
                }
          
          catch (Exception ex) 
                {
                    System.out.println(ex);
                }
    }
    public void search(int id)
    {
        try { 
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/SIES","postgres","123");
            //System.out.println("Connected to the PostgreSQL server successfully.");
            //3.Execute the statement object and return a query resultset.
            stmt = conn.createStatement();
            //4.Process the resultset.
         rs = stmt.executeQuery( "select * from company where ID ="+id);
         if (rs.next() == false) { 
             System.out.println("ResultSet in empty"); 
             JOptionPane.showMessageDialog(bt3, "Data Not Found");}
         else{
            do{
                int i= rs.getInt("id") ;
                tf1.setText(""+i);
                tf2.setText(rs.getString("name"));
                tf3.setText(""+rs.getInt("age"));
                tf5.setText(rs.getString("address"));
                tf6.setText(""+rs.getFloat("salary"));
                p1.setText(rs.getString("password"));
            //tf7.setText(""+rs.getString("pass"));
            //}
         }while ( rs.next() );
         }     
         //5.Close the resultset and statement objects.
         rs.close();
         stmt.close();
         //7.Close the connection.
         conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public void nextframe() 
     {
         this.setVisible(false);    
         new test1();
             
     }
    public void update(int id)
    {
             s1 = Integer.parseInt(tf1.getText());
             s2 = tf2.getText();
             s3 = Integer.parseInt(tf3.getText());
             s5 = tf5.getText();
             s6 = Float.parseFloat(tf6.getText());
            char[] pass = p1.getPassword();
            String s8 = new String(pass);
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/SIES","postgres","123");
            System.out.println("Connected to the PostgreSQL server successfully.");
            stmt = conn.createStatement();

        PreparedStatement ps = conn.prepareStatement("update company set id=?,name=?,age=?,address=?,salary=?,password=? where ID ="+id);
                    ps.setInt(1, s1);
                    ps.setString(2, s2);
                    ps.setInt(3, s3);
                    ps.setString(4, s5);
                    ps.setFloat(5, s6);
                    ps.setString(6, s8); 
                    ps.executeUpdate();
        System.out.println("id"+ id);
         stmt.close();
         conn.close();
            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
                JOptionPane.showMessageDialog(bt5, "Data Saved"); 
           clear();
        
        
        
    }
    public void clear()
    {
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf5.setText("");
            tf6.setText("");tf7.setText("");p1.setText("");
    }
    public static void main(String args[])
   {
       new Registration();
        
    }
    
}








