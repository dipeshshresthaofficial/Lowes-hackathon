import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewCustomer {

	private JFrame frame;
	private JTextField newCustFname;
	private JTextField newCustLname;
	String custFname="",custLname="";

	/**
	 * Launch the application.
	 */
	public static void main(String mobileEntered) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer window = new NewCustomer(mobileEntered);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param mobileEntered 
	 */
	public NewCustomer(String mobileEntered) {
		initialize(mobileEntered);
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String mobileEntered) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel newCustFnameLabel = new JLabel("First Name");
		newCustFnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newCustFnameLabel.setBounds(62, 77, 113, 25);
		frame.getContentPane().add(newCustFnameLabel);
		
		JLabel newCustLnameLabel = new JLabel("Last Name");
		newCustLnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newCustLnameLabel.setBounds(62, 140, 113, 25);
		frame.getContentPane().add(newCustLnameLabel);
		
		newCustFname = new JTextField();
		newCustFname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				custFname=newCustFname.getText();
			}
		});
		newCustFname.setBounds(198, 79, 158, 25);
		frame.getContentPane().add(newCustFname);
		newCustFname.setColumns(10);
		
		newCustLname = new JTextField();
		newCustLname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				custLname=newCustLname.getText();
			}
		});
		newCustLname.setColumns(10);
		newCustLname.setBounds(198, 142, 158, 25);
		frame.getContentPane().add(newCustLname);
		
		JButton signUpBtn = new JButton("Done");
		signUpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 
				try{  
					Class.forName("org.sqlite.JDBC");  
					Connection con=DriverManager.getConnection( 
					"jdbc:sqlite:C:\\Users\\Dipesh Shrestha\\eclipse-workspace\\Project3 working copy\\Database\\lowes.db");  
					//here dipesh is database name, root is username and password  
					Statement stmt=con.createStatement();  
					//ResultSet rs1=stmt.executeQuery("create table if not exists customer(F_name varchar(20),L_name varchar(20),Mobile varchar(10))");  
					
					ResultSet rs=stmt.executeQuery("insert into customer values('"+custFname+"','"+custLname+"','"+mobileEntered+"')");
//					while(rs.next())  
//					System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
					con.close();  
					
					}catch(Exception e1){ System.out.println(e1);}
				
					JOptionPane.showMessageDialog(null, "Thank you, "+custFname+" Now you can login.");
					frame.dispose();
					}  
			
		});
		signUpBtn.setBounds(136, 197, 113, 32);
		frame.getContentPane().add(signUpBtn);
	}
}
