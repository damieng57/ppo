package td2.exo2;

// Import si utilisation d'un design patern strategy - pour référence ultérieure
//import td2.exo2.Status.TypeClientPrivilege;
//import td2.exo2.Status.ClientExceptionnel;
//import td2.exo2.Status.BonClient;
//import td2.exo2.Status.VeryImportantClient;
import td2.exo2.Status.EnumTypeClientPriviliege;
import td2.exo2.Exceptions.NotImportantConsumerException;

/**
 *
 * @author Damien GAIGA
 */
public class ClientPrivilegie extends Client {

	private EnumTypeClientPriviliege enumTypeClientPriviliege;

	private final int SEUIL_BON_CLIENT = 1000;
	private final int SEUIL_CLIENT_EXCEPTIONNEL = 3000;
	private final int SEUIL_CLIENT_VERY_IMPORTANT = 10000;

	/**
	 *
	 * Promouvoir un simple client en client privilégié
	 * 
	 * @param clientApromouvoir
	 */
	public ClientPrivilegie(Client clientApromouvoir) {
		this(clientApromouvoir.getNomClient(), clientApromouvoir.getPrenomClient(), clientApromouvoir.getCaClient().getMontant());
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" (Reduction : %s)", this.enumTypeClientPriviliege.toString());
	}

	/**
	 *
	 * Constructeur du client prvilégié
	 * <p> On définira le type de client dans le constructeur en se basant sur
	 * une énumération (EnumTypeClientPriviliege.java)
	 * </p>
	 * 
	 * @param nomClient
	 * @param prenomClient
	 * @param caClient
	 */
	public ClientPrivilegie(String nomClient, String prenomClient, double caClient) {
		super(nomClient, prenomClient, caClient);

//		Version utilisant les énumération
		if (caClient < SEUIL_BON_CLIENT) {
			throw new NotImportantConsumerException();
		} else if (caClient < SEUIL_CLIENT_EXCEPTIONNEL) {
			this.enumTypeClientPriviliege = EnumTypeClientPriviliege.BON_CLIENT;
		} else if (caClient < SEUIL_CLIENT_VERY_IMPORTANT) {
			this.enumTypeClientPriviliege = EnumTypeClientPriviliege.CLIENT_EXCEPTIONNEL;
		} else {
			this.enumTypeClientPriviliege = EnumTypeClientPriviliege.VERY_IMPORTANT_CLIENT;
		}

//		Version utilisant le design pattern strategy - Pour référence ultérieure
//		if (caClient < SEUIL_BON_CLIENT) {
//			throw new NotImportantConsumerException();
//		} else if (caClient < SEUIL_CLIENT_EXCEPTIONNEL) {
//			this.typeClientPrivilege = new BonClient();
//		} else if (caClient < SEUIL_CLIENT_VERY_IMPORTANT) {
//			this.typeClientPrivilege = new ClientExceptionnel();
//		} else {
//			this.typeClientPrivilege = new VeryImportantClient();
//		}
	}
}
