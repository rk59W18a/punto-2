package interazione_5;

import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Vector;

import dominio_5.*;
import logica_5.*;
import utility.*;

import java.time.*;

/**
 * Questa classe permette una corretta gestione dell'uso dei menu'. E' essenzialmente suddivisa in due parti:
 * 1 - Metodi ausiliari per la gestione delle funzionalita' basilari del software (iscrizione, accesso)
 * 2 - Metodo logicaMenu per la realizzazione delle connessioni tra i vari menu'
 */
public class GestoreMenu implements Serializable
{
	private static final long serialVersionUID = 1L;
   
    /**
     * Vengono inizialmente creati i vari menu' con le relative intestazioni ed opzioni. 
     * In seguito l'andamento del programma e' scandito attraverso l'aggiornamento della variabile letteraMenu e l'uso di switch-case innestati,
     * in cui il primo livello (contraddistinto dalle variabili letterali) indica gli specifici menu', mentre il secondo livello (evidenziato
     * dall'uso della variabile intera 'scelta') indica le opzioni relative ad ogni menu' e le operazioni che vengono indi svolte
     * 
     * @pre : (af != null) && (ao != null) && (arc != null) && (ap != null) && (as != null)
     * 
     * @param af : oggetto di tipo AnagraficaFruitori
     * @param ao : oggetto di tipo AnagraficaOperatori
     * @param arc : oggetto di tipo Archivio
     * @param ap : oggetto di tipo ArchivioPrestiti
     * @param as : oggetto di tipo ArchivioStorico
     */
    public void logicaMenu(processi)
    {
		Menu a = new Menu(Costanti.INTESTAZIONE_A, Costanti.OPZIONI_A);
		Menu b = new Menu(Costanti.INTESTAZIONE_B, Costanti.OPZIONI_B);
		Menu c = new Menu(Costanti.INTESTAZIONE_C, Costanti.OPZIONI_C);
		Menu d = new Menu(Costanti.INTESTAZIONE_D, Costanti.OPZIONI_D_345);
		Menu e = new Menu(Costanti.INTESTAZIONE_E, Costanti.OPZIONI_E);
		Menu f = new Menu(Costanti.INTESTAZIONE_F, Costanti.OPZIONI_F_5);

      	boolean esci = false;
      	char letteraMenu =  'a';
        int scelta = 0;
        
        Fruitore attualef = null;
        Operatore attualeop = null;
       
        System.out.println(Costanti.SALUTO_INIZIALE);
          
        do
        {
        	processi.controlloScadenze();

           	switch(letteraMenu)
    	    {
    	      	case('a'):
    	        {
    	      		scelta = a.scegli();
	        	     
    	    		switch(scelta)
    	    		{
	        	    	case 1: letteraMenu = 'b';
	        	                break;
  	        	
	        	        case 2: letteraMenu = 'e';
  	                    		break;
  	                    		
	        	        case 3: esci = true;
	        	        		break;
	        	    }
    	    		    
    	        	processi.controlloScadenze();

    	    		break;
    	        }
    	          
				case ('b'): 
				{
					scelta = b.scegli();

					switch (scelta) 
					{
						case 1: processi.iscrizione();
								letteraMenu = 'a';
								break;

						case 2: letteraMenu = 'c';
								break;

						case 3: letteraMenu = 'a';
								break;
					}

		        	processi.controlloScadenze();

					break;
				}
    	          
				case ('c'): 
				{
					scelta = c.scegli();

					switch (scelta) 
					{
						case 1: attualef = (Fruitore) processi.accesso(af);

								if (attualef != null) 
								{
									letteraMenu = 'd';
								} 
								else 
								{
									System.out.println(Costanti.ERRORE);
									letteraMenu = 'c';
								}

								break;

						case 2: letteraMenu = 'b';
								break;
					}

		        	processi.controlloScadenze();
 
					break;
				}
    	          
				case ('d'): 
				{
					scelta = d.scegli();

					switch (scelta) 
					{
						case 1: if (attualef.rinnovaIscrizione())			///////////////////////processi.rinnovaIscrizione(attualef)
								{
									processi.aggiungiFruitore(attualef);
									System.out.println(Costanti.RINNOVO_OK);
								}
								else
								{
									System.out.println(Costanti.RINNOVO_NON_OK);
								}

								letteraMenu = 'd';
								break;

						case 2: System.out.println( attualef.toString() );
	        	         		letteraMenu = 'd';
	        	         		break;
	                
						case 3: System.out.println(attualef.visualizzaPrestitiInCorso(ap));	//////////////////processi.visualizzaPrestitiInCorso(attualef)
	        		        	letteraMenu = 'd';
	        		        	break;
	        		
						case 4: processi.registraPrestito(attualef);  
	        		        	letteraMenu = 'd';
	        		        	break;
	        		
						case 5: processi.richiediProroga(attualef);    
	        	         		letteraMenu = 'd';
	        	         		break;
	        		
						case 6: System.out.println(ricercaRisorsaFormatoStringa(ricercaRisorsa(attualef, arc)));	/////////////////////
	        		        	letteraMenu = 'd';
	        		        	break;
	        
						case 7: System.out.println( processi.valutazioneDisponibilita(attualef) );
								letteraMenu = 'd';
								break;
	        	
						case 8: if(processi.richiestaLogout())
								{
									letteraMenu = 'a';
									attualef = null;
								}
								else
								{
									letteraMenu = 'd';
								}
								break;
					}

		        	processi.controlloScadenze();

					break;
				}
    	        
				case ('e'): 
				{
					scelta = e.scegli();

					switch (scelta) 
					{
						case 1: attualeop = (Operatore) processi.accesso(ao);

								if (attualeop != null) 
								{
									letteraMenu = 'f';
								}
								else 
								{
									System.out.println(Costanti.ERRORE);
									letteraMenu = 'e';
								}

								break;

						case 2: letteraMenu = 'a';
								break;
					}

		        	processi.controlloScadenze();

					break;
				}

				case ('f'):
				{
					scelta = f.scegli();

					switch (scelta)
					{
						case 1: System.out.println(attualeop.visualizzaElencoFruitori(af));	//////////// processi.visualizzaElencoFruitori(attualeop);
								letteraMenu = 'f';
								break;

	        	     	case 2: System.out.println(attualeop.visualizzaArchivio(arc)); //////////// processi.visualizzaArchivio(attualeop);
	     	        			letteraMenu = 'f';
	     	        			break;
	     	        		
	        	     	case 3: processi.aggiungiRisorsa(attualeop, arc); //////////// processi.aggiungiRisorsa(attualeop);
     	     	        		letteraMenu = 'f';
     	     	        		break;
     	     		
	        	     	case 4: processi.rimuoviRisorsa(attualeop, arc, as); ////////////// processi.rimuoviRisorsa(attualeop);
     	     	        		letteraMenu = 'f';
     	     	        		break;
     	     	    
	        	     	case 5: System.out.println(processi.ricercaRisorsaFormatoStringa(ricercaRisorsa(attualeop, arc)));	////////////////
	     	        			letteraMenu = 'f';
	     	        			break;
     	     
	        	     	case 6: System.out.println( processi.valutazioneDisponibilita(attualeop) );		////////////////////
     	     					letteraMenu = 'f';
     	     					break;
     	     	    
	        	     	case 7: System.out.println(processi.sceltaInterrogazione(attualeop, af, as));	//////////////////////////
	     						letteraMenu = 'f';
	        	     			break;
	        	     			
	        	     	case 8: if(processi.richiestaLogout())
								{
     								letteraMenu = 'a';
     								attualeop = null;
								}
								else
								{
									letteraMenu = 'f';
								}
								break;
					}

		        	processi.controlloScadenze();
   
					break;
				}
    	        
    	    }
    	      
       }while(!esci);   
       
       System.out.println(Costanti.SALUTO_FINALE);       
    }
     
}
