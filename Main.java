package interazione.parte2.punto2;

public class Main 
{	
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