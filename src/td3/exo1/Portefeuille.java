package td3.exo1;

import td2.exo1.Exceptions.CurrencyNotExistsException;
import java.util.HashMap;
import java.util.Map.Entry;
import td2.exo1.Exceptions.NoMoneyException;

public class Portefeuille {

	// Attributs
	// Liste des devises
	private String nomPortefeuille;
	private HashMap<Devise, Double> listeDevise;

	// Getter/Setter nom du portefeuille
	public String getNomPortefeuille() {
		return nomPortefeuille;
	}

	public void setNomPortefeuille(String nomPortefeuille) {
		this.nomPortefeuille = nomPortefeuille;
	}

	// Constructeur
	public Portefeuille(String nomPortefeuille) {
		this.setNomPortefeuille(nomPortefeuille);
		listeDevise = new HashMap<>();
	}

	// Methodes private
	// Uniquement présente dans un but fonctionnelle et
	// pour diviser le code en élément simple
	// Afficher le montant présent pour une devise
	private Devise deviseTemp(NomDevises nomDevise) {
		// Création d'un objet devise pour recherche
		return new Devise(nomDevise);
	}

	public Double montantDevise(NomDevises nomDevise) {
		if (existeDevise(nomDevise)) {
			// Retourne le montant associé à la devise
			return listeDevise.get(deviseTemp(nomDevise));
		} else {
			throw new CurrencyNotExistsException();
		}
	}

	private boolean existeDevise(NomDevises deviseAcherche) {
		return listeDevise.containsKey(deviseTemp(deviseAcherche));
	}

	// Methodes publiques
	// Fonction pour obtenir le nombre de devises dans le porte-feuille
	public int nbreDevise() {
		return this.listeDevise.size();
	}

	// Ajouter une devise dans le porte-feuille avec un montant prédéfini
	public void ajouterDevise(NomDevises nomDevise, Double montant) {
		/* Ajoute des devises à ListeDevise
		* Si la devise existe, on ajoute simplement
		* le montant à la devise en portefeuille
		 */
		if (!existeDevise(nomDevise)) {
			listeDevise.put(deviseTemp(nomDevise), montant);
		} else {
			mettreDeviseDansPortefeuille(nomDevise, montant);
		}
	}

	// Ajouter une devise dans le porte-feuille avec un montant prédéfini
	public void ajouterDevise(NomDevises nomDevise) {
		/* Ajoute des devises directement à partir d'un nom de devise
		* issue de l'énumération
		 */
		ajouterDevise(nomDevise, 0.0);
	}

	// Supprimer une devise dans le porte-feuille
	public void supprimerDevise(NomDevises nomDevise) {
		/* Supprimer des devises à ListeDevise
		* Sinon alerte via CurrencyNotExistsException();
		 */
		if (existeDevise(nomDevise)) {
			listeDevise.remove(deviseTemp(nomDevise));
		} else {
			throw new CurrencyNotExistsException();
		}
	}

	// Sortir des devises du porte-feuille
	public void sortirDeviseDuPortefeuille(NomDevises nomDevise, double montant) {
		// Reduire le montant sur la devise selectionnée
		Devise deviseTemp = deviseTemp(nomDevise);
		try {
			Double nouveauMontant = montantDevise(nomDevise) - montant;
			if (existeDevise(nomDevise) != false && nouveauMontant >= 0) {
				listeDevise.replace(deviseTemp, nouveauMontant);
			} else {
				throw new NoMoneyException();
			}
		} catch (NullPointerException e) {
			throw new NoMoneyException();
		}
	}
	// Placer des devises dans le porte-feuille

	public void mettreDeviseDansPortefeuille(NomDevises nomDevise, double montant) {
		// Augmenter le montant sur la devise selectionnée
		//Devise deviseCourante = chercherDevise(nomDevise);
		Devise deviseTemp = deviseTemp(nomDevise);
		try {
			Double nouveauMontant = montantDevise(nomDevise) + montant;
			listeDevise.replace(deviseTemp, nouveauMontant);
		} catch (NullPointerException | NoMoneyException | CurrencyNotExistsException e) {
			ajouterDevise(nomDevise, montant);
		}
	}

	// Afficher les devises présentes dans le portefeuille
	public void afficher() {
		// Afficher les devises presentent dans le portefeuille
		System.out.println(String.format("%s".toUpperCase(), nomPortefeuille));

		boolean flag = false;

		for (Entry<Devise, Double> entry : listeDevise.entrySet()) {
			Devise cle = entry.getKey();
			Double valeur = entry.getValue();
			System.out.println(String.format("%s : %.02f", cle, valeur));
			flag = true;
		}

		if (!flag) {
			System.out.println("Ce portefeuille est vide");
		}

		System.out.println("FIN DU PORTEFEUILLE");
	}

	private Object deviseTemp() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}

//	Pour mémoire
//	private void lireClePortefeuille() {
//		for (Devise entry : listeDevise.keySet()) {
//			Devise cle = entry;
//			// traitements
//		}
//	}
//	private Double[] getValeursPortefeuille() {
//		Double[] montant = (Double[]) listeDevise.values().toArray();
//		return montant;
//	}
//	public Double[] getListeDevise() {
//		Double[] listeDevises = (Double[]) listeDevise.keySet().toArray();
//		return listeDevises;
//	}
//	private boolean existeDevise(Devise deviseAcherche) {
//		return listeDevise.containsKey(deviseAcherche);
//	}
