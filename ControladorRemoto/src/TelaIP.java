import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaIP extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnConectar = new JButton("Conectar");
	JTextField textIp = new JTextField();
	JLabel labelIp = new JLabel();
	
	public TelaIP() 
	{
		setSize(350, 180);       
		setTitle("Sistema "); 

		JPanel painel = new JPanel();
		painel.setLayout(null);        

		//label
		
		labelIp = new JLabel("IP");
		painel.add(labelIp);
		labelIp.setBounds(20, 50, 120, 25);

		//text        

		painel.add(textIp);
		textIp.setBounds(70, 50, 120, 25);

		painel.add(btnConectar);
		btnConectar.setBounds(220, 50, 100, 25);


		btnConectar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj=e.getSource();

				if(obj==btnConectar){
					String text = textIp.getText();
					if(!text.isEmpty()) {
						ConexaoRMI.Conectar(text); 
						setVisible(false);
					}

				}

			}});
		
		
		
		add(painel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}  
}