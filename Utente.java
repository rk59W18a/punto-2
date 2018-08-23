package logica.parte2.punto2;

import java.io.Serializable;

import java.util.ArrayList;

import dominio.parte2.punto2.*;

public class Utente implements Serializable
{
    private static final long serialVersionUID = 1L;
	
	private String nome;
    private String cognome;
    private String username;
    private String password;
    
    
    public Utente(String n, String c, String u, String p)
    {
    	this.nome = n;
    	this.cognome = c;
    	this.username = u;
    	this.password = p;
    }
    
    public String getNome()
    {
   	     return nome;
    }
    
    public String getCognome()
    {
   	     return cognome;
    }
    
    public String getUsername()
    {
   	     return username;
    }
    
    public String getPassword()
    {
    	 return password; 
    }
    
    /**
     * Metodo che permette all'utente di effettuare la ricerca di una risorsa in base alla categoria 
     * 
     * @pre: (c != null) && (o != null)
     * 
     * @param c: la categoria delle risorse da cercare
     * @param o: il generico oggetto che rappresenta quello che l'utente ha digitato con lo scopo di cercarlo nella categoria
     * @param r: stringa che specifica in base a quale parametro avviene la ricerca
     * @return il vettore con all'interno le risorse che hanno soddisfatto la ricerca
     */
    public ArrayList <Risorsa> ricercaRisorsa(Categoria c, Object o, String r)
    {
       	 return c.ricercaRisorsa(o, r);
    }
    
    /**
     * @pre: (ap != null) && (r != null)
     */
    public boolean valutazioneDisponibilita(ArchivioPrestiti ap, Risorsa r)
    {
         return	ap.controlloDisponibilitaRisorsa(r);
    }
}