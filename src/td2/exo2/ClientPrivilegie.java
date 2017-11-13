package td2.exo2;

import td2.exo2.Status.TypeClientPrivilege;
import td2.exo2.Status.ClientExceptionnel;
import td2.exo2.Status.BonClient;
import td2.exo2.Status.VeryImportantClient;
import td2.exo2.Exceptions.NotImportantConsumerException;

public class ClientPrivilegie extends Client {

	private TypeClientPrivilege typeClientPrivilege;

	public ClientPrivilegie(String nomClient, String prenomClient, double caClient) {
		super(nomClient, prenomClient, caClient);

		if (caClient < 1000) {
			throw new NotImportantConsumerException();
		} else if (caClient < 3000) {
			this.typeClientPrivilege = new BonClient();
		} else if (caClient < 10000) {
			this.typeClientPrivilege = new ClientExceptionnel();
		} else {
			this.typeClientPrivilege = new VeryImportantClient();
		}
	}

	public ClientPrivilegie(Client clientApromouvoir) {
		this(clientApromouvoir.getNomClient(), clientApromouvoir.getPrenomClient(), clientApromouvoir.getCaClient());
	}
	
	
	@Override
	public String toString(){
		return super.toString() + String.format(" (Statut spécial : %s)", this.typeClientPrivilege.getType());	
	}
}
