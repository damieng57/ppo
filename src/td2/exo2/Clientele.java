package td2.exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import td2.exo2.Exceptions.ClientNotExistsException;
import td2.exo2.Exceptions.ClientAlreadyExistsException;
import td2.exo2.Exceptions.NotImportantConsumerException;
import td2.exo2.Exceptions.NotPositiveValueException;

/**
 * La classe clientèle gère une HashMap(Integer, Client)
 * <p>
 * On utilise les propriètés de la HashMap pour ajouter/supprimer ou modifier
 * les clients dans la liste. </p>
 *
 * @author Damien GAIGA
 *
 */
public class Clientele {

// S'incremente à chaque création de clients
// mais ne décrémente jamais - un client supprimé n'existe plus
	private static int indexMap = 0;

// Permet de connaitre le nombre de clients. On aurait pu également se contenter
// d'utiliser la méthode .size() de la HashMap. J'ai voulu exposer un cas d'utilisation
// d'une variable de classe
	private static int nombreClient = 0;

//	Pourquoi utiliser un objet de type Hashmap/TreeMap ?
//	Pour fournir à chaque nouveau client un identifiant associé
//	Même si l'on supprime des éléments, le numéro client ne change pas (contrairement
//	à un indice dans un tableau qui change si l'on supprime/ajout/réordonne des éléments
	private HashMap<Integer, Client> clientele;

	/**
	 * Constructeur de clientèle. Ne prends pas de paramètre.
	 */
	public Clientele() {
		clientele = new HashMap<Integer, Client>();
	}

	/**
	 * Modifie le chiffre d'affaires d'un client présent dans la liste clientèle
	 *
	 *
	 * @param num Index du client dans la HashMap
	 * @param chiffre Chiffre d'affaires à ajouter au client
	 */
	public void addCA(int num, float chiffre) {
		if (clientele.containsKey(num)) {
			Client clientAmettreAjour = clientele.get(num);
			// On ajoute du CA au client, on ne peut donc rien soustraire, on interdit
			// les valeurs négatives
			if (chiffre < 0) {
				throw new NotPositiveValueException();
			} else {
				// On tente de promouvoir le client en fonction de son CA si nécessaire
				clientAmettreAjour.setCaClient(clientAmettreAjour.getCaClient().getMontant() + chiffre);
				try {
					updateClient(num, new ClientPrivilegie(clientAmettreAjour));
				} catch (NotImportantConsumerException ex) {
					// Affichage uniquement pour deboggage
					// Ce doit être transparent pour l'utilisateur
					System.err.println(ex);
				}
			}
			affiche();
		} else {
			throw new ClientNotExistsException();

		}

	}

	/**
	 *
	 * Ajoute un client à la liste clientèle
	 *
	 * <p>
	 * NOTA : Je ne teste pas s'il existe des homonymes. L'élément différenciant
	 * sera l'index du client dans la HashMap</p>
	 *
	 * @param clientAajouter Objet client à ajouter à la HashMap
	 */
	public void addClient(Client clientAajouter) {

		if (clientele.containsValue(clientAajouter)) {
			throw new ClientAlreadyExistsException();
		} else {
			if (clientAajouter.getCaClient().getMontant() >= 1000) {
				clientAajouter = new ClientPrivilegie(clientAajouter);
			}
			clientele.put(indexMap, clientAajouter);
			indexMap++;
			nombreClient++;
			affiche();
		}
	}

	/**
	 *
	 * Supprime un client à la liste clientèle
	 *
	 * @param indexClientAsupprimer Supprimer un client de la liste suivant son
	 * index
	 */
	public void delClient(int indexClientAsupprimer) {

		if (clientele.containsKey(indexClientAsupprimer)) {
			clientele.remove(indexClientAsupprimer);
			nombreClient--;
			affiche();
		} else {
			throw new ClientNotExistsException();

		}
	}

	/**
	 *
	 * Mettre à jour un client de la liste
	 *
	 * @param indexClientAmettreAjour Index du client sur lequel porte la mise à
	 * jour des données
	 * @param clientAmettreAjour Objet client à modifier dans la HashMap
	 */
	public void updateClient(int indexClientAmettreAjour, Client clientAmettreAjour) {

		if (clientele.containsKey(indexClientAmettreAjour)) {
			clientele.replace(indexClientAmettreAjour, clientAmettreAjour);
		} else {
			throw new ClientNotExistsException();

		}
	}

	// METHODE AFFICHE A VERTU PEDAGOGIQUE - NE RESPECTE PAS LE MVC
	/**
	 *
	 * Afficher la liste des clients ordonnés suivant suivant l'ordre
	 * décroissant de leur chiffre d'affaires
	 *
	 */
	public void affiche() {

		// Copie dans une liste pour trier suivant le CA
		ArrayList<Client> listeDeclientsAtrier = new ArrayList<>(clientele.values());

		// On aurait pu également regarder la variable nombre de client pour le test
		if (listeDeclientsAtrier.size() == 0) {
			throw new ClientNotExistsException();
		} else {
			Collections.sort(listeDeclientsAtrier, new CaClientComparator());

			// Pourquoi si compliqué?
			// Pour permettre de retrouver l'ID du client aprés le tri de la liste et l'afficher
			for (Client client : listeDeclientsAtrier) {
				for (Map.Entry<Integer, Client> chercheIdClient : clientele.entrySet()) {
					if (client.equals(chercheIdClient.getValue())) {
						System.out.println(String.format("%s %s", String.valueOf(chercheIdClient.getKey()), client.toString()));
					}
				}
			}
		}

	}

	/**
	 * Affiche le nombre de clients dans la liste clientèle
	 *
	 * @see nombreClient
	 *
	 */
	public void afficheNombreClient() {
		System.out.println(String.format("Il y a : %s client(s) enregistré(s)", nombreClient));
	}
}
