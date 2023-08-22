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

public class DeletePlan extends JFrame implements ActionListener,KeyListener {

	private JPanel contentPane;
	private JTextField txtname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePlan frame = new DeletePlan();
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
	public DeletePlan() {
		setTitle("Delete Plan");
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
		panel.setBounds(118, 31, 397, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete Existing Plan");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(57, 0, 294, 56);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Plan Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(144, 129, 156, 26);
		contentPane.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(310, 134, 132, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btndelete.addActionListener(this);
		btndelete.addKeyListener(this);
		btndelete.setIcon(new ImageIcon(DeletePlan.class.getResource("/dsc/images/icons8-delete-15.png")));
		btndelete.setBounds(246, 184, 109, 32);
		contentPane.add(btndelete);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/9.jpg"));
		Image i2 = ic.getImage().getScaledInstance(662, 385, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 0, 662, 385);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
	}

	public void deletePlan() {
		String planName = txtname.getText().trim();// String method->to remove leading and trailing spaces
		if (planName.length() == 0) {
			JOptionPane.showMessageDialog(this, "Please provide plan name");
		} else {
			int choice = JOptionPane.showConfirmDialog(this, "Do you wish to delete " + planName + " plan");
			// System.out.println(choice);
			if (choice == 0) { // 0 mtlb user ne yes click kiya hai
				Connection con = DBConnection_dsc.openConnection();
				PreparedStatement ps = null;
				String deleteQuery = "delete from plan_details where Plan_name=?";
				try {
					ps = con.prepareStatement(deleteQuery);
					ps.setString(1, planName);
					int result = ps.executeUpdate();
					System.out.println("Executed query output " + result);
					if (result > 0) {
						JOptionPane.showMessageDialog(this, planName + " plan deleted successfully");
					} else {
						JOptionPane.showMessageDialog(this, "No such plan exists", "No data found error ",
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
		int keyCode=e.getKeyCode();
		System.out.println(keyCode);
		if(keyCode==10) {
			deletePlan();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		deletePlan();
	}

}
