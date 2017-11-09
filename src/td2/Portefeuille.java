package td2;

import java.util.ArrayList;
import td2.Exceptions.NoMoneyException;

public class Portefeuille {

	// Attributs
	// Liste des devises
	private ArrayList<Devise> ListeDevise;

	// Constructeur
	public Portefeuille() {
		ListeDevise = new ArrayList<>();
	}

	// Methodes private
	// Uniquement présente dans un but fonctionnelle et
	// pour diviser en éléments plus simple
	private Devise chercherDevise(NomDevises nomDevise) {
		for (Devise devise : ListeDevise) {
			if (devise.getDevise().equals(nomDevise)) {
				return devise;
			}
		}
		return null;
	}

	private boolean existeDevise(Devise deviseAcherche) {
		for (Devise devise : ListeDevise) {
			if (devise.equals(deviseAcherche)) {
				return true;
			}
		}
		return false;
	}

	// Methodes publiques
	// Fonction pour obtenir le nombre de devises dans le porte-feuille
	public int nbreDevise() {
		return this.ListeDevise.size();
	}

	// Ajouter une devise dans le porte-feuille
	public void ajouterDevise(Devise devise) {
		/* Ajoute des devises à ListeDevise
		* Rien ne se passe si la devise existe
		* Pas de levée d'exception pour éviter des try/catch partout
		 */
		if (!existeDevise(devise)) {
			ListeDevise.add(devise);
		}
	}

	// Supprimer une devise dans le porte-feuille
	public void supprimerDevise(Devise devise) {
		/* Supprimer des devises à ListeDevise
		* Ne supprime que si la devise existe
		* Sinon, rien ne se passe
		 */
		if (existeDevise(devise)) {
			ListeDevise.remove(devise);
		}

	}

	// Sortir des devises du porte-feuille
	public void sortirDeviseDuPortefeuille(NomDevises nomDevise, int montant) throws NoMoneyException {
		// Reduire le montant sur la devise selectionnée
		try {
			Devise deviseCourante = chercherDevise(nomDevise);
			int solde = deviseCourante.getMontant() - montant;
			if (solde > 0) {
				deviseCourante.setMontant(deviseCourante.getMontant() - montant);
			} else {
				// On a la devise mais pas assez
				throw new NoMoneyException();
			}
		} catch (NullPointerException e) {
			// Car on n'a même pas la devise en portefeuille
			throw new NoMoneyException();
		}
	}

	// Placer des devises dans le porte-feuille
	public void mettreDeviseDansPortefeuille(NomDevises nomDevise, int montant) {
		// Augmenter le montant sur la devise selectionnée
		Devise deviseCourante = chercherDevise(nomDevise);
		try {
			deviseCourante.setMontant(deviseCourante.getMontant() + montant);
		} catch (NullPointerException e) {
			ajouterDevise(new Devise(nomDevise, montant));
		}
	}

	// Afficher les devises présentes dans le portefeuille
	public void afficher() {
		// Afficher les devises presentent dans le portefeuille
		for (Devise devise : ListeDevise) {
			System.out.println(devise.toString());
		}
	}

	// Afficher le montant présent pour une devise
	public int montantDevise(NomDevises nomDevise) {
		// Afficher les devises presentent dans le portefeuille
		try {
			return chercherDevise(nomDevise).getMontant();
		} catch (NullPointerException e) {
			return 0;
		}
	}
}
