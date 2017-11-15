package td2.exo2.Exceptions;

/**
 *
 * @author Damien GAIGA
 */
public class ClientAlreadyExistsException extends RuntimeException {

	/**
	 *
	 * Exception levée si le client est déjà existant dans la HashMap clientele
	 * 
	 */
	public ClientAlreadyExistsException() {
	}

	@Override
	public String getMessage() {
		return "Le client existe déjà, pas d'ajout possible";
	}

}
