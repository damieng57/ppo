package td2.exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import td2.exo2.Exceptions.ClientNotExistsException;
import td2.exo2.Exceptions.ClientAlreadyExistsException;
import td2.exo2.Exceptions.NotImportantConsumerException;

public class Clientele {

	public static int indexMap = 0;
		
	/*
	Pourquoi utiliser un objet de type Hashmap/TreeMap ?
	Pour fournir à chaque nouveau client un identifiant associé
	Même si l'on supprime des éléments, le numéro client ne change pas (contrairement
	à un indice dans un tableau qui change si l'on supprime/ajout/réordonne des éléments
	*/

	private HashMap<Integer, Client> clientele;

	public Clientele() {
		clientele = new HashMap<Integer, Client>();
	}

	public void addCA(int num, float chiffre) {
		if (clientele.containsKey(num)) {
			Client clientAmettreAjour = clientele.get(num);
			clientAmettreAjour.setCaClient(clientAmettreAjour.getCaClient().getMontant() + chiffre);

			// On tente de promouvoir le client en fonction de son CA
			try {
				updateClient(num, new ClientPrivilegie(clientAmettreAjour));
			} catch (NotImportantConsumerException ex) {
				// Affichage uniquement pour deboggage
				// Ce doit être transparent pour l'utilisateur
				System.err.println(ex);
			}

		} else {
			throw new ClientNotExistsException();

		}

	}

	public void addClient(Client clientAajouter) {

		if (clientele.containsValue(clientAajouter)) {
			throw new ClientAlreadyExistsException();
		} else {
			clientele.put(indexMap, clientAajouter);
			indexMap++;
		}
	}

	public void delClient(int indexClientAsupprimer) {

		if (clientele.containsKey(indexClientAsupprimer)) {
			clientele.remove(indexClientAsupprimer);
		} else {
			throw new ClientNotExistsException();

		}
	}

	public void updateClient(int indexClientAmettreAjour, Client clientAmettreAjour) {

		if (clientele.containsKey(indexClientAmettreAjour)) {
			clientele.replace(indexClientAmettreAjour, clientAmettreAjour);
		} else {
			throw new ClientNotExistsException();

		}
	}

	public void affiche() {
		
		// Copie dans une liste pour trier suivant le CA
		ArrayList<Client> listeDeclientsAtrier = new ArrayList<>(clientele.values());
		
		if (listeDeclientsAtrier.size() == 0){
			throw new ClientNotExistsException();
		} else {
			Collections.sort(listeDeclientsAtrier, new CaClientComparator());
			
			// Pourquoi si compiqué?
			// Pour permettre de retrouver l'ID du client aprés le tri de la liste et l'afficher
			
			for (Client client : listeDeclientsAtrier) {
				for (Map.Entry<Integer, Client> chercheIdClient : clientele.entrySet()) {
					if (client.equals(chercheIdClient.getValue())){
						System.out.println(String.format("%s %s", String.valueOf(chercheIdClient.getKey()), client.toString()));
					}
				}
			}
		}
		
	}
}
