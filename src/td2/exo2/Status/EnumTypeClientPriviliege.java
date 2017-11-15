package td2.exo2.Status;

/**
 *
 * @author Damien GAIGA
 */
public enum EnumTypeClientPriviliege {

	/**
	 * Reduction pour les bons clients 5%
	 */
	BON_CLIENT(0.05),

	/**
	 * Reduction pour les clients exceptionnels 15%
	 */
	CLIENT_EXCEPTIONNEL(0.15),

	/**
	 * Reduction pour les very importants clients 30%
	 */
	VERY_IMPORTANT_CLIENT(0.3);

	private double reduction;

	EnumTypeClientPriviliege(double reduction) {
		this.reduction = reduction;
	}
	
	/**
	 *
	 * @return double - La réduction sous forme décimale du client
	 */
	public double getReduction(){
		return reduction;
	}

	@Override
	public String toString() {
		return String.valueOf(this.getReduction());
	}
}
