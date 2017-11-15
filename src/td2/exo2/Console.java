package td2.exo2;

import td1.Saisie;
import td2.exo2.Exceptions.ClientNotExistsException;
import td2.exo2.Exceptions.NotPositiveValueException;

/**
 *
 * Version console du programme permattant de gérer les clients
 * <p> Affiche les messages, reçoit les commandes utilisateurs et
 * execute les commandes nécessaire suivant les choix effectués.
 * </p>
 * 
 * @author Damien GAIGA
 */
public class Console {

	private static final String[] options = {
		"1. Afficher les clients",
		"2. Ajouter un client",
		"3. Ajouter du CA à un client",
		"4. Supprimer un client",
		"5. Afficher le nombre de clients",
		"6. Quitter"
	};

	/**
	 * Demarre le programme
	 * 
	 */
	public static void startConsole() {
		
		System.out.println("GESTION DE CLIENTELE");

		Clientele clientele = new Clientele();

		do {

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

			switch (reponseUtilisateur) {
				case 1:
					try {
						clientele.affiche();
					} catch (ClientNotExistsException ex) {
						// On change le message standard
						System.out.println("Il n'y a pas de client!");
					}
					break;

				case 2:
					String nomClient = Saisie.saisieChaine("Indiquer le nom du client");
					String prenomClient = Saisie.saisieChaine("Indiquer le prénom du client");
					double caClient = Saisie.saisieReel("CA du client à l'heure actuelle");
					clientele.addClient(FabriqueClient.create(nomClient, prenomClient, caClient));
					break;

				case 3:
					int indexClientAmodifier = Saisie.saisieEntier("Donner l'index du client pour ajouter du CA");
					double caClientAajouter = Saisie.saisieReel("Combien faut-il ajouter au CA du client");
					try {
						clientele.addCA(indexClientAmodifier, (float) caClientAajouter);
					} catch (ClientNotExistsException ex) {
						System.out.println(ex.getMessage());
					} catch (NotPositiveValueException ex) {
						System.out.println(ex.getMessage());
					}

					break;
				case 4:
					int indexClientAsupprimer = Saisie.saisieEntier("Donner l'index du client à supprimer");
					try {
						clientele.delClient(indexClientAsupprimer);
					} catch (ClientNotExistsException ex) {
						System.out.println(ex.getMessage());
					}

					break;
				case 5:
					clientele.afficheNombreClient();
					break;
				case 6:
					System.exit(0);
					break;

			}

		} while (true);

	}

}
