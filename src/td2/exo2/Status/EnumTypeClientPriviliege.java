package td2.exo2.Status;

public enum EnumTypeClientPriviliege {
	BON_CLIENT(0.05), CLIENT_EXCEPTIONNEL(0.15), VERY_IMPORTANT_CLIENT(0.3);

	private double reduction;

	EnumTypeClientPriviliege(double reduction) {
		this.reduction = reduction;
	}
	
	public double getReduction(){
		return reduction;
	}

	@Override
	public String toString() {
		return String.valueOf(this.getReduction());
	}
}
