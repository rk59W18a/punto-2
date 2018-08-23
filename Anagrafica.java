package logica.parte2.punto2;

import java.io.Serializable;

import java.util.*;


public class Anagrafica implements Serializable
{
    private static final long serialVersionUID = 1L;
	
	protected ArrayList <Utente> elenco;
    

    public Anagrafica()
    {
    	   elenco = new ArrayList <Utente> ();
    }
    
    public ArrayList <Utente> getElenco()
    {
    	   return elenco;
    }
    
    /**
     * @pre: elenco != null
     */
    public Utente getUtente(String u, String p)
    {
      	for(int i = 0; i < elenco.size(); i++)
	    {
	    	  Utente ut = elenco.get(i);
	    	  
	    	  if((ut.getUsername()).equals(u) && (ut.getPassword().equals(p)))
	    			   return ut;
	    }
	    
	    return null;
    }
    
    /** 
     * @pre : elenco != null
     */
    public boolean accedi(String u, String p)
    {
    	    for(int i = 0; i < elenco.size(); i++)
    	    {
    	    	  Utente ut = elenco.get(i);
    	    	  
    	    	  if((ut.getUsername()).equals(u) && (ut.getPassword().equals(p)))
    	    			   return true;
    	    }
    	    
    	    return false;
    }
    
}