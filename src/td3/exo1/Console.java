package td3.exo1;

import td1.Saisie;
import td3.exo1.Exceptions.NoWalletException;

/**
 *
 * Version console du programme permattant de gérer les portefeuilles
 * <p>
 * Affiche les messages, reçoit les commandes utilisateurs et execute les
 * commandes nécessaire suivant les choix effectués.
 * </p>
 *
 * @author Damien GAIGA
 */
public class Console {

	private static final String[] menuPrincipal = {
		"1. Choisir un portefeuille",
		"2. Créer un portefeuille",
		"3. Afficher les portesfeuilles",
		"4. Quitter"
	};

	private static final String[] menuGestionPortefeuille = {
		"1. Ajouter au portefeuille courant",
		"2. Retrait du portefeuille courant",
		"3. Supprimer le portefeuille courant",
		"4. Afficher le portefeuille courant",
		"5. Retour"};

	/**
	 * Demarre le programme
	 *
	 */
	public static void startConsole() {

		//-----------------------
		// Portefeuille pour tests
		GestionPortefeuilles gestionPortefeuilles = new GestionPortefeuilles();
		gestionPortefeuilles.addPortefeuille(genererPortefeuilleA());
		gestionPortefeuilles.addPortefeuille(genererPortefeuilleB());
		//-----------------------

		System.out.println("GESTION DE PORTEFEUILLE");

		menuPrincipal(gestionPortefeuilles);

	}

	private static void menuPrincipal(GestionPortefeuilles gestionPortefeuilles) {
		do {

			switch (afficherMenu(menuPrincipal)) {
				// Choisir un portefeuille
				case 1:
					try {
						//TODO : Mécanisme pour choisir un portefeuille spécifique 
						menuGestionPortefeuille(gestionPortefeuilles.getPortefeuille(0), gestionPortefeuilles);
					} catch (NoWalletException | IndexOutOfBoundsException ex) {
						System.out.println("Aucun portefeuille");
					}
					break;
				// Afficher un portefeuille
				case 2:
					//TODO : Mecanisme pour saisir un nouveau portefeuille
					//Tester si le portefeuille existe déjà avec le même nom
					gestionPortefeuilles.addPortefeuille(new Portefeuille("Portefeuille de Sylia"));
					break;
				case 3:
					gestionPortefeuilles.affiche();
					break;
				// Quitter
				case 4:
					System.exit(0);
					break;
			}

		} while (true);
	}

	private static void menuGestionPortefeuille(Portefeuille portefeuille, GestionPortefeuilles gestionPortefeuilles) {
		do {

			switch (afficherMenu(menuGestionPortefeuille)) {
				// Ajouter au portefeuille courant
				case 1:
					// TODO : Mecanisme pour permettre de choisir la devise et la montant à ajouter
					portefeuille.mettreDeviseDansPortefeuille(NomDevises.LIVRE, 500.0);
					break;
				// Retrait du portefeuille courant
				case 2:
					// TODO : Mecanisme pour permettre de choisir la devise et la montant à retirer
					portefeuille.sortirDeviseDuPortefeuille(NomDevises.LIVRE, 250.0);
					break;
				// Supprimer le portefeuille courant
				case 3:
					gestionPortefeuilles.delPortefeuille(portefeuille);
					menuPrincipal(gestionPortefeuilles);
					break;
				// Afficher le portefeuille courant
				case 4:
					portefeuille.afficher();
					break;
				//Retour au menu principal
				case 5:
					menuPrincipal(gestionPortefeuilles);
					break;
			}

		} while (true);
	}

	private static int afficherMenu(String[] options) {
		// Affichage du menu
		System.out.println("\n");
		for (Object option : options) {
			System.out.println(option);
		}

		// Attente d'une réponse de l'utilisateur
		int reponseUtilisateur;

		// Traitement du choix utilisateur
		do {
			reponseUtilisateur = Saisie.saisieEntier();
		} while (reponseUtilisateur < 1 || reponseUtilisateur > options.length + 1);
		return reponseUtilisateur;
	}

	//-----------------------
	// Fonctions présentes uniquement pour le test
	private static Portefeuille genererPortefeuilleA() {

		// Générer un portefeuille
		Portefeuille porteFeuilleDePatrick = new Portefeuille("Portefeuille de Patrick");
		// Ajout des devises
		porteFeuilleDePatrick.ajouterDevise(NomDevises.EURO, 50.0);
		porteFeuilleDePatrick.ajouterDevise(NomDevises.DOLLAR, 150.0);
		// Test d'ajout d'une devise existant, DOLLAR doit passer à 250$
		porteFeuilleDePatrick.ajouterDevise(NomDevises.DOLLAR, 100.0);
		// Test d'ajout à une devise, ici DOLLAR doit passer à 400$
		porteFeuilleDePatrick.mettreDeviseDansPortefeuille(NomDevises.DOLLAR, 150.0);
		//porteFeuilleDePatrick.supprimerDevise(NomDevises.EURO);

		return porteFeuilleDePatrick;

	}

	private static Portefeuille genererPortefeuilleB() {

		Portefeuille porteFeuilleDeJohn = new Portefeuille("Portefeuille de John");
		porteFeuilleDeJohn.ajouterDevise(NomDevises.EURO, 100.0);
		porteFeuilleDeJohn.ajouterDevise(NomDevises.DOLLAR, 1000.0);
		porteFeuilleDeJohn.sortirDeviseDuPortefeuille(NomDevises.DOLLAR, 500.0);

		return porteFeuilleDeJohn;

	}

}
