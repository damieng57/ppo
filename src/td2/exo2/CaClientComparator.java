package td2.exo2;

import java.util.Comparator;

public class CaClientComparator implements Comparator<Client>{

	@Override
	public int compare(Client client1, Client client2) {
		// La méthode compareTo nécessite de comparer 2 chaînes de caractères.
		// Dans notre cas, pour ordonner dans le sens décroissant, nous allons
		// comparer les chiffres d'affaires.
		// Nous retournerons la valeur de résultat pour le tri
		double monComparateur = client1.getCaClient().getMontant() - client2.getCaClient().getMontant();
		int resultat;
		
		// NOTA: Pour tri dans le sens croissant, on inverse le signe au niveau
		// de la condition ou bien on inverse les valeurs de résultat entre la
		// premiere et la deuxième condition
		if(monComparateur > 0){
			resultat = -1;
		} else if (monComparateur < 0){
			resultat = 1;
		} else {
			resultat = 0;
		}
		return resultat;
	}
	
}
