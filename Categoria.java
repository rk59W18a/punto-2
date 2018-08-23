package dominio.parte2.punto2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @invariant: (elencoRisorse == null) || (elencoSottoCategorie == null)
 */
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
	   
	   /**
	    * @post: elencoRisorse != null
	    */
	   public void inizializzaElencoRisorse()
	   {
		   elencoRisorse = new ArrayList <Risorsa> ();
	   }
	   
	   /**
	    * @post: elencoSottoCategorie != null
	    */
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
	   
	   /**
	    * @pre: elencoRisorse != null
	    */
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
	    
	   /**
	    * @pre: (r != null) && !(elencoRisorse.contains(r))
	    * @post: elencoRisorse.contains(r)
	    */
	   public void aggiungiRisorsa(Risorsa r)  
	   {
		   elencoRisorse.add(r);
	   }
	   
	   /**
	    * @pre: (r != null) && (elencoRisorse.contains(r))
	    * @post: !(elencoRisorse.contains(r))
	    */
	   public void rimuoviRisorsa(Risorsa r)  
	   {
		   elencoRisorse.remove(r);
		   
	   }
	   
	   /**
	    * @pre: sc != null
	    * @post: elencoSottoCategorie.contains(sc)
	    */
	   public void aggiungiSottoCategoria(SottoCategoria sc)
	   {
		   elencoSottoCategorie.add(sc);
	   }
	   
	   /**
	    * Metodo che dato una stringa t che rappresenta il titolo della nuova risorsa da inserire in archivio, verifica 
	    * se all'interno della categoria associata alla nuova risorsa sia già presente una risorsa con lo stesso titolo
	    * 
	    * @pre: elencoSottoCategorie != null  && elencoRisorse != null
	    */
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
	 
	   /**
	    * Metodo per la ricerca delle risorse all'interno dell'elenco delle risorse
	    * 
	    * @pre: o != null
	    * 
	    * @param o: il generico oggetto che rappresenta quello che l'utente ha digitato con lo scopo di cercarlo nella categoria
	    * @param cr: stringa che specifica in base a quale parametro avviene la ricerca
	    * @return il vettore con all'interno le risorse che hanno soddisfatto la ricerca
	    */
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
	   
	   /**
	    * Metodo per la ricerca di risorse all'interno della categoria oppure all'interno
	    * delle varie sottocategorie della categoria, se presenti
	    * 
	    * @pre: o != null
	    * 
	    * @param o: il generico oggetto che rappresenta quello che l'utente ha digitato con lo scopo di cercarlo nella categoria
	    * @param comeRicercare: stringa che specifica in base a quale parametro avviene la ricerca
	    * @return il vettore con all'interno le risorse che hanno soddisfatto la ricerca
	    */
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
	  
	   /**
	    * @pre: elencoRisorse != null
	    */
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
	   
	   /**
	    * @pre: elencoSottoCategorie != null
	    */
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