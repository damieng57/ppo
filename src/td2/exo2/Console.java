/*
 * Programme en mode console permattant de gérer les clients
 * 
 * 
 */
package td2.exo2;

import java.util.Scanner;
import td1.Saisie;

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
					clientele.affiche();
					break;
				case 2:
					clientele.addClient(Client.saisie());
					break;
				case 3:
					int indexClient = Saisie.saisieEntier();
					//clientele.addCA(indexClient);
					break;
				case 4:
					//clientele.delClient();
					break;
				case 5:
					System.exit(0);
					break;

			}

		} while (true);

	}

}
