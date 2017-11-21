package td3.exo1;

import td1.Saisie;
import td2.exo1.Exceptions.NoMoneyException;
import td3.exo1.exceptions.NoWalletException;
import td3.exo2.fichiersplats.ChargerPortefeuille;
import td3.exo2.fichiersplats.SauvegarderPortefeuille;

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
		"4. Sauvegarder les portefeuilles",
		"5. Quitter"
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


		GestionPortefeuilles gestionPortefeuilles = new GestionPortefeuilles();
		ChargerPortefeuille.charge(gestionPortefeuilles);
		System.out.println("*** GESTION DE PORTEFEUILLE ***");
		menuPrincipal(gestionPortefeuilles);
	}

	private static void menuPrincipal(GestionPortefeuilles gestionPortefeuilles) {
		gestionPortefeuilles.affiche();

		do {

			switch (afficherMenu(menuPrincipal)) {
				// Choisir un portefeuille
				case 1:
					if (gestionPortefeuilles.getSize() > 0) {
						try {
							menuGestionPortefeuille(gestionPortefeuilles.getPortefeuille(Saisie.saisieEntier("Choisir un portefeuille") - 1), gestionPortefeuilles);
						} catch (NoWalletException | IndexOutOfBoundsException ex) {
							System.out.println("Le portefeuille n'existe pas");
						}
					} else {
						System.out.println("Aucun portefeuille à gérer. Veuillez en créer au moins un.");
					}
					break;
				// Afficher un portefeuille
				case 2:
					//TODO : Mecanisme pour saisir un nouveau portefeuille
					//Tester si le portefeuille existe déjà avec le même nom
					gestionPortefeuilles.addPortefeuille(new Portefeuille(Saisie.saisieChaine("Donner un nom au portefeuille")));
					break;
				// Afficher les portefeuilles
				case 3:
					gestionPortefeuilles.affiche();
					break;
				// Sauvegarder les portefeuilles
				case 4:
					String reponse = Saisie.saisieChaine("Voulez-vous sauvegarder les portefeuilles ? (o pour oui, autre touche pour annuler)");
					if (reponse.equals("o")) {
						SauvegarderPortefeuille.sauvegarde(gestionPortefeuilles);
					} else {
						System.out.println("Sauvegarde annulée");
					}
					break;
				// Quitter
				case 5:
					System.exit(0);
					break;
			}

		} while (true);
	}

	private static void menuGestionPortefeuille(Portefeuille portefeuille, GestionPortefeuilles gestionPortefeuilles) {
		do {
			int nomDevise;
			double montant;
			switch (afficherMenu(menuGestionPortefeuille)) {
				// Ajouter au portefeuille courant
				case 1:
					// Mecanisme pour permettre de choisir la devise et le montant à ajouter
					ChargerDevise.affiche();
					nomDevise = Saisie.saisieEntier("Choisir une devise pour l'ajout");
					montant = Saisie.saisieReel("Choisir le montant à ajouter");

					portefeuille.mettreDeviseDansPortefeuille(ChargerDevise.getSymbol(String.valueOf(nomDevise)), montant);
					break;
				// Retrait du portefeuille courant
				case 2:
					// Mecanisme pour permettre de choisir la devise et le montant à retirer
					ChargerDevise.affiche();
					try {
						nomDevise = Saisie.saisieEntier("Choisir une devise pour le retrait");
						montant = Saisie.saisieReel("Choisir le montant à retirer");
						portefeuille.sortirDeviseDuPortefeuille(ChargerDevise.getSymbol(String.valueOf(nomDevise)), montant);
					} catch (NoMoneyException ex) {
						System.out.println("Pas assez d'argent. Retrait impossible");
					}
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
}
