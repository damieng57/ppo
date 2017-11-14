package td2.exo2;

// Import si utilisation d'un design patern strategy - pour référence ultérieure
//import td2.exo2.Status.TypeClientPrivilege;
//import td2.exo2.Status.ClientExceptionnel;
//import td2.exo2.Status.BonClient;
//import td2.exo2.Status.VeryImportantClient;

import td2.exo2.Status.EnumTypeClientPriviliege;
import td2.exo2.Exceptions.NotImportantConsumerException;

public class ClientPrivilegie extends Client {

	private EnumTypeClientPriviliege enumTypeClientPriviliege;

	public ClientPrivilegie(String nomClient, String prenomClient, double caClient) {
		super(nomClient, prenomClient, caClient);
		
//		Version utilisant les énumération
		if (caClient < 1000) {
			throw new NotImportantConsumerException();
		} else if (caClient < 3000) {
			this.enumTypeClientPriviliege = EnumTypeClientPriviliege.BON_CLIENT;
		} else if (caClient < 10000) {
			this.enumTypeClientPriviliege = EnumTypeClientPriviliege.CLIENT_EXCEPTIONNEL;
		} else {
			this.enumTypeClientPriviliege = EnumTypeClientPriviliege.VERY_IMPORTANT_CLIENT;
		}
		
		
//		Version utilisant le design pattern strategy - Pour référence ultérieure
//		if (caClient < 1000) {
//			throw new NotImportantConsumerException();
//		} else if (caClient < 3000) {
//			this.typeClientPrivilege = new BonClient();
//		} else if (caClient < 10000) {
//			this.typeClientPrivilege = new ClientExceptionnel();
//		} else {
//			this.typeClientPrivilege = new VeryImportantClient();
//		}
	}

	public ClientPrivilegie(Client clientApromouvoir) {
		this(clientApromouvoir.getNomClient(), clientApromouvoir.getPrenomClient(), clientApromouvoir.getCaClient().getMontant());
	}
	
	
	@Override
	public String toString(){
		return super.toString() + String.format(" (Reduction : %s)", this.enumTypeClientPriviliege.toString());	
	}
}
