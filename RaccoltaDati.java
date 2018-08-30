package interazione.parte2.punto2;

import java.io.Serializable;

import logica.parte2.punto2.*;


public class RaccoltaDati implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private AnagraficaFruitori af;
    private AnagraficaOperatori ao;
    private Archivio arc;
    private ArchivioPrestiti ap;
    private ArchivioStorico as;
    
    public RaccoltaDati(AnagraficaFruitori af, AnagraficaOperatori ao, Archivio arc, ArchivioPrestiti ap, ArchivioStorico aStorico)
    {
    	   this.af = af;
    	   this.ao = ao;
    	   this.arc = arc;
    	   this.ap = ap;
    	   this.as = aStorico;
    }
    
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