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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.*;
import java.sql.*;
import java.awt.SystemColor;

public class DeleteSport extends JFrame implements ActionListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteSport frame = new DeleteSport();
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
	public DeleteSport() {
		setTitle("Delete Sport");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 679, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(122, 22, 408, 46);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Delete Existing Sport");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(49, 0, 310, 46);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter Sport Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(122, 107, 179, 32);
		contentPane.add(lblNewLabel_1);

		txtname = new JTextField();
		txtname.setBounds(288, 109, 143, 30);
		contentPane.add(txtname);
		txtname.setColumns(10);

		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.addKeyListener(this);
		btndelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btndelete.setIcon(new ImageIcon(DeleteSport.class.getResource("/dsc/images/icons8-delete-15.png")));
		btndelete.setBounds(219, 161, 113, 32);
		contentPane.add(btndelete);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(663, 386, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 663, 386);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
	}

	public void deleteSport() {
		String sportName = txtname.getText().trim();// String method->to remove leading and trailing spaces
		if (sportName.length() == 0) {
			JOptionPane.showMessageDialog(this, "Please provide sport name");
		} else {
			int choice = JOptionPane.showConfirmDialog(this, "Do you wish to delete " + sportName + " sport");
			// System.out.println(choice);
			if (choice == 0) { // 0 mtlb user ne yes click kiya hai
				Connection con = DBConnection_dsc.openConnection();
				PreparedStatement ps = null;
				String deleteQuery = "delete from sports_details where SportsName=?";
				try {
					ps = con.prepareStatement(deleteQuery);
					ps.setString(1, sportName);
					int result = ps.executeUpdate();
					System.out.println("Executed query output " + result);
					if (result > 0) {
						JOptionPane.showMessageDialog(this, sportName + " sport deleted successfully");
					} else {
						JOptionPane.showMessageDialog(this, "No such sport exists", "No data found error ",
								JOptionPane.ERROR_MESSAGE);
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
				}
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
			deleteSport();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		deleteSport();
	}

}
