package com.skck.desktop;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.skck.util.AppLog;
import com.skck.util.AppXML;
import com.skck.ws.publisher.SkckPublisher;
import com.skck.xml.model.OptionXML;

public class ServiceWSDL extends JFrame {
	private JTextField txtUrl;

	private Boolean start = false;
	private static final SkckPublisher pub = new SkckPublisher("http://localhost:9999/ws/skck?wsdl");
	private static ServiceWSDL instance = null;

	public static ServiceWSDL getInstance() {
		if (instance == null) {
			instance = new ServiceWSDL();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						// Agar posisi window di kanan bawah
						GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
						Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
						int x = (int) rect.getMaxX() - instance.getWidth();
						int y = (int) rect.getMaxY() - instance.getHeight();
						instance.setLocation(x, y);
						// Tampilkan
						instance.setVisible(false);
						instance.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE );
						instance.addWindowListener(new WindowAdapter() {
				            public void windowIconified(WindowEvent e) {
				            	instance.setVisible(false);
				            }
				        });
						instance.addWindowListener(new WindowAdapter() {
				            public void windowDeiconified(WindowEvent e) {
				            	instance.setVisible(true);
				            }
				        });
						instance.addWindowListener(new WindowAdapter() {
				            public void windowClosing(WindowEvent e) {
							    int response = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menutup aplikasi? \n Layanan data ke user akan dimatikan.", "Confirm",
								        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if (response == JOptionPane.YES_OPTION) {
									// Check if is published ?
									if(pub.getEp() != null) {
										if(pub.getEp().isPublished()) {
											pub.stop();
										}
									}
									
									AppLog.time().info("STOP SERVICE");
									System.exit(0);
						        }
								
				            }

				        });
					} catch (Exception e) {
						e.printStackTrace();
						AppLog.error().info(e.toString());
					}
				}
			});
		}

		return instance;
	}

	/**
	 * Create the frame.
	 */
	public ServiceWSDL() {	
		
		setResizable(false);
		setTitle("Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 185);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Panel Control Service", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panel.setToolTipText("");
		panel.setBounds(10, 11, 422, 109);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUrlServer = new JLabel("URL / Server Service");
		lblUrlServer.setFont(new Font("Arial", Font.PLAIN, 10));
		lblUrlServer.setBounds(10, 23, 109, 14);
		panel.add(lblUrlServer);
		
		txtUrl = new JTextField();
		txtUrl.setText("http://localhost:9999/ws/skck?wsdl");
		txtUrl.setBounds(126, 20, 286, 20);
		panel.add(txtUrl);
		txtUrl.setColumns(10);
		
		JLabel lblServiceStart = new JLabel("Service Start ");
		lblServiceStart.setFont(new Font("Arial", Font.PLAIN, 10));
		lblServiceStart.setBounds(10, 48, 109, 14);
		panel.add(lblServiceStart);
		
		JLabel lblTimeStart = new JLabel("-");
		lblTimeStart.setFont(new Font("Arial", Font.PLAIN, 9));
		lblTimeStart.setBounds(126, 48, 103, 14);
		panel.add(lblTimeStart);
		
		JButton btnStar = new JButton("Start");
		btnStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!start) {
					pub.start();
					AppLog.time().info("START SERVICE");
					Date date=new Date();    
					String time = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").format(date);
					lblTimeStart.setText(time);
					start = true;
					btnStar.setText("Stop");
					btnStar.setBackground(Color.RED);
					txtUrl.setEnabled(false);
				} else {
					// Check if is published ?
					if(pub.getEp().isPublished()) {
						AppLog.time().info("STOP SERVICE");
						pub.stop();
					}
					
					start = false;
					btnStar.setText("Start");
					btnStar.setBackground(null);
					txtUrl.setEnabled(true);
				}
			}
		});
		btnStar.setBounds(126, 73, 91, 23);
		panel.add(btnStar);
		
		JButton btnLogStart = new JButton("Log Error");
		btnLogStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "C:\\skck\\logs\\error_log_desktop.txt");
				try {
					pb.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					AppLog.error().info(e1.toString());
				}
			}
		});
		btnLogStart.setBounds(321, 73, 91, 23);
		panel.add(btnLogStart);
		
		JButton btnLogService = new JButton("Log Start");
		btnLogService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "C:\\skck\\logs\\start_log_desktop.txt");
				try {
					pb.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					AppLog.error().info(e1.toString());
				}
			}
		});
		btnLogService.setBounds(321, 44, 91, 23);
		panel.add(btnLogService);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(UIManager.getColor("Button.shadow"));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Config", TitledBorder.LEADING, TitledBorder.TOP, null, UIManager.getColor("Button.darkShadow")));
		panel_1.setBounds(227, 39, 91, 57);
		panel.add(panel_1);
		
		JCheckBox chckbxAutoStart = new JCheckBox("Auto Start");
		panel_1.add(chckbxAutoStart);
		chckbxAutoStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OptionXML o = new OptionXML();
				if(chckbxAutoStart.isSelected()) {
					o.setId(1);
					o.setAutostart("Y");
					AppXML.writeXML(o);
					txtUrl.setEnabled(false);
				} else {
					o.setId(1);
					o.setAutostart("N");
					AppXML.writeXML(o);
					txtUrl.setEnabled(true);
				}
			}
		});
		
		// Checking XML
		// Periksa jika option sudah diset Y / True maka
		// check list auto start
		if(AppXML.getXML() != null) {
			OptionXML op = AppXML.getXML();
			if(op.getAutostart().equals("Y")) {
				chckbxAutoStart.setSelected(true);
				pub.start();
				AppLog.time().info("START SERVICE");
				Date date=new Date();    
				String time = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss").format(date);
				lblTimeStart.setText(time);
				start = true;
				btnStar.setText("Stop");
				btnStar.setBackground(Color.RED);
				txtUrl.setEnabled(false);
			} else {
				txtUrl.setEnabled(true);
			}
		}
		
		JButton btnNewButton = new JButton("Keluar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    int response = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menutup aplikasi? \n Layanan data ke user akan dimatikan.", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					// Check if is published ?
					if(pub.getEp() != null) {
						if(pub.getEp().isPublished()) {
							pub.stop();
						}
					}
					
					AppLog.time().info("STOP SERVICE");
					System.exit(0);
		        }
			}
		});btnNewButton.setBounds(331,126,90,23);

	getContentPane().add(btnNewButton);
	}
}
