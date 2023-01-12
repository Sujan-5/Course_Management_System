package CMS;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPage {
	 JTextField textt = new JTextField(20);
	 JPasswordField passwordt = new JPasswordField(20);
	 JFrame f = new JFrame();
	 JComboBox cbox;
	 
	void Login() {
	
	JFrame f = new JFrame(); //creates a frame
	f.setTitle("Course Management System");
	f.setResizable(false);  //fix the size of frame
	f.setSize(800,800);    //set x- and y- dimension of frame
	f.getContentPane().setBackground(new Color(0,204,0));
	
	JLabel herald = new JLabel("Herald College Kathmandu");
	herald.setBounds(30,50,800,60);
	herald.setFont(new Font("Arial",Font.ITALIC,50));
	herald.setForeground(Color.green);
	f.add(herald);
	
	JLabel page = new JLabel("Login Page");
	page.setBounds(30,150,800,60);
	page.setFont(new Font("Arial",Font.ITALIC,30));
	page.setForeground(Color.green);
	f.add(page);
	
	JPanel panel1 = new JPanel();
	panel1.setBackground(java.awt.Color.WHITE);
	panel1.setBounds(0,0,800,50);
	panel1.setSize(800,250);
	panel1.setBackground(new Color(153,153,153));
	f.add(panel1);
	
	//Image icon
	ImageIcon i = new ImageIcon("logo.png");
	f.setIconImage(i.getImage());
	
	//panel
	JPanel p = new JPanel();
	p.setLayout(null);
	p.setBackground(Color.green);
	
	//label user name
	JLabel ul = new JLabel("Username");
	ul.setBounds(200,400,80,25);
	p.add(ul);

	//user name field
	textt.setBounds(300,400,165,25);
	p.add(textt);
	
	
	//label password
	JLabel pl = new JLabel("Password");
	pl.setBounds(200,450,80,25);
	p.add(pl);
	
	//password field
	passwordt.setBounds(300,450,165,25);
	p.add(passwordt);
	
	String[] choose = {"Admin","Student","Instructor"};
	cbox = new JComboBox(choose);
	cbox.setBounds(330,500,80,25);
	f.add(cbox);
	
	JButton b = new JButton("Login");
	b.setBounds(330,550,80,25);
	b.addActionListener(e -> Query());
	
	f.add(p);
	p.add(b);
	f.setVisible(true);     //make frame visible
	}
	
	
	void Query() {
		
        try {
            String name= textt.getText();
            char[] pasword=passwordt.getPassword();
            String s = String.valueOf(pasword);
            String type= cbox.getSelectedItem().toString();

             Connection conn=null;
             PreparedStatement myStatement=null;
             ResultSet myData=null;
             
             String query="SELECT * FROM loginp WHERE username=? and password=? and user=?";
             conn=DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
             myStatement=conn.prepareStatement(query);
             myStatement.setString(1, name.toString());
             myStatement.setString(2, s.toString());
             myStatement.setString(3, String.valueOf(type));
             myData=myStatement.executeQuery();

             if(myData.next()) {

                 if(cbox.getSelectedIndex()==0) {
                	 Admin ad = new Admin();
//                     ad.admin();
                     f.dispose();
                     JOptionPane.showMessageDialog(null,"Login Sucessfully");
                 }
                 
                 else if(cbox.getSelectedIndex()==1) {
                	 Student sd = new Student();
                     sd.student();
                     f.dispose();
                     JOptionPane.showMessageDialog(null,"Login Sucessfully");
                 }
                 
                 else if(cbox.getSelectedIndex()==2) { 
                	 Instructor inst = new Instructor();
             			inst.instructor();
                     f.dispose();
                     JOptionPane.showMessageDialog(null,"Login Sucessfully");
                 }
             }
             
             else {
                 JOptionPane.showMessageDialog(null,"Invalid username and password");
             }
        }
        catch(Exception e) {
            System.out.println("Error \n"+e);
        }
    }


        public static void main (String[] args) {
        	LoginPage lp = new LoginPage();
        	lp.Login();
	
       }
}


