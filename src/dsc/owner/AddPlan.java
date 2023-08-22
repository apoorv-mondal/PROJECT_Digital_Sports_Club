package dsc.owner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dsc.dbinfo.DBConnection_dsc;
import dsc.gui.LoginFrame_dsc;

import java.sql.*;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.SystemColor;

public class AddPlan extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtcharge;
	private JTextField txtduration;
	private JTextArea txtfacilities;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlan frame = new AddPlan();
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
	public AddPlan() {
		setResizable(false);
		setTitle("Add Plan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(202, 21, 217, 53);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New Plan");
		lblNewLabel.setBounds(38, 11, 145, 31);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));

		JLabel lblNewLabel_1 = new JLabel("Plan Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(131, 95, 95, 20);
		contentPane.add(lblNewLabel_1);

		txtname = new JTextField();
		txtname.setBounds(236, 97, 228, 20);
		txtname.addKeyListener(this);
		contentPane.add(txtname);
		txtname.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Facilities");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(131, 126, 95, 20);
		contentPane.add(lblNewLabel_2);

		txtfacilities = new JTextArea();
		txtfacilities.setBounds(236, 128, 348, 77);
		contentPane.add(txtfacilities);

		JLabel lblNewLabel_3 = new JLabel("Charges");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(131, 216, 95, 20);
		contentPane.add(lblNewLabel_3);

		txtcharge = new JTextField();
		txtcharge.setBounds(236, 216, 100, 18);
		txtcharge.addKeyListener(this);
		contentPane.add(txtcharge);
		txtcharge.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Duration");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(131, 247, 95, 20);
		contentPane.add(lblNewLabel_4);

		txtduration = new JTextField();
		txtduration.setBounds(236, 250, 119, 18);
		contentPane.add(txtduration);
		txtduration.setColumns(10);

		JButton btnadd = new JButton("Add");
		btnadd.setIcon(new ImageIcon(AddPlan.class.getResource("/dsc/images/icons8-add-15.png")));
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnadd.setBounds(174, 293, 89, 35);
		btnadd.addActionListener(this);
		contentPane.add(btnadd);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(662, 385, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 662, 385);
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
		String cfacility = txtfacilities.getText();
		String ccharge = txtcharge.getText();
		String cduration = txtduration.getText();
		if (cname.isEmpty() || cfacility.isEmpty() || ccharge.isEmpty() || cduration.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill all fields");
		} else {
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String insertQuery = "insert into plan_details(Plan_name, Facilities, Charges, Duration)values(?,?,?,?)";// ?
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
				ps.setString(2, cfacility);
				ps.setInt(3, Integer.parseInt(ccharge));
				ps.setString(4, cduration);

				System.out.println(ps);// ps has value in the reference query
				int result = ps.executeUpdate();// it will ask DBMS to execute the query
				if (result > 0) {
					JOptionPane.showMessageDialog(this, "Plan added successfully");
					txtname.setText("");
					txtfacilities.setText("");
					txtcharge.setText("");
					txtduration.setText("");
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
