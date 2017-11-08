package td2;

import java.util.ArrayList;

public class Portefeuille {

	// Attributs
	// Liste des devises
	private ArrayList<Devise> ListeDevise;
	private int NbreDevise = 0;

	// Constructeur
	public Portefeuille() {
		ListeDevise = new ArrayList<>();
	}

	public void ajouterDevise(Devise devise) {
		// Ajouter des devises à ListeDevise
		ListeDevise.add(devise);
	}

	public void supprimerDevise(Devise devise) {
		// Supprimer des devises à ListeDevise
		// Que se passe-t-il si la devise n'existe pas?
		ListeDevise.remove(devise);
	}

	// Modifier les paramètres à utiliser - Ce n'est pas devise mais nomDevises
	public void payerAvecDevise(Devise devise, int montant) {
		// Reduire le montant sur la devise selectionnée
		try {
			int indexCourant = ListeDevise.indexOf(devise);
			Devise deviseCourante = ListeDevise.get(indexCourant);
			int solde = deviseCourante.getMontant() - montant;

			if (solde > 0) {
				deviseCourante.setMontant(deviseCourante.getMontant() - montant);
			} else {
				// Levée une exception !!
				System.out.println("Pas assez d'argent pour payer !");
			}

		} catch (ArrayIndexOutOfBoundsException ex) {
			// Levée une exception !!
			System.out.println("La devise n'est pas dans le portefeuille");
		}

	}

	public void encaisserAvecDevise(Devise devise, int montant) {
		// Augmenter le montant sur la devise selectionnée

		try {
			int indexCourant = ListeDevise.indexOf(devise);
			Devise deviseCourante = ListeDevise.get(indexCourant);
			deviseCourante.setMontant(deviseCourante.getMontant() + montant);

		} catch (ArrayIndexOutOfBoundsException ex) {
			// Levée une exception !!
			System.out.println("La devise n'est pas dans le portefeuille, ajout de la devise");
			devise.setMontant(montant);
			ajouterDevise(devise);
		}

	}

	public void afficher() {
		// Afficher les devises presentent dans le portefeuille
		for (Devise devise : ListeDevise) {
			System.out.println(devise.toString());
		}
	}

}
