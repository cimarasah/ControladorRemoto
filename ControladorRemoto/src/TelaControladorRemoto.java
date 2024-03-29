import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import rmi.RemoteAccess;

public class TelaControladorRemoto extends javax.swing.JFrame 
implements MouseListener, MouseMotionListener, KeyListener {
	/**
	 * 
	 */
	private RemoteAccess obj;
	private String token ;
	double altReal, largReal, altJan, largJan;
	Dimension size;
	private JLabel a;

	private static final long serialVersionUID = -1842261777470977698L;

	public TelaControladorRemoto(byte image[], RemoteAccess obj, String token)  {
		getConfigure(image, obj, token);
	}

	private void getConfigure(byte image[], RemoteAccess obj, String token) {
		a = new javax.swing.JLabel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		a.setIcon(new javax.swing.ImageIcon(image));
		add(a);
		pack();
		this.obj = obj;
		altJan = a.getHeight();
		largJan=a.getWidth();
		this.token = token;
		size = a.getSize();
		try {
			altReal = obj.getHeightResolution(token);
			largReal = obj.getWidthResolution(token);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void update(byte image[]){
		a.setIcon(new javax.swing.ImageIcon(image));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			obj.pressMouse(token, InputEvent.getMaskForButton(e.getButton()));
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}          
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			obj.releaseMouse(token, InputEvent.getMaskForButton(e.getButton()));
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} 
	}
	public int coordRealAlt(int coordJanela) {
		return (int) (((altReal/altJan* coordJanela)))-42 ;
	}
	public int coordRealLarg(int coordJanela) {
		return (int) (((largReal* coordJanela)/largJan) ) ;
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			obj.moveMouse(token,coordRealLarg(e.getX()), coordRealAlt(e.getY()));
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	public void start()
	{
		addMouseListener( this );       
		addMouseMotionListener( this ); 
		addKeyListener(this);
		size = Toolkit.getDefaultToolkit().getScreenSize();
		setVisible( true );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			obj.pressKey(token, e.getKeyCode());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		try {
			obj.releaseKey(token, e.getKeyCode());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} 
	}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}
}
