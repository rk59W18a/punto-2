package logica.parte2.punto2;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Fruitore extends Utente implements Serializable
{
    private static final long serialVersionUID = 1L;
	
	private LocalDate dataDiNascita;             
    private LocalDate dataDiIscrizione;		
    private LocalDate dataDiScadenza;
      
    public static final String DESCRIZIONE_FRUITORE = "\nNome: %s\nCognome: %s\nUsername: %s\nPassword: %s\nData di nascita: %s\nData di iscrizione: %s\nData di scadenza: %s\n";
    public static final int TERMINE_SCADENZA = 5;
    public static final int DIECI_GIORNI = 10;
    public static final String FORMATO_DATA = "dd/MM/yyyy";
    public static final String NO_PRESTITI_ATTIVI = "Al momento non ci sono prestiti\n";
    public static final String PRESTITI_ATTIVI = "Elenco dei prestiti attivi:\n";
        
    public Fruitore(String n, String c, int an, int mn, int gn, String u, String p)
    {
   	     super(n, c, u, p);
   	     this.dataDiNascita = LocalDate.of(an, mn, gn);
   	     this.dataDiIscrizione = LocalDate.now();
   	     this.dataDiScadenza = dataDiIscrizione.plusYears(TERMINE_SCADENZA);
    }
    
    public LocalDate getDataDiNascita()
    {
   	     return dataDiNascita;
    }
   
    public LocalDate getDataDiIscrizione()
    {
   	     return dataDiIscrizione;
    }
    
    public LocalDate getDataDiScadenza()
    {
   	     return dataDiScadenza;
    }
    
    public void setDataDiScadenza(LocalDate nuovads)
    {
   	 	dataDiScadenza = nuovads;
    }
    
    public boolean rinnovaIscrizione()
    {
		 if((LocalDate.now().isBefore(dataDiScadenza)))
		 {
			  LocalDate ld = dataDiScadenza.minusDays(DIECI_GIORNI);
				
		      if((LocalDate.now().equals(ld)) || (LocalDate.now().isAfter(ld))) 
			  {
				 setDataDiScadenza(dataDiScadenza.plusYears(Fruitore.TERMINE_SCADENZA));
				 return true;
			  }
		 }
		 
		 return false;
    }
    
    public String visualizzaPrestitiInCorso(ArchivioPrestiti ap)
    {
    	ArrayList <Prestito> ris = new ArrayList <Prestito> ();
    	StringBuffer r = new StringBuffer();
        ris = ap.getPrestiti(this.getUsername());
   	    
   	    if(ris.size() == 0)
   	    	r.append(NO_PRESTITI_ATTIVI);
   	    else
        {
   	        r.append(PRESTITI_ATTIVI);
    	    for(int i = 0; i < ris.size(); i++)
    	    {
    	        Prestito p = ris.get(i);
    	   		r.append(i+1 + ")" + p.toString() +"\n");
    	    }
         }
    	    
      	return r.toString();
    }
    
    public void registraNuovoPrestito(ArchivioPrestiti ap, Prestito p)
    {
      	ap.aggiungiPrestito(p);
    }
   
    public boolean registraProrogaPrestito(Prestito p)
    {
    	return p.prorogaPrestito();
    }
    
    public String toString()
    {
      	StringBuffer ris = new StringBuffer();
      	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA);
    	    
      	ris.append(String.format(DESCRIZIONE_FRUITORE, getNome(), getCognome(), getUsername(), getPassword(), dataDiNascita.format(formatter), dataDiIscrizione.format(formatter), dataDiScadenza.format(formatter)));
        return ris.toString();
    }    
}