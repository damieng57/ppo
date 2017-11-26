package td3.exo4.bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import td3.exo1.Portefeuille;

/**
 *
 * @author Damien GAIGA
 */
public class DAOPortefeuille extends DAO<Portefeuille> {

	private static DAOPortefeuille instance;

	private DAOPortefeuille() {
	}

	public static DAOPortefeuille getInstance() {
		if (instance == null) {
			instance = new DAOPortefeuille();
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
		String requetePreparee = "SELECT id_portefeuille, nom_portefeuille FROM Portefeuille WHERE id_portefeuille=?";

		Portefeuille portefeuille = null;
		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				portefeuille = new Portefeuille(res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return portefeuille;
	}

	/**
	 * Permet de récupérer un objet dans la base de données via son nom
	 *
	 * @param nom
	 * @return Portefeuille
	 */
	public Portefeuille getByNom(String nom) {
		String requetePreparee = "SELECT id_portefeuille, nom_portefeuille FROM Portefeuille WHERE nom_portefeuille=?";

		Portefeuille portefeuille = null;
		try {
			// connexion est un attribut de la classe abstraite DAO
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, nom);
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				portefeuille = new Portefeuille(res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return portefeuille;
	}

	// EXTRAIT DU COURS
	// Les méthodes qui vont réaliser des Insert,
	// Update ou Delete prendront un objet métier en
	// paramètre
	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * INSERT INTO)
	 *
	 * @param Portefeuille obj
	 */
	@Override
	public void create(Portefeuille obj) {
		String requetePreparee = "INSERT INTO Portefeuille (nom_portefeuille) VALUES (?)";

		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, obj.getNomPortefeuille());
			requete.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * UPDATE)
	 *
	 * @param Portefeuille obj
	 */
	@Override
	public void update(Portefeuille obj) {
		String requetePreparee = "UPDATE Portefeuille SET nom_portefeuille=? WHERE id_portefeuille=?";

		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, obj.getNomPortefeuille());
			requete.setString(2, this.getByNom(obj.getNomPortefeuille()).getNomPortefeuille());
			requete.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de mettre à jour un objet dans la base de données (Correspond à
	 * DELETE)
	 *
	 * @param Portefeuille obj
	 */
	@Override
	public void delete(Portefeuille obj) {
		String requetePreparee = "DELETE FROM Portefeuille WHERE id_portefeuille=?";

		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(2, this.getByNom(obj.getNomPortefeuille()).getNomPortefeuille());
			requete.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
