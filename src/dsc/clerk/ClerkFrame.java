package dsc.clerk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dsc.gui.AllPlans;
import dsc.gui.AllSports;
import dsc.gui.ExpiryDateWiseMembers;
import dsc.gui.LoginFrame_dsc;
import dsc.gui.PlanPayment;
import dsc.gui.PlanWiseMembers;
import dsc.gui.RegistrationDateWiseMembers;
import dsc.gui.SportsPayment;
import dsc.gui.SportsWiseMembers;
import dsc.owner.AddPlan;
import dsc.owner.AddSport;
import dsc.owner.DeletePlan;
import dsc.owner.DeleteSport;
import dsc.owner.UpdatePlan;
import dsc.owner.UpdateSport;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.*;

public class ClerkFrame extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClerkFrame frame = new ClerkFrame();
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
	public ClerkFrame() {
		setResizable(false);
		this.addWindowListener(this);
		setTitle("Clerk Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(150, 149, 323, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Clerk");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel.setBounds(118, 11, 163, 50);
		panel.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 664, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Member");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu);
		
		JMenuItem miaddmember = new JMenuItem("Add Member");
		miaddmember.addActionListener(this);
		miaddmember.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-add-15.png")));
		mnNewMenu.add(miaddmember);
		
		JMenuItem miupdatemember = new JMenuItem("Update Member");
		miupdatemember.addActionListener(this);
		miupdatemember.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-update-15.png")));
		mnNewMenu.add(miupdatemember);
		
		JMenu mnNewMenu_1 = new JMenu("Payment");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem miplanpayment = new JMenuItem("Plan Payment");
		miplanpayment.addActionListener(this);
		miplanpayment.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_1.add(miplanpayment);
		
		JMenuItem misportspayment = new JMenuItem("Sports Payment");
		misportspayment.addActionListener(this);
		misportspayment.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_1.add(misportspayment);
		
		JMenu mnNewMenu_2 = new JMenu("Reports");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem misportswise = new JMenuItem("Sports-wise Members");
		misportswise.addActionListener(this);
		misportswise.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(misportswise);
		
		JMenuItem miplanwise = new JMenuItem("Plan-wise Members");
		miplanwise.addActionListener(this);
		miplanwise.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miplanwise);
		
		JMenuItem miallplans = new JMenuItem("All Plans");
		miallplans.addActionListener(this);
		miallplans.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miallplans);
		
		JMenuItem miallsports = new JMenuItem("All Sports");
		miallsports.addActionListener(this);
		miallsports.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miallsports);
		
		JMenuItem miexpiry = new JMenuItem("Expiry Date-wise Member Details");
		miexpiry.addActionListener(this);
		miexpiry.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miexpiry);
		
		JMenuItem miregistration = new JMenuItem("Registration Date-wise Members");
		miregistration.addActionListener(this);
		miregistration.setIcon(new ImageIcon(ClerkFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miregistration);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/8.jpg"));
		Image i2 = ic.getImage().getScaledInstance(664, 365, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 21, 664, 365);
		lblimg.setIcon(ic1);
		contentPane.add(lblimg);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		this.dispose();// close admin window
		LoginFrame_dsc frame = new LoginFrame_dsc();// to open login window after exiting from admin window
		frame.setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String caption = ae.getActionCommand();// return the text written on the menuItem
		switch (caption) {
		case "Add Member":
			AddMember admemFrame = new AddMember();
			admemFrame.setVisible(true);
			break;
		case "Update Member":
			UpdateMember upmemFrame = new UpdateMember();
			upmemFrame.setVisible(true);
			break;
		case "Sports-wise Members":
			SportsWiseMembers swm = new SportsWiseMembers();
			swm.setVisible(true);
			break;
		case "Plan-wise Members":
			PlanWiseMembers pwm = new PlanWiseMembers();
			pwm.setVisible(true);
			break;
		case "All Plans":
			AllPlans ap = new AllPlans();
			ap.setVisible(true);
			break;
		case "All Sports":
			AllSports as = new AllSports();
			as.setVisible(true);
			break;
		case "Expiry Date-wise Member Details":
			ExpiryDateWiseMembers edwm = new ExpiryDateWiseMembers();
			edwm.setVisible(true);
			break;
		case "Registration Date-wise Members":
			RegistrationDateWiseMembers rdwm = new RegistrationDateWiseMembers();
			rdwm.setVisible(true);
			break;
		case "Plan Payment":
			PlanPayment pp = new PlanPayment();
			pp.setVisible(true);
			break;
		case "Sports Payment":
			SportsPayment sp = new SportsPayment();
			sp.setVisible(true);
			break;
		}
	}
}
