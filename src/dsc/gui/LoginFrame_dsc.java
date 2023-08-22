package dsc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dsc.clerk.ClerkFrame;
import dsc.owner.OwnerFrame;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import java.awt.event.*;
import javax.swing.JPasswordField;

public class LoginFrame_dsc extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtuserid;
	private final ButtonGroup roles = new ButtonGroup();
	private JRadioButton rdowner, rdclerk; // initially stored null
	private JPasswordField userpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame_dsc frame = new LoginFrame_dsc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame_dsc() {
		setResizable(false);
		setTitle("Login Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 427);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtuserid = new JTextField();
		txtuserid.setBackground(SystemColor.text);
		txtuserid.setBounds(246, 129, 102, 23);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);

		rdowner = new JRadioButton("Owner");
		rdowner.setBackground(SystemColor.text);
		roles.add(rdowner);
		rdowner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdowner.setBounds(111, 207, 89, 23);
		contentPane.add(rdowner);

		rdclerk = new JRadioButton("Clerk");
		rdclerk.setBackground(SystemColor.text);
		roles.add(rdclerk);
		rdclerk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdclerk.setBounds(246, 207, 95, 23);
		contentPane.add(rdclerk);

		JButton btnsubmit = new JButton("SUBMIT");
		btnsubmit.setBackground(SystemColor.text);
		btnsubmit.setIcon(new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/icons8-tick-box-15.png")));
		btnsubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnsubmit.setBounds(166, 254, 111, 35);
		btnsubmit.addActionListener(this);
		contentPane.add(btnsubmit);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);
		panel.setBounds(111, 129, 89, 23);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("User-id :");
		lblNewLabel.setBounds(10, 0, 81, 23);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(111, 166, 111, 23);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setBounds(10, 0, 101, 23);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.setBackground(new Color(255, 250, 205));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));

		/*
		 * ImageIcon ic = new
		 * ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/1.jpg")); Image i2 =
		 * ic.getImage().getScaledInstance(661, 389, Image.SCALE_DEFAULT); ImageIcon ic1
		 * = new ImageIcon(i2);
		 */

		userpass = new JPasswordField();
		userpass.setBackground(SystemColor.text);
		userpass.setBounds(246, 163, 187, 23);
		contentPane.add(userpass);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(111, 68, 232, 40);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
				JLabel lblNewLabel_2 = new JLabel("User Login");
				lblNewLabel_2.setBounds(38, 0, 161, 40);
				panel_2.add(lblNewLabel_2);
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 32));

		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/5.jpg"));
		Image i2 = ic.getImage().getScaledInstance(661, 389, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 661, 389);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);

		// ImageIcon ic = new
		// ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/1.jpg"));
		// Image i2 = ic.getImage().getScaledInstance(661, 389, Image.SCALE_DEFAULT);
		// ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblbgimage = new JLabel("");
		lblbgimage.setBounds(-140, -158, 661, 387);
		// lblbgimage.setIcon(ic1);
		// panel_1.add(lblbgimage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txtuserid.getText();// to fetch the value from textfield
		char[] pwd = userpass.getPassword();// to fetch value from password field
		String password = String.valueOf(pwd);
		String userRole = "";
		if (rdowner.isSelected() == true) {
			userRole = rdowner.getText();// to fetch value from radio button
		}
		if (rdclerk.isSelected() == true) {
			userRole = rdclerk.getText();
		}
		if (id.isEmpty() || password.isEmpty() || userRole.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please provide id , password and role");
		} else {
			if (id.equalsIgnoreCase("apoorv") && password.equals("Lucknow") && userRole.equalsIgnoreCase("Owner")) {
				// System.out.println("Hello Admin");
				OwnerFrame frame = new OwnerFrame();
				frame.setVisible(true);
				this.dispose();// to close the login frame
			} else if (id.equalsIgnoreCase("aman") && password.equals("123") && userRole.equalsIgnoreCase("Clerk")) {
				// System.out.println("Hello Counsellor");
				ClerkFrame frame = new ClerkFrame();
				frame.setVisible(true);
				this.dispose();// to close the login frame
			} else {
				JOptionPane.showMessageDialog(this, "Invalid credentials", "Data Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
