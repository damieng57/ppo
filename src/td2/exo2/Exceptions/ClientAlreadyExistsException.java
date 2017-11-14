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
public class ClientAlreadyExistsException extends RuntimeException {

	public ClientAlreadyExistsException() {
	}

	@Override
	public String getMessage() {
		return "Le client existe déjà, pas d'ajout possible";
	}

}
