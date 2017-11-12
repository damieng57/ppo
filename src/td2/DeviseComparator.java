package td2;

import java.util.Comparator;

public class DeviseComparator implements Comparator<Devise>{

	@Override
	public int compare(Devise devise1, Devise devise2) {
		// getDevise retourne un objet de type NomDevise. 
		// La méthode compareTo nécessite de comparer 2 chaînes de caractères.
		// On récupère donc le symbole de chaque devise grâce à toString de NomDevises
		// Ce n'est pas nécessaire si la valeur à comparer est déjà une chaîne
		NomDevises nomO1 = devise1.getDevise();
		NomDevises nomO2 = devise2.getDevise();
		
		return nomO1.toString().compareTo(nomO2.toString());
	}
	
}
