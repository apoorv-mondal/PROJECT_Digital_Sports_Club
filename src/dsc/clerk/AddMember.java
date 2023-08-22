package dsc.clerk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.*;

import com.toedter.calendar.JDateChooser;

import dsc.dbinfo.DBConnection_dsc;
import dsc.gui.LoginFrame_dsc;

import java.awt.BorderLayout;
import java.awt.SystemColor;

public class AddMember extends JFrame implements ActionListener, KeyListener, ItemListener {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtgender;
	private JTextField txtoccupation;
	private JComboBox<String> cbplan, cbsport;
	private JDateChooser dc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
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
	public AddMember() {
		setTitle("Add Member");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(175, 11, 263, 32);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New Member");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(56, 0, 207, 32);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Member Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(35, 54, 125, 20);
		contentPane.add(lblNewLabel_1);

		txtid = new JTextField();
		txtid.setBounds(175, 54, 142, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Member Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(35, 85, 125, 20);
		contentPane.add(lblNewLabel_2);

		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setBounds(175, 86, 222, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(35, 116, 125, 20);
		contentPane.add(lblNewLabel_3);

		txtemail = new JTextField();
		txtemail.setBounds(175, 118, 268, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(35, 147, 125, 20);
		contentPane.add(lblNewLabel_4);

		txtaddress = new JTextField();
		txtaddress.setBounds(175, 149, 296, 20);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Phone");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(35, 178, 125, 20);
		contentPane.add(lblNewLabel_5);

		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(175, 180, 179, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Gender");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setBounds(35, 209, 125, 20);
		contentPane.add(lblNewLabel_6);

		txtgender = new JTextField();
		txtgender.setBounds(175, 211, 125, 20);
		contentPane.add(txtgender);
		txtgender.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("DOB");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setBounds(35, 237, 62, 20);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Occupation");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8.setBounds(35, 268, 104, 17);
		contentPane.add(lblNewLabel_8);

		txtoccupation = new JTextField();
		txtoccupation.setBounds(175, 268, 167, 20);
		contentPane.add(txtoccupation);
		txtoccupation.setColumns(10);

		cbplan = new JComboBox();
		cbplan.addItemListener(this);

		cbplan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbplan.setModel(new DefaultComboBoxModel(new String[] { "Plan" }));
		cbplan.setBounds(93, 299, 91, 31);
		contentPane.add(cbplan);

		cbsport = new JComboBox();
		cbsport.addItemListener(this);

		cbsport.setModel(new DefaultComboBoxModel(new String[] { "Sport" }));
		cbsport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbsport.setBounds(213, 299, 102, 31);
		contentPane.add(cbsport);

		JButton btnadd = new JButton("Add");
		btnadd.addActionListener(this);
		btnadd.setIcon(new ImageIcon(AddMember.class.getResource("/dsc/images/icons8-add-15.png")));
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnadd.setBounds(156, 341, 97, 35);
		contentPane.add(btnadd);

		dc = new JDateChooser();
		dc.setDateFormatString("yyyy-MM-dd");
		dc.setBounds(175, 242, 195, 20);
		contentPane.add(dc);

		JLabel lblNewLabel_9 = new JLabel("Select Date");
		dc.add(lblNewLabel_9, BorderLayout.WEST);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(664, 387, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 664, 387);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
		
		
		fillCombo();
		fillCombo1();
	}

	public void fillCombo1() {
		Connection con = DBConnection_dsc.openConnection();
		PreparedStatement ps = null;// will hold compiled query reference
		ResultSet rs = null;// will hold resultant dataset reference
		// String selectQuery = "select * from course_details";// * means all columns
		// with all records(rows)
		String selectQuery = "select SportsName from sports_details";
		try {
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();// this method is called only for select query
			while (rs.next() == true) {
				String sportName = rs.getString("SportsName");// to fetch the value from name column of course_details
																// table
				cbsport.addItem(sportName);// add the fetched value into combobox
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void fillCombo() {
		Connection con = DBConnection_dsc.openConnection();
		PreparedStatement ps = null;// will hold compiled query reference
		ResultSet rs = null;// will hold resultant dataset reference
		// String selectQuery = "select * from course_details";// * means all columns
		// with all records(rows)
		String selectQuery = "select Plan_name from plan_details";
		try {
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();// this method is called only for select query
			while (rs.next() == true) {
				String planName = rs.getString("Plan_name");// to fetch the value from name column of course_details
															// table
				cbplan.addItem(planName);// add the fetched value into combobox
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
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
		if (e.getSource() == txtphone) {
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
		String id = txtid.getText();
		String name = txtname.getText();
		String email = txtemail.getText();
		String address = txtaddress.getText();
		String phone = txtphone.getText();
		String gender = txtgender.getText();
		String occupation = txtoccupation.getText();
		String plan = (String) cbplan.getSelectedItem();// fetch the value from combo box
		String sport = (String) cbsport.getSelectedItem();
		

		if (id.isEmpty() || name.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty()
				|| gender.isEmpty() || occupation.isEmpty() || plan.equalsIgnoreCase("Plan") || sport.equalsIgnoreCase("Sport")
				||dc.getDate()==null) {
			JOptionPane.showMessageDialog(this, "All fields required ");
		} else {
			if (phone.length() != 10) {
				JOptionPane.showMessageDialog(this, "Phone number must be of 10 digits");
			}
			java.util.Date d1 = new java.util.Date();
			long dt1 = d1.getTime();
			java.sql.Date dateofMembership = new java.sql.Date(dt1);

			Calendar c = Calendar.getInstance();
			c.setTime(d1);
			c.add(Calendar.YEAR, 1);
			java.util.Date newDate = c.getTime();
			long dt2 = newDate.getTime();
			java.sql.Date dateOfExpiry = new java.sql.Date(dt2);

			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String insertQuery = "insert into member_details(Memberid, MemberName, Email, Address, Phone, Gender, DOB, Occupation, Plan_name, dateOfMembership, dateOfExpiry, SportsName)values(?,?,?,?,?,?,?,?,?,?,?,?)";// ?
			// is
			// known
			// as
			// place
			// holders
			try {
				java.util.Date d = dc.getDate();
				long dt = d.getTime();
				java.sql.Date sqlDate = new java.sql.Date(dt);
				ps = con.prepareStatement(insertQuery);// it prepares the query by passing it to RDBMS
				// and RDBMS compiler/parser will compile the query and give the reference of
				// that compiled
				// query to ps
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, address);
				ps.setString(5, phone);
				ps.setString(6, gender);
				ps.setDate(7, sqlDate);
				ps.setString(8, occupation);
				ps.setString(9, plan);
				ps.setDate(10, dateofMembership);
				ps.setDate(11, dateOfExpiry);
				ps.setString(12, sport);
				System.out.println(ps);// ps has value in the reference query
				int result = ps.executeUpdate();// it will ask DBMS to execute the query
				if (result > 0) {
					JOptionPane.showMessageDialog(this, "Member added successfully");
					txtid.setText("");
					txtname.setText("");
					txtemail.setText("");
					txtaddress.setText("");
					txtphone.setText("");
					txtgender.setText("");
					txtoccupation.setText("");
					cbplan.setSelectedIndex(0);
					cbsport.setSelectedIndex(0);
					dc.setDate(null);
				}
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(this, phone + " phone number already exists", "Duplicate value error",
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}