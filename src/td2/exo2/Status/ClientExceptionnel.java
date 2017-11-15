package td2.exo2.Status;

/**
 *
 * @author Damien GAIGA
 */
public class ClientExceptionnel implements TypeClientPrivilege{
	
	private String typeClient = "Client exceptionnel";
	private double reductionClient = 0.15;

	/**
	 *
	 * @return String - Chaine de caractère avec le type de client ("Client exceptionnel")
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
