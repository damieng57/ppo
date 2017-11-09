package td2;

public class Devise {

	// Attributs
	int montant = 0;
	NomDevises nomDevise;
	
	//Constructeur
	public Devise(){
		this.nomDevise = NomDevises.EURO;
		this.montant = 0;
	}
		
	public Devise(NomDevises devise, int montant){
		this.nomDevise = devise;
		this.montant = montant;
	}
	
	// Fonctions
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public NomDevises getDevise() {
		return this.nomDevise;
	}
	
	public void setNomDevise(NomDevises listeDevises) {
		this.nomDevise = listeDevises;
	}
	
	// Fonctions speciales
	@Override
	public String toString(){
		return this.montant+nomDevise.toString();
	}
	
	@Override
	public boolean equals(Object o){
		Devise d = (Devise) o;
		return d.getDevise() == this.nomDevise;
	}
	

	
	
	
}
