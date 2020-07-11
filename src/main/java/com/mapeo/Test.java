package com.mapeo;

import java.io.File;

public class Test {
	
	public static void main(String[] args) 
	{
		
		String directorio = "D:\\Cursos\\FrontEnd\\workspace\\springapp\\src\\main\\webapp\\WEB-INF\\views";
		String ruta = "C:\\frmAnularDocuTransp.jsp";
		
		File[] htmlsJsps = IOArchivos.buscarArchivos(Constantes.IN_DIRECTORIO);
		
		for(File file : htmlsJsps)
		{
			StringBuilder sb = IOArchivos.obtenerRecursosFront(file,Constantes.REGEX);
			IOArchivos.escribir(Constantes.OUT_DIRECTORIO+"\\"+file.getName()+".txt", sb.toString());	
		}
		
	}
}