package td3.exo2.fichiersplats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
import td3.exo1.GestionPortefeuilles;
import td3.exo1.Portefeuille;

/**
 *
 * @author gaiga4u
 */
public class ChargerPortefeuille {

	// Fonction annonyme
	private static final FilenameFilter iniFileFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(".ini");
		}
	};

	public static void charge(GestionPortefeuilles gestionPortefeuilles) {

		// A améliorer si j'ai le temps
		Path currentRelativePath = Paths.get("");
		String cheminDesfichiers = currentRelativePath.toAbsolutePath().toString() + "\\src\\td3\\exo2\\";
		
		final Properties loadProperties = new Properties();
		InputStream input = null;

		try {
			File repertoire = new File(cheminDesfichiers);
			File[] files = repertoire.listFiles(iniFileFilter);
			
			for (File file : files) {			
				input = new FileInputStream(file);

				// Charger les fichiers properties
				loadProperties.load(input);
				Enumeration e = loadProperties.propertyNames();

				Portefeuille portefeuille = new Portefeuille(loadProperties.getProperty("START"));

				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					if (!key.equals("START") && !key.equals("END")) {
						portefeuille.mettreDeviseDansPortefeuille(key, Double.valueOf(loadProperties.getProperty(key)));
					}
				}
				gestionPortefeuilles.addPortefeuille(portefeuille);
			}

		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
