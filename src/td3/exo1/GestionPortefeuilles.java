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
	
	public int getSize(){
		return groupePortefeuilles.size();
	}
	
	public ArrayList<Portefeuille> getGestionnaire(){
		return this.groupePortefeuilles;
	}

	public void affiche() {
		int compteur = 1;
		for (Portefeuille portefeuille : groupePortefeuilles) {
			System.out.println(String.format("%d. %s", compteur, portefeuille.toString()));
			compteur++;
		}
	}
}
