package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.MenuListener;

public class Principal extends JFrame implements ActionListener {

	/**
	 * Relacion al panel generarTabla
	 */
	private GenerarTabla panelGenerarTabla;

	/**
	 * Establece la barra de herrmientas
	 */
	private JMenuBar menu;

	/**
	 * Boton de la barra de herramientas para brindar ayuda al usuario
	 */
	private JMenu menuAyuda;

	/**
	 * item del menu, este permite establecer actionListener
	 */
	private JMenuItem menuAyudaItem;

	public Principal() {

		setTitle("Bienvenido");
		setSize(500, 380);
		setLayout(new BorderLayout());

		menu = new JMenuBar();
		menuAyuda = new JMenu("Ayuda");
		menuAyuda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		menuAyudaItem = new JMenuItem("Manejo del programa");

		menuAyuda.add(menuAyudaItem);

		menu.add(menuAyuda);
		setJMenuBar(menu);
		programaEventos();

		ImageIcon img = new ImageIcon("data/banner.png");
		JLabel lab = new JLabel("");
		lab.setIcon(img);
		add(lab, BorderLayout.NORTH);

		panelGenerarTabla = new GenerarTabla();
		add(panelGenerarTabla, BorderLayout.CENTER);

		JLabel labvacio = new JLabel("                                   ");
		add(labvacio, BorderLayout.WEST);

	}

	/**
	 * hace visible la ventana
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Principal principal = new Principal();
		principal.setVisible(true);
	}

	/**
	 * Descripcion: Permite Abrir el archivo de instrucciones para el usuario
	 */
	public void programaEventos() {

		ActionListener ayudaa = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// RUTA DEL ARCHIVO EN DATA
					File path = new File("data/Instrucciones.pdf");
					Desktop.getDesktop().open(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		this.menuAyudaItem.addActionListener(ayudaa);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
