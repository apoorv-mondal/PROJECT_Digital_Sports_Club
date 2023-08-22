package dsc.owner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dsc.dbinfo.DBConnection_dsc;
import dsc.gui.LoginFrame_dsc;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.sql.*;
import java.awt.event.*;
import java.awt.SystemColor;

public class AddSport extends JFrame implements ActionListener,KeyListener {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txttype;
	private JTextField txtcharge;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSport frame = new AddSport();
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
	public AddSport() {
		setTitle("Add Sport");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 677, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(179, 22, 260, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Sport");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(53, 0, 186, 46);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sports Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(148, 109, 111, 20);
		contentPane.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setBounds(269, 111, 141, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sports Type");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(148, 165, 111, 20);
		contentPane.add(lblNewLabel_2);
		
		txttype = new JTextField();
		txttype.addKeyListener(this);
		txttype.setBounds(269, 167, 121, 20);
		contentPane.add(txttype);
		txttype.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Charge Per Month");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(103, 220, 156, 20);
		contentPane.add(lblNewLabel_3);
		
		txtcharge = new JTextField();
		txtcharge.addKeyListener(this);
		txtcharge.setBounds(269, 222, 86, 20);
		contentPane.add(txtcharge);
		txtcharge.setColumns(10);
		
		JButton btnadd = new JButton("Add");
		btnadd.addActionListener(this);
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnadd.setIcon(new ImageIcon(AddSport.class.getResource("/dsc/images/icons8-add-15.png")));
		btnadd.setBounds(227, 277, 86, 32);
		contentPane.add(btnadd);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(661, 385, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 661, 385);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		// System.out.println("Key typed "+c);
		if (e.getSource() == txtname) {
			if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_SPACE)) {
				e.consume();// it will consume the event
				JOptionPane.showMessageDialog(this, "Only alphabets are allowed", " Data Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == txttype) {
			if (!(Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_SPACE)) {
				e.consume();// it will consume the event
				JOptionPane.showMessageDialog(this, "Only alphabets are allowed", " Data Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == txtcharge) {
			if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE)) {
				e.consume();
				JOptionPane.showMessageDialog(this, "Only digits are allowed", "Data Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cname = txtname.getText();
		String ctype = txttype.getText();
		String ccharge = txtcharge.getText();
		
		if (cname.isEmpty() || ctype.isEmpty() || ccharge.isEmpty() ) {
			JOptionPane.showMessageDialog(this, "Please fill all fields");
		} else {
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String insertQuery = "insert into sports_details(SportsName, SportsType, ChargesPerMonth)values(?,?,?)";// ?
																														// is
																														// known
																														// as
																														// place
			// holders
			try {
				ps = con.prepareStatement(insertQuery);// it prepares the query by passing it to RDBMS
				// and RDBMS compiler/parser will compile the query and give the reference of
				// that compiled
				// query to ps
				ps.setString(1, cname);
				ps.setString(2, ctype);
				ps.setInt(3, Integer.parseInt(ccharge));
				

				System.out.println(ps);// ps has value in the reference query
				int result = ps.executeUpdate();// it will ask DBMS to execute the query
				if (result > 0) {
					JOptionPane.showMessageDialog(this, "Sport added successfully");
					txtname.setText("");
					txttype.setText("");
					txtcharge.setText("");
					
				}
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(this, cname + " plan already exists", "Duplicate value error",
						JOptionPane.ERROR_MESSAGE);
				se.printStackTrace();
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			} // finally closed
		}
	}

}
