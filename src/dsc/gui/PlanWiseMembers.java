package dsc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import dsc.dbinfo.DBConnection_dsc;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;
import java.sql.*;
import java.awt.SystemColor;

public class PlanWiseMembers extends JFrame implements ActionListener, ItemListener, KeyListener {

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlanWiseMembers frame = new PlanWiseMembers();
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
	public PlanWiseMembers() {
		setTitle("Plan-wise Members");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 422);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(111, 33, 423, 55);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("View Plan-wise Members");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(45, 0, 355, 48);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(111, 105, 423, 55);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		comboBox = new JComboBox();
		comboBox.addItemListener(this);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select Plan" }));
		comboBox.setBounds(64, 11, 117, 33);
		panel_1.add(comboBox);

		JButton btngo = new JButton("Go");
		btngo.addActionListener(this);
		btngo.addKeyListener(this);
		btngo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btngo.setBounds(213, 11, 89, 33);
		panel_1.add(btngo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 640, 162);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.YELLOW);
		header.setFont(new Font("Arial", Font.PLAIN, 16));
		header.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(table);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(660, 384, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 660, 384);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
		fillCombo();
	}

	public void populateTable() {
		String planName = (String) comboBox.getSelectedItem();
		Connection con = DBConnection_dsc.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "select Memberid, MemberName, Email, Phone, DOB, Plan_name, dateOfMembership, dateOfExpiry, SportsName from member_details where Plan_name=?";
		try {
			ps = con.prepareStatement(selectQuery);
			ps.setString(1, planName);
			rs = ps.executeQuery();
			TableModel tableModel = DbUtils.resultSetToTableModel(rs);
			table.setModel(tableModel);
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		System.out.println(keyCode);
		if (keyCode == 10) {
			populateTable();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		populateTable();
	}

}
