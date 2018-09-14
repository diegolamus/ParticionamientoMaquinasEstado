package modelo;

import java.util.ArrayList;

public class Modelo{
	

	/**
	 * Este metodo reduce un algoritmo de Meally que su respuesta es binaria, es decir, 1 o 0.
	 * @param maquina Una maquina de Meally que contiene en la primera fila las entradas o estimulos y en la
	 * primera columna contiene los estados que tiene la maquina.
	 * @return Una maquina de Meally equivalente.
	 */
	public static String [][] meallyReduccion (String [][] maquina) {
		
		//eliminamos los estados que no estan conectados
		for (int i = 1; i < maquina.length-1; i++) {
			int cantidadConectados = 0;
			for (int j = 1; j < maquina[0].length; j++) {
				if (!(maquina[i][j]==null)) {
					cantidadConectados++;
				}
			}
			//al asignarle el 2 a la salida, en el siguiente metodo nunca lo va a tomar en cuenta
			if (cantidadConectados==0) {
				maquina[i][maquina[0].length-1]="2";
			}
			
		}
		
		//en esta parte se hara la primera paricion de dos elementos
		ArrayList<String> primerParticion =  new ArrayList<String>(2);
		for (int i = 1; i < maquina.length; i++) {
			//en el caso que la salida sea cero
			if (maquina[maquina.length-1][i].equals("0")) {
				primerParticion.add(0,maquina[maquina.length-1][i]+",");
			}
			//cuando la salida es 1
			else {
				primerParticion.add(1,maquina[maquina.length-1][i]+",");
			}	
		}
		//ahora vamos a hacer los siguientes pariticonamientos a partir de los dos pariticionamientos creados anteriormente.
		
		
		//hacemos la busqueda de cada pareja de elementos para verificar si pertenecen a cada particion
		
		
		//renombramiento
		
		
		// buscamos ver las nuevas relaciones del renombramiento minimo
		
		
		// retorno la maquina minima equivalente
		
		
		
		
		return respuesta;
	}
	
	/**
	 * Este metodo verifica que la particion existe en la particion anterior.
	 * @param maquina Maquina de Meally
	 * @param a 
	 * @param b 
	 * @return true si la particion esta dentro de la particion anterior.
	 */
	public boolean verificarParticion (String [][] maquina, String a, String b, String [] particion) {
		ArrayList<String> fila1 = buscarValoresMaquina(a, maquina);
		ArrayList<String> fila2 = buscarValoresMaquina(b, maquina);
		if ( (fila1.size()>0) && (fila2.size()>0) ) {
			for (int i = 0; i < fila1.size(); i++) {
				// buscamos si cada pareja pertenece al particionamiento anterior
			}
		}
		
		
		
		return true;
	}
	

	/**
	 * Busca el valor en una maquina. Devuelve un arraylist con todos los estados de la maquina
	 * @param a
	 * @param maquina
	 * @return
	 */
	public ArrayList<String> buscarValoresMaquina (String a, String [][] maquina) {
		ArrayList<String> respuesta = new ArrayList<String>();
		for (int i = 1; i < maquina.length; i++) {
			
			if ( maquina[i][0].equals(a) ) {
					for (int j = 1; j < maquina[0].length-1; j++) {
						respuesta.add(maquina[i][j]);
					}
				}
		
		}
		return respuesta;
		
	}
	
	
}
