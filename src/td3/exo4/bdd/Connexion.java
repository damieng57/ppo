package td3.exo4.bdd;

import java.sql.*;

public class Connexion {

	private final String url = "jdbc:mysql://localhost:8000/gaiga4u_portefeuilles";
	private final String login = "gaiga4u_appli";
	private final String pwd = "30216549";
	private static Connection connexion;

	private Connexion() {
		try {
			Connexion.connexion = DriverManager.getConnection(url, login, pwd);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion " + sqle.getMessage());
		}
	}

	public static Connection getInstance() {

		if (connexion == null) {
			new Connexion();
		}
		return Connexion.connexion;

	}

	public static void uneRequete(Connection connexion) {
		try {
			//Statement requete = connexion.createStatement();
			//ResultSet res = requete.executeQuery("select * from Portefeuille");

			PreparedStatement requete = connexion.prepareStatement("select id_portefeuille, nom_portefeuille from Portefeuille where id_portefeuille = ?");
			requete.setInt(1, 2);
			ResultSet res = requete.executeQuery();

			while (res.next()) {
				int id = res.getInt("id_portefeuille");
				String nom = res.getString("nom_portefeuille");
				System.out.println(id + " : " + nom);
			}

			if (res != null) {
				res.close();
			}
			if (requete != null) {
				requete.close();
			}
			if (connexion != null) {
				connexion.close();
			}

		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
}
