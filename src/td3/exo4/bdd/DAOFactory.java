/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo4.bdd;

import td3.exo4.bdd.mysql.MySqlDAOFactory;
import td3.exo4.bdd.xml.XmlDAOFactory;

/**
 *
 * @author user
 */
public abstract class DAOFactory {
	
	// POur le choix, possible d'utiliser une énumération
	// Je simplifie un peu le code pour cet exercice en l'évitant
	
	public abstract DAO getDaoPortefeuille();
	public abstract DAO getDaoDevise();
	
	
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
