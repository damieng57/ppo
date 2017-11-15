package td2.exo2;

import td2.exo2.Status.EnumTypeClientPrivilegie;

/**
 *
 * @author Damien GAIGA
 */

/**
 * Objet de type Fabrique (Design pattern fabric).
 *
 */
public class FabriqueClient {

	private static final int SEUIL_BON_CLIENT = EnumTypeClientPrivilegie.BON_CLIENT.getSeuilClientPrivilege();

	/**
	 * Objet de type Fabrique (Design pattern fabric).
	 *
	 * <p>
	 * Permet l'instanciation direct du bon objet lors de la création d'un
	 * client. Ceci est important dans le cas où le client est renseigné avec un
	 * CA permettant l'accès à des réductions.
	 *
	 * On utilise le seuil définissant un bon client dans
	 * EnumTypeClientPrivilegie
	 * </p>
	 *
	 * @param nomClient Le nom du client
	 * @param prenomClient Le prenom du client
	 * @param caClient Le CA du client
	 * @return Retourne un objet de la classe Client ou ClientPrivilege
	 */
	public static Client create(String nomClient, String prenomClient, double caClient) {
		if (caClient < SEUIL_BON_CLIENT) {
			return new Client(nomClient, prenomClient, caClient);
		} else {
			return new ClientPrivilegie(nomClient, prenomClient, caClient);
		}
	}
}
