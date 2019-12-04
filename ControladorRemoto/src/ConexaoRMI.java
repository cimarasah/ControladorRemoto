import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmi.RemoteAccess;

public class ConexaoRMI {

	public static void Conectar(String IP) {
		RemoteAccess obj;
		try {
			obj = (RemoteAccess) Naming
					.lookup("rmi://"+IP+"/RemoteControler");
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
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
