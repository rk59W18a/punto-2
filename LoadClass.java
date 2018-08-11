package interazione_5;

import java.io.File;

import logica_5.*;
import utility.*;

public class LoadClass {
	
    File gestoreRisorse = new File(Costanti.NOME_FILE);
    
    RaccoltaDati rd = null;
    AnagraficaFruitori af = null;
    AnagraficaOperatori ao = null;
    Archivio arc = null;
    ArchivioPrestiti ap = null;
    ArchivioStorico as = null;

    boolean caricamentoRiuscito = false;
	
    public void inizializza() {
    
	/**
	 * Tale istruzione verifica se il file in questione esiste all'interno del sistema di memorizzazione locale.
	 * In questo caso vengono estrapolate sia la RaccoltaDati sia l'AnagraficaFruitori, l'AnagraficaOperatori, l'Archivio, l'ArchivioPrestiti e l'ArchivioStorico venendo salvati nelle variabili opportune.
	 * Le probabili eccezioni vengono gestite secondo la modalita' piu' adatta al tipo di eccezione ed infine viene mostrato un messaggio di conferma se il caricamento da file gia' esistente si e' concluso con successo
	 */
	if (gestoreRisorse.exists())
	{
		/**
		 * Si cercano di reperire le istanze delle classi AnagraficaFruitori, AnagraficaOperatori, Archivio, ArchivioPrestiti e ArchivioStorico salvate su file. 
		 * Vengono inoltre opportunamente gestite le eccezioni di tipo ClassCast e NullPointer.
		 * Infine, nel caso in cui le istanze siano state correttamente inizializzate, viene mostrato a video un messaggio di conferma
		 * modificando al contempo una specifica variabile booleana per la segnalazione dell'avvenuto caricamento
		 */
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
	
	/**
	 * Nel caso in cui il caricamento da file non sia andato a buon fine si provvedono a costruire ex novo le strutture dati richieste e a creare la struttura del sistema
	 */
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
    
	/**
	 * L'operazione di salvataggio prevede la costruzione di una nuova RaccoltaDati attraverso i parametri AnagraficaFruitori, AnagraficaOperatori, Archivio, ArchivioPrestiti e ArchivioStorico e l'aggiornamento del file in gestoreRisorse
	 */
    public void salva() {
    	
	System.out.println(Costanti.MSG_SALVA);
	rd = new RaccoltaDati(af, ao, arc, ap, as);
    ServizioFile.salvaSingoloOggetto(gestoreRisorse, rd);

    }
    
}
