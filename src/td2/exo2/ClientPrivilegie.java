package td2.exo2;

import td2.exo2.Status.EnumTypeClientPrivilegie;
import td2.exo2.Exceptions.NotImportantConsumerException;

/**
 *
 * @author Damien GAIGA
 */

/**
 * La classe ClientPrivilege hérite de Client
 * <p>
 * Un objet ClientPrivilege possède un nom, prénom et CA et d'un privilège (enum)
 *  </p>
 *
 */
public class ClientPrivilegie extends Client {

	private EnumTypeClientPrivilegie enumTypeClientPriviliege;

	private final int SEUIL_BON_CLIENT = enumTypeClientPriviliege.BON_CLIENT.getSeuilClientPrivilege();
	private final int SEUIL_CLIENT_EXCEPTIONNEL = enumTypeClientPriviliege.CLIENT_EXCEPTIONNEL.getSeuilClientPrivilege();
	private final int SEUIL_CLIENT_VERY_IMPORTANT = enumTypeClientPriviliege.VERY_IMPORTANT_CLIENT.getSeuilClientPrivilege();

	/**
	 *
	 * Promouvoir un simple client en client privilégié
	 * 
	 * @param clientApromouvoir Tente de passer de remplacer un objet Client par un objet ClientPrivilegie
	 */
	public ClientPrivilegie(Client clientApromouvoir) {
		this(clientApromouvoir.getNomClient(), clientApromouvoir.getPrenomClient(), clientApromouvoir.getCaClient().getMontant());
	}

	/**
	 *
	 * Constructeur du client prvilégié
	 * <p> On définira le type de client dans le constructeur en se basant sur
 une énumération (EnumTypeClientPrivilegie.java)
 </p>
	 * 
	 * @param nomClient Le nom du client
	 * @param prenomClient Le prenom du client
	 * @param caClient Le CA du client
	 */
	public ClientPrivilegie(String nomClient, String prenomClient, double caClient) {
		super(nomClient, prenomClient, caClient);

//		Version utilisant les énumération
		if (caClient < SEUIL_BON_CLIENT) {
			throw new NotImportantConsumerException();
		} else if (caClient < SEUIL_CLIENT_EXCEPTIONNEL) {
			this.enumTypeClientPriviliege = EnumTypeClientPrivilegie.BON_CLIENT;
		} else if (caClient < SEUIL_CLIENT_VERY_IMPORTANT) {
			this.enumTypeClientPriviliege = EnumTypeClientPrivilegie.CLIENT_EXCEPTIONNEL;
		} else {
			this.enumTypeClientPriviliege = EnumTypeClientPrivilegie.VERY_IMPORTANT_CLIENT;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format(" (Reduction : %s)", this.enumTypeClientPriviliege.toString());
	}
}
