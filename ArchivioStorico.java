package logica.parte2.punto2;

import java.io.Serializable;

import java.util.ArrayList;

import dominio.parte2.punto2.*;
import utility_2.Costanti;


public class ArchivioStorico implements Serializable
{
    private static final long serialVersionUID = 1L;
	
	private AnagraficaFruitori iscrizioniFruitoriStoriche;
	private AnagraficaFruitori rinnovoIscrizioniFruitoriStorici;
	private AnagraficaFruitori decadenzeFruitoriStoriche;
    private ArchivioPrestiti prestitiStorici;
    private ArchivioPrestiti prestitiConProrogheStoriche;
	private ArrayList <Risorsa> elencoRisorseRimosse;
	
	/**
	 * @post: (iscrizioniFruitoriStoriche != null) && (rinnovoIscrizioniFruitoriStorici != null)
	 *        && (decadenzeFruitoriStoriche != null) && (prestitiStorici != null) 
	 *        && (prestitiConProrogheStoriche != null) && (elencoRisorseRimosse != null)
	 */
	public ArchivioStorico()
	{
		iscrizioniFruitoriStoriche = new AnagraficaFruitori();
		rinnovoIscrizioniFruitoriStorici = new AnagraficaFruitori();
		decadenzeFruitoriStoriche = new AnagraficaFruitori();
 	   	prestitiStorici = new ArchivioPrestiti();
 	    prestitiConProrogheStoriche = new ArchivioPrestiti();
		elencoRisorseRimosse = new ArrayList <Risorsa> ();
	}
	
    public AnagraficaFruitori getIscrizioniFruitoriStoriche()
    {
      	return iscrizioniFruitoriStoriche;
    }
    
    public AnagraficaFruitori getRinnovoIscrizioniFruitoriStorici()
    {
    	    return rinnovoIscrizioniFruitoriStorici;
    }
    
    public AnagraficaFruitori getDecadenzeFruitoriStoriche()
    {
    	    return decadenzeFruitoriStoriche;
    }
	
    public ArchivioPrestiti getPrestitiStorici()
    {
        return prestitiStorici;
    }
    
    public ArchivioPrestiti getPrestitiConProrogheStoriche()
    {
        return prestitiConProrogheStoriche;
    }
    
    public ArrayList <Risorsa> getElencoRisorseRimosse()
    {
      	return elencoRisorseRimosse;
    }
	
    /**
     * @pre: (r != null) && (elencoRisorseRimosse != null)
     * @post: elencoRisorseRimosse.contains(r)
     */
	public void aggiungiRisorsaRimossa(Risorsa r)
	{
		elencoRisorseRimosse.add(r);
	}
	
	/**
	 * @pre: prestitiStorici != null
	 */
    public int numeroPrestitiPerAnno(int a)
    {
    	   int num = 0;
    	   
    	   for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
    	   {
    		   Prestito p = prestitiStorici.getElencoPrestiti().get(i);
    		   
    		   if(p.getDataDiInizioPrestito().getYear() == a)
    			    num++;
    	   }
    	   
    	   return num;
    }
    
    /** 
	 * @pre: prestitiConProrogheStoriche != null
	 */
    public int numeroProroghePerAnno(int a)
    {
    	   int num = 0;
    	   
    	   for(int i = 0; i < prestitiConProrogheStoriche.getElencoPrestiti().size(); i++)
    	   {
    		   Prestito p = prestitiConProrogheStoriche.getElencoPrestiti().get(i);
    		   
    		   if(!(p.getProrogaNonEffettuata()))
    		   {
    			   if(p.getDataProrogaEffettuata().getYear() == a)
    				     num++;	   
    		   }
    	   }
    	   
    	   return num;
    }
	
    /**
     * @pre: prestitiStorici != null
     */
	public String getRisorsaPiuRichiesta(int a)
	{
		Risorsa ris = null;
		int max = 0;
		ArrayList <Prestito> elencoPrestiti = new ArrayList <Prestito> ();
		
		for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
		{
			Prestito p = prestitiStorici.getElencoPrestiti().get(i);
			
			if(p.getDataDiInizioPrestito().getYear() == a)
			{
				elencoPrestiti.add(p);
			}
		}
			
		if(elencoPrestiti.size() != Costanti.VUOTO)
		{
		   for(int j = 0; j < elencoPrestiti.size(); j++) 
		   { 
			 int num = 0;
			 Risorsa r1 = elencoPrestiti.get(j).getRisorsaInPrestito();
			 Risorsa r2 = null;
					
			 for(int k = j+1; k < elencoPrestiti.size(); k++)
			 {
				r2 = elencoPrestiti.get(k).getRisorsaInPrestito();
				if(r2.equals(r1))
					  num++;
			 }
			
			 if(num > max)
			 {
			    ris = r1;
			    max = num;
			 }
			 
			 if(max == 0)
			 {
				 ris = r1;
			 }
		   }
           return ris.getTitolo();
		}
		else
			return "";
	}
	
	/**
     * @pre: (prestitiStorici != null) && (f != null)
     */
	public int numeroPrestitiPerFruitorePerAnno(Fruitore f, int a)
	{
	    	int num = 0;
	    	   
	    	for(int i = 0; i < prestitiStorici.getElencoPrestiti().size(); i++)
	    	{
	    		 Prestito p = prestitiStorici.getElencoPrestiti().get(i);
	    		   
	    		 if((p.getFruitoreAssociato().equals(f)) && p.getDataDiInizioPrestito().getYear() == a)
	    		       num++;
	    	}
	    	   
	    	return num;
	 }
	
}
