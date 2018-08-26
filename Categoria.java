package dominio.parte2.punto2;

import java.io.Serializable;
import java.util.ArrayList;

public class Categoria implements Serializable
{
	   private static final long serialVersionUID = 1L;
	   
	   private String nomeCategoria;
	   protected ArrayList <Risorsa> elencoRisorse;
	   private ArrayList <SottoCategoria> elencoSottoCategorie;
	   
	   private int numeroMaxGiorniPrestito;
	   private int numeroMaxGiorniProroga;
	   private int numeroGiorniRichiestaProroga;
	   private int numeroMaxRisorseInPrestito;

	   public static final String DESCRIZIONE_CATEGORIA_SEMPLICE = "Nome categoria: %s\nRisorse in essa contenute:\n";
	   public static final String DESCRIZIONE_CATEGORIA_COMPOSTA = "Nome categoria: %s\nSottocategorie in essa contenute:\n";
	   public static final String ELENCO_SOTTOCATEGORIE_VUOTO = "\tAl momento non sono presenti sottocategorie\n";
	   public static final String ELENCO_RISORSE_VUOTO = "\t\tAl momento non sono presenti risorse\n";

	   public static final String RIC_PER_TITOLO = "titolo";
	   public static final String RIC_PER_AUTORE_I = "autore_i";
	   public static final String RIC_PER_GENERE = "genere";
	   public static final String RIC_PER_ANNOPUB = "annoPub";
	   public static final String RIC_PER_CASAED = "casaEditrice";
	   public static final String RIC_PER_REGISTA = "regista";
	   public static final String RIC_PER_ATTORE_I = "attore_i";
	 
	   
	   public Categoria(String n, int numPres, int numMaxPro, int numRiPro, int numRis)
	   {
		   this.nomeCategoria = n;
		   this.numeroMaxGiorniPrestito = numPres;
		   this.numeroMaxGiorniProroga = numMaxPro;
		   this.numeroGiorniRichiestaProroga = numRiPro;
		   this.numeroMaxRisorseInPrestito = numRis;
	   }
	   
	   public Categoria()
	   {
		   
	   }
	   
	   public void inizializzaElencoRisorse()
	   {
		   elencoRisorse = new ArrayList <Risorsa> ();
	   }
	   
	   public void inizializzaElencoSottoCategorie()
	   {
		   elencoSottoCategorie = new ArrayList <SottoCategoria> ();
	   }
	   
	   public String getNome()
	   {
		   return nomeCategoria;
	   }
	   
	   public int getNumeroMaxGiorniPrestito()
	   {
		   return numeroMaxGiorniPrestito;
	   }
	   
	   public int getNumeroMaxGiorniProroga()
	   {
		   return numeroMaxGiorniProroga;
	   }
	   
	   public int getNumeroGiorniRichiestaProroga()
	   {
		   return numeroGiorniRichiestaProroga;
	   }
	   
	   public int getNumeroMaxRisorseInPrestito()
	   {
		   return numeroMaxRisorseInPrestito;
	   }
	     
	   public ArrayList <Risorsa> getElencoRisorse()  
	   {
		   return elencoRisorse;
	   }
	   
	   public ArrayList <SottoCategoria> getElencoSottoCategorie() 
	   {
		   return elencoSottoCategorie;
	   }
	   
	   public Risorsa getRisorsa(String t)
	   {
		   for(int i = 0; i < elencoRisorse.size(); i++)
		   {
			   Risorsa r = elencoRisorse.get(i);
			   if(r.getTitolo().equalsIgnoreCase(t))
				   return r;
		   }
		   
		   return null;
	   }
	    
	   public void aggiungiRisorsa(Risorsa r)  
	   {
		   elencoRisorse.add(r);
	   }
	   
	   public void rimuoviRisorsa(Risorsa r)  
	   {
		   elencoRisorse.remove(r);
		   
	   }
	   
	   public void aggiungiSottoCategoria(SottoCategoria sc)
	   {
		   elencoSottoCategorie.add(sc);
	   }
	   
	   public boolean verificaPresenza(String t)
	   {
		  boolean presente = false;
		   
		  for(int i = 0; i < elencoSottoCategorie.size(); i++)
		  {
			  SottoCategoria sc = elencoSottoCategorie.get(i);
			  
			  for(int j = 0; j < sc.getElencoRisorse().size(); j++)
			  {
				  Risorsa r = sc.getElencoRisorse().get(j);
				  
				  if(r.getTitolo().equalsIgnoreCase(t))
				  {
					  presente = true;
					  break;
				  }	   
			  }
		   }
		   return presente;
	   }
	 
