package interazione_5;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Vector;



public class Processi {
	
    /**
	 * Metodo di interazione con l'utente per l'aggiunta di un nuovo fruitore all'elenco dei fruitori gia' presenti all'interno di af.
	 * Vengono effettuati dei controlli sulla correttezza della data di nascita inserita e sulla possibile presenza di fruitori gia' iscritti in possesso delle medesime credenziali indicate
	 * 
	 * @pre : af != null
	 * @pre : as != null
	 * 
	 * @param af : oggetto di tipo AnagraficaFruitori contenente l'elenco dei fruitori presenti ed i metodi per l'esecuzione dei vari controlli
	 * @param as : oggetto di tipo ArchivioStorico
	 */
	public void iscrizione(AnagraficaFruitori af, ArchivioStorico as)
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
			 * I metodi di controllo verificano se un utente gia' iscritto cerca di iscriversi nuovamente, se non vi sono casi di condivisione di username
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
	 * Metodo di interazione con l'utente per l'accesso al sistema.
	 * Vengono effettuati dei controlli sulla correttezza dello username e della password indicati
	 * 
	 * @pre : ag != null
	 * 
	 * @param ag : oggetto di tipo Anagrafica contenente l'elenco degli utenti presenti ed il metodo per l'accesso
	 * @return l'utente specificato dalle credenziali indicate
	 */
    public Utente accesso(Anagrafica ag)
    {
		String use = "";
		String pwd = "";
		boolean end = false;
		Utente ut = null;
		
	    do
	    {
			use = InputDati.leggiStringaNonVuota(Costanti.USERNAME);
			pwd = InputDati.leggiStringaNonVuota(Costanti.PASSWORD);

			/**
			 * Se viene effettivamente reperito l'utente indicato, l'accesso si conclude con successo.
			 * Altrimenti, a meno che l'utente non esprima la volonta' di terminare l'operazione, si procede con le modifiche necessarie sui dati inseriti
			 */
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
     * Metodo di interazione con l'operatore per l'aggiunta di una risorsa ad una (sotto)categoria dell'archivio
     * 
     * @pre: (op != null) && (arc != null) && (arc.getElencoCategorie().size != 0)
     * 
     * @param op: l'operatore che effettua l'aggiunta della risorsa
     * @param arc: l'archivio a cui aggiungere la risorsa
     */
    public void aggiungiRisorsa(Operatore op, Archivio arc)
    {
    	Categoria c = null;
    	SottoCategoria sc = null;
    	Risorsa nuovar = null;
    	     
    	System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
    	
        int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_AGGIUNTA_RISORSA, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
        c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);
        
        if(c.getElencoSottoCategorie() == null)
        {
        	System.out.printf(Costanti.CAT_SENZA_SOTTO, c.getNome());
          	    
          	if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_CAT, "SN") == 'S')
          	{
          		if((c.getNome()).equalsIgnoreCase(Costanti.LIBRI))  
          		{
          			nuovar = InserimentoRisorsa.inserisciLibro();
          		}
          		else if((c.getNome()).equalsIgnoreCase(Costanti.FILM))  
          		{
          			nuovar = InserimentoRisorsa.inserisciFilm();
          		}
          		
        	    if(nuovar != null)
        	    {
          			if(c.getRisorsa(nuovar.getTitolo()) == null )
          			{
          				op.aggiungiRisorsaCategoria(nuovar, c);
          				System.out.println(Costanti.OP_SUCCESSO);
          			}
          			else
          				System.out.println(Costanti.OP_NO_SUCCESSO_1);
          		}
          			
          	}
          	    
        }
        else if(c.getElencoSottoCategorie().size() == Costanti.VUOTO)
        {
        	System.out.printf(Costanti.CONTENUTO_ELENCO_SOTTO_VUOTO, c.getNome());
        }
        else
        {
        	System.out.printf(Costanti.CONTENUTO_CAT_SOTTO, c.getNome(), c.stampaElencoSottocategorie());
            	
            if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_SOTTO, "SN") == 'S')
            {	 
            	int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_SOTTO_AGGIUNTA_RISORSA, Costanti.NUM_MINIMO, (c.getElencoSottoCategorie()).size());
            	sc = (c.getElencoSottoCategorie()).get(num2-Costanti.NUM_MINIMO);
        	    	    	    	    	    
            	if((c.getNome()).equalsIgnoreCase(Costanti.LIBRI))  
            	{
            		nuovar = InserimentoRisorsa.inserisciLibro();
            	}
            	else if((c.getNome()).equalsIgnoreCase(Costanti.FILM))  
            	{
            		nuovar = InserimentoRisorsa.inserisciFilm();
            	}
            	
            	if(nuovar != null)
            	{
            	
            		if(!(c.verificaPresenza(nuovar.getTitolo())))
            		{       
      	    	   		if( (nuovar.getGenere()).equalsIgnoreCase(sc.getNome()) )
      	    	   		{
      	    	   			op.aggiungiRisorsaCategoria(nuovar, sc);
      	    	   			System.out.println(Costanti.OP_SUCCESSO);
      	    	   		}
      	    	   		else
      	    	   			System.out.println(Costanti.OP_NO_SUCCESSO_2);
            		}
            		else
            			System.out.println(Costanti.OP_NO_SUCCESSO_1);
            	}
            	
            }
        	    
        }
          	
    }
     
     /**
      * Metodo di interazione con l'operatore per la rimozione di una risorsa da una (sotto)categoria dell'archivio
      * 
      * @pre: (op != null) && (arc != null) && (arc.getElencoCategorie().size != 0) && (as != null)
      * 
      * @param op: l'operatore che effettua la rimozione della risorsa
      * @param arc: l'archivio da cui rimuovere la risorsa
      * @param as: l'archivio storico
      */
     public void rimuoviRisorsa(Operatore op, Archivio arc, ArchivioStorico as)
     {
        	Categoria c = null;
 	    SottoCategoria sc = null;
 	    Risorsa daEliminare = null;
 	    
 	    System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
 	    
 	    int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_RIMOZIONE_RISORSA, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
         c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);
     	
         if(c.getElencoSottoCategorie() == null)
     	{
         	if((c.getElencoRisorse()).size() != 0)
         	{
         		System.out.printf(Costanti.CAT_SENZA_SOTTO, c.getNome());
         		System.out.printf(Costanti.CONTENUTO_CAT_RISORSA, c.getNome(), c.stampaElencoRisorse());

         		if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_RISORSA, "SN") == 'S')
     	    	{
         			int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_RIMOZIONE, Costanti.NUM_MINIMO, (c.getElencoRisorse()).size());
     		    	daEliminare = (c.getElencoRisorse()).get(num2-Costanti.NUM_MINIMO);
     		    	op.rimuoviRisorsaCategoria(daEliminare, c);
     		    	as.aggiungiRisorsaRimossa(daEliminare);
             		System.out.println(Costanti.OP_SUCCESSO);
     	    	}

     	    } 
       	    else
     	    {
         		System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_CAT_VUOTO, c.getNome());
     	    }
         
     	 }
         else if((c.getElencoSottoCategorie()).size() == Costanti.VUOTO)
         {
   	       	System.out.printf(Costanti.CONTENUTO_ELENCO_SOTTO_VUOTO, c.getNome());
         }
         else
         {
         	System.out.printf(Costanti.CONTENUTO_CAT_SOTTO, c.getNome(), c.stampaElencoSottocategorie());
       	       		
       	    if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_SOTTO, "SN") == 'S')
       	    {
       	    	int num2 = InputDati.leggiIntero(Costanti.INS_NUMERO_SOTTO_RIMOZIONE_RISORSA, Costanti.NUM_MINIMO, (c.getElencoSottoCategorie()).size());
     	    	sc = (c.getElencoSottoCategorie()).get(num2-Costanti.NUM_MINIMO);
     	    	   
     	    	if(sc.getElencoRisorse().size() != Costanti.VUOTO)
     	    	{
     	           	System.out.printf(Costanti.CONTENUTO_SOTTO, sc.getNome(), sc.stampaElencoRisorse());
     	           	
     	      	    if(InputDati.leggiUpperChar(Costanti.INS_PROCEDERE_RISORSA, "SN") == 'S')
     	      	    {
     	      	    	int num3 = InputDati.leggiIntero(Costanti.INS_NUMERO_RISORSA_RIMOZIONE, Costanti.NUM_MINIMO, (sc.getElencoRisorse()).size());
         	    		daEliminare = (sc.getElencoRisorse()).get(num3-Costanti.NUM_MINIMO);
         	    		op.rimuoviRisorsaCategoria(daEliminare, sc);
         	    		as.aggiungiRisorsaRimossa(daEliminare);
         	           	System.out.println(Costanti.OP_SUCCESSO);
     	      	    }
     	    		
     	    	}
     	    	else
     	    		System.out.printf(Costanti.CONTENUTO_ELENCO_RISORSE_SOTTO_VUOTO, sc.getNome());
 	 
       	    }

       	    	 	   
         }   
   	    
     }
     
     /**
      * Metodo di interazione con il fruitore, al quale permette di registrare un prestito se sono rispettate
      * delle condizioni. Se la registrazione del prestito avviene con successo, il prestito viene aggiunto all'archivio
      * dei prestiti
      * 
      * @pre: (f != null) && (arc != null) && (ap != null) && (arc.getElencoCategorie().size != 0) && (as != null)
      * 
      * @param f: il fruitore che vuole effettuare la registrazione del prestito
      * @param arc: l'archivio delle risorse
      * @param ap: l'archivio dei prestiti
      * @param as: l'archivio storico
      */
     public void registraPrestito(Fruitore f, Archivio arc, ArchivioPrestiti ap, ArchivioStorico as) 
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
      * Metodo di interazione con il fruitore per la richiesta della proroga di una risorsa
      * 
 	  *	@pre: (f != null) && (ap != null) && (as != null)
      * 
      * @param f: il fruitore che richiede la proroga
      * @param ap: l'archivio dei prestiti
      * @param as: l'archivio storico
      */
     public void richiediProroga(Fruitore f, ArchivioPrestiti ap, ArchivioStorico as) 
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
    
     /**
      * Metodo di interazione con l'utente per la scelta della categoria a cui appartiene
      * la risorsa che si vuole cercare: in base alla categoria scelta il metodo invocher�
      * il metodo pi� specifico di ricerca in base alla categoria selezionata
      * 
      * @pre: (ut != null) && (arc != null) && (arc.getElencoCategorie().size != 0)
      * 
      * @param ut: l'utente che effettua la ricerca 
      * @param arc: l'archivio delle risorse
      * @return il vettore contenente le risorse che hanno soddisfatto la ricerca
      */
     public Vector <Risorsa> ricercaRisorsa(Utente ut, Archivio arc)
     {
        	Categoria c = null;
        	
    	 	System.out.printf(Costanti.CONTENUTO_ARC, arc.stampaElencoCategorie());
    	 	
    	    int num1 = InputDati.leggiIntero(Costanti.INS_NUMERO_CAT_RICERCA, Costanti.NUM_MINIMO, (arc.getElencoCategorie()).size());
    	    c = (arc.getElencoCategorie()).get(num1-Costanti.NUM_MINIMO);
    	 		
    	 	if(c.getNome().equalsIgnoreCase(Costanti.LIBRI))
 				return ricercaRisorsaLibri(ut, c);
    	 	else if(c.getNome().equalsIgnoreCase(Costanti.FILM))
 				return ricercaRisorsaFilm(ut, c);
    	 	else
    	 		return null;
     }
     
     /**
      * Metodo di interazione con l'utente per la ricerca di un libro secondo diverse opzioni
      * di ricerca
      * 
      * @pre: (ut != null) && (c != null)
      * 
      * @param ut: l'utente che effettua la ricerca
      * @param c: la categoria delle risorse di cui si sta effettuando la ricerca
      * @return il vettore contenente le risorse che hanno soddisfatto la ricerca
      */
     public Vector <Risorsa> ricercaRisorsaLibri(Utente ut, Categoria c)
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
 	    
    	    return ut.ricercaRisorse(c, o, s);
     }
    
     /**
      * Metodo di interazione con l'utente per la ricerca di un film secondo diverse opzioni
      * di ricerca
      * 
      * @pre: (ut != null) && (c != null)
      * 
      * @param ut: l'utente che effettua la ricerca
      * @param c: la categoria delle risorse di cui si sta effettuando la ricerca
      * @return il vettore contenente le risorse che hanno soddisfatto la ricerca
      */
     public Vector <Risorsa> ricercaRisorsaFilm(Utente ut, Categoria c)
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
 	    
    	    return ut.ricercaRisorse(c, o, s);
     }
     
    /**
     * Metodo per la creazione di una stringa descrittiva delle risorse che sono state trovate mediante 
     * una ricerca
     * 
     * @pre: elencoRisorse != null
     * 
     * @param elencoRisorse: il vettore contenente le risorse, risultato dalla ricerca, da stampare
     * @return la stringa desrittiva delle risorse
     */
    public String ricercaRisorsaFormatoStringa(Vector <Risorsa> elencoRisorse)
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
    * Metodo di interazione con l'utente per la valutazione della disponibilit� di una risorsa in archivio
    * 
    * @pre: (ut != null) && (arc != null) && (ap != null)
    * 
    * @param ut: l'utente che effettua la valutazione
    * @param arc: l'archivio delle risorse
    * @param ap: l'archivio dei prestiti
    * @return una stringa contenente informazioni riguardo alla disponibilita' di una risorsa
    */
   public String valutazioneDisponibilita(Utente ut, Archivio arc, ArchivioPrestiti ap)
   {
	    Vector<Risorsa> risorseTrovate = ricercaRisorsa(ut, arc);
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
   
   /**
    * Metodo di interazione con l'operatore per la scelta dell'interrogazione da fare all'archivio storico
    * 
    * @pre: (o != null) && (af != null) && (as != null)
    * 
    * @param o: l'operatore che vuole effettuare un'interrogazione
    * @param af: l'anagrafica dei fruitori
    * @param as: l'archivio storico da interrogare
    * @return una stringa contenente il risultato dell'interrogazione
    */
   public String sceltaInterrogazione(Operatore o, AnagraficaFruitori af, ArchivioStorico as)
   {
	    int numScelta = InputDati.leggiIntero(Costanti.SCELTA_INTERROGAZIONE, Costanti.NUM_MINIMO, Costanti.NUM_MASSIMO_RICERCA);
	    int anno = 0;
	    Fruitore f = null;
	    String s1 = "";
	    String s2 = "";
	    
	    anno = InputDati.leggiIntero(Costanti.INS_ANNO_RICHIESTO, Costanti.ANNO_MINIMO_INTERROGAZIONE, LocalDate.now().getYear());
	    
	    switch(numScelta)
	    {
	    	   case 1: s2 = Costanti.NUM_PRESTITI_PER_ANNO + o.visualizzaPrestitiPerAnno(as, anno);
	    			   break;
	    		
	       case 2: s2 = Costanti.NUM_PROROGHE_PER_ANNO + o.visualizzaProroghePerAnno(as, anno);
	    			   break;
	       
	       case 3: s2 = o.visualizzaRisorsaPiuRichiesta(as, anno);
	    	           if(!(s2.equals("")))
	    	           {
	    	              s2 = Costanti.TITOLO_RISORSA_PIU_PRESTITI_PER_ANNO + s2;
	    	           }
	    	           else
	    	           {
	    	        	      s2 = Costanti.TITOLO_RISORSA_SENZA_PRESTITI_PER_ANNO;
	    	           }
	    	           break;
	    	   
	       case 4: s1 = InputDati.leggiStringa(Costanti.INS_FRUITORE_RICHIESTO);
	    			 
	    			   if(af.getFruitore(s1) != null)
	    			   {
	    				  f = af.getFruitore(s1);
	   	    			  s2 = Costanti.NUM_PRESTITI_PER_FRUITORE_PER_ANNO + o.visualizzaNumeroPrestitiPerFruitorePerAnno(as, f, anno);
	    			   }
	    			   else
	    			   {
	    				  s2 = Costanti.FRUITORE_NON_TROVATO;
	    		 	   }
	    			   break;
	    }  
	    
	    return s2;
   }
   
   /**
    * Metodo di interazione con l'utente per la conferma della richiesta di logout
    * @return boolean : true se l'utente conferma il logout
    */
   public boolean richiestaLogout()
   {
   	if(InputDati.leggiUpperChar(Costanti.RICHIESTA_LOGOUT, "SN") == 'S')
   		return true;
   	else
   		return false;
   }
   
   public boolean controlloScadenze() {

   	af.decadenzaFruitore(as);
   	ap.scadenzaPrestito();
   }

}
