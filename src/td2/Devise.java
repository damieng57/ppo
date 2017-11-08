package td2;

public class Devise {

	// Attributs
	int montant = 0;
	NomDevises devise;
	
	//Constructeur
	public Devise(){
		this.devise = NomDevises.EURO;
		this.montant = 0;
	}
		
	public Devise(NomDevises devise, int montant){
		this.devise = devise;
		this.montant = montant;
	}
	
	// Fonctions
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public String getDevise() {
		return this.devise.toString();
	}
	
	public void setDevise(NomDevises listeDevises) {
		this.devise = listeDevises;
	}
	
	// Fonctions speciales
	@Override
	public String toString(){
		return this.montant+devise.toString();
	}
	
	@Override
	public boolean equals(Object o){
		Devise d = (Devise) o;
		return d.devise == this.devise;
	}
	

	
	
	
}
