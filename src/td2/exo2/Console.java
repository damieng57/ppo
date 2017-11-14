/*
 * Programme en mode console permattant de gérer les clients
 * 
 * 
 */
package td2.exo2;

import java.util.Scanner;
import td1.Saisie;
import td2.exo2.Exceptions.ClientNotExistsException;

/**
 *
 * @author gaiga4u
 */
public class Console {

	private static final String[] options = {
		"1. Afficher les clients",
		"2. Ajouter un client",
		"3. Modifier CA d'un client",
		"4. Supprimer un client",
		"5. Quitter"
	};

	public static void startConsole() {

		Clientele clientele = new Clientele();

		do {

			// Affichage du menu
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
						System.out.println("Il n'y a pas de client!");
					}
					break;
					
				case 2:
					String nomClient = Saisie.saisieChaine("Indiquer le nom du client");
					String prenomClient = Saisie.saisieChaine("Indiquer le prénom du client");
					double caClient = Saisie.saisieReel("CA du client à l'heure actuelle");
					clientele.addClient(new Client(nomClient, prenomClient, caClient));
					break;
					
				case 3:
					int indexClientAmodifier = Saisie.saisieEntier("Donner l'index du client pour ajouter du CA");
					double caClientAajouter = Saisie.saisieReel("Combien faut-il ajouter au CA du client");
					try {
						clientele.addCA(indexClientAmodifier, (float) caClientAajouter);
					} catch (ClientNotExistsException ex) {
						System.out.println("L'indice du client n'existe pas");
					}

					break;
				case 4:
					int indexClientAsupprimer = Saisie.saisieEntier("Donner l'index du client à supprimer");
					try {
						clientele.delClient(indexClientAsupprimer);
					} catch (ClientNotExistsException ex) {
						System.out.println("L'indice du client n'existe pas");
					}

					break;
				case 5:
					System.exit(0);
					break;

			}

		} while (true);

	}

}
