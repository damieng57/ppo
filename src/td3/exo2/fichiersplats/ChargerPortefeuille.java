/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo2.fichiersplats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import td3.exo1.ChargerDevise;
import td3.exo1.GestionPortefeuilles;
import td3.exo1.Portefeuille;

/**
 *
 * @author Damien GAIGA
 */
public class ChargerPortefeuille {

	public static GestionPortefeuilles charger() {

		GestionPortefeuilles obj = new GestionPortefeuilles();
		Portefeuille portefeuille = new Portefeuille();
		Scanner sc = null;
		ArrayList<String> listeDevise = ChargerDevise.liste();

		try {
			sc = new Scanner(new File("src\\td3\\exo2\\save.txt"));
			while (sc.hasNextLine()) {
				
				String next = sc.nextLine();
				String[] table = next.split(" : ");
								
				if (next.equals("FIN DU PORTEFEUILLE")) {
					portefeuille.afficher();
					obj.addPortefeuille(portefeuille);
				} else if (listeDevise.contains(table[0])) {
					portefeuille.mettreDeviseDansPortefeuille(table[0], Double.valueOf(table[1]));
				} else {				
					portefeuille = new Portefeuille(next);
				}
			}
			return obj;

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			sc.close();
		}
		return null;
	}
}
