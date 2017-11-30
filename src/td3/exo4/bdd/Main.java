/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo4.bdd;

import java.sql.Connection;
import td3.exo4.bdd.mysql.MySqlDAODevise;
import td3.exo4.bdd.mysql.MySqlDAOPortefeuille;

/**
 *
 * @author user
 */
public class Main {

	public static void main(String[] args) {
		// Création d'un objet portefeuilleA
		Portefeuille aurelie = new Portefeuille("aurelie");
		// Ajout de devises
		aurelie.ajouterDevise("EURO", 549.90);
		aurelie.ajouterDevise("DOLLAR", 977.45);
		aurelie.ajouterDevise("LIVRE", 1297.57);

		// Création d'un objet portefeuilleA
		Portefeuille henri = new Portefeuille("henri");
		// Ajout de devises
		henri.ajouterDevise("EURO", 1452.90);
		henri.ajouterDevise("DOLLAR", 687.45);
		henri.ajouterDevise("LIVRE", 1370.57);

		// Création d'une DAO Factory pour une base MySQL
		DAOFactory factory = DAOFactory.getDAOFactory(0);

		// Création d'un DAO pour gérer les objets via SQL
		MySqlDAOPortefeuille daoPortefeuille = (MySqlDAOPortefeuille) factory.getDaoPortefeuille();

		// Ajout du aurelie à la base de données
		daoPortefeuille.create(aurelie);

		// Ajout du henri à la base de données
		daoPortefeuille.create(henri);

		// Récupération du portefeuille henri en base de données
		Portefeuille pa = daoPortefeuille.getByNom("henri");
		pa.afficher();

		// Récupération du portefeuille aurelie en base de données
		Portefeuille pb = daoPortefeuille.getByNom("aurelie");
		pb.afficher();

		// Suppression du portefeuille henri en base de données
		daoPortefeuille.delete(pa);
		
		// Modification des valeurs du portefeuille aurelie
		// NB : Ajoute la valeur fournie à l'existant
		pb.mettreDeviseDansPortefeuille("EURO", 10000.0);
		daoPortefeuille.update(pb);
			
		
		// Teste sur les devises
		// Création d'objets de type devises
		Devise deviseA = new Devise("YEN");
		Devise deviseB = new Devise("ROUBLE");
				
		MySqlDAODevise daodevise = (MySqlDAODevise) factory.getDaoDevise();
		
		// Enregistrement des objets devises en base
		daodevise.create(deviseA);
		daodevise.create(deviseB);
		
		// Récupération d'un objet devise depuis la base
		Devise db = daodevise.getByNom("ROUBLE");

		daodevise.delete(deviseA);
	}
}
