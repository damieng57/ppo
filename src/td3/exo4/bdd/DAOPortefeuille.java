package td3.exo4.bdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

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

			// En recupérant les infos suivant l'id, on ne doit avoir qu'un seul résultat
			// on peut donc créer directement l'objet
			portefeuille = new Portefeuille(res.getString(2));

		} catch (SQLException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
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
			portefeuille = new Portefeuille(res.getString(2));

		} catch (SQLException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
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
		// Requete recherche un portefeuille en base de données
		String requetePortefeuille = "SELECT * FROM Portefeuille WHERE nom_portefeuille=?";
		// Requete recherche dans contenu le couple portefeuille/devise
		String requeteContenu = "SELECT * FROM Contenu WHERE id_portefeuille=? AND id_devise=?";

		// Insert le portefeuille en base de données
		String requeteAjoutPortefeuille = "INSERT INTO Portefeuille (nom_portefeuille) VALUES (?)";
		// Insert dans la table contenu
		String requeteAjoutContenu = "INSERT INTO Contenu (id_portefeuille, id_devise, montant) VALUES (?, ?, ?)";
		// Update de la table contenu
		String requeteMiseAjoutContenu = "UPDATE Contenu SET montant=? WHERE id_contenu=?";

		// TODO : requète à basculer dans DAODevise
		// Requete recherche une devise en base de données
		String requeteDevise = "SELECT * FROM Devise WHERE nom_devise=?";
		// Insert la devise en base de données (si n'existe pas)
		String requeteAjoutDevise = "INSERT INTO Devise (nom_devise) VALUES (?)";

		// TODO : Utiliser les methodes getByNom/getById
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
			
			// S'il existe en base de données
			if (this.getByNom(obj.getNomPortefeuille())!=null){
				// Récupérer l'id
				idGeneratePortefeuille = obj.getIdPortefeuille();
				System.out.println("A - Le portefeuille existe, son id est le : " + idGeneratePortefeuille);
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

					System.out.println("B - Le portefeuille n'existait pas, on le crée, son id est le : " + idGeneratePortefeuille);
				}
			}
			
//			PreparedStatement requete = connexion.prepareStatement(requetePortefeuille, Statement.RETURN_GENERATED_KEYS);
//			requete.setString(1, obj.getNomPortefeuille());
//			ResultSet resultat = requete.executeQuery();
//			// Si résultat :
//			//Recupère l'id - pas d'ajout
//			if (resultat.next()) {
//				idGeneratePortefeuille = resultat.getInt(1);
//				System.out.println("A - Le portefeuille existe, son id est le : " + idGeneratePortefeuille);
//			} // Sinon :
//			else {
//				// Créer le portefeuille et récupérer l'id
//				requete = connexion.prepareStatement(requeteAjoutPortefeuille, Statement.RETURN_GENERATED_KEYS);
//				requete.setString(1, obj.getNomPortefeuille());
//				requete.executeUpdate();
//
//				resultat = requete.getGeneratedKeys();
//
//				if (resultat.next()) {
//					idGeneratePortefeuille = resultat.getInt(1);
//
//					System.out.println("B - Le portefeuille n'existait pas, on le crée, son id est le : " + idGeneratePortefeuille);
//				}
//			}

			// Ajout des devises dans la table portefeuille suivant obj.getListeDevise
			// Pour chaque devise on cherche suivant le nom	
			for (Map.Entry<Devise, Double> entry : obj.getListeDevise().entrySet()) {
				Devise cle = entry.getKey();
				Double valeur = entry.getValue();
				
				// On récupère le DAO de devise
				DAODevise daoDevise = DAODevise.getInstance();
				
				// On crée la devise en base si nécessaire
				daoDevise.create(cle);
				// On récupère l'objet présent en base et son id en base de données
				idGenerateDevise = daoDevise.getByNom(cle.getNomDevise()).getIdDevise();

				// On ajoute la devise et le portefeuille en cours à la table contenu
				// (Création du lien entre Devise et portefeuille via la table contenu)
				// On vérifie dans la table si on a un lien déjà présent en comparant les ID récupérés
				// SELECT * FROM Contenu WHERE id_portefeuille=? AND id_devise=?

				System.out.println("Id portefeuille : "+ idGeneratePortefeuille);
				System.out.println("Id devise : "+ idGenerateDevise);
				
				requete = connexion.prepareStatement(requeteContenu, Statement.RETURN_GENERATED_KEYS);
				requete.setInt(1, idGeneratePortefeuille);
				requete.setInt(2, idGenerateDevise);
				resultat = requete.executeQuery();

				//resultat = requete.getGeneratedKeys();

				// Si résultat :
				// On récupère l'id de la ligne contenu
				if (resultat.next()) {
					idGenerateContenu = resultat.getInt(1);
					
					System.out.println("Id contenu : "+ idGenerateContenu);

					System.out.println("E - La contenu existe, son id est le : " + idGenerateContenu);

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

					System.out.println("F - On crée le contenu");
				}
			}

		} catch (SQLException | NullPointerException e) {
			//System.out.println("Erreur SQL : " + e.getMessage());
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
			requete.execute();
		} catch (SQLException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
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
			requete.execute();
		} catch (SQLException e) {
			System.out.println("Erreur SQL : " + e.getMessage());
		}
	}

}
