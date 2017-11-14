package td2.exo2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import td2.exo2.Exceptions.ClientNotExistsException;
import td2.exo2.Exceptions.ClientAlreadyExistsException;
import td2.exo2.Exceptions.NotImportantConsumerException;

public class Clientele {

	public static int indexMap = 0;

	private HashMap<Integer, Client> clientele;

	public Clientele() {
		clientele = new HashMap<>();
	}

	public void addCA(int num, float chiffre) {
		if (clientele.containsKey(num)) {
			Client clientAmettreAjour = clientele.get(num);
			clientAmettreAjour.setCaClient(clientAmettreAjour.getCaClient() + chiffre);

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

	public void affiche() {

		
		List<Client> listeDeclientsAtrier = new ArrayList<>(clientele.values());
		Collections.sort(listeDeclientsAtrier, new CaClientComparator());

		for (Client client : listeDeclientsAtrier) {
			System.out.println(client.toString());
		}

//		for (Map.Entry<Integer, Client> client : clientele.entrySet()) {
//			System.out.println(String.format("%s %s", String.valueOf(client.getKey()), String.valueOf(client.getValue())));
//		}
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

// PAS ENCORE FONCTIONNEL
//	public void delClient(Client clientAsupprimer) {
//
//		if (clientele.containsValue(clientAsupprimer)) {
//
//			for (Map.Entry<Integer, Client> client : clientele.entrySet()) {
//				Client clientActuel = clientele.get(client.getKey());
//				if (client.getValue() == clientActuel){
//					clientele.remove(client.getKey());
//				}
//			}
//			
//		} else {
//			throw new ClientNotExistsException();
//
//		}
//	}

	public void updateClient(int indexClientAmettreAjour, Client clientAmettreAjour) {

		if (clientele.containsKey(indexClientAmettreAjour)) {
			clientele.replace(indexClientAmettreAjour, clientAmettreAjour);
		} else {
			throw new ClientNotExistsException();

		}
	}

}
