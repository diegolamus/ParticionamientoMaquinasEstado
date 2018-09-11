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
		String [][] respuesta = new String [maquina.length][maquina[0].length];
		
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
		String [] primerParticion =  new String [2];
		for (int i = 1; i < respuesta.length; i++) {
			//en el caso que la salida sea cero
			if (respuesta[maquina.length-1][i].equals("0")) {
				primerParticion [0] = respuesta[maquina.length-1][i]+",";
			}
			//cuando la salida es 1
			else {
				primerParticion [1] = respuesta[maquina.length-1][i]+",";
			}	
		}
		//ahora vamos a hacer los siguientes pariticonamientos a partir de los dos pariticionamientos creados anteriormente.
		
		
		
		
		
		
		
		return respuesta;
	}
	
	
	
	

}
