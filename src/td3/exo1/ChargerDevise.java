package td3.exo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author gaiga4u
 */
public class ChargerDevise {

	private static Properties accesBdd = new Properties();

	static {
		// Charger les  devises

		// A améliorer si j'ai le temps
		Path currentRelativePath = Paths.get("");
		String fichierDevise = currentRelativePath.toAbsolutePath().toString();
		fichierDevise += "\\src\\td3\\exo1\\Devise.properties";

		try (FileInputStream source = new FileInputStream(new File(fichierDevise))) {
			accesBdd.load(source);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// Récupérer le symbole associé à un nom de devise
	public static String getSymbol(String nomDevise) {

		return accesBdd.getProperty(nomDevise);

	}

	public static void affiche() {
		Object[] listeDevise = (Object[]) accesBdd.stringPropertyNames().toArray();

		// BOUCLE POUR RESPECTER L'ORDRE NUMERIQUE
		int compteur = 1;
		for (Object valeur : listeDevise) {
			System.out.println(compteur + ". " + accesBdd.getProperty(String.valueOf(compteur)));
			compteur++;
		}

		// Variante problématique
//		Object[] listeDevise = (Object[]) accesBdd.stringPropertyNames().toArray();
//		for (Object valeur : accesBdd.keySet()) {
//			System.out.println(valeur + ". " + accesBdd.getProperty((String) valeur));
//		}
	}

}
