package utility;

import java.io.Serializable;

public class Costanti implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final String SALUTO_INIZIALE = "Benvenuto nell'applicazione per la gestione di risorse multimediali\n";
	public static final String SALUTO_FINALE = "Arrivederci, alla prossima!\n";
	public static final String INTESTAZIONE_A = "IN QUALE MODALITA' VUOI ACCEDERE?";
	public static final String[] OPZIONI_A = { "Fruitore", "Operatore", "Esci" };
	public static final String INTESTAZIONE_B = "SCEGLI UN'OPZIONE";
	public static final String[] OPZIONI_B = { "Iscriviti come nuovo fruitore", "Accedi", "Indietro" };
	public static final String INTESTAZIONE_C = "ACCESSO FRUITORE";
	public static final String[] OPZIONI_C = { "Inserisci username e password", "Indietro" };
	public static final String INTESTAZIONE_D = "COSA DESIDERI FARE?";
	public static final String[] OPZIONI_D_12 = { "Rinnova iscrizione", "Visualizza profilo", "Logout" };
	public static final String [] OPZIONI_D_345 = {"Rinnova iscrizione", "Visualizza profilo", "Visualizza prestiti in corso", "Richiedi un nuovo prestito", "Richiedi proroga per un prestito", "Ricerca risorsa", "Valuta disponibilita' risorsa", "Logout"};
	public static final String INTESTAZIONE_E = "ACCESSO OPERATORE";
	public static final String[] OPZIONI_E = { "Inserisci username e password", "Indietro" };
	public static final String INTESTAZIONE_F = "COSA DESIDERI FARE?";
	public static final String[] OPZIONI_F_1 = { "Visualizza anagrafica fruitori", "Logout" };
	public static final String [] OPZIONI_F_2 = {"Visualizza anagrafica fruitori", "Visualizza archivio risorse", "Aggiungi risorsa", "Rimuovi risorsa", "Logout"};
	public static final String [] OPZIONI_F_34 = {"Visualizza anagrafica fruitori", "Visualizza archivio risorse", "Aggiungi risorsa", "Rimuovi risorsa", "Ricerca risorsa", "Valuta disponibilita' risorsa", "Logout"};
	public static final String [] OPZIONI_F_5 = {"Visualizza anagrafica fruitori", "Visualizza archivio risorse", "Aggiungi risorsa", "Rimuovi risorsa", "Ricerca risorsa", "Valuta disponibilita' risorsa", "Interroga l'archivio storico", "Logout"};
	
	public static final String INS_NOME = "Inserisci il tuo nome: ";
	public static final String INS_COGNOME = "Inserisci il tuo cognome: ";
	public static final String INS_USERNAME = "Inserisci il tuo username: ";
	public static final String INS_PASSWORD = "Inserisci la tua password: ";
	public static final String INS_GIORNO_NASCITA = "Inserisci il tuo giorno di nascita (in cifre): ";
	public static final String INS_MESE_NASCITA = "Inserisci il tuo mese di nascita (in cifre): ";
	public static final String INS_ANNO_NASCITA = "Inserisci il tuo anno di nascita (indicare 4 cifre): ";

	public static final String ISCRIZIONE_OK = "Complimenti, l'iscrizione e' avvenuta con successo!\n";
	public static final String ISCRIZIONE_NON_OK = "Non e' stato possibile iscrivere alcun utente\n";
	public static final String ISCRIZIONE_NON_OK_FRUITORE_GIA_ISCRITTO = "ATTENZIONE! Sei gia' iscritto all'applicazione.\n";
	public static final String ISCRIZIONE_NON_OK_STESSO_USERNAME = "ATTENZIONE! Lo username indicato non e' valido poiche' gia' in uso.\n";
	public static final String ISCRIZIONE_NON_OK_MAGGIORE_ETA = "ATTENZIONE! L'utente indicato non puo' iscriversi in quanto non e' maggiorenne.\n";

	public static final String RINNOVO_OK = "Il rinnovo dell'iscrizione e' avvenuto con successo.\n";
	public static final String RINNOVO_NON_OK = "Non e' possibile effettuare il rinnovo dell'iscrizione.\n";

	public static final String USERNAME = "Username: ";
	public static final String PASSWORD = "Password: ";
	public static final String CREDENZIALI_ERRATE = "ATTENZIONE! Lo username e/o la password non sono validi.\n";
	public static final String DATA_DI_NASCITA_ERRATA = "ATTENZIONE! La data di nascita inserita non e' valida.\n";

	public static final String RICHIESTA_PROSECUZIONE = "Si desidera riprovare? (S/N)\n";
	public static final String RICHIESTA_LOGOUT = "Si desidera effettuare il logout? (S/N)\n";
	public static final String ERRORE = "Si e' verificato un errore\n";

	public static final String CONTENUTO_ARC = "L'archivio contiene le seguenti categorie:\n%s\n";
	public static final String CONTENUTO_CAT_RISORSA = "La categoria %s contiene queste risorse:\n%s\n";
	public static final String CAT_SENZA_SOTTO = "La categoria %s non presenta sottocategorie in quanto contiene direttamente le risorse\n";
	public static final String CONTENUTO_ELENCO_SOTTO_VUOTO = "Per la categoria %s e' in corso l'aggiunta di sottocategorie, in questo momento non è possibile effettuare operazioni\n";
	public static final String CONTENUTO_ELENCO_RISORSE_CAT_VUOTO = "La categoria %s non contiene delle risorse quindi non e' possibile effettuare operazioni\n";	
	public static final String CONTENUTO_ELENCO_RISORSE_SOTTO_VUOTO = "La sottocategoria %s non contiene delle risorse quindi non e' possibile effettuare operazioni\n";	
	public static final String CONTENUTO_CAT_SOTTO = "La categoria %s contiene queste sottocategorie:\n%s\n";
	public static final String CONTENUTO_SOTTO = "La sottocategoria %s contiene queste risorse:\n%s\n"; 
	
	public static final String OP_SUCCESSO = "L'operazione e' avvenuta con successo\n";
	public static final String OP_NO_SUCCESSO_1 = "Attenzione! La risorsa e' gia' presente nella categoria\n";
	public static final String OP_NO_SUCCESSO_2 = "Attenzione! La risorsa non e' compatibile con la sottocategoria dove si vuole inserire\n";    
	public static final String OP_NO_SUCCESSO_PRESTITO_1 = "Attenzione! Non e' possibile effettuare il prestito perche' la risorsa indicata non e' disponibile\n";
	public static final String OP_NO_SUCCESSO_PRESTITO_2 = "Attenzione! Non e' possibile effettuare il prestito perche' hai superato il massimo numero di prestiti relativo alla categoria\n";
	public static final String OP_NO_SUCCESSO_PRESTITO_3 = "Attenzione! Non e' possibile effettuare il prestito perche' hai gia' in prestito la risorsa\n";
	public static final String OP_NO_SUCCESSO_PROROGA_1 = "Attenzione! Non e' stato possibile effettuare la proroga richiesta\n";
	public static final String OP_NO_SUCCESSO_PROROGA_2 = "Al momento non ci sono prestiti in corso quindi non e' possibile effettuare una proroga\n";

    public static final String INS_NUMERO_CAT_AGGIUNTA_RISORSA = "Inserisci il numero della categoria a cui aggiungere la risorsa:\n";
    public static final String INS_NUMERO_CAT_RIMOZIONE_RISORSA = "Inserisci il numero della categoria a cui rimuovere la risorsa:\n";
 
    public static final String INS_NUMERO_SOTTO_AGGIUNTA_RISORSA =  "Inserisci il numero della sottocategoria a cui aggiungere la risorsa:\n";
    public static final String INS_NUMERO_SOTTO_RIMOZIONE_RISORSA =  "Inserisci il numero della sottocategoria a cui rimuovere la risorsa:\n";

    public static final String INS_NUMERO_RISORSA_RIMOZIONE = "Inserisci il numero della risorsa da rimuovere:\n";
 
    public static final String INS_PROCEDERE_PRESTITO = "Vuoi proseguire nella richiesta del prestito della risorsa? (S/N)\n";
    public static final String INS_PROCEDERE_CAT = "Vuoi proseguire nella scelta della categoria (S/N)?\n";
    public static final String INS_PROCEDERE_SOTTO = "Vuoi proseguire nella scelta della sottocategoria (S/N)?\n";
    public static final String INS_PROCEDERE_RISORSA = "Vuoi proseguire nella scelta della risorsa? (S/N)\n";
        
    public static final String INS_NUMERO_CAT_PRESTITO = "Inserisci il numero della categoria di cui vuoi richiedere una risorsa in prestito:\n";
    public static final String INS_NUMERO_SOTTOC_PRESTITO =  "Inserisci il numero della sottocategoria in cui cercare la risorsa:\n";
    public static final String INS_NUMERO_RISORSA_PRESTITO = "Inserisci il numero della risorsa da richiedere in prestito:\n";      

    public static final String INS_NUMERO_PRESTITO_PROROGA = "Inserisci il numero del prestito di cui vuoi richiedere la proroga:";      
    public static final String INS_PROCEDERE_PROROGA = "Vuoi proseguire nella richiesta della proroga del prestito? (S/N)\n";
    
    public static final String INS_PAROLA_TITOLO_RISORSA = "Inserisci una parola contenuta nel titolo: ";      
    public static final String INS_COGNOME_AUTORE_LIBRO = "Inserisci il cognome di un autore: ";      
    public static final String INS_GENERE_RISORSA = "Inserisci il genere: ";      
    public static final String INS_ANNOPUB_RISORSA = "Inserisci l'anno di pubblicazione: ";      
    public static final String INS_CASAED_LIBRO = "Inserisci la casa editrice: "; 
    
    public static final String INS_COGNOME_REGISTA_FILM = "Inserisci il cognome di un regista: ";      
    public static final String INS_COGNOME_ATTORE_FILM = "Inserisci il cognome di un attore: ";      

    public static final String INS_NUMERO_CAT_RICERCA = "Inserisci il numero della categoria in cui vuoi cercare la risorsa:\n";
    public static final String AVVIO_RICERCA_LIBRI = "Come intendi ricercare il libro?\n1-Per una parola contenuta nel titolo\n2-Per il cognome di un autore\n3-Per genere\n4-Per anno di pubblicazione\n5-Per casa editrice\n\nDigitare un numero:\n";
    public static final String AVVIO_RICERCA_FILM = "Come intendi ricercare il film?\n1-Per una parola contenuta nel titolo\n2-Per il cognome del regista\n3-Per il cognome di un attore\n4-Per anno di pubblicazione\n5-Per genere\n\nDigitare un numero:\n";
	public static final String INTESTAZIONE_RICERCA_RISORSE = "Elenco delle risorse trovate: \n";
	public static final String RICHIESTA_DIGITAZIONE_VALUTAZIONE = "Digitare il numero della risorsa scelta:\n";
	public static final String RICERCA_VUOTA = "Non sono state trovate delle risorse";
	public static final String RISORSA_DISPONIBILE = "La risorsa indicata e' disponibile\n";
	public static final String RISORSA_NON_DISPONIBILE = "La risorsa indicata non e' disponibile\n";
    public static final String NO_VALUTAZIONE = "Non è possibile effettuare la valutazione in quanto non sono state trovate risorse\n";
	
	public static final String SCELTA_INTERROGAZIONE = "Quale delle seguenti interrogazioni desideri fare all'archivio?\n1-Numero di prestiti per anno solare\n2-Numero di proroghe per anno solare\n3-Risorsa piu' richiesta per anno solare\n4-Numero di prestiti per fruitore per anno solare\n";
	public static final String INS_ANNO_RICHIESTO = "Inserisci l'anno:";
	public static final String INS_FRUITORE_RICHIESTO = "Inserisci lo username del fruitore: ";
	public static final String FRUITORE_NON_TROVATO = "Il fruitore selezionato non e' presente nell'anagrafica\n";
	public static final String NUM_PRESTITI_PER_ANNO = "Il numero di prestiti per l'anno selezionato e': ";
	public static final String NUM_PROROGHE_PER_ANNO = "Il numero di proroghe per l'anno selezionato e': ";
	public static final String TITOLO_RISORSA_PIU_PRESTITI_PER_ANNO = "Il titolo della risorsa con piu' prestiti per l'anno selezionato e': ";
	public static final String TITOLO_RISORSA_SENZA_PRESTITI_PER_ANNO = "Per l'anno selezionato non sono stati effettuati prestiti";
	public static final String NUM_PRESTITI_PER_FRUITORE_PER_ANNO = "Il numero di prestiti per il fruitore e per l'anno selezionato e': ";
    
	public static final String LIBRI = "Libri";
    public static final String FILM = "Film";
    
	public static final int MAGGIORE_ETA = 18;
	public static final int NUM_MINIMO = 1;
	public static final int VUOTO = 0;
	public static final int ANNO_MINIMO_INTERROGAZIONE = 1990;
	
	public static final int NUM_MASSIMO_RICERCA = 5;
	public static final int NUM_MASSIMO_SCELTA = 4;
	
	public static final String NOME_FILE = "gestoreRisorse.txt";								
	public static final String MSG_NO_CAST = "ATTENZIONE PROBLEMI CON IL CAST";			
	public static final String MSG_OK_FILE = "CARICAMENTO DA FILE EFFETTUATO";			
	public static final String MSG_NO_FILE = "CARICAMENTO DA FILE NON RIUSCITO. OCCORRE CREARE UNA NUOVA ANAGRAFICA DEI FRUITORI, UN NUOVO ARCHIVIO DELLE RISORSE, UN NUOVO ARCHIVIO PRESTITI E UN NUOVO ARCHIVIO STORICO";			
	public static final String MSG_SALVA = "SALVATAGGIO DATI";
	public static final String ERRORE_CONVERSIONE_DATA = "Attenzione! Si e' verificato un errore di conversione della data";	

}
