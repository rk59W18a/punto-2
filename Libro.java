package dominio.parte2.punto2;

import java.io.Serializable;
import java.util.ArrayList;


public class Libro extends Risorsa implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    private ArrayList <String> autore_i; 
    private int numPagine;
    private String casaEditrice;
    
    public static final String DESCRIZIONE_LIBRO = "Titolo: %s\n\t\tAutore/i: %s\n\t\tNumero pagine: %d\n\t\tAnno di pubblicazione: %d\n\t\tCasa editrice: %s\n\t\tLingua: %s\n\t\tGenere: %s\n\t\tNumero licenze: %d\n";
    
    /**
    * @pre: a != null
    * @post: autore_i != null
    */
    public Libro(String titolo, int licenze, String genere, int annoPub, String lingua, ArrayList <String> a, int np, String ce)
    {
    	 super(titolo, licenze, genere, annoPub, lingua);
    	 this.autore_i = a;
    	 this.numPagine = np;
    	 this.casaEditrice = ce;
    }
    
    public String getAutore()
    {
         StringBuffer aut = new StringBuffer();
        
         for(int i = 0; i < autore_i.size(); i++)
         {
     	     aut.append(autore_i.get(i));
     	     if(i < autore_i.size()-1)
     	       	 aut.append("-");
         }
        	
         return aut.toString();
    }
        
    public String getCasaEditrice()
    {
    	 return casaEditrice;
    }
    
    /**
     * @pre: autore_i != null
     */
    public String toString()
    {
         StringBuffer ris = new StringBuffer();
         String aut = getAutore();
       
         ris.append(String.format(DESCRIZIONE_LIBRO, getTitolo(), aut, numPagine, getAnnoPub(), casaEditrice, getLingua(), getGenere(), getNumLicenze()));
         return ris.toString();
    }
    
}