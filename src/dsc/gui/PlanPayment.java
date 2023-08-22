package dsc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dsc.dbinfo.DBConnection_dsc;

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
import java.sql.*;
import java.util.Calendar;
import java.awt.event.*;
import java.awt.SystemColor;

public class PlanPayment extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private JTextField txtid;
	private JComboBox<String> cbplan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanPayment frame = new PlanPayment();
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
	public PlanPayment() {
		setTitle("Plan Payment");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 674, 426);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(84, 54, 465, 49);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Plan Payment Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(86, 0, 413, 49);
		panel.add(lblNewLabel);

		cbplan = new JComboBox();
		cbplan.addItemListener(this);
		cbplan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbplan.setModel(new DefaultComboBoxModel(new String[] { "Plan Name" }));
		cbplan.setBounds(231, 145, 128, 31);
		contentPane.add(cbplan);

		JLabel lblNewLabel_1 = new JLabel("Member Id-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(119, 193, 102, 35);
		contentPane.add(lblNewLabel_1);

		txtid = new JTextField();
		txtid.setBounds(231, 197, 153, 31);
		contentPane.add(txtid);
		txtid.setColumns(10);

		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this);
		btnsubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnsubmit.setIcon(new ImageIcon(PlanPayment.class.getResource("/dsc/images/icons8-tick-box-15.png")));
		btnsubmit.setBounds(231, 249, 128, 31);
		contentPane.add(btnsubmit);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(658, 388, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 658, 388);
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
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txtid.getText();
		String plan = (String) cbplan.getSelectedItem();// fetch the value from combo box

		if (id.isEmpty() || plan.equalsIgnoreCase("Plan Name")) {
			JOptionPane.showMessageDialog(this, "All fields required ");
		} else {

			java.util.Date d1 = new java.util.Date();
			long dt1 = d1.getTime();
			java.sql.Date dateofPayment = new java.sql.Date(dt1);

			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String insertQuery = "insert into plan_payment_details(Plan_name, member_id, Date_of_Payment)values(?,?,?)";// ?
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
				ps.setString(1, plan);
				ps.setString(2, id);
				ps.setDate(3, dateofPayment);

				System.out.println(ps);// ps has value in the reference query
				int result = ps.executeUpdate();// it will ask DBMS to execute the query
				if (result > 0) {
					JOptionPane.showMessageDialog(this, "Plan Payment Detail added successfully");
					txtid.setText("");

					cbplan.setSelectedIndex(0);

				}
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
			} // finally closed
		}
	}
}
