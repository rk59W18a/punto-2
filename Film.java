package dominio.parte2.punto2;

import java.io.Serializable;
import java.util.ArrayList;

public class Film extends Risorsa implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String regista;
    private ArrayList <String> attore_i;
    private int durataInMinuti;
    
    public static final String DESCRIZIONE_FILM = "Titolo: %s\n\t\tRegista: %s\n\t\tAttore/i: %s\n\t\tGenere: %s\n\t\tLingua: %s\n\t\tDurata (in minuti): %d\n\t\tAnno di uscita: %d\n\t\tNumero licenze: %d\n";
    
    public Film(String titolo, int licenze, String genere, int annoPub, String lingua, String r, ArrayList <String> a, int dm)
    {
    	 super(titolo, licenze, genere, annoPub, lingua);
    	 this.regista = r;
    	 this.attore_i = a;
    	 this.durataInMinuti =dm;
    }
   
    public String getRegista()
    {
    	 return regista;
    }
    
    public String getAttore()
    {
         StringBuffer att= new StringBuffer();
        
         for(int i = 0; i < attore_i.size(); i++)
         {
     	     att.append(attore_i.get(i));
     	     if(i < attore_i.size()-1)
     	       	 att.append("-");
         }
        	
         return att.toString();
    }
    
    public int getDurata()
    {
    	 return durataInMinuti;
    }
        
    public String toString()
    {
        StringBuffer ris = new StringBuffer();
        String att = getAttore();
       
        ris.append(String.format(DESCRIZIONE_FILM, getTitolo(), regista, att, getGenere(), getLingua(), durataInMinuti, getAnnoPub(), getNumLicenze()));
        return ris.toString();
    }
}