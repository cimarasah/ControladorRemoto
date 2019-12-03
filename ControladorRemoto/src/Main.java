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
import javax.swing.JPanel;
import javax.swing.JTextField;

import rmi.RemoteAccess;


public class Main {

	public static void main(String[] args) throws RemoteException {
		//System.setSecurityManager(new RMISecurityManager());
		
		try {
			//TelaIP telaIp = new TelaIP();
			ConexaoRMI.Conectar("10.61.5.203"); 
			 
		} catch (Exception e) {
			System.out.println("RemoteControler erro" + e.getMessage());
		}
	}
}


