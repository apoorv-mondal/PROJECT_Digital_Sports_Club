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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.sql.*;
import java.awt.SystemColor;

public class UpdateSport extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private JTextField txtcharge;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateSport frame = new UpdateSport();
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
	public UpdateSport() {
		setTitle("Update Sport");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(142, 30, 366, 50);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Update Existing Sport");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(27, 0, 318, 50);
		panel.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.addItemListener(this);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select Sport" }));
		comboBox.setBounds(247, 109, 116, 26);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Charges Per Month");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(44, 173, 163, 20);
		contentPane.add(lblNewLabel_1);

		txtcharge = new JTextField();
		txtcharge.setBounds(217, 172, 95, 26);
		contentPane.add(txtcharge);
		txtcharge.setColumns(10);

		JButton btnupdate = new JButton("Update");
		btnupdate.setIcon(new ImageIcon(UpdateSport.class.getResource("/dsc/images/icons8-update-15.png")));
		btnupdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnupdate.setBounds(120, 209, 116, 33);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(660, 385, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 660, 385);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
		fillCombo();
	}

	public void fillCombo() {
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
				comboBox.addItem(sportName);// add the fetched value into combobox
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
			String sportsName = (String) comboBox.getSelectedItem();
			if (sportsName.equalsIgnoreCase("Select Sport")) {
				JOptionPane.showMessageDialog(this, "Please enter valid sport");
			}
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String select_Query = "select ChargesPerMonth from sports_details where SportsName=?";
			try {
				ps = con.prepareStatement(select_Query);
				ps.setString(1, sportsName);
				rs = ps.executeQuery();
				rs.next();
				String ccharge = rs.getString("ChargesPerMonth");// to fetch the values from fees column

				txtcharge.setText(ccharge);

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
		String charge = txtcharge.getText();

		String sports_Name = (String) comboBox.getSelectedItem();
		if (charge.isEmpty() || sports_Name.equalsIgnoreCase("Select Sport")) {
			JOptionPane.showMessageDialog(this, "All fields required", "Fields empty", JOptionPane.ERROR_MESSAGE);
		} else {
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String updateQuery = "update sports_details set ChargesPerMonth=? where SportsName=?";
			try {
				ps = con.prepareStatement(updateQuery);
				ps.setInt(1, Integer.parseInt(charge));

				ps.setString(2, sports_Name);
				int result = ps.executeUpdate();
				if (result > 0)
					JOptionPane.showMessageDialog(this, sports_Name + " sport updated successfully");

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
