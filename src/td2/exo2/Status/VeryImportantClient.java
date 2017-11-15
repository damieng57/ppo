package td2.exo2.Status;

/**
 *
 * @author Damien
 */
public class VeryImportantClient implements TypeClientPrivilege{
	
	private String typeClient = "Very important client";
	private double reductionClient = 0.3;

	/**
	 *
	 * @return String - Chaine de caractère avec le type de client ("Very important client")
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
