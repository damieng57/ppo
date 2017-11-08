package td2;

public enum NomDevises {
	EURO("€"), DOLLAR("$"), LIVRE("£");
	
	private String symbol;
	
	NomDevises(String symbol){
		this.symbol = symbol;
	}
	
	@Override
	public String toString(){
		return symbol;
	}
}
