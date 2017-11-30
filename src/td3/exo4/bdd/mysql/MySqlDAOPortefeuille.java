package td3.exo4.bdd.mysql;

import td3.exo4.bdd.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import td3.exo4.bdd.Connexion;
import td3.exo4.bdd.Devise;
import td3.exo4.bdd.Portefeuille;

/**
 *
 * @author Damien GAIGA
 */
public class MySqlDAOPortefeuille extends DAO<Portefeuille> {

	private static MySqlDAOPortefeuille instance;

	// Constructeur privé
	private MySqlDAOPortefeuille(Connection connexion) {
		super(connexion);
	}

	// Singleton DAOPortefeuille
	public static MySqlDAOPortefeuille getInstance(Connection connexion) {
		if (instance == null) {
			instance = new MySqlDAOPortefeuille(connexion);
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
		String requeteContenu = "SELECT Portefeuille.id_portefeuille, "
				+ "Portefeuille.nom_portefeuille, "
				+ "Devise.nom_devise, "
				+ "Contenu.montant "
				+ "FROM Portefeuille, Devise, Contenu "
				+ "WHERE Portefeuille.id_portefeuille=? "
				+ "AND Portefeuille.id_portefeuille=Contenu.id_portefeuille "
				+ "AND Contenu.id_devise=Devise.id_devise";

		String requeteSurId = "SELECT id_portefeuille, nom_portefeuille FROM Portefeuille WHERE id_portefeuille=?";

		Portefeuille portefeuille = null;
		try {
			// Recherche du nom dans la base de données
			PreparedStatement requete = connexion.prepareStatement(requeteSurId, Statement.RETURN_GENERATED_KEYS);
			requete.setInt(1, id);
			ResultSet resultat = requete.executeQuery();

			if (resultat.next()) {
				// On crée le portefeuille avec son id et son nom
				portefeuille = new Portefeuille(resultat.getInt(1), resultat.getString(2));
				// On part du principe que l'id du portefeuille est unique, donc le premier
				// résultat doit aussi être le seul !

				// On recherche son contenu éventuel:
				requete = connexion.prepareStatement(requeteContenu, Statement.RETURN_GENERATED_KEYS);
				requete.setInt(1, portefeuille.getIdPortefeuille());
				resultat = requete.executeQuery();

				while (resultat.next()) {
					portefeuille.mettreDeviseDansPortefeuille(resultat.getString(3), resultat.getFloat(4));
				}
				return portefeuille;
			}

		} catch (SQLException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
		}

		return null;
	}

	/**
	 * Permet de récupérer un objet dans la base de données via son nom
	 *
	 * @param nom
	 * @return Portefeuille
	 */
	public Portefeuille getByNom(String nom) {
		String requeteContenu = "SELECT Portefeuille.id_portefeuille, "
				+ "Portefeuille.nom_portefeuille, "
				+ "Devise.nom_devise, "
				+ "Contenu.montant "
				+ "FROM Portefeuille, Devise, Contenu "
				+ "WHERE Portefeuille.id_portefeuille=? "
				+ "AND Portefeuille.id_portefeuille=Contenu.id_portefeuille "
				+ "AND Contenu.id_devise=Devise.id_devise";

		String requeteSurNom = "SELECT id_portefeuille, nom_portefeuille FROM Portefeuille WHERE nom_portefeuille=?";

		Portefeuille portefeuille = null;
		try {
			// Recherche du nom dans la base de données
			PreparedStatement requete = connexion.prepareStatement(requeteSurNom, Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, nom);
			ResultSet resultat = requete.executeQuery();

			if (resultat.next()) {
				// On crée le portefeuille avec son id et son nom
				portefeuille = new Portefeuille(resultat.getInt(1), resultat.getString(2));
				// On part du principe que le nom de portefeuille est unique, donc le premier
				// résultat doit aussi être le seul !

				// On recherche son contenu éventuel:
				requete = connexion.prepareStatement(requeteContenu, Statement.RETURN_GENERATED_KEYS);
				requete.setInt(1, portefeuille.getIdPortefeuille());
				resultat = requete.executeQuery();

				while (resultat.next()) {
					portefeuille.mettreDeviseDansPortefeuille(resultat.getString(3), resultat.getFloat(4));
				}
				return portefeuille;
			}

		} catch (SQLException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
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
	 * @param Portefeuille obj
	 */
	@Override
	public void create(Portefeuille obj) {

		// Insert le portefeuille en base de données
		String requeteAjoutPortefeuille = "INSERT INTO Portefeuille (nom_portefeuille) VALUES (?)";

		// Requete recherche dans contenu le couple portefeuille/devise
		String requeteContenu = "SELECT * FROM Contenu WHERE id_portefeuille=? AND id_devise=?";
		// Insert dans la table contenu
		String requeteAjoutContenu = "INSERT INTO Contenu (id_portefeuille, id_devise, montant) VALUES (?, ?, ?)";
		// Update de la table contenu
		String requeteMiseAjoutContenu = "UPDATE Contenu SET montant=? WHERE id_contenu=?";

		try {
			// On effectue la connexion
			connexion = Connexion.getInstance();
			int idGeneratePortefeuille = 0;
			int idGenerateDevise = 0;
			int idGenerateContenu = 0;
			PreparedStatement requete;
			ResultSet resultat;

			// Ajout du portefeuille dans la table portefeuille
			// Recherche du portefeuille en base suivant le nom
			Portefeuille temp = getByNom(obj.getNomPortefeuille());
			// S'il existe en base de données
			if (temp != null) {
				// Récupérer l'id
				idGeneratePortefeuille = temp.getIdPortefeuille();
				// Sinon :
			} else {
				// Créer le portefeuille suivant le nom de l'objet passé en paramètre
				requete = connexion.prepareStatement(requeteAjoutPortefeuille, Statement.RETURN_GENERATED_KEYS);
				requete.setString(1, obj.getNomPortefeuille());
				requete.executeUpdate();

				// Et récupérer l'id
				resultat = requete.getGeneratedKeys();
				if (resultat.next()) {
					idGeneratePortefeuille = resultat.getInt(1);
				}
			}

			// Ajout des devises dans la table portefeuille suivant obj.getListeDevise
			// Pour chaque devise on cherche suivant le nom	
			for (Map.Entry<Devise, Double> entry : obj.getListeDevise().entrySet()) {
				Devise cle = entry.getKey();
				Double valeur = entry.getValue();

				// On récupère le DAO de devise
				MySqlDAODevise daoDevise = MySqlDAODevise.getInstance(connexion);

				// On crée la devise en base si nécessaire
				daoDevise.create(cle);
				// On récupère l'objet présent en base et son id en base de données
				idGenerateDevise = daoDevise.getByNom(cle.getNomDevise()).getIdDevise();

				// On ajoute la devise et le portefeuille en cours à la table contenu
				// (Création du lien entre Devise et portefeuille via la table contenu)
				// On vérifie dans la table si on a un lien déjà présent en comparant les ID récupérés
				// SELECT * FROM Contenu WHERE id_portefeuille=? AND id_devise=?
				requete = connexion.prepareStatement(requeteContenu, Statement.RETURN_GENERATED_KEYS);
				requete.setInt(1, idGeneratePortefeuille);
				requete.setInt(2, idGenerateDevise);
				resultat = requete.executeQuery();

				//resultat = requete.getGeneratedKeys();
				// Si résultat :
				// On récupère l'id de la ligne contenu
				if (resultat.next()) {
					idGenerateContenu = resultat.getInt(1);
					// On prend le contenu de obj.montantDevise(NomDeLaDevise)
					// On fait l'update de la table
					requete = connexion.prepareStatement(requeteMiseAjoutContenu);
					requete.setDouble(1, valeur);
					requete.setInt(2, idGenerateContenu);
					requete.execute();

				} else {
					// Si le couple n'existe pas, on le crée
					// INSERT INTO Contenu (id_portefeuille, id_devise, montant) VALUES (?, ?, ?)
					requete = connexion.prepareStatement(requeteAjoutContenu);
					requete.setInt(1, idGeneratePortefeuille);
					requete.setInt(2, idGenerateDevise);
					requete.setDouble(3, valeur);
					requete.executeUpdate();
				}
			}

		} catch (SQLException | NullPointerException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
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
		// L'objectif est de mettre le montant à jour + le nom
		
		String requeteUpdate = "UPDATE Portefeuille, Devise, Contenu "
				+ "SET montant=?, nom_portefeuille=? "
				+ "WHERE Contenu.id_portefeuille=? "
				+ "AND Devise.nom_devise=? "
				+ "AND Devise.id_devise=Contenu.id_devise "
				+ "AND Portefeuille.id_portefeuille=Contenu.id_portefeuille";

		// Si id de l'objet est supérieur à -1, il est synchro avec la base
		// On peut faire l'update.
		if (obj.getIdPortefeuille() > -1) {
			try {

				// On met à jour chaque ligne du portefeuille à chaque mise à jour
				// même si qu'une ligne modifiée...
				// On peut optimiser!
				for (Map.Entry<Devise, Double> entry : obj.getListeDevise().entrySet()) {
					Devise cle = entry.getKey();
					Double valeur = entry.getValue();

					PreparedStatement requete = connexion.prepareStatement(requeteUpdate);

					requete.setDouble(1, valeur);
					requete.setString(2, obj.getNomPortefeuille());
					requete.setInt(3, obj.getIdPortefeuille());
					requete.setString(4, cle.getNomDevise());
					requete.execute();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				//System.out.println("Erreur SQL : " + e.getMessage());
			}
		} else {
			// Si l'id est à -1, on fait une recherche pour voir si
			// par rapport au nom, on a quelque chose en base
			obj = this.getByNom(obj.getNomPortefeuille());

			// Si rien n'est trouvé, on fait un create
			if (obj.getIdPortefeuille() == -1) {
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
	 * @param Portefeuille obj
	 */
	@Override
	public void delete(Portefeuille obj
	) {
		String requeteDeleteContenu = "DELETE FROM Contenu WHERE id_portefeuille=?";

		String requeteDelete = "DELETE FROM Portefeuille WHERE id_portefeuille=?";

		if (obj.getIdPortefeuille() > -1) {
			// Si l'id est supérieur à -1, on fait la suppression de l'id selectionné
			try {
				// On supprime d'abord les éléments dans contenu dépendant d'un portefeuille
				PreparedStatement requete = connexion.prepareStatement(requeteDeleteContenu);
				requete.setInt(1, this.getByNom(obj.getNomPortefeuille()).getIdPortefeuille());
				requete.execute();

				// Puis on supprime le portefeuille lui-même
				requete = connexion.prepareStatement(requeteDelete);
				requete.setInt(1, this.getByNom(obj.getNomPortefeuille()).getIdPortefeuille());
				requete.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				//System.out.println("Erreur SQL : " + e.getMessage());
			}
		} else {
			// On recherche par nom
			obj = this.getByNom(obj.getNomPortefeuille());

			// Si on trouve quelque chose, on tente de nouveau d'effacer l'entrée
			if (obj.getIdPortefeuille() > -1) {
				this.delete(obj);
			} // Sinon, c'est qu'il n'y rien à supprimer
		}
	}

}
