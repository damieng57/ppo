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
		//Bonjour.exercice8();
		
		// Pour exercices 11 à 15
		int tab [] = Tables.saisieTable();
		
		// Test des exercices 11 à 14
		//Tables.afficheTable(tab);
		//Tables.triAbulles(tab);
		//Tables.afficheTable(tab);
		//System.out.println("La valeur recherchée est en position " + Tables.position(tab, 5));
		
		//Tables.afficheTable(tab);
		System.out.println(Tables.exercice15(tab, 5));
	}
	
}
