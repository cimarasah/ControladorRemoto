import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import rmi.RemoteAccess;


public class Main {

	public static void main(String[] args) throws RemoteException {
		//System.setSecurityManager(new RMISecurityManager());
		
		try {
			
			RemoteAccess obj = (RemoteAccess) Naming
					.lookup("rmi://localhost/RemoteControler");
			
			String token = obj.logIn("12345");
			
			System.out.println("Token: " + token);
			
			obj.moveMouse(token, 500, 500);
			
	
			byte screenshot[] = obj.getScreenshot(token);
			
			ShowScreen s = new ShowScreen(screenshot);
			s.setVisible(true);
			
			for(;;){
				screenshot = obj.getScreenshot(token);
				s.update(screenshot);
			}
	
			 
		} catch (Exception e) {
			System.out.println("RemoteControler erro" + e.getMessage());
		}
	}
}

class ShowScreen extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1842261777470977698L;

	public ShowScreen(byte image[]) {
		a = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		a.setIcon(new javax.swing.ImageIcon(image));
		add(a);
		pack();
	}
	public void update(byte image[]){
		a.setIcon(new javax.swing.ImageIcon(image));
	}

	private javax.swing.JLabel a;
}
