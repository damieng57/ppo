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
public class UndefinedClientException extends RuntimeException {

	public UndefinedClientException() {
	}

	@Override
	public String getMessage() {
		return "Un client ne peut avoir un nom ou un prénom vide";
	}

}
