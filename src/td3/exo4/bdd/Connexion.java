package td3.exo4.bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author Damien GAIGA
 */
public class Connexion {

	private static Connection connexion;

	private Connexion() {
		try {
			String mesAccess[] = getAcces();
			String url = "jdbc:mysql://" + mesAccess[4] + ":" + mesAccess[3] + "/" + mesAccess[0];
			String login = mesAccess[1];
			String pwd = mesAccess[2];
			Connexion.connexion = DriverManager.getConnection(url, login, pwd);
			connexion.setAutoCommit(true);
		} catch (SQLException sqle) {
			System.out.println("Erreur connexion " + sqle.getMessage());
		}
	}

	/**
	 *
	 * @return Connection
	 */
	public static Connection getInstance() {
		if (connexion == null) {
			new Connexion();
		}
		return Connexion.connexion;
	}

	/**
	 *
	 * @return infoAcces String[]
	 */
	public static String[] getAcces() {

		String infoAcces[] = new String[5];

		// A améliorer si j'ai le temps
		Path currentRelativePath = Paths.get("");
		// local.properties (à l'intérieur de l'université)
		// bdd.properties (à l'extérieur ou en wifi)
		String fichier = currentRelativePath.toAbsolutePath().toString() + "\\src\\td3\\exo4\\bdd.properties";

		Properties accesBdd = new Properties();
		File fBdd = new File(fichier);
		try (FileInputStream source = new FileInputStream(fBdd)) {

			accesBdd.loadFromXML(source);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		infoAcces[0] = accesBdd.getProperty("bdd");
		infoAcces[1] = accesBdd.getProperty("login");
		infoAcces[2] = accesBdd.getProperty("pass");
		infoAcces[3] = accesBdd.getProperty("port");
		infoAcces[4] = accesBdd.getProperty("adresse_ip");

		return infoAcces;
	}
}
