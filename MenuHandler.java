package interazione;

import java.io.Serializable;
import logica.*;
import utility.*;

public class MenuHandler implements Serializable
{
	private static final long serialVersionUID = 1L;
	   
    public void logicaMenu(ProcessOperatoreHandler processOperatore, ProcessFruitoreHandler processFruitore)
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
          	processFruitore.controlloScadenzeAutomatiche();

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
    	    		processFruitore.controlloScadenzeAutomatiche();
    	            break;
    	         }
    	          
				case ('b'): 
				{
					scelta = b.scegli();

					switch (scelta) 
					{
						case 1: processFruitore.iscrizione();
								letteraMenu = 'a';
								break;

						case 2: letteraMenu = 'c';
								break;

						case 3: letteraMenu = 'a';
								break;
					}
					processFruitore.controlloScadenzeAutomatiche();
					break;
				}
    	          
				case ('c'): 
				{
					scelta = c.scegli();

					switch (scelta) 
					{
						case 1: attualef = (Fruitore) processFruitore.accesso();  

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
					processFruitore.controlloScadenzeAutomatiche();
					break;
				}
    	          
				case ('d'): 
				{
					scelta = d.scegli();

					switch (scelta) 
					{
						case 1: processFruitore.rinnovaIscrizione(attualef);
								letteraMenu = 'd';
								break;

						case 2: System.out.println(attualef.toString());
	        	         	    letteraMenu = 'd';
	        	                break;
	                
						case 3: processFruitore.visualizzaPrestitiInCorso(attualef);
	        		            letteraMenu = 'd';
	        		            break;
	        		
						case 4: processFruitore.registraPrestito(attualef);  
	        	    	        letteraMenu = 'd';
	         	             	break;
	        		
						case 5: processFruitore.richiediProroga(attualef);    
	        	         	    letteraMenu = 'd';
	        	         		break;
	        		
						case 6: System.out.println(processFruitore.ricercaRisorsaFormatoStringa(processFruitore.ricercaRisorsa(attualef)));
	        		        	letteraMenu = 'd';
	        		            break;
	        
						case 7: System.out.println(processFruitore.valutazioneDisponibilita(attualef));
								letteraMenu = 'd';
								break;
	        	
						case 8: if(processFruitore.richiestaLogout())
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
					processFruitore.controlloScadenzeAutomatiche();
					break;
				}
    	        
				case ('e'): 
				{
					scelta = e.scegli();

					switch (scelta) 
					{
						case 1: attualeop = (Operatore) processOperatore.accesso();

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
					processFruitore.controlloScadenzeAutomatiche();
					break;
				}

				case ('f'):
				{
					scelta = f.scegli();

					switch (scelta)
					{
						    case 1: processOperatore.visualizzaElencoFruitori(attualeop);
								    letteraMenu = 'f';
								    break;

	        	     	    case 2: processOperatore.visualizzaArchivio(attualeop);
	     	        			    letteraMenu = 'f';
	     	        			    break;
	     	        		
	        	     	    case 3: processOperatore.aggiungiRisorsa(attualeop);
     	     	        		    letteraMenu = 'f';
     	     	        		    break;
     	     		
	        	     	    case 4: processOperatore.rimuoviRisorsa(attualeop);
     	     	        		    letteraMenu = 'f';
     	     	        		    break;
     	     	    
	        	     	    case 5: System.out.println(processOperatore.ricercaRisorsaFormatoStringa(processOperatore.ricercaRisorsa(attualeop)));
	     	        			    letteraMenu = 'f';
	     	        			    break;
     	     
	        	     	    case 6: System.out.println(processOperatore.valutazioneDisponibilita(attualeop));
     	     					    letteraMenu = 'f';
     	     					    break;
     	     			
	        	     	    case 7: System.out.println(processOperatore.sceltaInterrogazione(attualeop));
    						        letteraMenu = 'f';
       	     			            break;
     	     	    
	        	     	    case 8: if(processOperatore.richiestaLogout())
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
					processFruitore.controlloScadenzeAutomatiche();
					break;  
				}
    	        
    	    }
    	      
       }while(!esci);   
       
       System.out.println(Costanti.SALUTO_FINALE);       
    }
     
}
