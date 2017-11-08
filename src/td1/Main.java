/*
 * TD1 : PPO
 */
package td1;

import java.io.IOException;

/**
 *
 * @author Damien
 */
public class Main {

	public static void main(String [] args) throws IOException{
		
		System.out.println("Exercice 10");
		Bonjour.exercice10();
		
		// Pour exercices 11 à 15
		System.out.println("\nExercices 11 à 14");
		System.out.println("Renseigner 5 nombres : ");
		int tab [] = Tables.saisieTable();
		int tab2 [] = Tables.copieTable(tab);
		
		// Test des exercices 11 à 14
		System.out.println("Afficher la table 1");
		Tables.afficheTable(tab);
		System.out.println("Afficher la table 2");
		Tables.afficheTable(tab2);
		
		// Tri de la table 1
		System.out.println("\nTri de la table 1");
		Tables.triAbulles(tab);
		
		System.out.println("\nAfficher la table 1");
		Tables.afficheTable(tab);
		System.out.println("\nAfficher la table 2");
		Tables.afficheTable(tab2);
		
		System.out.println("\nLa valeur recherchée (5) est en position " + Tables.position(tab, 5)+"\n");
		
		System.out.println("Exercice 15 (reprends la liste 2 des exercices précédents)");
		System.out.println(Tables.exercice15(tab2, 5));
	}
	
}
