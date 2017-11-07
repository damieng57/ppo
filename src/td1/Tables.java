package td1;

import java.util.Arrays;

public class Tables {

	// Exercice 11
	public static int[] saisieTable() {
		int[] table = new int[5];

		for (int i = 0; i < table.length; i++) {
			table[i] = Saisie.saisieEntier();
		}

		return table;

	}

	public static void afficheTable(int[] tab) {

		for (int elt : tab) {
			System.out.printf(elt +" ");
		}
		System.out.println("\n");
	}

	// Exercice 12
	public static int[] copieTable(int[] tab) {
		int copie[] = tab;
		return copie;
	}

	// Exercice 13
	// Tri à bulles
	// Passage du tableau par référence
	public static void triAbulles(int[] tab) {

		boolean triOk = false;

		while (!triOk) {
			triOk = true;
			for (int i = 1; i < tab.length; i++) {
				if (tab[i - 1] > tab[i]) {
					int temp = tab[i - 1];
					tab[i - 1] = tab[i];
					tab[i] = temp;
					triOk = false;
				}
			}
		}
	}
	
	
	// Exercice 14
	// Position d'un élément dans la table
	public static int position(int [] tab, int valeur){
		
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == valeur){
				// Ajout de 1 pour avoir une position
				// donnée de manière plus naturelle
				return i+1;
			}
		}
		return -1;
	}
	
	
	// Exercice 15
	// ArrayList
	public static int exercice15(int [] tab, int valeur){
		// Copie de tableaux
		int[] array = Arrays.copyOf(tab, tab.length);
		// Tri à bulles (ou tri)
		Arrays.sort(array);
		// Position d'une valeur (Ajout de 1 pour avoir une position
		// donnée de manière plus naturelle
		return Arrays.binarySearch(array, valeur)+1;
	}

}
