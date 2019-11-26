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
			
			RemoteAccess obj = (RemoteAccess) Naming
					.lookup("rmi://localhost/RemoteControler");
			
			String token = obj.logIn("12345");
			
			System.out.println("Token: " + token);
			
			obj.moveMouse(token, 500, 500);
			
	
			byte screenshot[] = obj.getScreenshot(token);
			
			ShowScreen s = new ShowScreen(screenshot);
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

class ShowScreen extends javax.swing.JFrame 
implements MouseListener, MouseMotionListener {
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
	
	
	JLabel mousePosition;
    @Override
    public void mouseClicked(MouseEvent e) {
        mousePosition.setText("Mouse clicado na coordenada : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseEntered(MouseEvent e) {
        mousePosition.setText("Coordenada atual do mouse : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseExited(MouseEvent e) {
        mousePosition.setText("O Mouse está fora da janela de acesso");
          
    }
  
    @Override
    public void mousePressed(MouseEvent e) {
        mousePosition.setText("Mouse pressionado nas coordenadas : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseReleased(MouseEvent e) {
        mousePosition.setText("Coordenada atual do mouse : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition.setText("Mouse arrastado nas coordenadas : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.setText("Mouse movido para as coordenadas : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    public void start()
    {
        mousePosition=new JLabel();
         addMouseListener( this );        // listens for own mouse and
          addMouseMotionListener( this );  // mouse-motion events
          setLayout(new FlowLayout(1));
          add(mousePosition);
          Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
          setSize(size );
          setUndecorated(true);
          setVisible( true );
          setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	
}
