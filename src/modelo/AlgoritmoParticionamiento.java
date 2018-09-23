package modelo;

import java.util.ArrayList;

import javax.smartcardio.ResponseAPDU;

/**
 * Esta clase aplica el particionamiento a la maquina moore si es maquina mealy,
 * se debe convertir a moore.
 */
public class AlgoritmoParticionamiento {

	private String[][] maquina;
	private ArrayList<ArrayList<String>> particionFinal;

	public AlgoritmoParticionamiento(String[][] maquina) {
		this.maquina = maquina;
		particionFinal = new ArrayList<ArrayList<String>>();
		generacionAutomataEquivalente();
	}

	/**
	 * Convierte el automata de mealy a moore para que pueda ser procesado por
	 * el algoritmo de particionamiento Si el automata esta en mealy, empieza
	 * aqui, en moore empieza en generacionAutomataEquivalente
	 * 
	 * @return
	 */
	public String[][] convertirAutomataMealy(String[][] automataMealy) {

		// TODO
		// convertir automata

		maquina = automataMealy;
		String[][] respuesta = null;

		return respuesta;
	}

	/**
	 * Genera el automata equivalente siguiendo el algoritmo del enunciado
	 * 
	 * @return
	 */
	public void generacionAutomataEquivalente() {

		// ---------------------PASO 1------------------------------------------

		// 1. elimina los estados que no esten conexos a la maquina
		automataConexo();

		limpiarNulls();

		// -------------------PASO
		// 2----------------------------------------------

		// 2. pariticion inicial
		ArrayList<ArrayList<String>> pK = particionamientoPrimerNivel();

		// 3. particionamiento pk+1
		ArrayList<ArrayList<String>> PKMas1 = particionamientoKmas1(pK);

		// 4. verifica si pk es igual a pkMas1
		boolean terminoParticionamiento = verificarParticionamientosIguales(pK, PKMas1);
		if (terminoParticionamiento) {
			particionFinal = PKMas1;
		}

		// 5. itera hasta encontrar un pk = pk +1
		for (int i = 0; i < maquina[0].length && terminoParticionamiento == false; i++) {
			ArrayList<ArrayList<String>> PK = PKMas1;
			PKMas1 = particionamientoKmas1(PK);

			if (verificarParticionamientosIguales(PK, PKMas1)) {
				terminoParticionamiento = true;
				particionFinal = PKMas1;
			}
		}

		// -----------------------PASO
		// 3---------------------------------------------
		maquina = nuevoAutomataEquivalente();
		convertirmatrizNumericaAsciiAChar();

	}

	/**
	 * genera el nuevo aitomata equivalente a partir del particionamiento final
	 * que contiene todos los bloques para asignar los nuevos estados y los
	 * estados de llegada de cada uno segun si su entrada es 0 o 1.
	 * 
	 * @return
	 */
	public String[][] nuevoAutomataEquivalente() {

		String[][] automataequivalente = new String[particionFinal.size() + 1][4];
		automataequivalente[0][1] = "a";
		automataequivalente[0][2] = "b";
		automataequivalente[0][3] = "Salida";

		int letraEstado = 65;

		for (int i = 0; i < particionFinal.size(); i++, letraEstado++) {
			automataequivalente[i + 1][0] = (char) letraEstado + "";

			// determina el estado al que llega con la entrada 0:
			int posicion = (int) particionFinal.get(i).get(0).charAt(0);

			// PARA ENTRADA 0-----

			// toma el estado (letra) a la que llega el estado en 0, a partir de
			// la posicion anterior.
			String letra0 = maquina[posicion + 1 - 65][1];

			// busca el estado al que llega con 0
			String estado0 = estadoLlegada(letra0);

			automataequivalente[i + 1][1] = estado0;

			// PARA ENTRADA 1------

			// toma el estado (letra) a la que llega el estado en 0, a partir de
			// la posicion anterior.
			String letra1 = maquina[posicion + 1 - 65][2];

			// busca el estado al que llega con 0
			String estado1 = estadoLlegada(letra1);

			automataequivalente[i + 1][2] = estado1;

			// establece el numero de salida para ese estado
			automataequivalente[i + 1][3] = maquina[posicion + 1 - 65][3];
		}
		return automataequivalente;
	}

