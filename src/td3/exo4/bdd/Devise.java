package td3.exo4.bdd;

import td3.exo1.*;
import java.io.Serializable;
import td2.exo1.Exceptions.NoMoneyException;

public class Devise implements Serializable {

	private static final long serialVersionUID = 0320171121L;

	// Attributs
	private int idDevise;
	private String nomDevise;


	//Constructeur
	public Devise(String nomDevise) throws NoMoneyException {
		// Une devise dont la correspondance n'est pas encore faite avec
		// les éléments en base de données (ex:pas encore ajouter) a l'id = -1
		this(-1, nomDevise);
	}

	public Devise(int idDevise, String nomDevise) throws NoMoneyException {
		this.setIdDevise(idDevise);
		this.setNomDevise(nomDevise);
	}

	public int getIdDevise() {
		return idDevise;
	}

	public void setIdDevise(int idDevise) {
		this.idDevise = idDevise;
	}

	public String getNomDevise() {
		return this.nomDevise;
	}

	public void setNomDevise(String nomDevise) {
		this.nomDevise = nomDevise;
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
		return this.nomDevise.hashCode();
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((nomDevise == null) ? 0 : nomDevise.hashCode());
//        return result;
	}
}
