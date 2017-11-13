package td2.exo1.Exceptions;
// On hérite de RuntimeException pour éviter l'utilisation de throws, si erreur, elle
// se produira à l'execution, pas à la compilation.
public class NoMoneyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NoMoneyException(){
		
	}
	
	public String getMessage(){
		return "le montant de la devise est inférieur à 0";
	}
	
}
