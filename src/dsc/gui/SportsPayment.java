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

public class SportsPayment extends JFrame implements ActionListener,ItemListener{

	private JPanel contentPane;
	private JTextField txtid;
	private JComboBox<String> cbsport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SportsPayment frame = new SportsPayment();
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
	public SportsPayment() {
		setTitle("Sports Payment");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 672, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(97, 47, 440, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sports Payment Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(56, 0, 363, 45);
		panel.add(lblNewLabel);
		
		cbsport = new JComboBox();
		cbsport.addItemListener(this);
		cbsport.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbsport.setModel(new DefaultComboBoxModel(new String[] {"Sports Name"}));
		cbsport.setBounds(234, 137, 130, 29);
		contentPane.add(cbsport);
		
		JLabel lblNewLabel_1 = new JLabel("Member Id-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(117, 193, 95, 29);
		contentPane.add(lblNewLabel_1);
		
		txtid = new JTextField();
		txtid.setBounds(234, 193, 152, 29);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this);
		btnsubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnsubmit.setIcon(new ImageIcon(SportsPayment.class.getResource("/dsc/images/icons8-tick-box-15.png")));
		btnsubmit.setBounds(234, 251, 130, 29);
		contentPane.add(btnsubmit);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(656, 386, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 656, 386);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txtid.getText();
		String sport = (String) cbsport.getSelectedItem();// fetch the value from combo box

		if (id.isEmpty() || sport.equalsIgnoreCase("Sports Name")) {
			JOptionPane.showMessageDialog(this, "All fields required ");
		} else {

			java.util.Date d1 = new java.util.Date();
			long dt1 = d1.getTime();
			java.sql.Date dateofPayment = new java.sql.Date(dt1);

			Connection con = DBConnection_dsc.openConnection();
			PreparedStatement ps = null;
			String insertQuery = "insert into sports_payment_details(Sports_name, Member_Id, Date_of_payment)values(?,?,?)";// ?
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
				ps.setString(1, sport);
				ps.setString(2, id);
				ps.setDate(3, dateofPayment);

				System.out.println(ps);// ps has value in the reference query
				int result = ps.executeUpdate();// it will ask DBMS to execute the query
				if (result > 0) {
					JOptionPane.showMessageDialog(this, "Sports Payment Detail added successfully");
					txtid.setText("");

					cbsport.setSelectedIndex(0);

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
