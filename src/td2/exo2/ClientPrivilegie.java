package td2.exo2;

public class ClientPrivilegie extends Client {
	
	private TypeClientPrivilege typeClientPrivilege;

	public ClientPrivilegie(String nomClient, String prenomClient, double caClient, TypeClientPrivilege typeClientPrivilege) {
		super(nomClient, prenomClient, caClient);
		this.typeClientPrivilege = typeClientPrivilege;
	}

}
