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

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;
import java.awt.SystemColor;

public class AllPlans extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllPlans frame = new AllPlans();
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
	public AllPlans() {
		setTitle("All Plans");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 679, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(168, 39, 319, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Available Plans");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(50, 0, 273, 45);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 112, 615, 222);
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
		Image i2 = ic.getImage().getScaledInstance(663, 385, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 663, 385);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
	}

	public void populateTable() {
		Connection con = DBConnection_dsc.openConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "select * from plan_details";
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
