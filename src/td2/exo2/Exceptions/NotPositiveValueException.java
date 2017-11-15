package td2.exo2.Exceptions;

/**
 *
 * @author Damien GAIGA
 */
public class NotPositiveValueException extends RuntimeException {

	/**
	 *
	 * Exception levée si le client est déjà existant dans la HashMap clientele
	 * 
	 */
	public NotPositiveValueException() {
	}

	@Override
	public String getMessage() {
		return "La valeur doit être positive";
	}

}
