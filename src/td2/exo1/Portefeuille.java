package td2.exo1;

import td2.exo1.Exceptions.CurrencyNotExistsException;
import java.util.ArrayList;
import td2.exo1.Exceptions.NoMoneyException;

public class Portefeuille {

	// Attributs
	// Liste des devises
	private ArrayList<Devise> listeDevise;

	// Constructeur
	public Portefeuille() {
		listeDevise = new ArrayList<>();
	}

	// Methodes private
	// Uniquement présente dans un but fonctionnelle et
	// pour diviser le code en élément simple
	private Devise chercherDevise(NomDevises nomDevise) {

		// Possiblité d'utiliser les fonctions des ArrayLists
//		int idx = listeDevise.indexOf(new Devise(nomDevise,0));
//		if (idx>=0) {
//			return listeDevise.get(idx);
//		}
//		return null;
		// Version "manuelle"
		for (Devise devise : listeDevise) {
			if (devise.getDevise().equals(nomDevise)) {
				return devise;
			}
		}
		return null;
	}

	private boolean existeDevise(Devise deviseAcherche) {
		return listeDevise.contains(deviseAcherche);
	}

	// Methodes publiques
	// Fonction pour obtenir le nombre de devises dans le porte-feuille
	public int nbreDevise() {
		return this.listeDevise.size();
	}

	// Ajouter une devise dans le porte-feuille
	public void ajouterDevise(Devise devise) {
		/* Ajoute des devises à ListeDevise
		* Si la devise existe, on ajoute simplement
		* le montant à la devise en portefeuille
		 */
		if (!existeDevise(devise)) {
			listeDevise.add(devise);
		} else {
			mettreDeviseDansPortefeuille(devise.getDevise(), devise.getMontant());
		}
	}

	// Supprimer une devise dans le porte-feuille
	public void supprimerDevise(Devise devise) {
		/* Supprimer des devises à ListeDevise
		* Sinon alerte via CurrencyNotExistsException();
		 */
		if (existeDevise(devise)) {
			listeDevise.remove(devise);
		} else {
			throw new CurrencyNotExistsException();
		}
	}

	// Sortir des devises du porte-feuille
	public void sortirDeviseDuPortefeuille(NomDevises nomDevise, double montant) {
		// Reduire le montant sur la devise selectionnée
		Devise deviseCourante = chercherDevise(nomDevise);
		if (deviseCourante != null) {
			deviseCourante.setMontant(deviseCourante.getMontant() - montant);
		} else {
			throw new NoMoneyException();
		}
	}

	// Placer des devises dans le porte-feuille
	public void mettreDeviseDansPortefeuille(NomDevises nomDevise, double montant) {
		// Augmenter le montant sur la devise selectionnée
		Devise deviseCourante = chercherDevise(nomDevise);
		try {
			deviseCourante.setMontant(deviseCourante.getMontant() + montant);
		} catch (NullPointerException | NoMoneyException e) {
			ajouterDevise(new Devise(nomDevise, montant));
		}
	}

	// Afficher les devises présentes dans le portefeuille
	public void afficher() {
		// Afficher les devises presentent dans le portefeuille
		for (Devise devise : listeDevise) {
			System.out.println(devise.toString());
		}
	}

	// Afficher le montant présent pour une devise
	public double montantDevise(NomDevises nomDevise) {
		// Afficher les devises presentent dans le portefeuille
		try {
			return chercherDevise(nomDevise).getMontant();
		} catch (NullPointerException e) {
			throw new CurrencyNotExistsException();
		}
	}

	public ArrayList<Devise> getListeDevise() {
		return listeDevise;
	}
}
