package vista;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MostrarResultados extends JFrame {

	public MostrarResultados(String[][] resultados) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(300, 200));
		
		
		this.pack();
	}
}
