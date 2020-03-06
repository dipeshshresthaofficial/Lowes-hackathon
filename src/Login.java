import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField mobileField;
	String mobileEntered="";
	String originalPwd = "admin";
	String dbFname="";
	String dbMobileNo="";
	PreparedStatement prepStmt=null;
	boolean result;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		BasicConfigurator.configure();
		
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
//		Database();
		initialize();
	}

//	private void Database() {
//		// TODO Auto-generated method stub
//		try{  
//			Class.forName("org.sqlite.JDBC");  
//			Connection con=DriverManager.getConnection( 
//			"jdbc:sqlite:C:\\Users\\Dipesh Shrestha\\eclipse-workspace\\Project3 working copy\\Database\\lowes.db");  
//			//here dipesh is database name, root is username and password  
//			Statement stmt=con.createStatement();  
//			ResultSet rs1=stmt.executeQuery("create table if not exists customer(F_name varchar(20),L_name varchar(20),Mobile varchar(10))");  
//			ResultSet rs2=stmt.executeQuery("select mobile,fname from customer where mobile=");
//
////			while(rs.next())  
////			System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
//			con.close();  
//			}catch(Exception e){ System.out.println(e);}  
//  
//		
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 540, 283);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel mobileLabel = new JLabel("Enter Your Mobile Number");
		mobileLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		mobileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mobileLabel.setBackground(Color.LIGHT_GRAY);
		mobileLabel.setBounds(279, 51, 204, 36);
		frame.getContentPane().add(mobileLabel);
		
		mobileField = new JTextField();
		mobileField.setBounds(290, 98, 187, 29);
		frame.getContentPane().add(mobileField);
		mobileField.setColumns(10);
		
		JButton fastPayBtn = new JButton("FAST PAY");
		fastPayBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fastPayBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mobileEntered= mobileField.getText();
				System.out.println(mobileEntered);

//				checking if the mobile number entered only contains numbers or not through REGULAR EXPRESSION
				if(!((mobileEntered.matches("[0-9]+") && mobileEntered.length()==10))){
					JOptionPane.showMessageDialog(null, "Please enter valid mobile number");
				}
				else {
					
					try{  
						
						Class.forName("org.sqlite.JDBC");  
						Connection con=DriverManager.getConnection( 
						"jdbc:sqlite:./Database/lowes.db");  
						//here dipesh is database name, root is username and password  
						Statement stmt=con.createStatement();  
//						ResultSet rs1=stmt.executeQuery("create table if not exists customer(F_name varchar(20),L_name varchar(20),Mobile varchar(10))");  
						
//Using BIND VAIRABLE concept for accessing values from database through a specific mobile number present in a VARIABLE
						String select="select * from customer where Mobile=?";
						prepStmt = con.prepareStatement(select);
						prepStmt.setString(1,mobileEntered);
						ResultSet rs2=prepStmt.executeQuery();
						
//Retrived database values are put into the variables and displaying				
						while(rs2.next())
						{
							dbFname=rs2.getString("F_name");
							String lname=rs2.getString("L_name");
							dbMobileNo=rs2.getString("Mobile");
							System.out.println(dbFname+" "+lname+"  "+dbMobileNo);
						}
							  
						con.close();  
						}catch(Exception e1){ System.out.println(e1);}  
					
					if(mobileEntered.matches(dbMobileNo)) {
//						JOptionPane.showMessageDialog(null, "Correct Password..");
						
						frame.dispose();
						Dashboard d1= new Dashboard();
						d1.main(null);
					}
					else {
						 
						NewCustomer n1=new NewCustomer(mobileEntered);
						n1.main(mobileEntered);
					}
				}
				
			}
		});
		fastPayBtn.setBounds(330, 140, 100, 42);
		frame.getContentPane().add(fastPayBtn);
		
		JLabel logoImgLabel = new JLabel("");
		logoImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		Image img = new ImageIcon(this.getClass().getResource("/lowes.png")).getImage();
		Image modifiedImg = img.getScaledInstance(300, 250, java.awt.Image.SCALE_SMOOTH);
		
		logoImgLabel.setIcon(new ImageIcon(modifiedImg));
		logoImgLabel.setBounds(7, 38, 262, 132);
		frame.getContentPane().add(logoImgLabel);
	}
}
