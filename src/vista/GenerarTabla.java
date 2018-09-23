package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GenerarTabla extends JPanel {

	private JTextField estados;
	private JTextField largo_vocabulario;
	
	public GenerarTabla() {
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		//this.setResizable(false);
		this.setPreferredSize(new Dimension(250, 240));
		// Agregar campo de numero de estados
		JLabel est = new JLabel("Número estados:");
		est.setBounds(10, 10, 150, 30);
		this.add(est);
		estados = new JTextField();
		estados.setBounds(190, 10, 40, 25);
		this.add(estados);
		
		// Agregar campo de tamanio vocabulario
		JLabel voc = new JLabel("Número entradas:");
		voc.setBounds(10, 40, 150, 30);
		this.add(voc);
		largo_vocabulario= new JTextField();
		largo_vocabulario.setBounds(190, 40, 40, 25);
		this.add(largo_vocabulario);
		
		// Agregar boton para generar
		JButton buton = new JButton("Generar Automata Moore");
		buton.setBounds(10, 120, 200, 30);
		buton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int esta = 0;
				int alfa = 0;
				try {
					esta = Integer.parseInt(estados.getText());
					alfa = Integer.parseInt(largo_vocabulario.getText());
					Tabla t = new Tabla(esta,alfa);
					t.setVisible(true);
				} catch (Exception exception){
					JOptionPane.showMessageDialog(null, "El numero de estado u el largo de el alfabeto deben ser numeros enteros");
				}
			}
		});
		
		// Agregar boton para generar automata de mealy
		JButton butonMealy = new JButton("Generar Automata Mealy");
		butonMealy.setBounds(10, 160, 200, 30);
		butonMealy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int esta = 0;
				int alfa = 0;
				try {
					esta = Integer.parseInt(estados.getText());
					alfa = Integer.parseInt(largo_vocabulario.getText());
					TablaMealy t = new TablaMealy(esta,alfa);
					t.setVisible(true);
				} catch (Exception exception){
					JOptionPane.showMessageDialog(null, "El numero de estado u el largo de el alfabeto deben ser numeros enteros");
				}
			}
		});


		this.add(buton);
		this.add(butonMealy);
		//this.pack();
	}
	

}
