package td2.exo2.Status;

/**
 *
 * @author Damien
 */
public class VeryImportantClient implements TypeClientPrivilege{
	
	private String typeClient = "Very important client";
	private double reductionClient = 0.3;

	@Override
	public String getType() {
		return typeClient;
	}

	@Override
	public double getReduction() {
		return reductionClient;
	}
	
}