	public String estadoLlegada(String estadoOriginal) {
		String letraLlegada = "";

		// busca el bloque al que pertenece
		for (int j = 0; j < particionFinal.size(); j++) {
			for (int j2 = 0; j2 < particionFinal.get(j).size(); j2++) {
				if (particionFinal.get(j).get(j2).equals(estadoOriginal)) {
					letraLlegada = (char) j + 65 + "";
				}
			}
		}

		return letraLlegada;
	}

	/**
	 * Este metodo realiza el paso 1, se encarga de eliminar los estados que no
	 * estan conexos al automata, es decir, los estados a los que es imposible
	 * llegar.
	 * 
	 * modifica la maquina orginal
	 */
	public void automataConexo() {

		String ultimoEstado = maquina[0][maquina[0].length - 1];
		int ultimEstadoASCII = (int) ultimoEstado.charAt(0);

		// el 65 representa el estado inicial A
		int estadoActual = 65;

		for (int i = 1; i < maquina.length - 1 && estadoActual <= ultimEstadoASCII; i++) {
			boolean accesible = false;
			for (int j = 1; j < maquina[0].length && estadoActual <= ultimEstadoASCII; j++) {

				if ((int) maquina[i][j].charAt(0) == estadoActual) {
					accesible = true;
					estadoActual++;
					i = 1;
					j = 1;

					// si llego al final de la mtriz y el estadoActual no es
					// accesible, toda esa fila sera null
				} else if (j == maquina[0].length && i == maquina.length - 1 && accesible == false) {

					int posicionEstadoAEliminar = estadoActual - 65;

					for (int k = 0; k < maquina.length; k++) {
						maquina[posicionEstadoAEliminar + 1][k] = null;
					}
				}
			}
		}

	}

	/**
	 * crea un arraylist que contiene otro arraylist. el primer array, en la
	 * posicion 0 guarda las letras cuya salida es 0 e igual con el 1.
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<String>> particionamientoPrimerNivel() {
		ArrayList<ArrayList<String>> particionamientoN1 = new ArrayList<ArrayList<String>>();

		particionamientoN1.add(new ArrayList<String>());
		particionamientoN1.add(new ArrayList<String>());
		for (int i = 1; i < maquina[0].length - 1; i++) {

			// verifica que no sea nulo por si se ha eliminado algun estado que
			// no estuviera conexo en la maquina original
			if (maquina[i][0] != null) {

				if (maquina[i][3].equals("0")) {
					particionamientoN1.get(0).add(maquina[i][0]);
				} else {
					particionamientoN1.get(1).add(maquina[i][0]);
				}
			}
		}

		return particionamientoN1;
	}

	/**
	 * realiza el particionamiento de p(k+1) a partir del pk
	 * 
	 * @param pK
	 * @return
	 */
	public ArrayList<ArrayList<String>> particionamientoKmas1(ArrayList<ArrayList<String>> pK) {

		// clave significa que los estados de salida correspondan a los mismos
		// bloques en el anterior nivel de particionamiento
		// arraylist de claves guarda cada bloque del particionamiento
		ArrayList<String> claves = new ArrayList<String>();

		ArrayList<ArrayList<String>> pKMas1 = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < pK.size(); i++) {
			for (int j = 0; j < pK.get(i).size(); j++) {

				// 1. toma la primer letra del particionamiento original
				String letra = pK.get(i).get(j);

				// 2. saca la clave de la letra del punto (1)
				String claveDeBloque = buscarClave(letra, pK);

				// mira la posicion en la que debe ir la clave y la agrega segun
				// los bloques semejantes
				int posicion = posicionClave(claves, claveDeBloque);

				claves.add(posicion, claveDeBloque);

				// TODO
				pKMas1.add(new ArrayList<String>());

				// añade segun sus bloques, la letra que corresponda.
				pKMas1.get(posicion).add(letra);
			}

		}

