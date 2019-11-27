import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import rmi.RemoteAccess;

public class TelaControladorRemoto extends javax.swing.JFrame 
implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private RemoteAccess obj;
	private String token ;
	double altReal, largReal, altJan, largJan;
	Dimension size;
	
	private static final long serialVersionUID = -1842261777470977698L;

	public TelaControladorRemoto(byte image[], RemoteAccess obj, String token)  {
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void update(byte image[]){
		a.setIcon(new javax.swing.ImageIcon(image));
	}

	private javax.swing.JLabel a;
	
	
	public JLabel mousePosition;
    @Override
    public void mouseClicked(MouseEvent e) {
       // mousePosition.setText("Mouse clicado na coordenada : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseEntered(MouseEvent e) {
        //mousePosition.setText("Coordenada atual do mouse : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseExited(MouseEvent e) {
       // mousePosition.setText("O Mouse está fora da janela de acesso");
          
    }
  
    @Override
    public void mousePressed(MouseEvent e) {
       // mousePosition.setText("Mouse pressionado nas coordenadas : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseReleased(MouseEvent e) {
       // mousePosition.setText("Coordenada atual do mouse : ["+e.getX()+","+e.getY()+"]");
          
    }
  
    @Override
    public void mouseDragged(MouseEvent e) {
        //mousePosition.setText("Mouse arrastado nas coordenadas : ["+e.getX()+","+e.getY()+"]");
          
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
        	System.out.println("______________________________________");
        	System.out.println("___ Resul. Real: "+altReal+" X "+largReal+"     ____");
        	System.out.println("____Resul. janela:   "+altJan+" X "+largJan+"     ____");
        	System.out.println("____Coordenada Janela:  "+ e.getY() +"  X  "+e.getX()+"  ____");
        	System.out.println("____Coordenada real:  "+coordRealLarg(e.getX())+" X "+coordRealAlt(e.getY())+"        ____");
        	System.out.println("______________________________________");
			obj.moveMouse(token,coordRealLarg(e.getX()), coordRealAlt(e.getY())
					
					);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
    }
  
    public void start()
    {
       // mousePosition=new JLabel();
         addMouseListener( this );        // listens for own mouse and
          addMouseMotionListener( this );  // mouse-motion events
         // setLayout(new FlowLayout(1));
         // add(mousePosition);
          size = Toolkit.getDefaultToolkit().getScreenSize();
          //setSize(size );
          setUndecorated(true);
          setVisible( true );
          setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	
}
