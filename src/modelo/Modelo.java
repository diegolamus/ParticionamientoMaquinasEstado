package modelo;

import java.util.ArrayList;

public class Modelo{
	

	
	public static String [][] meallyReduccion (String [][] maquina) {
		String [][] respuesta = new String [maquina.length][maquina[0].length];
		
		//en esta parte se hara la primera paricion de dos elementos
		String [] primerParticion =  new String [2];
		for (int i = 1; i < respuesta.length; i++) {
			//en el caso que la salida sea cero
			if (respuesta[i][maquina.length-1].equals("0")) {
				primerParticion [0] = respuesta[i][maquina.length-1]+",";
			}
			//cuando la salida es 1
			else {
				primerParticion [1] = respuesta[i][maquina.length-1]+",";
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		return respuesta;
	}
	
	
	
	

}
