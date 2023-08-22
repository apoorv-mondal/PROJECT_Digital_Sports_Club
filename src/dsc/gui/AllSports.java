package dsc.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;

public class AllSports extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllSports frame = new AllSports();
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
	public AllSports() {
		setTitle("All Sports");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(152, 30, 332, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Available Sports");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(45, 0, 259, 46);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 111, 620, 214);
		contentPane.add(scrollPane);
		
		table = new JTable();
		// Table heading color
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.YELLOW);
		header.setFont(new Font("Arial", Font.PLAIN, 16));
		header.setBackground(Color.DARK_GRAY);
		populateTable();
		scrollPane.setViewportView(table);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(662, 387, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 662, 387);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
	}
	public void populateTable() {
		Connection con = DBConnection_dsc.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "select * from sports_details";
		try {
			ps = con.prepareStatement(selectQuery);
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
}
