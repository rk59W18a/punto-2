package interazione_5;

import java.io.File;

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
		
		//Processi processi = new Processi();	processi.inizializza();
		
		/**
		 * Viene costruito un nuovo gestore menu' che possa dare avvio alla logica del sistema applicativo 
		 */
		GestoreMenu gestore = new GestoreMenu();
		gestore.logicaMenu(processi);

		loadclass.salva();
	}
	
}
