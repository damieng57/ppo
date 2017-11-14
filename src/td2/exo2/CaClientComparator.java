package td2.exo2;

import td2.exo1.*;
import java.util.Comparator;

public class CaClientComparator implements Comparator<Client>{

	@Override
	public int compare(Client client1, Client client2) {
		// getDevise retourne un objet de type NomDevise. 
		// La méthode compareTo nécessite de comparer 2 chaînes de caractères.
		// On récupère donc le symbole de chaque devise grâce à toString de NomDevises
		// Ce n'est pas nécessaire si la valeur à comparer est déjà une chaîne
		String caClient1 = String.valueOf(client1.getCaClient());
		String caClient2 = String.valueOf(client2.getCaClient());
		
		return caClient1.compareTo(caClient2);
	}
	
}
