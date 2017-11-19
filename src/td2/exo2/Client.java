package td2.exo2;

import td2.exo1.Devise;
import td2.exo1.Exceptions.NoMoneyException;
import td2.exo1.NomDevises;
import td2.exo2.Exceptions.UndefinedClientException;

/**
 *
 * @author Damien GAIGA
 */

/**
 * La classe Client permet de créer un objet de type Client
 * <p>
 * Un objet Client possède un nom, prénom et CA
 * </p>
 *
 */
public class Client {

	private String nomClient;
	private String prenomClient;
	Devise caClient;

	// NOTA : J'ai volontairement supprimer les méthodes saisie et affiche
	// car je n'en ai pas, à mon sens, l'utilité

	/**
	 * Constructeur du client. Tous les éléments doivent être renseignés
	 * <p> Le CA est au minimum égal à 0€. Une exception sera levée (NoMonetException)
	 * par la classe devise dans le cas contraire. Je regle le problème en passant
	 * le CA à 0.
	 * </p>
	 * 
	 * @param nomClient Le nom du client
	 * @param prenomClient Le prenom du client
	 * @param caClient Le CA du client
	 */
	public Client(String nomClient, String prenomClient, double caClient) {
		if (nomClient.equals("") || prenomClient.equals("")) {
			throw new UndefinedClientException();
		} else {
			this.setNomClient(nomClient);
			this.setPrenomClient(prenomClient);
			try {
				this.caClient = new Devise(NomDevises.EURO, caClient);
			} catch (NoMoneyException ex) {
				this.caClient = new Devise(NomDevises.EURO, 0);
			}
		}
	}

	@Override
	public String toString() {
		return String.format("%s %s : %.2f%s", nomClient, prenomClient, caClient.getMontant(), caClient.getDevise());
	}

	/**
	 * @return the nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}

	/**
	 * @param nomClient the nomClient to set
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient.toUpperCase();
	}

	/**
	 * @return the prenomClient
	 */
	public String getPrenomClient() {
		return prenomClient;
	}

	/**
	 * @param prenomClient the prenomClient to set
	 */
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient.substring(0, 1).toUpperCase() + prenomClient.substring(1);
	}

	/**
	 * @return the caClient
	 */
	public Devise getCaClient() {
		return caClient;
	}

	/**
	 * @param caClient the caClient to set
	 */
	public void setCaClient(double caClient) {
		if (caClient < 0) {
			this.caClient.setMontant(0);
		} else {
			this.caClient.setMontant(caClient);
		}
	}

}
