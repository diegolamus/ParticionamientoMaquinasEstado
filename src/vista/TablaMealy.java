
package vista;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modelo.AlgoritmoParticionamiento;


@SuppressWarnings("serial")
public class TablaMealy extends JFrame {
	
	
		public final static int SEPARACION_COLUMNAS = 100;
		public final static int SEPARACION_FILAS = 110;
		public final static int SEPARACION_FILASNUM = 50;
		
		private String[][] matriz;
		private String[] listaEstados;
		private ArrayList<JComboBox<String>> combosTransiciones;
		private ArrayList<JComboBox<String>> combosSalidas;
		private ArrayList<JComboBox<String>> combosSalidas1;
		private AlgoritmoParticionamiento modelo;
		
		
		public TablaMealy(int estados, int alfabeto) {
			matriz = new String[estados+1][alfabeto+2];
			listaEstados = new String[estados];
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setLayout(null);
			this.setResizable(false);
			this.setPreferredSize(new Dimension(160 + SEPARACION_COLUMNAS*alfabeto, 150+SEPARACION_FILAS*estados));
			
			// Agregar las transiciones
			for ( int i = 0; i<= alfabeto; i++) {
				if(i==alfabeto) {
					matriz[0][i+1] = "Salida";
					//pintar salida 
					JLabel lab = new JLabel("");
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
				lab.setBounds(20,65 + SEPARACION_FILAS*i , 15, 10);
				this.add(lab);
			}
			
			// Agregar combo boxes transiciones
			combosTransiciones = new ArrayList<>();
			combosSalidas = new ArrayList<>();
			combosSalidas1 = new ArrayList<>();
			
			for (int i =0; i<estados;i++) {
				for (int j = 0; j<alfabeto;j++) {
						JComboBox<String> combito = new JComboBox<>(listaEstados);
						combito.setBounds(60 + SEPARACION_FILAS*j,60 + SEPARACION_COLUMNAS*i,40,40);
						this.add(combito);
						combosTransiciones.add(combito);
						
				}
			}
			
			//agregar combo boxes salida de cada nodo
			for (int j = 0; j<estados;j++) {
				for (int i = 0; i < alfabeto; i++) {
					JComboBox<String> combito = new JComboBox<>();
				    combito.addItem("1");
				    combito.addItem("0");
				    combosSalidas.add(combito);
				    combito.setBounds(100+SEPARACION_FILAS*i,60+ SEPARACION_COLUMNAS*j,40,40);
				    this.add(combito);
				}
				
			}
			//TODO
//			for (int j = 0; j<estados;j++) {
//				JComboBox<String> combito = new JComboBox<>();
//				combito.addItem("1");
//				combito.addItem("0");
//				combosSalidas1.add(combito);
//				combito.setBounds(10+SEPARACION_FILASNUM*alfabeto,60+ SEPARACION_COLUMNAS*j,40,40);
//				this.add(combito);
//				
//				
//			}
			
			// Agregar boton para generar resultados
			JButton buton = new JButton("Generar resultados");
			buton.setBounds(-75+(160 + SEPARACION_COLUMNAS*alfabeto)/2, 80+SEPARACION_FILAS*estados, 150, 30);
			buton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					recoger_matriz();
					modelo = new AlgoritmoParticionamiento(matriz);
					String[][] resultados = modelo.getMaquina();
					// TODO Recoger combos, coonsumir metodo Eilen, generar nuevo frame
					MostrarResultados mos = new MostrarResultados(resultados);
					mos.setVisible(true);
				}
				
				private void recoger_matriz() {
					for (int i =0; i<estados;i++) {
						for (int j = 0; j<alfabeto;j++) {
								String value = combosTransiciones.get((i*alfabeto)+j).getSelectedItem().toString();
								matriz[i+1][j+1] = value;
						}
					}
					for (int j = 0; j<estados;j++) {
						String value = combosSalidas.get(j).getSelectedItem().toString();
						matriz[j+1][alfabeto+1] = value;
					}
						
				}
				
			});
			this.add(buton);
			this.pack();	
		}
	}


