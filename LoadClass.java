package interazione;

import java.io.File;

import logica.*;
import utility.*;

public class LoadClass 
{
	
	 File gestoreRisorse = new File(Costanti.NOME_FILE);
	    
	 RaccoltaDati rd = null;
	 AnagraficaFruitori af = null;
	 AnagraficaOperatori ao = null;
	 Archivio arc = null;
	 ArchivioPrestiti ap = null;
	 ArchivioStorico as = null;

	 boolean caricamentoRiuscito = false;
		
	 public void inizializza() 
	 {
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
