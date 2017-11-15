package td2.exo2;

import td2.exo1.Devise;
import td2.exo1.NomDevises;
import td2.exo2.Exceptions.UndefinedClientException;

/**
 *
 * @author gaiga4u
 */
public class Client {

	private String nomClient;
	private String prenomClient;
	//private double caClient;
	private Devise caClient;

	// NOTA : J'ai volontairement supprimer les méthodes saisie et affiche
	// car je n'en ai pas, à mon sens, l'utilité
	public Client(String nomClient, String prenomClient, double caClient) {
		if (nomClient.equals("") || prenomClient.equals("")) {
			throw new UndefinedClientException();
		} else {
			this.setNomClient(nomClient);
			this.setPrenomClient(prenomClient);
			this.caClient = new Devise(NomDevises.EURO, caClient);
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
