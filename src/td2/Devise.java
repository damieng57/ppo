package td2;

import td2.Exceptions.NoMoneyException;

public class Devise {

	// Attributs
	private int montant;
	private NomDevises nomDevise;
	
	//Constructeur
	public Devise() throws NoMoneyException{
		// On appelle le constructeur avec paramètres
		this(NomDevises.EURO, 0);
	}
		
	public Devise(NomDevises devise, int montant) {
		// On utilise les setters pour contrôler la cohérence
		this.setNomDevise(devise);
		this.setMontant(montant);
	}
	
	// Fonctions
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		if (montant > 0){
			this.montant = montant;
		} else {
			throw new NoMoneyException();
		}
	}
	
	public NomDevises getDevise() {
		return this.nomDevise;
	}
	
	public void setNomDevise(NomDevises listeDevises) {
		this.nomDevise = listeDevises;
	}
	
	// Fonctions speciales
	@Override
	public String toString() {
		return String.format("Montant: %s - Devise: %s%n", this.getMontant(), this.getDevise());
	}
	
	// Permet l'utilisation de la méthode contains
	@Override
	public boolean equals(Object o){
		Devise d = (Devise) o;
		return d.getDevise() == this.nomDevise;
	}

	@Override
	public int hashCode() {
		int hash = this.nomDevise.hashCode();
		return hash;
	}
}