		return pKMas1;

	}

	/**
	 * IMPORTANTE: LA ENUMERACION DE LOS BLOQUES EMPIEZA EN 0
	 * 
	 * @Descripcion: busca los bloques del particionamiento a los que
	 *               corresponde cada estado de respuesta del estado actual.
	 * 
	 *               ej: P1 = {{a,b},{c,g},{d,e,f} el estado a tiene como
	 *               salidas los estados g,f; por lo tanto su clave es "12"
	 * 
	 * @param estado
	 *            actual
	 * @param pK
	 *            particionamiento k
	 * @return devuelve una cadena de numeros que contienen los bloques a los
	 *         que corresponden los estados de respuesta.
	 */
	public String buscarClave(String estado, ArrayList<ArrayList<String>> pK) {

		int numeroEstado = (int) estado.charAt(0) - 65 + 1;
		String estadoSalida1 = maquina[numeroEstado][1];
		String estadoSalida2 = maquina[numeroEstado][2];

		boolean encontroclave = false;

		String respuesta = "";

		for (int i = 0; i < pK.size() && encontroclave == false; i++) {
			for (int j = 0; j < pK.get(i).size() && encontroclave == false; j++) {
				if (pK.get(i).get(j).equals(estadoSalida1)) {
					respuesta += i + "";
				}
				if (pK.get(i).get(j).equals(estadoSalida2)) {
					respuesta += i + "";
				}
			}
		}

		return respuesta;
	}

	/**
	 * Verifica que el particionamiento anterior sea igual al actual para parar.
	 * 
	 * @param pK
	 *            particionamiento anterior
	 * @param pKMas1
	 *            particionamiento actual
	 * @return verdadero si son iguales
	 */
	public boolean verificarParticionamientosIguales(ArrayList<ArrayList<String>> pK,
			ArrayList<ArrayList<String>> pKMas1) {

		boolean respuesta = true;

		for (int i = 0; i < pK.size(); i++) {
			for (int j = 0; j < pK.get(i).size(); j++) {
				if (pK.get(i).size() == pKMas1.get(i).size()) {
					if (!pK.get(i).get(j).equals(pKMas1.get(i).get(j))) {
						respuesta = false;
					}
				} else if (pK.get(i).size() != pKMas1.get(i).size()) {
					respuesta = false;
				}

			}
		}

		return respuesta;
	}

	/**
	 * Busca en el arraylist, la posicion a la que corresponde la clave del
	 * bloque
	 * 
	 * ej: claves = {00, 01, 21...} si la clave es igual a las almacenadas en la
	 * posicion 0 o 1 o ... entonces devuelve ese valor para seguir asignando
	 * ahi los demas estados que corresponden al mismo bloque en ese
	 * particionamiento
	 * 
	 * @param claves
	 * @param claveNueva
	 * @return
	 */
	public int posicionClave(ArrayList<String> claves, String claveNueva) {

		int respuesta = -1;

		if (claves.size() == 0) {
			respuesta = 0;
		}

		for (int i = 0; i < claves.size(); i++) {
			if (claves.get(i).equals(claveNueva)) {
				respuesta = i;
			}
		}

		if (respuesta == -1) {
			respuesta = claves.size();
		}

		return respuesta;
	}

	public void convertirmatrizNumericaAsciiAChar() {
		for (int i = 1; i < maquina.length; i++) {
			for (int j = 1; j < maquina[0].length - 1; j++) {
				// maquina[i][j] = (char)(Integer.parseInt(maquina[i][j]))+"";
			}
		}
	}

	public void limpiarNulls() {

		int numNulos = 0;
		for (int i = 1; i < maquina.length; i++) {
			if (maquina[i][0] == null) {
				numNulos++;
			}
		}

		if (numNulos>0) {
			String[][] matrizAuxiliar = new String[maquina.length - numNulos][maquina[0].length];
		for (int i = 0, a = 0; i < maquina.length && maquina[i][0]!=null; i++) {
			for (int j = 0; j < maquina[0].length; j++) {
					matrizAuxiliar[a][j] = maquina[i][j];
			}
			a++;
		}
		maquina = matrizAuxiliar;
		}
		
	}

	public String[][] getMaquina() {
		return maquina;
	}

	public void setMaquina(String[][] maquina) {
		this.maquina = maquina;
	}

}
