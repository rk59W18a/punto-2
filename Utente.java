package logica.parte2.punto2;

import java.io.Serializable;
import java.util.ArrayList;

import dominio.parte2.punto2.Categoria;
import dominio.parte2.punto2.Risorsa;

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
   
    public ArrayList <Risorsa> ricercaRisorsa(Categoria c, Object o, String r)
    {
       	 return c.ricercaRisorsa(o, r);
    }
    
    public boolean valutazioneDisponibilita(ArchivioPrestiti ap, Risorsa r)
    {
         return	ap.controlloDisponibilitaRisorsa(r);
    }
}