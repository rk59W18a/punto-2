package interazione_5;

import java.io.Serializable;

import logica_5.*;

/**
 * Questa classe rappresenta un raccoglitore di dati, utile per la memorizzazione su file
 */
public class RaccoltaDati implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private AnagraficaFruitori af;
    private AnagraficaOperatori ao;
    private Archivio arc;
    private ArchivioPrestiti ap;
    private ArchivioStorico as;
    
    /**
    * Metodo costruttore della classe RaccoltaDati
    * 
    * @pre: (af != null) && (ao != null) && (arc != null) && (ap != null) && (as != null)
    * 
    * @param af: anagrafica fruitori
    * @param ao: anagrafica operatori
    * @param arc: archivio risorse
    * @param ap: archivio prestiti
    * @param aStorico: archivio storico
     */
    public RaccoltaDati(AnagraficaFruitori af, AnagraficaOperatori ao, Archivio arc, ArchivioPrestiti ap, ArchivioStorico aStorico)
    {
    	   this.af = af;
    	   this.ao = ao;
    	   this.arc = arc;
    	   this.ap = ap;
    	   this.as = aStorico;
    }
    
    /**
     * Metodi get della classe RaccoltaDati
     * @return i vari attributi della classe RaccoltaDati
     */
    public AnagraficaFruitori getAnagraficaFruitori()
	{
		return af;
	}
    
    public AnagraficaOperatori getAnagraficaOperatori()
	{
		return ao;
	}
    
    public Archivio getArchivio()
   	{
   		return arc;
   	}
    
    public ArchivioPrestiti getArchivioPrestiti()
   	{
   		return ap;
   	}
    
    public ArchivioStorico getArchivioStorico()
    {
    	return as;
    }
    
}
