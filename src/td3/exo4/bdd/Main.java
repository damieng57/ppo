/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td3.exo4.bdd;

import java.sql.Connection;
import td3.exo4.bdd.Connexion;

/**
 *
 * @author user
 */
public class Main {
	
	public static void main(String[] args) {
		Connection connexion = Connexion.getInstance();
		Connexion.uneRequete(connexion);
	}
	
}
