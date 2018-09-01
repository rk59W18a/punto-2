package logica.parte2.punto2;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.parte2.punto2.Categoria;


public class Archivio implements Serializable
{  
	private static final long serialVersionUID = 1L;

	private ArrayList <Categoria> elencoCategorie;
	
	public static final String DESCRIZIONE_ARCHIVIO = "\nL'archivio presenta il seguente contenuto:\n";
	
	public Archivio()
	{
		elencoCategorie = new ArrayList <Categoria> ();
	}
	
	public ArrayList <Categoria> getElencoCategorie()
	{
		return elencoCategorie;
	}
		
	public void aggiungiCategoria(Categoria c)
	{
		elencoCategorie.add(c);
	}
	
	public String stampaElencoCategorie()
	{
		StringBuffer ris = new StringBuffer();
		   
		for(int i = 0; i < elencoCategorie.size(); i++)
		{
			   Categoria c = elencoCategorie.get(i);
			   ris.append(i+1 + ")" + c.getNome() + "\n");
		}
		   
	    return ris.toString();
	}
	
	public String toString()
	{
		StringBuffer ris = new StringBuffer();
		ris.append(DESCRIZIONE_ARCHIVIO);
		
		for(int i = 0; i < elencoCategorie.size(); i++)
		{
			Categoria c = elencoCategorie.get(i);
			ris.append(i+1 + ")" + c.toString());
		}
		
		return ris.toString();
	}	
}