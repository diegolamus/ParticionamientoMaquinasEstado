package vista;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MostrarResultados extends JFrame {

	public MostrarResultados(String[][] resultados) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(160 + Tabla.SEPARACION_COLUMNAS*(resultados[0].length-1), 150+Tabla.SEPARACION_FILAS*(resultados.length-1)));
		for (int i = 0; i < resultados.length; i++) {
			for (int j = 0; j < resultados[0].length; j++) {
				JLabel lab = new JLabel(resultados[i][j]);
				lab.setBounds(20 + Tabla.SEPARACION_FILAS*j,20 + Tabla.SEPARACION_COLUMNAS*i,40,40);
				this.add(lab);
			}
		}
		this.pack();
	}
}
