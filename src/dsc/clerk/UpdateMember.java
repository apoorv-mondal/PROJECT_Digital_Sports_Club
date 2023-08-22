package dsc.clerk;

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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.sql.*;
import java.awt.SystemColor;

public class UpdateMember extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private JTextField txtemail;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtoccupation;
	private JComboBox<String> cbplan, cbsport, comboBox;
	private JLabel lblimg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMember frame = new UpdateMember();
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
	public UpdateMember() {
		setTitle("Update Member");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 679, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(134, 25, 383, 44);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Update Existing Member");
		lblNewLabel.setBounds(24, 0, 338, 44);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));

		comboBox = new JComboBox();
		comboBox.addItemListener(this);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select Member" }));
		comboBox.setBounds(244, 93, 135, 26);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(156, 141, 63, 20);
		contentPane.add(lblNewLabel_1);

		txtemail = new JTextField();
		txtemail.setBounds(229, 143, 256, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(141, 171, 73, 22);
		contentPane.add(lblNewLabel_2);

		txtaddress = new JTextField();
		txtaddress.setBounds(229, 174, 203, 20);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(156, 202, 58, 22);
		contentPane.add(lblNewLabel_3);

		txtphone = new JTextField();
		txtphone.setBounds(229, 205, 135, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Occupation");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(117, 235, 97, 22);
		contentPane.add(lblNewLabel_5);

		txtoccupation = new JTextField();
		txtoccupation.setBounds(229, 236, 122, 20);
		contentPane.add(txtoccupation);
		txtoccupation.setColumns(10);

		cbplan = new JComboBox();
		cbplan.addItemListener(this);
		cbplan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbplan.setModel(new DefaultComboBoxModel(new String[] { "Plan" }));
		cbplan.setBounds(134, 268, 103, 25);
		contentPane.add(cbplan);

		cbsport = new JComboBox();
		cbsport.addItemListener(this);
		cbsport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbsport.setModel(new DefaultComboBoxModel(new String[] { "Sport" }));
		cbsport.setBounds(265, 267, 99, 26);
		contentPane.add(cbsport);

		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setIcon(new ImageIcon(UpdateMember.class.getResource("/dsc/images/icons8-update-15.png")));
		btnupdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnupdate.setBounds(197, 312, 109, 34);
		contentPane.add(btnupdate);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(663, 387, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 663, 387);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
		fillCombo();
		fillCombo1();
		fillCombo2();
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

	public void fillCombo2() {
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

	public void fillCombo() {
		Connection con = DBConnection_dsc.openConnection();
		PreparedStatement ps = null;// will hold compiled query reference
		ResultSet rs = null;// will hold resultant dataset reference
		// String selectQuery = "select * from course_details";// * means all columns
		// with all records(rows)
		String selectQuery = "select MemberName from member_details";
		try {
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();// this method is called only for select query
			while (rs.next() == true) {
				String memberName = rs.getString("MemberName");// to fetch the value from name column of course_details
																// table
				comboBox.addItem(memberName);// add the fetched value into combobox
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
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int state = e.getStateChange();
		if (state == 1) {
			String memberName = (String) comboBox.getSelectedItem();
			if (memberName.equalsIgnoreCase("Select Member")) {
				JOptionPane.showMessageDialog(this, "Please select a member");
			}
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String select_Query = "select * from member_details where MemberName=?";
			try {
				ps = con.prepareStatement(select_Query);
				ps.setString(1, memberName);
				rs = ps.executeQuery();
				rs.next();
				String cemail = rs.getString("Email");// to fetch the values from fees column
				String caddress = rs.getString("Address");
				String cphone = rs.getString("Phone");

				String coccupation = rs.getString("Occupation");
				txtemail.setText(cemail);
				txtaddress.setText(caddress);
				txtphone.setText(cphone);

				txtoccupation.setText(coccupation);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String email = txtemail.getText();
		String address = txtaddress.getText();
		String phone = txtphone.getText();
		String occupation = txtoccupation.getText();
		String member_Name = (String) comboBox.getSelectedItem();
		String plan = (String) cbplan.getSelectedItem();
		String sport = (String) cbsport.getSelectedItem();
		if (email.isEmpty() || address.isEmpty() || phone.isEmpty() || occupation.isEmpty()
				|| cbplan.getSelectedItem() == "Plan" || cbsport.getSelectedItem() == "Sport"
				|| member_Name.equalsIgnoreCase("Select Member")) {
			JOptionPane.showMessageDialog(this, "All fields required", "Fields empty", JOptionPane.ERROR_MESSAGE);
		} else {
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String updateQuery = "update member_details set Email=?, Address=?, Phone=?, Occupation=?, Plan_name=?, SportsName=? where MemberName=?";
			try {
				ps = con.prepareStatement(updateQuery);
				ps.setString(1, email);
				ps.setString(2, address);
				ps.setString(3, phone);
				ps.setString(4, occupation);
				ps.setString(5, plan);
				ps.setString(6, sport);
				ps.setString(7, member_Name);
				int result = ps.executeUpdate();
				if (result > 0)
					JOptionPane.showMessageDialog(this, member_Name + " member updated successfully");

			} catch (SQLException se) {
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
			}
		}
	}
}
