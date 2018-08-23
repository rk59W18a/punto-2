package interazione.parte2.punto2;

import java.io.File;

import logica.parte2.punto2.*;
import utility_2.*;

public class LoadClass 
{
	
	 private File gestoreRisorse = new File(Costanti.NOME_FILE);
	    
	 private RaccoltaDati rd = null;
	 private AnagraficaFruitori af = null;
	 private AnagraficaOperatori ao = null;
	 private Archivio arc = null;
	 private ArchivioPrestiti ap = null;
	 private ArchivioStorico as = null;
	 
		
	 public void inizializza() 
	 {
		boolean caricamentoRiuscito = false;
		 
		if (gestoreRisorse.exists())
		{
			try 
			{
				rd = (RaccoltaDati)ServizioFile.caricaSingoloOggetto(gestoreRisorse);
				af = rd.getAnagraficaFruitori();
				ao = rd.getAnagraficaOperatori();
				arc = rd.getArchivio();
				ap = rd.getArchivioPrestiti();
				as = rd.getArchivioStorico();
			}
			catch (ClassCastException e)
			{
				System.out.println(Costanti.MSG_NO_CAST);
			}
			catch(NullPointerException a)
			{
				System.out.println();
			}
			finally
			{
				if (af != null && ao != null && arc != null && ap != null && as != null)
				{
					System.out.println(Costanti.MSG_OK_FILE);
					caricamentoRiuscito = true;
				}
			}		
		 }
		
		if (!caricamentoRiuscito)				
		{
			System.out.println(Costanti.MSG_NO_FILE);				
			af = new AnagraficaFruitori();
			ao = new AnagraficaOperatori();
			arc = new Archivio();
			ap = new ArchivioPrestiti();
			as = new ArchivioStorico();
			
			StrutturaSistema.aggiuntaOperatoriPreimpostati(ao);
			StrutturaSistema.creazioneStrutturaArchivioLibri(arc);
			StrutturaSistema.creazioneStrutturaArchivioFilm(arc);
		}
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
	    
	public void salva() 
	{	
	  System.out.println(Costanti.MSG_SALVA);
      rd = new RaccoltaDati(af, ao, arc, ap, as);
	  ServizioFile.salvaSingoloOggetto(gestoreRisorse, rd);
    } 
	
}
