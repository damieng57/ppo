package td3.exo4.bdd.xml;

import java.sql.Connection;
import td3.exo4.bdd.DAO;
import td3.exo4.bdd.Portefeuille;

/**
 *
 * @author Damien GAIGA
 */
public class XmlDAOPortefeuille extends DAO<Portefeuille> {

	private static XmlDAOPortefeuille instance;

	// Constructeur privé
	private XmlDAOPortefeuille(Connection connexion) {
		super(connexion);
	}

	// Singleton DAOPortefeuille
	public static XmlDAOPortefeuille getInstance(Connection connexion) {
		if (instance == null) {
			instance = new XmlDAOPortefeuille(connexion);
		}
		return instance;
	}

	/**
	 * Permet de récupérer un portefeuille dans la base de données via son ID
	 *
	 * @param id
	 * @return Portefeuille
	 */
	@Override
	public Portefeuille getById(int id) {
		
		return null;
	}

	/**
	 * Permet de récupérer un objet dans la base de données via son nom
	 *
	 * @param nom
	 * @return Portefeuille
	 */
	public Portefeuille getByNom(String nom) {
		
		return null;
	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * INSERT INTO)
	 *
	 * @param Portefeuille obj
	 */
	@Override
	public void create(Portefeuille obj) {

	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * UPDATE)
	 *
	 * @param Portefeuille obj
	 */
	@Override
	public void update(Portefeuille obj) {
		
	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * DELETE)
	 *
	 * @param Portefeuille obj
	 */
	@Override
	public void delete(Portefeuille obj) {

	}

}
