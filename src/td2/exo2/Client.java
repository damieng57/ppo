package td2.exo2;

import td2.exo2.Exceptions.UndefinedClientException;

/**
 *
 * @author gaiga4u
 */
public class Client {

	private String nomClient;
	private String prenomClient;
	private double caClient;

	public Client(String nomClient, String prenomClient, double caClient) {
		if (nomClient.equals("") || prenomClient.equals("")) {
			throw new UndefinedClientException();
		} else {
			this.setNomClient(nomClient);
			this.setPrenomClient(prenomClient);
			this.setCaClient(caClient);
		}
	}
	
	@Override
	public boolean equals(Object o){
		Client c = (Client) o;
		return c.getCaClient() == this.caClient;
	}

	public void affiche() {
		// TODO
	}

	public void saisie() {
		// TODO
	}

	@Override
	public String toString() {
		return String.format("%s %s : %.2f", nomClient, prenomClient, caClient);
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
		this.nomClient = nomClient;
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
		this.prenomClient = prenomClient;
	}

	/**
	 * @return the caClient
	 */
	public double getCaClient() {
		return caClient;
	}

	/**
	 * @param caClient the caClient to set
	 */
	public void setCaClient(double caClient) {
		if (caClient < 0) {
			this.caClient = 0;
		} else {
			this.caClient = caClient;
		}
	}

}
