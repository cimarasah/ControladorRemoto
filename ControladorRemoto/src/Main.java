import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import rmi.RemoteAccess;


public class Main {

	public static void main(String[] args) throws RemoteException {
		//System.setSecurityManager(new RMISecurityManager());
		
		try {
			TelaIP ip = new TelaIP();
			ip.setSize(640,480);
		    ip.setVisible(true);
			RemoteAccess obj = (RemoteAccess) Naming
					.lookup("rmi://10.61.5.82/RemoteControler");
			
			String token = obj.logIn("12345");
			
			System.out.println("Token: " + token);
			
			obj.moveMouse(token, 500, 500);
			
	
			byte screenshot[] = obj.getScreenshot(token);
			
			TelaControladorRemoto s = new TelaControladorRemoto(screenshot, obj, token);
			s.setVisible(true);
			s.start();
			
			for(;;){
				screenshot = obj.getScreenshot(token);
				s.update(screenshot);
			}
	
			 
		} catch (Exception e) {
			System.out.println("RemoteControler erro" + e.getMessage());
		}
	}
}

