package td3.exo4.bdd.mysql;

import td3.exo4.bdd.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import td3.exo4.bdd.Connexion;
import td3.exo4.bdd.Devise;

/**
 *
 * @author Damien GAIGA
 */
public class MySqlDAODevise extends DAO<Devise> {

	private static MySqlDAODevise instance;

	// Constructeur privé
	private MySqlDAODevise(Connection connexion) {
		super(connexion);
	}

	// Singleton
	public static MySqlDAODevise getInstance(Connection connexion) {
		if (instance == null) {
			instance = new MySqlDAODevise(connexion);
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
				return devise;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
			PreparedStatement requete = connexion.prepareStatement(requetePreparee);
			requete.setString(1, nom);
			ResultSet res = requete.executeQuery();
			while (res.next()) {
				devise = new Devise(res.getInt(1), res.getString(2));
				// On part du principe que le nom de devise est unique, donc le premier
				// résultat doit aussi être le seul !
				return devise;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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

		// Requete recherche une devise en base de données
		String requeteDevise = "SELECT * FROM Devise WHERE nom_devise=?";
		//String requeteDevise = "SELECT * FROM Devise WHERE id_devise=? AND nom_devise=?";
		// Insert la devise en base de données (si n'existe pas)
		String requeteAjoutDevise = "INSERT INTO Devise (nom_devise) VALUES (?)";

		try {
			// On effectue la connexion
			connexion = Connexion.getInstance();
			int idGenerateDevise = 0;
			PreparedStatement requete;
			ResultSet resultat;

			Devise temp = getByNom(obj.getNomDevise());

			if (temp != null) {
				// Récupérer l'id
				idGenerateDevise = temp.getIdDevise();
				// Sinon :
			} else {
				// Créer le portefeuille suivant le nom de l'objet passé en paramètre
				requete = connexion.prepareStatement(requeteAjoutDevise, Statement.RETURN_GENERATED_KEYS);
				requete.setString(1, obj.getNomDevise());
				requete.executeUpdate();

				// Et récupérer l'id
				resultat = requete.getGeneratedKeys();
				if (resultat.next()) {
					idGenerateDevise = resultat.getInt(1);
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
		String requeteUpdate = "UPDATE Devise SET nom_devise=? WHERE id_devise=?";

		// Si id de l'objet est supérieur à -1, il est synchro avec la base
		// On peut faire l'update.
		if (obj.getIdDevise() > -1) {
			try {
				connexion = Connexion.getInstance();
				PreparedStatement requete = connexion.prepareStatement(requeteUpdate);
				requete.setString(1, obj.getNomDevise());
				requete.setInt(2, obj.getIdDevise());
				requete.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("--------");
				System.out.println("Erreur SQL : " + e.getMessage());
			}
		} else {
			// Si l'id est à -1, on fait une recherche pour voir si
			// par rapport au nom, on a quelque chose en base
			obj = this.getByNom(obj.getNomDevise());

			// Si rien n'est trouvé, on fait un create
			if (obj.getIdDevise() == -1) {
				this.create(obj);
				// Si on a quelque chose, on fait un update
			} else {
				this.update(obj);
			}

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
		String requeteDeleteContenu = "DELETE FROM Contenu WHERE id_devise=?";
		
		String requeteDelete = "DELETE FROM Devise WHERE id_devise=?";

		if (obj.getIdDevise() > -1) {
			// Si l'id est supérieur à -1, on fait la suppression de l'id selectionné
			try {
				// On supprime d'abord les éléments dans contenu dépendant d'une devise
				PreparedStatement requete = connexion.prepareStatement(requeteDeleteContenu);
				requete.setInt(1, this.getByNom(obj.getNomDevise()).getIdDevise());
				requete.execute();

				// On supprime la devise en elle-même
				requete = connexion.prepareStatement(requeteDelete);
				requete.setInt(1, this.getByNom(obj.getNomDevise()).getIdDevise());
				requete.execute();
			} catch (SQLException e) {
				System.out.println("Erreur SQL : " + e.getMessage());
			}
		} else {
			// On recherche par nom
			obj = this.getByNom(obj.getNomDevise());

			// Si on trouve quelque chose, on tente de nouveau d'effacer l'entrée
			if (obj.getIdDevise() > -1) {
				this.delete(obj);
			} // Sinon, c'est qu'il n'y rien à supprimer
		}
	}

}
