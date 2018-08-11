package interazione;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

import dominio.Categoria;
import dominio.Risorsa;
import dominio.SottoCategoria;
import logica.AnagraficaFruitori;
import logica.Archivio;
import logica.ArchivioPrestiti;
import logica.ArchivioStorico;
import logica.Fruitore;
import logica.Prestito;
import utility.Costanti;
import utility.InputDati;

public class ProcessFruitore extends Process
{
	/**
	 * @pre: (af != null) && (as != null)
	 */
	public void iscrizione()
	{
		String nome = "";
		String cognome = "";
		String use = "";
		String pwd = "";
		int giorno = 0;
		int mese = 0;
		int anno = 0;

		boolean end = true;
		
		boolean ins_nome = true;
		boolean ins_cognome = true;
		boolean ins_use = true;
		boolean ins_pwd = true;
		boolean ins_data = true;
		
		/**
		 * Ciclo globale per l'inserimento di un nuovo fruitore nel sistema in accordo con le condizioni indicate.
		 * E' possibile suddividere tale ciclo in 4 parti:
		 * 1 - Inserimento dei parametri richiesti
		 * 2 - Controllo sulla correttezza lessicale della data di nascita inserita
		 * 3 - Controlli sulle condizioni necessarie per l'iscrizione
		 * 4 - Completamento iscrizione o richiesta di perfezionamento della stessa
		 */
	    do
	    {
	      	/**
	    	 * Inserimento parametri
	    	 */
	      	if(ins_nome)
	    	{
				nome = InputDati.leggiStringaNonVuota(Costanti.INS_NOME);
	    	}
	    	
	    	if(ins_cognome)
	      	{
				cognome = InputDati.leggiStringaNonVuota(Costanti.INS_COGNOME);
	      	}
	    	
	      	if(ins_use)
	    	{
				use = InputDati.leggiStringaNonVuota(Costanti.INS_USERNAME);
	      	}
	     	
	      	if(ins_pwd)
	      	{
				pwd = InputDati.leggiStringaNonVuota(Costanti.INS_PASSWORD);
	      	}
	    	
			Fruitore f = null;	
			boolean exc = false;			
			end = true;
			
			/**
			 * Il controllo per la correttezza della data di nascita inserita viene gestito autonomamente dalla classe LocalDate.
			 * Nel caso in cui quest'ultima generi un'eccezione, e dunque la data inserita non sia lessicalmente corretta, viene modificata un'opportuna
			 * variabile booleana che impedisce la fuoriuscita dal ciclo do-while fintanto che non viene digitata una data valida
			 */
			while(!exc) {
			
				try 
				{
					if(ins_data)
					{
						giorno = InputDati.leggiIntero(Costanti.INS_GIORNO_NASCITA);
						mese = InputDati.leggiIntero(Costanti.INS_MESE_NASCITA);
						anno = InputDati.leggiIntero(Costanti.INS_ANNO_NASCITA);
					}
					
					f = new Fruitore(nome, cognome, anno, mese, giorno, use, pwd);
					
					exc = true;
				}
				catch(DateTimeException e)
				{
					System.out.println(Costanti.DATA_DI_NASCITA_ERRATA);
				}
				
			};
			
			ins_nome = false;
			ins_cognome = false;
			ins_use = false;
			ins_pwd = false;
			ins_data = false;

			/**
			 * I metodi di controllo verificano se un utente già iscritto cerca di iscriversi nuovamente, se non vi sono casi di condivisione di username
			 * e se l'utente e' maggiorenne. In caso di inesattezze vengono reimpostati i parametri di inserimento e viene impedita la fuoriuscita dal ciclo globale
			 */
			if(af.verificaPresenza(f.getNome(), f.getCognome(), f.getDataDiNascita()))
			{
				System.out.println(Costanti.ISCRIZIONE_NON_OK_FRUITORE_GIA_ISCRITTO);
				ins_nome = true;
				ins_cognome = true;
				ins_data = true;
				end = false;
			}
			
			if(af.verificaStessoUsername(f.getUsername()))
			{
				System.out.println(Costanti.ISCRIZIONE_NON_OK_STESSO_USERNAME);
				ins_use = true;
				end = false;
			}
			
			if(Period.between(f.getDataDiNascita(), LocalDate.now()).getYears() < Costanti.MAGGIORE_ETA)
			{
				System.out.println(Costanti.ISCRIZIONE_NON_OK_MAGGIORE_ETA);
				ins_nome = true;
				ins_cognome = true;
				ins_data = true;
				end = false;
			}
			
			/**
			 * Se non sono stati segnalati errori, l'iscrizione si conclude con successo.
			 * Altrimenti, a meno che l'utente non esprima la volonta' di terminare l'operazione, si procede con le modifiche necessarie sui dati inseriti
			 */
			if(end)
			{
				af.aggiungiFruitore(f);
				as.getIscrizioniFruitoriStoriche().aggiungiFruitore(f);
				System.out.println(Costanti.ISCRIZIONE_OK);
			}
			else
			{
	
				if(InputDati.leggiUpperChar(Costanti.RICHIESTA_PROSECUZIONE, "SN") == 'N')
				{
					end = true;				
					System.out.println(Costanti.ISCRIZIONE_NON_OK);
				}
				
			}
			
		}while(!end);
	    
	}
	
