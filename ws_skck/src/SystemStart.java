import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.skck.desktop.ServiceWSDL;

public class SystemStart {
    
	// start of main method
	public static void main(String[] args) {
		// checking for support
		if (!SystemTray.isSupported()) {
			System.out.println("System tray is not supported !!! ");
			return;
		}
		// get the systemTray of the system
		SystemTray systemTray = SystemTray.getSystemTray();

		// get default toolkit
		// Toolkit toolkit = Toolkit.getDefaultToolkit();
		// get image
		// Toolkit.getDefaultToolkit().getImage("src/resources/busylogo.jpg");
		Image image = Toolkit.getDefaultToolkit().getImage(SystemStart.class.getClassLoader().getResource("computer.png"));

		 ServiceWSDL frame = ServiceWSDL.getInstance();
		
		// popupmenu
		PopupMenu trayPopupMenu = new PopupMenu();

		// 1t menuitem for popupmenu
		MenuItem action = new MenuItem("Show");
		action.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
			}
		});
		trayPopupMenu.add(action);

		// 2nd menuitem of popupmenu
		MenuItem close = new MenuItem("Close");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    int response = JOptionPane.showConfirmDialog(null, "Apakah anda ingin menutup aplikasi? \n Layanan data ke user akan dimatikan.", "Confirm",
			        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			        if (response == JOptionPane.YES_OPTION)
						System.exit(0);
			      
			}
		});
		trayPopupMenu.add(close);

		// setting tray icon
		TrayIcon trayIcon = new TrayIcon(image, "Service WSDL SKCK", trayPopupMenu);
		// adjust to default size as per system recommendation
		trayIcon.setImageAutoSize(true);

		try {
			systemTray.add(trayIcon);
		} catch (AWTException awtException) {
			awtException.printStackTrace();
		}

	}// end of main

}// end of class