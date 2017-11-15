package td2.exo2.Exceptions;

/**
 *
 * @author Damien GAIGA
 */
public class ClientNotExistsException extends RuntimeException {

	/**
	 *
	 * Exception levée si le client n'est pas existant dans la HashMap clientele
	 * 
	 */
	public ClientNotExistsException() {
	}

	@Override
	public String getMessage() {
		return "Le client n'est pas dans la liste ou la liste de clients est vide";
	}

}
