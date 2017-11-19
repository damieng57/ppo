/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo1;

import java.util.ArrayList;

/**
 *
 * @author Damien
 */
public class GestionPortefeuilles {

	ArrayList<Portefeuille> groupePortefeuilles;

	public GestionPortefeuilles() {
		groupePortefeuilles = new ArrayList<>();
	}

	public void addPortefeuille(Portefeuille nouveauPortefeuille) {
		groupePortefeuilles.add(nouveauPortefeuille);
	}

	public void delPortefeuille(Portefeuille nouveauPortefeuille) {
		groupePortefeuilles.remove(nouveauPortefeuille);
	}

	public Portefeuille getPortefeuille(int index) {
		return groupePortefeuilles.get(index);
	}

	public void affiche() {
		for (Portefeuille portefeuille : groupePortefeuilles) {
			portefeuille.afficher();
		}
	}
}
