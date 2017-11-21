/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo3.serialisation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import td3.exo1.GestionPortefeuilles;


/**
 *
 * @author gaiga4u
 */
public class ChargerBinPortefeuille {

	public static GestionPortefeuilles charger() {
		
		
		GestionPortefeuilles gestionPortefeuilles = new GestionPortefeuilles();
		
		// A améliorer si j'ai le temps
		Path currentRelativePath = Paths.get("");
		String cheminDesfichiers = currentRelativePath.toAbsolutePath().toString() + "\\src\\td3\\exo3\\save_portefeuilles.bin";
		
		File f = new File(cheminDesfichiers);
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(f));
			gestionPortefeuilles = (GestionPortefeuilles) is.readObject();
			is.close();
		} catch (IOException ioe) {
			System.out.println("Problème fichier");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Problème de cast de classe");
			cnfe.printStackTrace();
		}
		return gestionPortefeuilles;

	}

}
