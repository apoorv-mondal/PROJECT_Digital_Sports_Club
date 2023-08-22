package dsc.owner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dsc.gui.AllPlans;
import dsc.gui.AllSports;
import dsc.gui.ExpiryDateWiseMembers;
import dsc.gui.LoginFrame_dsc;
import dsc.gui.PlanWiseMembers;
import dsc.gui.RegistrationDateWiseMembers;
import dsc.gui.SportsWiseMembers;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.*;

public class OwnerFrame extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerFrame frame = new OwnerFrame();
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
	public OwnerFrame() {
		setResizable(false);
		this.addWindowListener(this);// register the listener with frame
		setTitle("Owner Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(143, 141, 336, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Club Owner");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel.setBounds(69, 11, 231, 55);
		panel.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 660, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Plan");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu);
		
		JMenuItem miaddplan = new JMenuItem("Add Plan");
		miaddplan.addActionListener(this);
		miaddplan.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-add-15.png")));
		mnNewMenu.add(miaddplan);
		
		JMenuItem miupdateplan = new JMenuItem("Update Plan");
		miupdateplan.addActionListener(this);
		miupdateplan.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-update-15.png")));
		mnNewMenu.add(miupdateplan);
		
		JMenuItem mideleteplan = new JMenuItem("Delete Plan");
		mideleteplan.addActionListener(this);
		mideleteplan.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-delete-15.png")));
		mnNewMenu.add(mideleteplan);
		
		JMenu mnNewMenu_1 = new JMenu("Sports");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem miaddsport = new JMenuItem("Add Sport");
		miaddsport.addActionListener(this);
		miaddsport.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-add-15.png")));
		mnNewMenu_1.add(miaddsport);
		
		JMenuItem miupdatesport = new JMenuItem("Update Sport");
		miupdatesport.addActionListener(this);
		miupdatesport.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-update-15.png")));
		mnNewMenu_1.add(miupdatesport);
		
		JMenuItem mideletesport = new JMenuItem("Delete Sport");
		mideletesport.addActionListener(this);
		mideletesport.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-delete-15.png")));
		mnNewMenu_1.add(mideletesport);
		
		JMenu mnNewMenu_2 = new JMenu("Reports");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem misportswise = new JMenuItem("Sports-wise Members");
		misportswise.addActionListener(this);
		misportswise.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(misportswise);
		
		JMenuItem miplanwise = new JMenuItem("Plan-wise Members");
		miplanwise.addActionListener(this);
		miplanwise.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miplanwise);
		
		JMenuItem miallplans = new JMenuItem("All Plans");
		miallplans.addActionListener(this);
		miallplans.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miallplans);
		
		JMenuItem miallsports = new JMenuItem("All Sports");
		miallsports.addActionListener(this);
		miallsports.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miallsports);
		
		JMenuItem miexpiry = new JMenuItem("Expiry Date-wise Members Details");
		miexpiry.addActionListener(this);
		miexpiry.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miexpiry);
		
		JMenuItem miregistration = new JMenuItem("Registration Date-wise Members");
		miregistration.addActionListener(this);
		miregistration.setIcon(new ImageIcon(OwnerFrame.class.getResource("/dsc/images/icons8-bullet-15.png")));
		mnNewMenu_2.add(miregistration);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/8.jpg"));
		Image i2 = ic.getImage().getScaledInstance(660, 365, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblimg = new JLabel("");
		lblimg.setBounds(0, 21, 660, 365);
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
		case "Add Plan":
			AddPlan courseFrame = new AddPlan();
			courseFrame.setVisible(true);
			break;
		case "Update Plan":
			UpdatePlan updateframe = new UpdatePlan();
			updateframe.setVisible(true);
			break;
		case "Delete Plan":
			DeletePlan deleteFrame = new DeletePlan();
			deleteFrame.setVisible(true);
			break;
		case "Add Sport":
			AddSport addspFrame = new AddSport();
			addspFrame.setVisible(true);
			break;
		case "Update Sport":
			UpdateSport updatespframe = new UpdateSport();
			updatespframe.setVisible(true);
			break;
		case "Delete Sport":
			DeleteSport deletespFrame = new DeleteSport();
			deletespFrame.setVisible(true);
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
		case "Expiry Date-wise Members Details":
			ExpiryDateWiseMembers edwm = new ExpiryDateWiseMembers();
			edwm.setVisible(true);
			break;
		case "Registration Date-wise Members":
			RegistrationDateWiseMembers rdwm = new RegistrationDateWiseMembers();
			rdwm.setVisible(true);
			break;
		}
	}
}
