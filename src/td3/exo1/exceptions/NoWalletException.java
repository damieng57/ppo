package td3.exo1.exceptions;
// On hérite de RuntimeException pour éviter l'utilisation de throws, si erreur, elle
// se produira à l'execution, pas à la compilation.

import td2.exo1.Exceptions.*;
// On hérite de RuntimeException pour éviter l'utilisation de throws, si erreur, elle
// se produira à l'execution, pas à la compilation.
public class NoWalletException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NoWalletException(){
		
	}
	
	@Override
	public String getMessage(){
		return "Pas de portefeuille crée";
	}
	
}
