package td2.exo2.Status;

/**
 *
 * @author Damien
 */
public class ClientExceptionnel implements TypeClientPrivilege{
	
	private String typeClient = "Client exceptionnel";
	private double reductionClient = 0.5;

	@Override
	public String getType() {
		return typeClient;
	}

	@Override
	public double getReduction() {
		return reductionClient;
	}
	
}
