package td3.exo4.bdd.xml;

import java.sql.Connection;
import td3.exo4.bdd.DAO;
import td3.exo4.bdd.Devise;

/**
 *
 * @author Damien GAIGA
 */
public class XmlDAODevise extends DAO<Devise> {

	private static XmlDAODevise instance;

	// Constructeur privé
	private XmlDAODevise(Connection connexion) {
		super(connexion);
	}

	// Singleton
	public static XmlDAODevise getInstance(Connection connexion) {
		if (instance == null) {
			instance = new XmlDAODevise(connexion);
		}
		return instance;
	}

	/**
	 * Permet de récupérer un portefeuille dans la base de données via son ID
	 *
	 * @param id
	 * @return Devise
	 */
	@Override
	public Devise getById(int id) {

		return null;
	}

	/**
	 * Permet de récupérer un objet dans la base de données via son nom
	 *
	 * @param nom
	 * @return Devise
	 */
	public Devise getByNom(String nom) {

		return null;
	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * INSERT INTO)
	 *
	 * @param Devise obj
	 */
	@Override
	public void create(Devise obj) {

	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * UPDATE)
	 *
	 * @param Devise obj
	 */
	@Override
	public void update(Devise obj) {

	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * DELETE)
	 *
	 * @param Devise obj
	 */
	@Override
	public void delete(Devise obj) {
		
	}

}
