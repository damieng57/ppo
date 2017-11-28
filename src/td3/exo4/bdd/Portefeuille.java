package td3.exo4.bdd;

import td3.exo1.*;
import java.io.Serializable;
import td2.exo1.Exceptions.CurrencyNotExistsException;
import java.util.HashMap;
import java.util.Map.Entry;
import td2.exo1.Exceptions.NoMoneyException;

public class Portefeuille implements Serializable{
	
	private static final long serialVersionUID = 0120171121L;

	// Attributs
	// Liste des devises
	private int idPortefeuille;

	private String nomPortefeuille;
	private HashMap<Devise, Double> listeDevise;

	public Portefeuille() {
		
	}

	// Getter/Setter nom du portefeuille
	public String getNomPortefeuille() {
		return nomPortefeuille;
	}

	public void setNomPortefeuille(String nomPortefeuille) {
		this.nomPortefeuille = nomPortefeuille.toUpperCase();
	}

	public int getIdPortefeuille() {
		return idPortefeuille;
	}
	
	public void setIdPortefeuille(int idPortefeuille) {
		this.idPortefeuille = idPortefeuille;
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
	private Devise deviseTemp(String nomDevise) {
		// Création d'un objet devise pour recherche
		return new Devise(nomDevise);
	}

	public Double montantDevise(String nomDevise) {
		if (existeDevise(nomDevise)) {
			// Retourne le montant associé à la devise
			return listeDevise.get(deviseTemp(nomDevise));
		} else {
			throw new CurrencyNotExistsException();
		}
	}

	private boolean existeDevise(String deviseAcherche) {
		return listeDevise.containsKey(deviseTemp(deviseAcherche));
	}

	// Methodes publiques
	// Fonction pour obtenir le nombre de devises dans le porte-feuille
	public int nbreDevise() {
		return this.listeDevise.size();
	}

	
	public HashMap<Devise, Double> getListeDevise(){
		return this.listeDevise;
	}
	

	// Ajouter une devise dans le porte-feuille avec un montant prédéfini
	public void ajouterDevise(String nomDevise, Double montant) {
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
	public void ajouterDevise(String nomDevise) {
		/* Ajoute des devises directement à partir d'un nom de devise
		* issue de l'énumération
		 */
		ajouterDevise(nomDevise, 0.0);
	}

	// Supprimer une devise dans le porte-feuille
	public void supprimerDevise(String nomDevise) {
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
	public void sortirDeviseDuPortefeuille(String nomDevise, double montant) {
		// Reduire le montant sur la devise selectionnée
		Devise deviseTemp = deviseTemp(nomDevise);
		try {
			Double nouveauMontant = montantDevise(nomDevise) - montant;
			if (existeDevise(nomDevise) != false && nouveauMontant >= 0) {
				listeDevise.replace(deviseTemp, nouveauMontant);
			} else {
				throw new NoMoneyException();
			}
		} catch (NullPointerException | CurrencyNotExistsException e) {
			throw new NoMoneyException();
		}
	}
	
	// Placer des devises dans le porte-feuille
	public void mettreDeviseDansPortefeuille(String nomDevise, double montant) {
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
		System.out.println(String.format("%s", nomPortefeuille));

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
	
	@Override
	public String toString(){
		return this.nomPortefeuille;
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
