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
		Portefeuille portefeuilleA = new Portefeuille("PortefeuilleA");
		// Ajout de devises
		portefeuilleA.ajouterDevise("EURO", 549.90);
		portefeuilleA.ajouterDevise("DOLLAR", 977.45);
		portefeuilleA.ajouterDevise("LIVRE", 1297.57);

		// Création d'un objet portefeuilleA
		Portefeuille portefeuilleB = new Portefeuille("PortefeuilleB");
		// Ajout de devises
		portefeuilleB.ajouterDevise("EURO", 1452.90);
		portefeuilleB.ajouterDevise("DOLLAR", 687.45);
		portefeuilleB.ajouterDevise("LIVRE", 1370.57);

		// Création d'une DAO Factory pour une base MySQL
		DAOFactory factory = DAOFactory.getDAOFactory(0);

		// Création d'un DAO pour gérer les objets via SQL
		MySqlDAOPortefeuille daoPortefeuille = (MySqlDAOPortefeuille) factory.getDaoPortefeuille();

		// Ajout du portefeuilleA à la base de données
		daoPortefeuille.create(portefeuilleA);

		// Ajout du portefeuilleB à la base de données
		daoPortefeuille.create(portefeuilleB);

		// Récupération du portefeuille B en base de données
		System.out.println("------Ajout de l'objet en base--------");
		Portefeuille pa = daoPortefeuille.getByNom("PortefeuilleA");
		pa.afficher();
		System.out.println("--- Fin Ajout de l'objet en base ----");

		// Récupération du portefeuille B en base de données
		Portefeuille pb = daoPortefeuille.getByNom("PortefeuilleB");
		//pb.afficher();

		// Suppression du portefeuille B en base de données
		daoPortefeuille.delete(pb);
		// daoPortefeuille.delete(portefeuilleB);

		// Modification du portefeuille A qui va devenir portefeuille C
		System.out.println("------Mise à jour de l'objet--------");
		pa.mettreDeviseDansPortefeuille("EURO", 9999.0);
		pa.setNomPortefeuille("PortefeuilleC");
		pa.afficher();
		System.out.println("---Fin de mise à jour de l'objet----");

		// Mise à jour du portefeuille A (devenu C)
		daoPortefeuille.update(pa);
		// On recree l'objet pa depuis les éléments en base de données
		pa = daoPortefeuille.getByNom("PortefeuilleC");
		// On vérifie que la mise à jour effective
		pa.afficher();
		
		
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

		// Modification d'un objet devise
		db.setNomDevise("YUAN");
		daodevise.update(db);
		
		
		daodevise.delete(deviseA);
	}
}
