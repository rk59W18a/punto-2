package interazione;

public class Main 
{	
	/**
	 * Metodo main per l'esecuzione del software
	 * @param args: i parametri del main
	 */
	public static void main(String[] args) 
	{
		LoadClass loadclass = new LoadClass();
		loadclass.inizializza();
		
		ProcessOperatoreHandler processOperatore = new ProcessOperatoreHandler(loadclass.getArchivio(), loadclass.getArchivioPrestiti(), loadclass.getAnagraficaFruitori(), loadclass.getAnagraficaOperatori(), loadclass.getArchivioStorico());  
        ProcessFruitoreHandler processFruitore = new ProcessFruitoreHandler(loadclass.getArchivio(), loadclass.getArchivioPrestiti(), loadclass.getAnagraficaFruitori(), loadclass.getArchivioStorico());    
		
		MenuHandler gestore = new MenuHandler();
		gestore.logicaMenu(processOperatore,processFruitore);

		loadclass.salva();
	}
	
}