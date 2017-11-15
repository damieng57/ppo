/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td2.exo2.Exceptions;

/**
 *
 * @author Damien
 */
public class NotImportantConsumerException extends RuntimeException {

	/**
	 *
	 * Exception levée on tente de créer un client privilégié dont le CA n'est pas
	 * suffisant
	 * 
	 */
	public NotImportantConsumerException() {
	}

	@Override
	public String getMessage() {
		return "Le chiffre d'affaire du client est insuffisant pour devenir client privilégié";
	}

}