	   public ArrayList <Risorsa> ricercaRisorsaInElenco(Object o, String cr)
	   {
		   ArrayList <Risorsa> risorseCercate = new ArrayList <>();
	   	    
	   	    for(int i = 0; i < elencoRisorse.size(); i++)
	   	    {
	   	    	    Risorsa r = elencoRisorse.get(i);
	   	    	    
	   	    	    switch(cr)
	   	    	    {
	   	    	        case RIC_PER_TITOLO: if(r.getTitolo().toLowerCase().indexOf(((String)o).toLowerCase()) > -1)
	  	    	    	                               risorseCercate.add(r);
	   	    	                             break;
	   	    	                       
	   	    	        case RIC_PER_AUTORE_I: if(((Libro)r).getAutore().toLowerCase().indexOf(((String)o).toLowerCase()) > -1)
	     	                     	              risorseCercate.add(r);
	                                           break;
	   	    	                       
	   	    	        case RIC_PER_GENERE: if(r.getGenere().equalsIgnoreCase((String)o))
	   	    	        	                      risorseCercate.add(r);
	   	    	                             break;
	   	    	                    
	   	    	        case RIC_PER_ANNOPUB: if(r.getAnnoPub() ==  (Integer)o)
	     	                     	              risorseCercate.add(r);
	   	    	        					  break;
	                      
	   	    	        case RIC_PER_CASAED: if(((Libro)r).getCasaEditrice().equalsIgnoreCase((String)o))
	     	                     	               risorseCercate.add(r);
	   	    	        					 break;
	   	    	        					 
	   	    	        case RIC_PER_REGISTA: if(((Film)r).getRegista().toLowerCase().indexOf(((String)o).toLowerCase()) > -1)
	   	    	        							risorseCercate.add(r);
	   	    	        					  break; 
	                
	   	    	        case RIC_PER_ATTORE_I: if(((Film)r).getAttore().toLowerCase().indexOf(((String)o).toLowerCase()) > -1)
	   	    	        							risorseCercate.add(r);
	   	    	        					   break;
	   	    	    } 	     
	   	    }
	   	    
	   	    return risorseCercate;
	   }
	   
	   public ArrayList <Risorsa> ricercaRisorsa(Object o, String comeRicercare)
	   {
		   ArrayList <Risorsa> risorseCercate = new ArrayList <>();
		   
		   if(elencoSottoCategorie == null)
		   { 
			   risorseCercate = ricercaRisorsaInElenco(o, comeRicercare);
		   }
		   else
		   {
			   for(int i = 0; i < elencoSottoCategorie.size(); i++)
			   {
				   SottoCategoria sc = elencoSottoCategorie.get(i);
				   risorseCercate.addAll(sc.ricercaRisorsaInElenco(o, comeRicercare));
			   }
		   }
		   
		   return risorseCercate;
	   }
	  
	   public String stampaElencoRisorse()
	   {
	      StringBuffer ris = new StringBuffer();
	 	   
	 	   for(int i = 0; i < elencoRisorse.size(); i++)
	 	   {
	 		   Risorsa r = elencoRisorse.get(i);
	 		   ris.append(i+1 + ")"+ r.getTitolo() + "\n");
	 	   }
	 	   
	 	   return ris.toString();
	   }
	   
	   public String stampaElencoSottocategorie()
	   {
		   StringBuffer ris = new StringBuffer();
		   
		   for(int i = 0; i < elencoSottoCategorie.size(); i++)
		   {
			   SottoCategoria sc = elencoSottoCategorie.get(i);
			   ris.append(i+1 + ")" + sc.getNome() + "\n");
		   }
		   
		   return ris.toString();
	   }
	   
	   public String toString()
	   {
		   StringBuffer ris = new StringBuffer();
		   
		   if(elencoSottoCategorie == null)
		   {
		       ris.append(String.format(DESCRIZIONE_CATEGORIA_SEMPLICE, nomeCategoria));
		   
		       if(elencoRisorse.size() == 0)
		    	       ris.append(ELENCO_RISORSE_VUOTO);
		       else
		       {
		          for(int i = 0; i < elencoRisorse.size(); i++)
		          {
			         Risorsa r = elencoRisorse.get(i);
			         ris.append("\t\t" + (i+1) + ")"+ r.toString());
		          }
		       }
		   }
		   else
		   {
			   ris.append(String.format(DESCRIZIONE_CATEGORIA_COMPOSTA, nomeCategoria));
			   
			   if(elencoSottoCategorie.size() == 0)
				    ris.append(ELENCO_SOTTOCATEGORIE_VUOTO);
			   else
			   {
			      for(int i = 0; i < elencoSottoCategorie.size(); i++)
		          {
			         SottoCategoria s = elencoSottoCategorie.get(i);
			         ris.append("\t"+ (i+1) + ")"+ s.toString());
		          }
		        }
		    }
		   
		   return ris.toString();
	   }	   
}