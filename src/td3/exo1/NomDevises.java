package td3.exo1;

public enum NomDevises {
	EURO("€"), DOLLAR("$"), LIVRE("£"), YUAN("RMB");

	private String symbol;

	NomDevises(String symbol) {
		this.symbol = symbol;
	}

	// Idem toString, je préfère la définir des
	// fois que je décide de modifier l'affichage dans
	// la méthode toString
	public String getSymbol() {
		return symbol;
	}


	@Override
	public String toString() {
		return symbol;
	}
}
