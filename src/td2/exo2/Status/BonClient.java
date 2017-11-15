package td2.exo2.Status;

/**
 *
 * @author Damien GAIGA
 */
public class BonClient implements TypeClientPrivilege{
	
	private String typeClient = "Bon client";
	private double reductionClient = 0.05;

	/**
	 *
	 * @return String - Chaine de caractère avec le type de client ("Bon client")
	 */
	@Override
	public String getType() {
		return typeClient;
	}

	/**
	 *
	 * @return double - Réduction à laquelle peut prétendre le client
	 */
	@Override
	public double getReduction() {
		return reductionClient;
	}
	
}
