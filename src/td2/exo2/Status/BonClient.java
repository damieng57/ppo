package td2.exo2.Status;

/**
 *
 * @author Damien
 */
public class BonClient implements TypeClientPrivilege{
	
	private String typeClient = "Bon client";
	private double reductionClient = 0.05;

	@Override
	public String getType() {
		return typeClient;
	}

	@Override
	public double getReduction() {
		return reductionClient;
	}
	
}
