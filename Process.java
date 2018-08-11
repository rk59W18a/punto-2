package interazione;

import java.util.ArrayList;

import dominio.Categoria;
import dominio.Risorsa;
import logica.Anagrafica;
import logica.AnagraficaFruitori;
import logica.AnagraficaOperatori;
import logica.Archivio;
import logica.ArchivioPrestiti;
import logica.ArchivioStorico;
import logica.Utente;
import utility.Costanti;
import utility.InputDati;

public class Process 
{
	
	/**
	 * @pre : ag != null
	 */
    public Utente accesso()
    {
		String use = "";
		String pwd = "";
		boolean end = false;
		Utente ut = null;
		
	    do
	    {
			use = InputDati.leggiStringaNonVuota(Costanti.USERNAME);
			pwd = InputDati.leggiStringaNonVuota(Costanti.PASSWORD);

			if(ag.accedi(use, pwd))
			{
				ut = ag.getUtente(use, pwd);
				end = true;
			}
			else
			{
				System.out.println(Costanti.CREDENZIALI_ERRATE);
				 
				if(InputDati.leggiUpperChar(Costanti.RICHIESTA_PROSECUZIONE, "SN") == 'N')
				{
					end = true;
				}	
			}
	
		}while(!end);
		
	    return ut;
	}
    
    /**
     * @pre: (ut != null) && (arc != null) && (arc.getElencoCategorie().size != 0)
     */
    public ArrayList <Risorsa> ricercaRisorsa(Utente ut)
    {
       	Categoria c = null;
       	
   	 	System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
   	 	
   	    int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_RICERCA, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
   	    c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);
   	 		
   	 	if(c.getNome().equalsIgnoreCase("Libri"))
				return ricercaRisorsaLibri(ut, c);
   	 	else if(c.getNome().equalsIgnoreCase("Film"))
				return ricercaRisorsaFilm(ut, c);
   	 	else
   	 		return null;
    }
    
    /**
     * @pre: (ut != null) && (c != null)
     */
    public ArrayList <Risorsa> ricercaRisorsaLibri(Utente ut, Categoria c)
    {
   	    int numScelta = InputDati.leggiIntero(Costanti.AVVIO_RICERCA_LIBRI, Costanti.NUM_MINIMO, Costanti.NUM_MASSIMO_RICERCA);
   	    Object o = null;
   	    String s = "";
   	
   	    switch(numScelta)
   	    {
	    	   case 1: o = InputDati.leggiStringa(Costanti.INS_PAROLA_TITOLO_RISORSA);
	    			   s = Categoria.RIC_PER_TITOLO;
	    			   break;
	    		
	    	   case 2: o = InputDati.leggiStringa(Costanti.INS_COGNOME_AUTORE_LIBRO); 
	    		       s = Categoria.RIC_PER_AUTORE_I;
	    		       break;
	       
	    	   case 3: o = InputDati.leggiStringa(Costanti.INS_GENERE_RISORSA);
	    		       s = Categoria.RIC_PER_GENERE;
	    		       break;
	    	   
	    	   case 4: o = InputDati.leggiIntero(Costanti.INS_ANNOPUB_RISORSA);
	    	   		   s = Categoria.RIC_PER_ANNOPUB;
	    	   		   break;
	    		
	    	   case 5: o = InputDati.leggiStringa(Costanti.INS_CASAED_LIBRO);
	    	   		   s = Categoria.RIC_PER_CASAED;
	    	   		   break;
   	    }  
	    
   	    return ut.ricercaRisorsa(c, o, s);
    }
   
    /** 
     * @pre: (ut != null) && (c != null)
     */
    public ArrayList <Risorsa> ricercaRisorsaFilm(Utente ut, Categoria c)
    {
   	    int numScelta = InputDati.leggiIntero(Costanti.AVVIO_RICERCA_FILM, Costanti.NUM_MINIMO, Costanti.NUM_MASSIMO_RICERCA);
   	    Object o = null;
   	    String s = "";
   	
   	    switch(numScelta)
   	    {
   	     	case 1: o = InputDati.leggiStringa(Costanti.INS_PAROLA_TITOLO_RISORSA);
   	    			s = Categoria.RIC_PER_TITOLO;
	    			    break;
	    		
   	      	case 2: o = InputDati.leggiStringa(Costanti.INS_COGNOME_REGISTA_FILM); 
	    		        s = Categoria.RIC_PER_REGISTA;
	    		        break;
	       
   	        case 3: o = InputDati.leggiStringa(Costanti.INS_COGNOME_ATTORE_FILM);
	    		        s = Categoria.RIC_PER_ATTORE_I;
	                    break;
	    	   
   	      	case 4: o = InputDati.leggiIntero(Costanti.INS_ANNOPUB_RISORSA);
	                    s = Categoria.RIC_PER_ANNOPUB;
	                    break;
	    		
   	       	case 5: o = InputDati.leggiStringa(Costanti.INS_GENERE_RISORSA);
   	    		    s = Categoria.RIC_PER_GENERE;
   	    			break;
   	    }  
	    
   	    return ut.ricercaRisorsa(c, o, s);
    }
    
   /**
    * Metodo per la creazione di una stringa descrittiva delle risorse che sono state trovate mediante 
    * una ricerca
    * 
    * @pre: elencoRisorse != null
    * 
    * @param elencoRisorse: il vettore contenente le risorse, risultato dalla ricerca, da stampare
    */
   public String ricercaRisorsaFormatoStringa(ArrayList <Risorsa> elencoRisorse)
   {
  	    StringBuffer ris = new StringBuffer();
  	    ris.append(Costanti.INTESTAZIONE_RICERCA_RISORSE);
  	    
  	    if(elencoRisorse.size() == Costanti.VUOTO)
  	    	    ris.append(Costanti.RICERCA_VUOTA);
  	    
		for(int i = 0; i < elencoRisorse.size(); i++)
		{
			Risorsa r = elencoRisorse.get(i);
			ris.append("\t\t" + (i+1) + ")" + r.toString());
		}
		
		return ris.toString();
   }
   
   /**
    * @pre: (ut != null) && (arc != null) && (ap != null)
    */
   public String valutazioneDisponibilita(Utente ut)
   {
     	ArrayList <Risorsa> risorseTrovate = ricercaRisorsa(ut);
	    String s = "";
     	System.out.println(ricercaRisorsaFormatoStringa(risorseTrovate));
   	    
     	if(risorseTrovate.size() != Costanti.VUOTO)
     	{
     	   int num = InputDati.leggiIntero(Costanti.RICHIESTA_DIGITAZIONE_VALUTAZIONE, Costanti.NUM_MINIMO, risorseTrovate.size());
     	   Risorsa r = risorseTrovate.get(num-Costanti.NUM_MINIMO);
      	
  	       if(ut.valutazioneDisponibilita(ap, r))
     	        s += Costanti.RISORSA_DISPONIBILE;
     	   else
   		    s += Costanti.RISORSA_NON_DISPONIBILE;
     	}
     	else 
     	      s += Costanti.NO_VALUTAZIONE;
     	
     	return s;
   }
   
   public void controlloScadenzeAutomatiche()
   {
	   af.decadenzaFruitore(as);
       ap.scadenzaPrestito();
   }
   
   public boolean richiestaLogout()
   {
      	if(InputDati.leggiUpperChar(Costanti.RICHIESTA_LOGOUT, "SN") == 'S')
    		     return true;
      	else
    		     return false;
   }
    
}
