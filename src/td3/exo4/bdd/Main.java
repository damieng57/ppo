/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo4.bdd;

import java.sql.Connection;

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

		// Création ou récupération de l'objet DAO relative aux portefeuilles
		DAO<Portefeuille> daoPortefeuille = DAOPortefeuille.getInstance();

		// Ajout du portefeuilleA à la base de données
		daoPortefeuille.create(portefeuilleA);

		// Ajout du portefeuilleA à la base de données
		daoPortefeuille.create(portefeuilleB);

		// Création d'un objet portefeuilleA
		Portefeuille portefeuilleC = new Portefeuille("PortefeuilleC");
		// Ajout de devises
		portefeuilleB.ajouterDevise("EURO", 75.12);
		portefeuilleB.ajouterDevise("DOLLAR", 125.74);
		portefeuilleB.ajouterDevise("LIVRE", 863.54);

		// Ajout du portefeuilleA à la base de données
		daoPortefeuille.create(portefeuilleC);

	}

}
