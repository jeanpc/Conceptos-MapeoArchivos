package com.mapeo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class IOArchivos {

	
	public static File[] buscarArchivos(String directorio) 
	{
		File dir = new File(directorio);

		File[] matches = dir.listFiles(new FilenameFilter()
		{
		  public boolean accept(File dir, String name)
		  {
		     return name.endsWith(".jsp") || name.endsWith(".html");
		  }
		});
		return matches;		
	}
	
	public static void leer(String ruta) 
	{
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null)
				System.out.println(linea);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	

	public static StringBuilder obtenerRecursosFront(File archivo, String regex) 
	{
		//File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		StringBuilder sb = null;
		
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			//archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			sb = new StringBuilder();
			while ((linea = br.readLine()) != null)
			{

				Pattern pattern = Pattern.compile("\".+[.](js|jpg|css|json|gif)\"");
				Matcher matcher = pattern.matcher(linea);
				if (matcher.find())
				{
					String[] strings = StringUtils.substringsBetween(matcher.group(0), "\"", "\"");
					for(String s : strings)
					{
						if(s.contains("."))
						{
							sb.append(s+"\n");
						}						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return sb;
	}	
	
    public static void escribir(String ruta, String contenido)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.println(contenido);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }

}
