package td2.exo1.Exceptions;

public class CurrencyNotExistsException extends RuntimeException {

	public CurrencyNotExistsException() {
	}

	public String getMessage() {
		return "La devise n'est pas présente dans le porte-feuille";
	}

}
