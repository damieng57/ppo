/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo4.bdd;

import java.sql.Connection;
import td3.exo4.bdd.mysql.MySqlDAOPortefeuille;

/**
 *
 * @author user
 */
public class Main {

	public static void main(String[] args) {
		Connection connexion = Connexion.getInstance();
		//Connexion.testRequete(connexion);

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
		Portefeuille pb = daoPortefeuille.getByNom("PortefeuilleB");
		pb.afficher();

		// Suppression du portefeuille B en base de données
		daoPortefeuille.delete(pb);
		// daoPortefeuille.delete(portefeuilleB);

		// Modification du portefeuille A
		portefeuilleA.ajouterDevise("EURO", 1000.0);

		// Mise à jour du portefeuille A
		Portefeuille pa = daoPortefeuille.getByNom("PortefeuilleA");
		factory.getDaoPortefeuille().update(pa);
		pa.afficher();

	}
}
