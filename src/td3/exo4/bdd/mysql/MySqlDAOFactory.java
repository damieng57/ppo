package td3.exo4.bdd.mysql;

import java.sql.Connection;
import td3.exo4.bdd.Connexion;
import td3.exo4.bdd.DAOFactory;


/**
 *
 * @author Damien GAIGA
 */
public class MySqlDAOFactory extends DAOFactory {

	protected static final Connection connexion = Connexion.getInstance();

	@Override
	public MySqlDAOPortefeuille getDaoPortefeuille() {
		return MySqlDAOPortefeuille.getInstance(connexion);
	}

	@Override
	public MySqlDAODevise getDaoDevise() {
		return MySqlDAODevise.getInstance(connexion);
	}

}
