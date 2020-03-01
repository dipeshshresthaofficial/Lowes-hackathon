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
import javax.swing.SwingConstants;
import java.awt.Font;

public class Login {

	private JFrame frame;
	private JTextField mobileField;
	String originalMobile= "8073977621";
	String originalPwd = "admin";
	
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
		initialize();
	}

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
				String mobileEntered= mobileField.getText();
				if(mobileEntered.matches(originalMobile)) {
//					JOptionPane.showMessageDialog(null, "Correct Password..");
					

					
					Dashboard d1= new Dashboard();
					d1.main(null);
				}
				else {
					 
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
