package vista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Tabla extends JFrame {

	private final int SEPARACION_COLUMNAS = 50;
	private final int SEPARACION_FILAS = 50;
	
	private String[][] matriz;
	private String[] listaEstados;
	private ArrayList<JComboBox<String>> combosTransiciones;
	private ArrayList<JComboBox<String>> combosSalidas;
	
	
	public Tabla(int estados, int alfabeto) {
		matriz = new String[estados+1][alfabeto+2];
		listaEstados = new String[estados];
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(160 + SEPARACION_COLUMNAS*alfabeto, 150+SEPARACION_FILAS*estados));
		
		// Agregar los estados
		for ( int i = 0; i<= alfabeto; i++) {
			if(i==alfabeto) {
				matriz[0][i+1] = "Salida";
				//pintar salida 
				JLabel lab = new JLabel("Salida");
				lab.setBounds(60 + SEPARACION_COLUMNAS*i,20 , 60, 10);
				this.add(lab);
			}else {
				String estado =Character.toString ((char) (97+i));
				matriz[0][i+1] = estado;
				//pintar letra 
				JLabel lab = new JLabel(estado);
				lab.setBounds(75 + SEPARACION_COLUMNAS*i,20 , 15, 10);
				this.add(lab);
			}
		}
		
		// Agregar los estados
		for ( int i = 0; i< estados; i++) {
			String estado =Character.toString ((char) (65+i));
			matriz[i+1][0] = estado;
			listaEstados[i] = estado;
			//pintar letra 
			JLabel lab = new JLabel(estado);
			lab.setBounds(20,75 + SEPARACION_FILAS*i , 15, 10);
			this.add(lab);
		}
		
		// Agregar combo boxes transiciones
		combosTransiciones = new ArrayList<>();
		combosSalidas = new ArrayList<>();
		for (int i =0; i<=alfabeto;i++) {
			if(i<alfabeto) {
				for (int j = 0; j<estados;j++) {
					JComboBox<String> combito = new JComboBox<>(listaEstados);
					combosTransiciones.add(combito);
					combito.setBounds(60 + SEPARACION_FILAS*i,60 + SEPARACION_COLUMNAS*j,40,40);
					this.add(combito);
				}
			}else {
				for (int j = 0; j<estados;j++) {
					JComboBox<String> combito = new JComboBox<>();
					combito.addItem("1");
					combito.addItem("0");
					combosSalidas.add(combito);
					combito.setBounds(60 + SEPARACION_FILAS*i,60 + SEPARACION_COLUMNAS*j,40,40);
					this.add(combito);
				}
			}
		}
		// Agregar boton para generar resultados
		JButton buton = new JButton("Generar resultados");
		buton.setBounds(-75+(160 + SEPARACION_COLUMNAS*alfabeto)/2, 80+SEPARACION_FILAS*estados, 150, 30);
		buton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] resultados = matriz;
				// TODO Recoger combos, coonsumir metodo Eilen, generar nuevo frame
				MostrarResultados mos = new MostrarResultados(resultados);
				mos.setVisible(true);
			}
		});
		this.add(buton);
		this.pack();	
	}
}
