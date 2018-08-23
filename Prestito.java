package logica.parte2.punto2;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dominio.parte2.punto2.*;

public class Prestito implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private LocalDate dataDiInizioPrestito;
	private LocalDate dataDiScadenzaPrestito;
	private Categoria categoriaAssociata;
	private Fruitore fruitoreAssociato;
	private Risorsa risorsaInPrestito;
	private boolean prorogaNonEffettuata;
	private LocalDate dataProrogaEffettuata;
	
	public static final String DESCRIZIONE_PRESTITO = "Categoria della risorsa in prestito: %s\nRisorsa presa in prestito:\n\t\t%s\nData inizio prestito:%s\nData scadenza prestito: %s\nProroga già effettuata: %s\n";
		
	/**
	 * @pre: (c != null) && (f != null) && (r != null)
	 * @post: dataDiScadenzaPrestito == dataDiInizioPrestito.plusDays(categoriaAssociata.getNumeroMaxGiorniPrestito())
	 */
	public Prestito(Categoria c, Fruitore f, Risorsa r)
	{
		this.dataDiInizioPrestito = LocalDate.now();
		this.categoriaAssociata = c;
		this.dataDiScadenzaPrestito = dataDiInizioPrestito.plusDays(categoriaAssociata.getNumeroMaxGiorniPrestito());
		this.fruitoreAssociato = f;
		this.risorsaInPrestito = r;
		this.prorogaNonEffettuata = true;
	}
	
	public LocalDate getDataDiInizioPrestito()
	{
		return dataDiInizioPrestito;
	}
	
	public LocalDate getDataDiScadenzaPrestito()
	{
		return dataDiScadenzaPrestito;
	}
	
	public Categoria getCategoriaAssociata()
	{
		return categoriaAssociata;
	}
	
	public Fruitore getFruitoreAssociato()
	{
		return fruitoreAssociato;
	}
	
	public Risorsa getRisorsaInPrestito()
	{
		return risorsaInPrestito;
	}
	
	public boolean getProrogaNonEffettuata()
	{
		return prorogaNonEffettuata;
	}
	
	public LocalDate getDataProrogaEffettuata()
	{
		return dataProrogaEffettuata;
	}
	
	public void setProrogaNonEffettuata(LocalDate ld) 
	{
		prorogaNonEffettuata = false;
		this.dataProrogaEffettuata = ld;
	}
	
	public void setDataDiScadenzaPrestito(LocalDate nuovads)
	{
		dataDiScadenzaPrestito = nuovads;
	}
	
    public boolean prorogaPrestito()
    {
        if(prorogaNonEffettuata)
   	    {
        	    if((LocalDate.now().isBefore(dataDiScadenzaPrestito)))
   	 		    {
       	 	    	LocalDate ld2 = dataDiScadenzaPrestito.minusDays(categoriaAssociata.getNumeroGiorniRichiestaProroga());
   	 			
   	 			    if((LocalDate.now().equals(ld2)) || (LocalDate.now().isAfter(ld2)))
       	 		    {
   	 				   setDataDiScadenzaPrestito(dataDiScadenzaPrestito.plusDays(categoriaAssociata.getNumeroMaxGiorniProroga()));
       	 			   setProrogaNonEffettuata(LocalDate.now());
       	 			   return true;
       	 		    }
       	        }
   	 	}
	    return false;
    }

    public String toString()
    {
        StringBuffer ris = new StringBuffer();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Fruitore.FORMATO_DATA);
  	
	    String perProroga = "no";
  	    if(!prorogaNonEffettuata)
  		      perProroga = "si";
  	
  	    ris.append(String.format(DESCRIZIONE_PRESTITO, categoriaAssociata.getNome(), risorsaInPrestito.toString(), dataDiInizioPrestito.format(formatter), dataDiScadenzaPrestito.format(formatter), perProroga));
        return ris.toString();

    } 
    
}