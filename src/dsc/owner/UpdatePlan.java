package dsc.owner;

import java.awt.EventQueue;
import java.awt.event.*;
import java.sql.*;
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
import java.awt.SystemColor;

public class UpdatePlan extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private JTextField txtfacilities;
	private JTextField txtcharge;
	private JTextField txtduration;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePlan frame = new UpdatePlan();
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
	public UpdatePlan() {
		setResizable(false);
		setTitle("Update Plan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 426);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(126, 34, 398, 60);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Update Existing Plan");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(53, 0, 304, 60);
		panel.add(lblNewLabel);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select Plan" }));
		comboBox.addItemListener(this);
		comboBox.setBounds(256, 118, 108, 26);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Facilities");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(160, 170, 90, 20);
		contentPane.add(lblNewLabel_1);

		txtfacilities = new JTextField();
		txtfacilities.setBounds(256, 172, 238, 60);
		contentPane.add(txtfacilities);
		txtfacilities.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Charges");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(160, 255, 90, 20);
		contentPane.add(lblNewLabel_2);

		txtcharge = new JTextField();
		txtcharge.setBounds(256, 257, 90, 18);
		contentPane.add(txtcharge);
		txtcharge.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Duration");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(160, 299, 90, 20);
		contentPane.add(lblNewLabel_3);

		txtduration = new JTextField();
		txtduration.setBounds(256, 301, 121, 20);
		contentPane.add(txtduration);
		txtduration.setColumns(10);

		JButton btnupdate = new JButton("Update");
		btnupdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnupdate.addActionListener(this);
		btnupdate.setIcon(new ImageIcon(UpdatePlan.class.getResource("/dsc/images/icons8-update-15.png")));
		btnupdate.setBounds(205, 339, 114, 26);
		contentPane.add(btnupdate);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(662, 388, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 662, 388);
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
		String selectQuery = "select Plan_name from plan_details";
		try {
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();// this method is called only for select query
			while (rs.next() == true) {
				String planName = rs.getString("Plan_name");// to fetch the value from name column of course_details
															// table
				comboBox.addItem(planName);// add the fetched value into combobox
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
			String planName = (String) comboBox.getSelectedItem();
			if (planName.equalsIgnoreCase("Select Plan")) {
				JOptionPane.showMessageDialog(this, "Please enter valid plan");
			}
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			String select_Query = "select * from plan_details where Plan_name=?";
			try {
				ps = con.prepareStatement(select_Query);
				ps.setString(1, planName);
				rs = ps.executeQuery();
				rs.next();
				String cfacilities = rs.getString("Facilities");// to fetch the values from fees column
				String ccharges = rs.getString("Charges");
				String cduration = rs.getString("Duration");
				txtfacilities.setText(cfacilities);
				txtcharge.setText(ccharges);
				txtduration.setText(cduration);
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
		String facilities = txtfacilities.getText();
		String charges = txtcharge.getText();
		String duration = txtduration.getText();
		String plan_Name = (String) comboBox.getSelectedItem();
		if (facilities.isEmpty() || charges.isEmpty() || duration.isEmpty()
				|| plan_Name.equalsIgnoreCase("Select Plan")) {
			JOptionPane.showMessageDialog(this, "All fields required", "Fields empty", JOptionPane.ERROR_MESSAGE);
		} else {
			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String updateQuery = "update plan_details set Facilities=?, Charges=?, Duration=? where Plan_name=?";
			try {
				ps = con.prepareStatement(updateQuery);
				ps.setString(1, facilities);
				ps.setInt(2, Integer.parseInt(charges));
				ps.setString(3, duration);
				ps.setString(4, plan_Name);
				int result = ps.executeUpdate();
				if (result > 0)
					JOptionPane.showMessageDialog(this, plan_Name + " plan updated successfully");

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
