package CMS;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class SResult {
	
	JFrame frame = new JFrame();
	JTable table = new JTable();
	JPanel panel1 = new JPanel();
	
	SResult(){
		sresult();
	}
	
	void sresult() {
		frame.setLayout(null);
		frame.setSize(1000,800);
		frame.setTitle("Student Result");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0,204,0));
		frame.setResizable(false);
		
		ImageIcon headerimg = new ImageIcon("logo.png");
		frame.setIconImage(headerimg.getImage());
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(50,50,600,30);
		panel1.setSize(900,550);
		frame.add(panel1);
		
		JButton click = new JButton();
		click.setText("CLICK ME");
		click.setBounds(300,650,300,50);
		click.addActionListener(e -> marks());
		frame.add(click);
		
		String[] column= {"ID","Firstname","Lastname","Course","Level","Semester","ModuleCode","Mark","Remarks"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        table.setModel(model); 
        table.setBounds(890,110,500,30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(850, 500));
        panel1.add(scroll);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		
		
		frame.setVisible(true);
	}
	
	void marks() {
		Connection connection = null;
		Statement MS = null;
		ResultSet RS = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
            MS = connection.createStatement();
            RS = MS.executeQuery("select * from student");
            while(RS.next()) {
            	String inst = "select * from grades";
            	PreparedStatement Database=connection.prepareStatement(inst);
                Database.executeQuery();
                

            }
		}catch(Exception e) {
			System.out.println("error"+e);
		}finally {
			try {
				MS.close();
				RS.close();
				connection.close();
			}catch(Exception e) {
				System.out.println("error"+e);
			}
			showTableData();
		}
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
		SResult sr = new SResult();
	}

}
