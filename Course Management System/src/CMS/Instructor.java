package CMS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

//import java.awt.*;

public class Instructor {
	
	JFrame frame = new JFrame();
	JPanel panel1 = new JPanel();
	JTextField text, text1;
	JTable table = new JTable();
	JComboBox cbox = new JComboBox();
	JLabel id = new JLabel("Instrutor detail");
	JLabel tn = new JLabel("ID");
	JButton submit = new JButton();
	JLabel idd = new JLabel("ID");
	JLabel en = new JLabel("See Students Enrolled");
	JButton but = new JButton();
	
	void instructor() {
		frame.setLayout(null);
		frame.setSize(1400,800);
		frame.setTitle("INSTRUCTOR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0,204,0));
		frame.setResizable(false);
		ImageIcon headerimg = new ImageIcon("instructor.png");
		frame.setIconImage(headerimg.getImage());
		
		
		
		
		id.setBounds(30,130,170,30);
		id.setFont(new Font("Arial",Font.BOLD,20));
		frame.add(id);
		
		
		tn.setBounds(50,180,290,30);
		text = new JTextField(); //create TextField  for User name
		text.setBounds(150,180,300,30);
		frame.add(tn);
		frame.add(text);
		
		
		submit.setText("SUBMIT");
		submit.setBounds(300,230,150,30);
		submit.addActionListener(e -> connect());
		frame.add(submit);
		
		JLabel toresult = new JLabel("Result Segment");
		toresult.setBounds(30,300,290,30);
		toresult.setFont(new Font("Arial",Font.BOLD,20));
		frame.add(toresult);
		
		
		idd.setBounds(50,350,100,30);
		frame.add(idd);
		text1 = new JTextField();
		text1.setBounds(150,350,100,30);
		frame.add(text1);
		
//		JLabel choose = new JLabel("Select the Course: ");
//		choose.setBounds(50,400,200,30);
//		frame.add(choose);
//		String[] bb = {"bcs","bibm"};
//		cbox = new JComboBox(bb);
//		cbox.setBounds(300,350,100,30);
//		frame.add(cbox);
		
		
		en.setBounds(50,530,300,30);
		en.setFont(new Font("Arial",Font.BOLD,18));
		frame.add(en);
		
		JButton student = new JButton();
		student.setText("Students Enrolled");
		student.setBounds(50,570,190,30);
		frame.add(student);
		student.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	        	   Enrolled teacher = new Enrolled();
	               teacher.enrolled();     
	               frame.dispose();}
		});
		
		
		JButton see = new JButton();
		see.setText("ADD RESULT");
		see.setBounds(130,400,120,30);
		see.addActionListener(e -> grades());
		frame.add(see);
	
		
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(780,100,600,30);
		panel1.setSize(600,600);
		frame.add(panel1);
		
		
		
		but.setText("LogOut");
		but.setFont(new Font("Arial",Font.BOLD,15));
		but.setBounds(1200,15,100,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              LoginPage ad = new LoginPage();
	              ad.Login();     
	             frame.dispose();
	             }
		});
		
		String[] column= {"ID","Teachername","moduletitle","modulecode","course","level"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        table.setModel(model); 
        table.setAutoResizeMode(JTable.FRAMEBITS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scroll.setPreferredSize(new Dimension(550, 550));
        panel1.add(scroll);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		frame.setVisible(true);
	}
	
	void connect() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			String id = text.getText();
			
			 connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from instructor");
	         while(RS.next()) {
	        	 if(id.equals(RS.getString("id"))){
	        		 break;
	        	 }
	        	 else {
	        		 String a = "SELECT * FROM instructor WHERE id = "+"'"+id+"'";
	        		 PreparedStatement Database=connection.prepareStatement(a);
	        		 Database.executeUpdate();
//	        		 table.setModel(DbUtils.resultSetToTableModel(RS));
	        		 table.setModel(DbUtils.resultSetToTableModel(RS));
		             JOptionPane.showMessageDialog(null, "Sucessfully added","SUCESS",JOptionPane.PLAIN_MESSAGE);
	        	 }
	         }
			
		}catch(Exception e) {
			System.out.println("kei ni nai"+e);
		}
//		showTableData();
	}
	
	void grades() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			 String ID = text1.getText();
			 connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from instructor");
	         while(RS.next()) {
	        	 if(ID.equals(RS.getString("id"))) {
	        		 Result ad = new Result();
	        		 ad.result();
	        		 frame.dispose();
	        		 JOptionPane.showMessageDialog(null, "Let's go","SUCESS",JOptionPane.PLAIN_MESSAGE);
	        		 
	        	 }
	        	 else {
//	        		 JOptionPane.showMessageDialog(null, "Not found in database","ERROR",JOptionPane.ERROR_MESSAGE);
	        		
	        	 }
	         }
			
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}
	
//	public void showTableData() {
//		Connection connection;
//		Statement MS;
//		ResultSet RS;
//		try {
//			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
//	         MS = connection.createStatement();
//	         RS = MS.executeQuery("select * from instructor");
//	         
//			
//			
//		}catch(Exception e) {
//			System.out.println("error"+e);
//		}
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Instructor inst = new Instructor();
		inst.instructor();
		inst.connect();

	}

}
