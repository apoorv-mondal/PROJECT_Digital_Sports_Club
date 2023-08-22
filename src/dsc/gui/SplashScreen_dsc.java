package dsc.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

public class SplashScreen_dsc extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen_dsc frame = new SplashScreen_dsc();
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
	public SplashScreen_dsc() {
		setTitle("Digital Sports Club");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 123, 601, 86);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digital Sports Club Welcomes You !!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(21, 11, 570, 64);
		panel.add(lblNewLabel);
		
		ImageIcon ic = new ImageIcon(LoginFrame_dsc.class.getResource("/dsc/images/splash.jpg"));
		Image i2 = ic.getImage().getScaledInstance(664, 386, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(i2);
		JLabel lblbgimage = new JLabel("");
		lblbgimage.setBounds(0, 0, 664, 386);
		lblbgimage.setIcon(ic1);
		contentPane.add(lblbgimage);
		loadFrame();
	}
	public void loadFrame() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
					LoginFrame_dsc frame = new LoginFrame_dsc();
					frame.setVisible(true);
					SplashScreen_dsc.this.dispose();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		});
		t.start();
	}
}
