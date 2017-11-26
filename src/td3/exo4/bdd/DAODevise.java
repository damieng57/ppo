package td3.exo4.bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import td3.exo1.Devise;

/**
 *
 * @author Damien GAIGA
 */
public class DAODevise extends DAO<Devise> {

	private static DAODevise instance;

	private DAODevise() {
	}

	public static DAODevise getInstance() {
		if (instance == null) {
			instance = new DAODevise();
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
		String requetePreparee = "SELECT id_devise, nom_devise FROM Devise WHERE id_devise=?";

		Devise devise = null;
		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				devise = new Devise(res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return devise;
	}

	/**
	 * Permet de récupérer un objet dans la base de données via son nom
	 *
	 * @param nom
	 * @return Devise
	 */
	public Devise getByNom(String nom) {
		String requetePreparee = "SELECT id_devise, nom_devise FROM Devise WHERE nom_devise=?";

		Devise devise = null;
		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, nom);
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				devise = new Devise(res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return devise;
	}

	// EXTRAIT DU COURS
	// Les méthodes qui vont réaliser des Insert,
	// Update ou Delete prendront un objet métier en
	// paramètre
	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * INSERT INTO)
	 *
	 * @param Devise obj
	 */
	@Override
	public void create(Devise obj) {
		String requetePreparee = "INSERT INTO Devise (nom_devise) VALUES (?)";

		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, obj.getNomDevise());
			requete.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * UPDATE)
	 *
	 * @param Devise obj
	 */
	@Override
	public void update(Devise obj) {
		String requetePreparee = "UPDATE Devise SET nom_devise=? WHERE id_devise=?";

		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, obj.getNomDevise());
			requete.setString(2, this.getByNom(obj.getNomDevise()).getNomDevise());
			requete.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * DELETE)
	 *
	 * @param Devise obj
	 */
	@Override
	public void delete(Devise obj) {
		String requetePreparee = "DELETE FROM Portefeuille WHERE id_portefeuille=?";

		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(2, this.getByNom(obj.getNomDevise()).getNomDevise());
			requete.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
