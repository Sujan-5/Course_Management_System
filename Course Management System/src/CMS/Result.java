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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class Result {
	JFrame frame = new JFrame();
	JTextField tex, text, text1, text2, text3, text4, text5;
	JComboBox combo, combo1, combo2;
//	JTable jTable = new JTable();
	 JTable table = new JTable();
	
	
	Result(){
		result();
	}
	
	void result() {
		frame.setLayout(null);
		frame.setSize(1400,800);
		frame.setTitle("Student Result");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0,204,0));
		frame.setResizable(false);
		ImageIcon header = new ImageIcon("logo.png");
		frame.setIconImage(header.getImage());
		
		
		JButton res = new JButton();
		res.setBounds(380,70,190,30);
		res.setText("Result");
		frame.add(res);
		
		JLabel re = new JLabel("Result Sheet");
		re.setBounds(50,80,170,30);
		re.setFont(new Font("Arial",Font.BOLD,22));
		frame.add(re);
		
		JLabel idd = new JLabel("ID");
		idd.setBounds(60,130,290,30);
		tex = new JTextField();
		tex.setBounds(200,130,100,30);
		frame.add(idd);
		frame.add(tex);
		
		JLabel name = new JLabel("First Name");
		name.setBounds(60,180,290,30);
		text = new JTextField(); //create TextField  for User name
		text.setBounds(200,180,340,30);
		frame.add(name);
		frame.add(text);
		
		JLabel sname = new JLabel("Second Name");
		sname.setBounds(60,230,290,30);
		text1 = new JTextField(); //create TextField  for User name
		text1.setBounds(200,230,340,30);
		frame.add(sname);
		frame.add(text1);
		
		JLabel co = new JLabel("Course");
		co.setBounds(60,280,290,30);
		frame.add(co);
		String[] title= {"bcs","bibm"};
		combo = new JComboBox(title);
	    combo.setBounds(200, 280, 80, 30);
	    frame.add(combo);
	    
	    JLabel level = new JLabel("Level");
		level.setBounds(350,280,290,30);
		frame.add(level);
		String[] le = {"3","4","5","6"};
		combo1 = new JComboBox(le);
	    combo1.setBounds(450, 280, 90, 30);
	    frame.add(combo1);
	    
	    JLabel sem = new JLabel("Semester");
		sem.setBounds(60,330,290,30);
		frame.add(sem);
		String[] seme = {"1","2","3","4","5","6","7","8"};
		combo2 = new JComboBox(seme);
		combo2.setBounds(200, 330, 80, 30);
		frame.add(combo2);

		
		JLabel mcode = new JLabel("Module Code");
		mcode.setBounds(350,330,290,30);
		text2 = new JTextField(); //create TextField  for User name
		text2.setBounds(450,330,90,30);
		frame.add(mcode);
		frame.add(text2);	
	    
		JLabel mtitle = new JLabel("Module Title");
		mtitle.setBounds(60,380,290,30);
		text3 = new JTextField(); //create TextField  for User name
		text3.setBounds(200,380,340,30);
		frame.add(mtitle);
		frame.add(text3);
			
		JLabel marks = new JLabel("Marks Obtained");
		marks.setBounds(60,430,290,30);
		text4 = new JTextField(); //create TextField  for User name
		text4.setBounds(200,430,100,30);
		frame.add(marks);
		frame.add(text4);
		
		JLabel remark = new JLabel("Remarks");
		remark.setBounds(60,480,290,30);
		text5 = new JTextField(); //create TextField  for User name
		text5.setBounds(200,480,340,30);
		frame.add(remark);
		frame.add(text5);
		
		JButton submit = new JButton();
		submit.setText("Submit");
		submit.setBounds(250,550,150,30);
		submit.addActionListener(e -> subconnect());
		frame.add(submit);
		
		JButton but = new JButton();
		but.setText("Back");
		but.setFont(new Font("Arial",Font.BOLD,20));
		but.setBounds(1200,20,100,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	             Admin ad = new Admin();
	              ad.admin();     
	             frame.dispose();
	             ad.setVisible(true);
	             }
		});
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(650,100,600,30);
		panel1.setSize(700,550);
		frame.add(panel1);
		
		String[] column= {"ID","Firstname","Lastname","Course","Level","Semester","ModuleCode","Mark","Remarks"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        JTable table = new JTable();
        table.setModel(model); 
        table.setAutoResizeMode(JTable.FRAMEBITS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scroll.setPreferredSize(new Dimension(630, 500));
        panel1.add(scroll);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		
		
		frame.setVisible(true);
	}
	
	void subconnect() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			
			 String id = tex.getText();
			 String firstname = text.getText();
	         String lastname = text1.getText();
	         String course = combo.getSelectedItem().toString();
	         String level = combo1.getSelectedItem().toString();
	         String semester = combo2.getSelectedItem().toString();
	         String modulecode = text2.getText();
	         String moduletitle = text3.getText();
	         String marks = text4.getText();
	         String remarks = text5.getText();
			
			 connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from grades");
	         boolean b = true;
	         while(RS.next()) {
	                if(firstname.equals(RS.getString("firstname"))&lastname.equals(RS.getString("lastname"))) {
	                	b = false;
	                	break;
	                }
	                }
	         		if(b) {
	         			String a = "INSERT INTO grades(id, firstname, lastname, course, level, semester, modulecode, moduletitle, marks, remarks)"+
	         		               "values('"+id+"','"+firstname+"','"+lastname+"','"+course+"','"+level+"','"+semester+"','"+modulecode+"','"+moduletitle+"','"+marks+"','"+remarks+"')";
	         			PreparedStatement Database=connection.prepareStatement(a);
		                Database.executeUpdate();
		                JOptionPane.showMessageDialog(null, "Sucessfully added","SUCESS",JOptionPane.PLAIN_MESSAGE);
		            }
		            	else {
		                JOptionPane.showMessageDialog(null, "Already exists","ERROR",JOptionPane.ERROR_MESSAGE);
		            	}
		}catch(Exception e) {
			System.out.println("kei ni nai" +e);
		}
		showTableData();
	}
	

	public void showTableData() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from grades");
	         
			table.setModel(DbUtils.resultSetToTableModel(RS));
			
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Result result = new Result();
		

	}

}
