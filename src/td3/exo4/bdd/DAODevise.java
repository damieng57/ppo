package td3.exo4.bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import td3.exo4.bdd.Devise;

/**
 *
 * @author Damien GAIGA
 */
public class DAODevise extends DAO<Devise> {

	private static DAODevise instance;

	// Requete recherche une devise en base de données
	private String requeteDevise = "SELECT * FROM Devise WHERE nom_devise=?";
	// Insert la devise en base de données (si n'existe pas)
	private String requeteAjoutDevise = "INSERT INTO Devise (nom_devise) VALUES (?)";

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
		String requetePreparee = "SELECT * FROM Devise WHERE nom_devise=?";

		Devise devise = null;
		try {
			connexion = Connexion.getInstance();
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, nom);
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				devise = new Devise(res.getInt(1), res.getString(2));
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

		// TODO : requète à basculer dans DAODevise
		// Requete recherche une devise en base de données
		String requeteDevise = "SELECT * FROM Devise WHERE nom_devise=?";
		//String requeteDevise = "SELECT * FROM Devise WHERE id_devise=? AND nom_devise=?";
		// Insert la devise en base de données (si n'existe pas)
		String requeteAjoutDevise = "INSERT INTO Devise (nom_devise) VALUES (?)";

		try {
			// On effectue la connexion
			connexion = Connexion.getInstance();

			int idGenerateDevise = 0;

			// Recherche de l'objet en base de données
			PreparedStatement requete = connexion.prepareStatement(requeteDevise, Statement.RETURN_GENERATED_KEYS);
			//requete.setInt(1, obj.getIdDevise());
			requete.setString(1, obj.getNomDevise());
			ResultSet resultat = requete.executeQuery();
			
			// Si résultat :
			//Recupère l'id
			if (resultat.next()) {
				idGenerateDevise = resultat.getInt(1);
				//System.out.println("C - La devise existe, son id est le : " + idGenerateDevise);
			} else {
				// Si pas de resultat :
				// on ajoute la devise et on stocke l'id de la devise crée
				requete = connexion.prepareStatement(requeteAjoutDevise, Statement.RETURN_GENERATED_KEYS);
				requete.setString(1, obj.getNomDevise());
				requete.executeUpdate();

				resultat = requete.getGeneratedKeys();

				if (resultat.next()) {
					idGenerateDevise = resultat.getInt(1);

					//System.out.println("D - La devise n'existait pas, on la crée, son id est le : " + idGenerateDevise);
				}

			}

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
			requete.execute();
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
			requete.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
