/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo3.serialisation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import td3.exo1.GestionPortefeuilles;

/**
 *
 * @author gaiga4u
 */
public class SauvegarderBinPortefeuille {

	public static void sauvegardeBinaire(GestionPortefeuilles gestionPortefeuilles) {

		// A améliorer si j'ai le temps
		Path currentRelativePath = Paths.get("");
		String cheminDesfichiers = currentRelativePath.toAbsolutePath().toString() + "\\src\\td3\\exo3\\save_";

		File f = new File(cheminDesfichiers+"portefeuilles.bin");

		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
			os.writeObject(gestionPortefeuilles);
			os.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

	}

}
