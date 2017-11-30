package td3.exo2.fichiersplats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import td3.exo1.Devise;
import td3.exo1.GestionPortefeuilles;
import td3.exo1.Portefeuille;

/**
 *
 * @author user
 */
public class SauvegarderPortefeuille {

	public static void sauvegarde(GestionPortefeuilles groupePortefeuilles) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src\\td3\\exo2\\save.txt")))) {

			for (Portefeuille portefeuille : groupePortefeuilles.getGestionnaire()) {

				bw.append(portefeuille.getNomPortefeuille());
				bw.newLine();
				for (Map.Entry<Devise, Double> entry : portefeuille.getListeDevise().entrySet()) {
					Object key = entry.getKey();
					Object value = entry.getValue();

					bw.append(key + " : " + value);
					bw.newLine();
				}
				bw.append("FIN DU PORTEFEUILLE");
				bw.newLine();
			}

		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}
