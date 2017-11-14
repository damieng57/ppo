/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td2.exo2.Exceptions;

/**
 *
 * @author gaiga4u
 */
public class ClientNotExistsException extends RuntimeException {

	public ClientNotExistsException() {
	}

	@Override
	public String getMessage() {
		return "Le client n'est pas dans la liste";
	}

}
