package td2;

public enum NomDevises {
	EURO("€"), DOLLAR("$"), LIVRE("£"), YUAN("RMB");
	
	private String symbol;
	
	NomDevises(String symbol){
		this.symbol = symbol;
	}
	
	@Override
	public String toString(){
		return symbol;
	}
}