    /** 
     * @pre: (f != null) && (arc != null) && (ap != null) && (arc.getElencoCategorie().size != 0) && (as != null)
     */
    public void registraPrestito(Fruitore f) 
    {
    	 Categoria c = null;
    	 SottoCategoria sc = null;
    	 Risorsa r = null;
    	 Prestito nuovo = null;
    	
    	 System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
    	 int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_PRESTITO, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
    	 c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);

    	 if(c.getElencoSottoCategorie() == null)
    	 {
      	    if(c.getElencoRisorse().size() != Costanti.VUOTO)  
    	    {
      	    	     System.out.printf(Costanti.CONTENUTO_CAT_RISORSA, c.getNome(), c.stampaElencoRisorse());
    	      	 
      	    	     if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_PRESTITO, "SN") == 'S')
   	    	     {
      	    	        int num = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_PRESTITO, Costanti.NUM_MINIMO, c.getElencoRisorse().size());
    	                r = c.getElencoRisorse().get(num-Costanti.NUM_MINIMO);
    	    	
    	                if(ap.controlloDisponibilitaRisorsa(r) && ap.controlloPerUlteriorePrestito(c, f.getUsername()) && !(ap.verificaPresenza(r, f.getUsername())))
  	   	                {
  	   		               nuovo = new Prestito(c, f, r);
  	   		               f.registraNuovoPrestito(ap, nuovo);
  	   		               as.getPrestitiStorici().aggiungiPrestito(nuovo);
  	    	               System.out.println(Costanti.OP_SUCCESSO);
  	   	                }
  	   	                else if(!(ap.controlloDisponibilitaRisorsa(r)))
   	                          System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_1);
                       else if(!(ap.controlloPerUlteriorePrestito(c, f.getUsername())))
 	                              System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_2);
                       else  
               	              System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_3);
   	    	      }
  	    	} 
      	    else
      	    	    System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_CAT_VUOTO, c.getNome());
    	 }
    	 else if((c.getElencoSottoCategorie()).size() == Costanti.VUOTO)
    	 {
    		System.out.printf(Costanti.CONTENUTO_ELENCO_SOTTO_VUOTO, c.getNome());
    	 }
    	 else
    	 {
    		System.out.printf(Costanti.CONTENUTO_CAT_SOTTO, c.getNome(), c.stampaElencoSottocategorie());
    		int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_SOTTOC_PRESTITO, Costanti.NUM_MINIMO, (c.getElencoSottoCategorie()).size());
    	    sc = (c.getElencoSottoCategorie()).get(num2-Costanti.NUM_MINIMO);
    	    	    	    	    	    
    	    if((sc.getElencoRisorse()).size() != Costanti.VUOTO)
	        {
	           System.out.printf(Costanti.CONTENUTO_SOTTO, sc.getNome(), sc.stampaElencoRisorse());
	           
	           if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_PRESTITO, "SN") == 'S')
	           {
	              int num = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_PRESTITO, Costanti.NUM_MINIMO, sc.getElencoRisorse().size());
	       	      r = sc.getElencoRisorse().get(num-Costanti.NUM_MINIMO);
           
	       	      if(ap.controlloDisponibilitaRisorsa(r) && ap.controlloPerUlteriorePrestito(c, f.getUsername()) && !(ap.verificaPresenza(r, f.getUsername())))
	              {
  	   		         nuovo = new Prestito(c, f, r);
  	   		         f.registraNuovoPrestito(ap, nuovo);
  	   		         as.getPrestitiStorici().aggiungiPrestito(nuovo);
	      	         System.out.println(Costanti.OP_SUCCESSO);
	              }
	              else if(!(ap.controlloDisponibilitaRisorsa(r)))
	      	                System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_1);
	              else if(!(ap.controlloPerUlteriorePrestito(c, f.getUsername())))
	    	                System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_2);
	              else
	            	        System.out.println(Costanti.OP_NO_SUCCESSO_PRESTITO_3);
	           }
	        } 
    	    else
    		    System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_SOTTO_VUOTO, sc.getNome());	     
    	}	
    }
     
    /**
     * @pre: (f != null) && (ap != null) && (as != null)
     */
    public void richiediProroga(Fruitore f) 
    { 
   	    if(ap.getPrestiti(f.getUsername()).size() != Costanti.VUOTO)
   	    {
   	       System.out.println(f.visualizzaPrestitiInCorso(ap));
   	
   	       if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_PROROGA, "SN") == 'S')
   	       {
   	          int num = InputDati.leggiIntero(Costanti.INS_NUMERO_PRESTITO_PROROGA, Costanti.NUM_MINIMO, ap.getPrestiti(f.getUsername()).size());
   	          Prestito pr = ap.getPrestiti(f.getUsername()).get(num-Costanti.NUM_MINIMO);
   	      
   	          if(f.registraProrogaPrestito(pr))
   	          {
   	    	       as.getPrestitiConProrogheStoriche().aggiungiPrestito(pr);;
   	    	       System.out.println(Costanti.OP_SUCCESSO);
   	          }
   	          else
   	          {
   	    	       System.out.println(Costanti.OP_NO_SUCCESSO_PROROGA_1);
   	          }
   	       }
   	    }
   	    else
   	    {
   		    System.out.println(Costanti.OP_NO_SUCCESSO_PROROGA_2);
   	    }  
    }
    
}
