package dsc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import com.toedter.calendar.JDateChooser;

import dsc.dbinfo.DBConnection_dsc;
import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.*;
import java.sql.*;
import java.awt.SystemColor;

public class RegistrationDateWiseMembers extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JDateChooser dc;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationDateWiseMembers frame = new RegistrationDateWiseMembers();
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
	public RegistrationDateWiseMembers() {
		setTitle("Registration Date-wise Members");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(35, 24, 594, 52);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("View Registration Date-wise Members");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(41, 0, 527, 52);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(35, 98, 594, 52);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		dc = new JDateChooser();
		dc.setDateFormatString("yyyy-MM-dd");
		dc.setBounds(63, 11, 205, 30);
		panel_1.add(dc);

		JLabel lblNewLabel_1 = new JLabel("Select Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dc.add(lblNewLabel_1, BorderLayout.WEST);

		JButton btngo = new JButton("Go");
		btngo.addActionListener(this);
		btngo.addKeyListener(this);
		btngo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btngo.setBounds(308, 11, 89, 30);
		panel_1.add(btngo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 176, 642, 180);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.YELLOW);
		header.setFont(new Font("Arial", Font.PLAIN, 16));
		header.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(table);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(662, 385, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 662, 385);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
	}

	public void populateTable() {
		if(dc.getDate()==null) {
			JOptionPane.showMessageDialog(this, "No Date Selected","Invalid Selection",JOptionPane.ERROR_MESSAGE);
		}
		java.util.Date d = dc.getDate();
		long dt = d.getTime();
		java.sql.Date sqlDate = new java.sql.Date(dt);

		Connection con = DBConnection_dsc.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "select Memberid, MemberName, Email, Phone, DOB, Plan_name, dateOfExpiry, SportsName from member_details where dateOfMembership=?";
		try {
			ps = con.prepareStatement(selectQuery);
			ps.setDate(1, sqlDate);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		populateTable();
	}
}
