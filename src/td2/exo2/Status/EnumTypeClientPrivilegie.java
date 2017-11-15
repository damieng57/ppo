package td2.exo2.Status;

/**
 *
 * @author Damien GAIGA
 */
public enum EnumTypeClientPrivilegie {

	/**
	 * Reduction pour les bons clients 5%
	 */
	BON_CLIENT(0.05, 1000),
	/**
	 * Reduction pour les clients exceptionnels 15%
	 */
	CLIENT_EXCEPTIONNEL(0.15, 3000),
	/**
	 * Reduction pour les very importants clients 30%
	 */
	VERY_IMPORTANT_CLIENT(0.3, 10000);

	private final double reduction;
	private int seuilClientPrivilege;

	EnumTypeClientPrivilegie(double reduction, int seuilClientPrivilege) {
		this.reduction = reduction;
		this.seuilClientPrivilege = seuilClientPrivilege;
	}

	/**
	 *
	 * @return double - La réduction sous forme décimale du client
	 */
	public double getReduction() {
		return reduction;
	}

	/**
	 *
	 * @return int - Le seuil pour atteindre un certain niveau de privilège
	 */
	public int getSeuilClientPrivilege() {
		return seuilClientPrivilege;
	}

	@Override
	public String toString() {
		return String.valueOf(this.getReduction());
	}
}
