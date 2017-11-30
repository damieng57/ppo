package td3.exo2.fichiersplats;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map.Entry;
import java.util.Properties;
import td3.exo1.Devise;
import td3.exo1.GestionPortefeuilles;
import td3.exo1.Portefeuille;

/**
 *
 * @author Damien GAIGA
 */

//********************************************//
//	Pour test sur fichier Properties
// N'est pas utilisé dans le programme principal
//********************************************//

public class SauvegarderPortefeuilleProperties {

	public static void sauvegarde(GestionPortefeuilles groupePortefeuilles) {

		final Properties saveProperties = new Properties();
		OutputStream output = null;

		try {

			// set the properties value
			for (Portefeuille portefeuille : groupePortefeuilles.getGestionnaire()) {

				// A améliorer si j'ai le temps
				// Création d'un fichier par portefeuille
				Path currentRelativePath = Paths.get("");
				String fichierSauvegarde = currentRelativePath.toAbsolutePath().toString();
				fichierSauvegarde += "\\src\\td3\\exo2\\save_";
				fichierSauvegarde += portefeuille.getNomPortefeuille();
				fichierSauvegarde += ".ini";
				
				output = new FileOutputStream(fichierSauvegarde);

				// La première valeur stockée sera la dernière insérée au fichier
				saveProperties.setProperty("END", "FIN DU PORTEFEUILLE");
				for (final Entry<Devise, Double> entry : portefeuille.getListeDevise().entrySet()) {
					final Devise key = entry.getKey();
					final Double value = entry.getValue();
					saveProperties.setProperty(key.getNomDevise(), String.valueOf(value));
				}
				saveProperties.setProperty("START", portefeuille.getNomPortefeuille());
				saveProperties.store(output, null);
			}
			

		} catch (final IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
