package td3.exo4.bdd;

import td3.exo4.bdd.mysql.MySqlDAOFactory;
import td3.exo4.bdd.xml.XmlDAOFactory;

/**
 *
 * @author Damien GAIGA
 */
public abstract class DAOFactory {
	
	// Pour le choix, possible d'utiliser une énumération
	// Je simplifie un peu le code pour cet exercice en l'évitant
	
	public abstract DAO getDaoPortefeuille();
	public abstract DAO getDaoDevise();
	
	// Création d'une factory suivant le paramètre fourni
	public static DAOFactory getDAOFactory(int choice){
		
		switch (choice){
			case 0:
				return new MySqlDAOFactory();
			case 1:
				return new XmlDAOFactory();
			default:
				return null;
		}
		
		
	}
}
