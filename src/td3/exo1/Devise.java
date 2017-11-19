package td3.exo1;

import td2.exo1.Exceptions.NoMoneyException;

public class Devise {

	// Attributs
	private NomDevises nomDevise;

	//Constructeur
	public Devise(NomDevises nomDevise) throws NoMoneyException {
		this.nomDevise = nomDevise;
	}

	public String getNomDevise() {
		return this.nomDevise.getSymbol();
	}

	// Fonctions speciales
	@Override
	public String toString() {
		return String.format("%s", this.getNomDevise());
	}

	// Permet l'utilisation de la méthode contains (et dérivée type ContainsKey)
	@Override
	public boolean equals(Object o) {
		Devise d = (Devise) o;
		return d.getNomDevise().equals(this.getNomDevise());
	}
	
	// Permet l'utilisation de la méthode ContainsKey (obligatoire sinon, ça ne fonctionne pas)
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomDevise == null) ? 0 : nomDevise.hashCode());
        return result;
    }
}
