package logica.parte2.punto2;

import java.io.Serializable;
import java.time.LocalDate;

public class AnagraficaFruitori extends Anagrafica implements Serializable
{
    private static final long serialVersionUID = 1L;
	
	public static final String INTESTAZIONE_ELENCO = "Elenco degli attuali fruitori: \n";
    public static final String ANAGRAFICA_VUOTA = "Al momento non sono presenti fruitori.\n";
	   
    public AnagraficaFruitori()
    {
   	    super();
    }
    
    public Fruitore getFruitore(String usef)
    {
      	for(int i = 0; i < elenco.size(); i++)
	    {
	    	  Fruitore f = (Fruitore) elenco.get(i);
	    	  
	    	  if(f.getUsername().equals(usef))
	    			   return f;
	    }
	    
	    return null;
    }
    
    public void aggiungiFruitore(Fruitore f)  
    {
   	    elenco.add(f);
    }
    
    public boolean verificaPresenza(String n, String c, LocalDate dn)
    {
    	   for(int i = 0; i < elenco.size(); i++)
    	   {
    		   Fruitore f = (Fruitore) elenco.get(i);
    		   
    		   if((f.getNome()).equalsIgnoreCase(n) && (f.getCognome().equalsIgnoreCase(c)) && (f.getDataDiNascita().isEqual(dn)))
                 return true;
    	   }
    	   
    	   return false;
    }
    
    public boolean verificaStessoUsername(String u) 
    {
   	    for(int i = 0; i < elenco.size() ; i++)
   	    {
   	       Fruitore f = (Fruitore) elenco.get(i);
   	    	   
   	    	   if((f.getUsername()).equals(u))
   	    		      return true;
   	    }
   	    
   	    return false;
    }
   
    public void decadenzaFruitore(ArchivioStorico as)
    {
   	 	for(int i = 0; i < elenco.size() ; i++)
   	 	{
   	 		Fruitore f = (Fruitore) elenco.get(i);	
   	 		
   	 	    if ((LocalDate.now().equals(f.getDataDiScadenza())) || (LocalDate.now().isAfter(f.getDataDiScadenza())))
   	 	    {
   	 	    	elenco.remove(f);
   	 	      	as.getDecadenzeFruitoriStoriche().aggiungiFruitore(f);
   	 	    }
   	 	}
    }
        
    public String toString()		
    {
   	    StringBuffer ris = new StringBuffer();
   	    
   	    if(elenco.size() == 0)
   	    {
   	    	    ris.append(ANAGRAFICA_VUOTA);
   	    }
   	    else
   	    {
   	   	    ris.append(INTESTAZIONE_ELENCO);

   	   	    for(int i = 0; i < elenco.size(); i++)
   	   	    {
   	   	    	    Fruitore f = (Fruitore) elenco.get(i);
   	   	    	    ris.append(i+1 + ")" + f.toString() + "\n");
   	   	    }
   	    }

   	    return ris.toString();
    }  
}