import java.awt.HeadlessException;

import javax.swing.JTextArea;

public class TelaIP extends javax.swing.JFrame{
	private JTextArea texto = new JTextArea();
	
	
	public TelaIP() {
		super("IP Servidor");
	}


	private void montaJanela(){
	      this.getContentPane().add(texto);
	   }
}
