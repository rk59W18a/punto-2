package dominio.parte2.punto2;

import java.io.Serializable;


public abstract class Risorsa implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected String titolo;
	private int numLicenze;
	private String genere;
	private int annoPub;
	private String lingua;
   
    public Risorsa(String t, int lic, String g, int ap, String l)
    {
    	 this.titolo = t;
    	 this.numLicenze = lic;
    	 this.genere = g;
    	 this.annoPub = ap;
    	 this.lingua = l;
    }
    
    public String getTitolo()
    {
    	 return titolo;
    }
    
    public int getNumLicenze() 
    {
    	 return numLicenze;
    }
    
    public String getGenere()
    {
    	 return genere;
    }
    
    public int getAnnoPub()
    {
    	 return annoPub;
    }
    
    public String getLingua()
    {
    	 return lingua;
    }
    
    public abstract String toString();  
}
