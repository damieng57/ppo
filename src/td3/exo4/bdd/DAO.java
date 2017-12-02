package td3.exo4.bdd;

import java.sql.Connection;

/**
 *
 * @author Damien GAIGA
 * @param <T>
 */
public abstract class DAO<T> {

	protected Connection connexion = null;

	public DAO(Connection connexion) {
		this.connexion = connexion;
	}

	/**
	 * Permet de récupérer un objet via son ID
	 *
	 * @param id
	 * @return T
	 */
	public abstract T getById(int id);

	/**
	 * Permet de créer une entrée dans la base de données par rapport à un objet
	 * (Correspond à INSERT INTO)
	 *
	 * @param obj
	 */
	public abstract void create(T obj);

	/**
	 * Permet de mettre à jour les données d'une entrée dans la base
	 *
	 * @param obj
	 */
	public abstract void update(T obj);

	/**
	 * Permet la suppression d'une entrée de la base
	 *
	 * @param obj
	 */
	public abstract void delete(T obj);

	/**
	 * Permet de récupérer les éléments d'une table pour les afficher
	 *
	 * @param obj
	 */
	public abstract void display();

}
